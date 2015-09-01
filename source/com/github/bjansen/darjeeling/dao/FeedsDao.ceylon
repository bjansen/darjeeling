import ceylon.interop.java {
    javaClass,
    CeylonList
}
import ceylon.time {
    now
}

import com.github.bjansen.darjeeling {
    darjeelingDS
}
import com.github.bjansen.darjeeling.model {
    Feed,
    Item,
    Folder
}

import gen.com.github.bjansen.darjeeling.tables {
    Feeds {
        feeds
    },
    Items {
        items
    },
    Folders {
        folders
    },
    Subscriptions {
        subscriptions
    },
    ItemsToRead {
        itemsToRead
    }
}

import java.lang {
    JInteger=Integer,
    JString=String
}

import org.jooq {
    SQLDialect,
    RecordMapper,
    Record,
    TableField,
    SelectField
}
import org.jooq.impl {
    DSL
}
import org.modelmapper {
    ModelMapper
}
import org.modelmapper.convention {
    NameTokenizers
}
import org.modelmapper.jooq {
    RecordValueReader
}

shared class FeedsDao() {
    value db = DSL.using(darjeelingDS, SQLDialect.\iMYSQL);
    value modelMapper = ModelMapper();
    modelMapper.configuration
        .addValueReader(RecordValueReader())
        .setSourceNameTokenizer(NameTokenizers.\iUNDERSCORE);

    {SelectField<out Object>*} selectedFieldsInItems = {items.id, items.title, items.description, items.url, items.publicationDate, items.feedId};

    shared Feed[] listFeeds() {
        return CeylonList(db.select().from(feeds).fetch().into(javaClass<Feed>())).sequence();
    }
    
    shared <Feed|Folder>[] listFoldersAndFeeds() {
        value folderRecords = db.select()
            .from(folders)
            .join(subscriptions).on(subscriptions.folderId.equal(folders.id))
            .join(feeds).on(subscriptions.feedId.equal(feeds.id))
            .where()
            .fetch()
            .map(FolderRecordMapper());
        
        value feedsRecords = db.select()
            .from(feeds)
                .join(subscriptions).on(subscriptions.feedId.equal(feeds.id))
                .where(subscriptions.folderId.isNull())
                .fetchInto(javaClass<Feed>());
        
        return CeylonList(folderRecords).coalesced.sequence()
                .append(CeylonList(feedsRecords).sequence());
    }
    
    // TODO restrict by user
    shared Item[] listItemsByFeed(Integer? feedId, Boolean unreadOnly) {
        value query = db.selectQuery();
        
        query.addSelect(*selectedFieldsInItems);
        query.addFrom(items);
        
        if (unreadOnly) {
            query.addJoin(itemsToRead, itemsToRead.itemId.eq(items.id));
        }
        if (exists feedId) {
            query.addConditions(items.feedId.eq(feedId));
        }

        query.addLimit(10);
        
        return CeylonList(query.fetch().into(javaClass<Item>())).sequence();
    }
    
    // TODO restrict by user
    shared Item[] listItemsByFolder(Integer folderId, Boolean unreadOnly) {
        value query = db.selectQuery();

        query.addSelect(*selectedFieldsInItems);
        query.addFrom(items);
        query.addJoin(subscriptions, subscriptions.feedId.eq(items.feedId));
        
        if (unreadOnly) {
            query.addJoin(itemsToRead, itemsToRead.itemId.eq(items.id));
        }
        query.addConditions(subscriptions.folderId.eq(folderId));
        
        query.addLimit(10);
        
        return CeylonList(query.fetch().into(javaClass<Item>())).sequence();
    }

    shared Feed subscribe(String url, String title, Integer? folderId) {
        Folder? folder = if (exists folderId)
                         then db.select().from(folders).where(folders.id.eq(folderId)).fetchOneInto(javaClass<Folder>())
                         else null;
        
        value adjustedFolderId = folder?.id;
        
        Feed? persistedFeed = db.selectFrom(feeds).where(feeds.url.eq(url)).fetchOneInto(javaClass<Feed>());
        
        value _now = now().dateTime();
        Feed feed;
        if (exists persistedFeed) {
            feed = persistedFeed;
        } else {
            value record = db.insertInto(feeds, feeds.name, feeds.url, feeds.createdAt, feeds.updatedAt)
                                  .values(title, url, _now, _now)
                                  .returning(feeds.id)
                                  .fetchOne();
            feed = record.into(javaClass<Feed>());
        }
        
        db.insertInto(subscriptions, subscriptions.feedId, subscriptions.folderId, subscriptions.createdAt, subscriptions.updatedAt)
               .values(feed.id, adjustedFolderId, _now, _now)
               .execute();
        
        return feed;
    }
}

class FolderRecordMapper() satisfies RecordMapper<Record, Folder> {
    variable Folder? lastFolder = null;
    
    shared actual Folder? map(Record r) {
        value id = r.getValue(folders.id, javaClass<JInteger>())?.intValue() else -1;
        
        value folder = if (exists last = lastFolder)
                       then if (last.id == id) then last else Folder()
                       else Folder();

        folder.id = id;
        folder.name = r.getValue(folders.name, javaClass<JString>())?.string else "";

        value feed = Feed();
        feed.id = r.getValue(feeds.id, javaClass<JInteger>())?.intValue() else -1;
        feed.name = r.getValue(feeds.name, javaClass<JString>())?.string else "";
        folder.feeds = folder.feeds.withTrailing(feed);
        
        if (exists last = lastFolder, last == folder) {
            return null;
        }
        lastFolder = folder;
        return folder;
    }
}