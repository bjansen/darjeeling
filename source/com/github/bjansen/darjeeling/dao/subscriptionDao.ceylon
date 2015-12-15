import com.github.bjansen.darjeeling {
    darjeelingDS
}
import com.github.bjansen.darjeeling.model {
    Subscription,
    User,
    Feed
}

import gen.com.github.bjansen.darjeeling.tables {
    Subscriptions {
        subscriptions
    },
    Feeds {
        feeds
    },
    Users {
        users
    }
}

import org.jooq {
    SQLDialect,
    RecordMapper,
    Record
}
import org.jooq.impl {
    DSL
}

shared object subscriptionDao {
    value db = DSL.using(darjeelingDS, SQLDialect.\iMYSQL);

    shared Subscription? findSubscription(Integer userId, Integer feedId) {
        value subs = db.select()
            .from(subscriptions)
            .join(feeds).on(feeds.id.eq(subscriptions.feedId))
            .join(users).on(users.id.eq(subscriptions.userId))
            .where(subscriptions.userId.eq(userId))
            .and(subscriptions.feedId.eq(feedId))
            .limit(1)
            .fetch()
            .map(subscriptionMapper);
        
        return if (subs.empty) then null else subs.get(0);
    }
    
    shared void update(Subscription newSub) {
        db.update(feeds)
            .set(feeds.url, newSub.feed.url)
            .set(feeds.name, newSub.feed.name)
            .where(feeds.id.eq(newSub.feed.id))
            .execute();
        
        db.update(subscriptions)
            .set(subscriptions.folderId, newSub.folder?.id)
            .execute();
    }
}

object subscriptionMapper satisfies RecordMapper<Record, Subscription> {
    
    shared actual Subscription map(Record r) {
        value feed = Feed();
        feed.id = r.getValue(feeds.id);

        value user = User(
            r.getValue(users.id),
            "", "", ""
        );
        return Subscription(feed, null, user);
    }
}