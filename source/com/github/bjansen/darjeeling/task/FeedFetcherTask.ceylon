import ceylon.interop.java {
    CeylonIterable,
    CeylonList
}
import ceylon.logging {
    logger
}
import ceylon.time {
    DateTime,
    Instant,
    now
}
import ceylon.time.timezone {
    timeZone
}

import com.github.bjansen.darjeeling {
    darjeelingDS
}
import com.rometools.fetcher {
    FetcherListener,
    FetcherEvent
}
import com.rometools.fetcher.impl {
    HttpURLFeedFetcher,
    HashMapFeedInfoCache
}
import com.rometools.modules.feedburner {
    FeedBurner
}
import com.rometools.rome.feed.synd {
    SyndEntry,
    SyndFeed
}

import gen.com.github.bjansen.darjeeling.tables {
    Feeds {
        feeds
    },
    Items {
        items
    },
    ItemsToRead {
        itemsToRead
    },
    Subscriptions {
        subscriptions
    }
}
import gen.com.github.bjansen.darjeeling.tables.records {
    FeedsRecord
}

import java.lang {
    Runnable,
    Error
}
import java.net {
    URL
}
import java.util {
    Date,
    ArrayList
}

import org.jooq {
    SQLDialect
}
import org.jooq.impl {
    DSL
}

shared object feedFetcherTask satisfies Runnable & FetcherListener {

    value db = DSL.using(darjeelingDS, SQLDialect.\iMYSQL);
    
    value feedInfoCache = HashMapFeedInfoCache.instance;
    value fetcher = HttpURLFeedFetcher(feedInfoCache);
    value log = logger(`module com.github.bjansen.darjeeling`);
    variable FeedsRecord? currentFeed = null;
    
    shared void setup() {
        fetcher.setConnectTimeout(30_000);
        fetcher.addFetcherEventListener(this);
    }

    shared actual void run() {
        value allFeeds = CeylonIterable(db.selectFrom(feeds).fetch());
        
        for (feed in allFeeds) {
            currentFeed = feed;

            try {
                fetcher.retrieveFeed("Mozilla/5.0 (compatible; MSIE 2.0; Windows 3.1)", URL(feed.url));
            } catch (Exception|AssertionError e) {
                log.error("A problem occurred while fetching ``feed.name``", e);
            } catch (Error err) {
                log.fatal("Unknown error", err);
                throw err;
            }
        }
        currentFeed = null;
        
        log.info("Pushing items to read");
        pushItemsToRead(allFeeds);
    }

    DateTime toCeylonDateTime(Date? d, DateTime defaultValue) {
        if (exists d) {
            return Instant(d.time).dateTime(timeZone.utc);
        }
        return defaultValue;
    }
    
    void insertItems(FeedsRecord feed, SyndFeed syndFeed, {SyndEntry*} entries) {
        value lastUpdate = now().dateTime(timeZone.utc);
        
        if (entries.size > 0) {
            log.debug("Inserting ``entries.size`` new entries");
            for (item in entries) {
                value itemDate = toCeylonDateTime(item.updatedDate else item.publishedDate else syndFeed.publishedDate, lastUpdate); 
                
                value desc = if (!item.contents.empty) then item.contents.get(0).\ivalue
                             else (item.description?.\ivalue else "<empty>");
                
                db.insertInto(items, items.feedId, items.title, items.description, items.publicationDate,
                        items.url, items.subscriptionPushed, items.createdAt, items.updatedAt)
                    .values(feed.id, item.title, desc, itemDate,
                        getLink(item), false, lastUpdate, lastUpdate)
                    .execute();
            }
        }
        
        updateFeedInfo(feed, lastUpdate, true);
    }
    
    String getLink(SyndEntry entry) {
        for (mod in CeylonIterable(entry.modules)) {
            if (is FeedBurner mod) {
                return mod.origLink else entry.link else "";
            }
        }
        
        return entry.link else "";
    }
    
    void updateFeedInfo(FeedsRecord feed, DateTime lastUpdate, Boolean wasChanged = false) {
        log.debug("Updating feed info");
        
        value query = db.updateQuery(feeds);
        query.addValue(feeds.lastCheckedDate, lastUpdate);
        
        if (wasChanged) {
            query.addValue(feeds.updatedAt, lastUpdate);
        }
        query.addConditions(feeds.id.eq(feed.id));
        query.execute();
    }
    
    shared actual void fetcherEvent(FetcherEvent fetcherEvent) {
        assert(exists feed = currentFeed);
        value currentDate = now().dateTime(timeZone.utc);
        
        if (fetcherEvent.eventType == "FEED_RETRIEVED") {
            log.debug("Fetched feed ``feed.name``");
            
            value syndFeed = fetcherEvent.feed;

            if (syndFeed.entries.size() > 0) {
                if (exists lastDate = feed.lastCheckedDate) {
                    value entries = CeylonList(syndFeed.entries);
                    
                    // see https://github.com/ceylon/ceylon-compiler/issues/2319
                    value links = ArrayList<String>();
                    entries.each((e) => links.add(getLink(e)));
                    
                    value existingUrls = db.select(items.url)
                        .from(items)
                        .where(items.feedId.eq(feed.id))
                        .and(items.url.\iin(links))
                        .fetchSet(items.url);
                    
                    function isNew(SyndEntry e) 
                            => !existingUrls.contains(getLink(e));
                    
                    value newItems = entries.filter(isNew);
                    
                    insertItems(feed, syndFeed, newItems);
                } else {
                    insertItems(feed, syndFeed, CeylonList(syndFeed.entries));
                }
            }
        } else if (fetcherEvent.eventType == "FEED_UNCHANGED") {
            log.debug("Feed unchanged: ``feed.name``");
            updateFeedInfo(feed, currentDate);
        }
    }
    
    void pushItemsToRead({FeedsRecord*} allFeeds) {
        value currentDate = now().dateTime(timeZone.utc);

        value itemsToInsert = db.select(items.id, subscriptions.userId, DSL.val(currentDate, itemsToRead.createdAt),
            DSL.val(currentDate, itemsToRead.updatedAt))
                .from(items)
                .join(subscriptions).on(subscriptions.feedId.eq(items.feedId))
                .where(items.subscriptionPushed.eq(false));
        
        db.insertInto(itemsToRead, itemsToRead.itemId, itemsToRead.userId, itemsToRead.createdAt, itemsToRead.updatedAt)
            .select(itemsToInsert).execute();
        
        db.update(items)
            .set(items.subscriptionPushed, true)
            .where(items.subscriptionPushed.eq(false))
            .execute();
    }
}
