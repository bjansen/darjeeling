import com.github.bjansen.darjeeling {
    darjeelingDS
}
import org.jooq.impl {
    DSL
}
import org.jooq {
    SQLDialect,
    RecordMapper,
    Record
}
import com.github.bjansen.darjeeling.model {
    Feed,
    Item,
    Folder
}
import ceylon.interop.java {
    javaClass,
    CeylonList
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
    }
}
import java.lang {
    JInteger=Integer,
    JString=String
}
import org.modelmapper {
    ModelMapper
}
import org.modelmapper.jooq {
    RecordValueReader
}
import org.modelmapper.convention {
    NameTokenizers
}
import ceylon.time {
    now
}

shared class FeedsDao() {
    value feedzee = DSL.using(darjeelingDS, SQLDialect.\iMYSQL);
    value modelMapper = ModelMapper();
    modelMapper.configuration
        .addValueReader(RecordValueReader())
        .setSourceNameTokenizer(NameTokenizers.\iUNDERSCORE);
    
    shared Feed[] listFeeds() {
        return CeylonList(feedzee.select().from(feeds).fetch().into(javaClass<Feed>())).sequence();
    }
    
    shared <Feed|Folder>[] listFoldersAndFeeds() {
        value folderRecords = feedzee.select()
            .from(folders)
            .join(subscriptions).on(subscriptions.folderId.equal(folders.id))
            .join(feeds).on(subscriptions.feedId.equal(feeds.id))
            .fetch()
            .map(FolderRecordMapper());
        
        value feedsRecords = feedzee.select()
            .from(feeds)
                .join(subscriptions).on(subscriptions.feedId.equal(feeds.id))
                .where(subscriptions.folderId.isNull())
                .fetchInto(javaClass<Feed>());
        
        return CeylonList(folderRecords).coalesced.sequence()
                .append(CeylonList(feedsRecords).sequence());
    }
    
    shared Item[] listItemsByFeed(Integer feedId) {
        return CeylonList(
            feedzee.select()
                .from(items)
                .where(items.feedId.eq(feedId))
                .limit(10)
                .fetch().into(javaClass<Item>())
        ).sequence();
    }
    
    shared Feed subscribe(String url, String title, Integer? folderId) {
        Folder? folder = if (exists folderId)
                         then feedzee.select().from(folders).where(folders.id.eq(folderId)).fetchOneInto(javaClass<Folder>())
                         else null;
        
        value adjustedFolderId = folder?.id;
        
        Feed? persistedFeed = feedzee.selectFrom(feeds).where(feeds.url.eq(url)).fetchOneInto(javaClass<Feed>());
        
        value _now = now().dateTime();
        Feed feed;
        if (exists persistedFeed) {
            feed = persistedFeed;
        } else {
            value record = feedzee.insertInto(feeds, feeds.name, feeds.url, feeds.createdAt, feeds.updatedAt)
                                  .values(title, url, _now, _now)
                                  .returning(feeds.id)
                                  .fetchOne();
            feed = record.into(javaClass<Feed>());
        }
        
        feedzee.insertInto(subscriptions, subscriptions.feedId, subscriptions.folderId, subscriptions.createdAt, subscriptions.updatedAt)
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