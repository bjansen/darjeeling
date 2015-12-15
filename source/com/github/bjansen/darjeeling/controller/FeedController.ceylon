import com.github.bjansen.darjeeling.model {
	Item,
	Folder,
    Feed,
    Subscription
}
import com.github.bjansen.gyokuro {
	route,
	controller,
    session,
    halt
}
import com.github.bjansen.darjeeling.dao {
	FeedsDao,
    subscriptionDao
}
import ceylon.net.http {
    post
}

route ("feeds")
controller
class FeedController() {
	
	value feedsDao = FeedsDao();
	
	route ("all")
	shared <Feed|Folder>[] listAll(session Integer userId) {
		return feedsDao.listFoldersAndFeeds(userId);
	}
	
	route ("items")
	shared Item[] listItems(Integer? feedId, Boolean unreadOnly, session Integer userId) {
		return feedsDao.listItemsByFeed(userId, feedId, unreadOnly);
	}
	
	route ("itemsInFolder")
	shared Item[] listItemsInFolder(Integer folderId, Boolean unreadOnly, session Integer userId) {
		return feedsDao.listItemsByFolder(userId, folderId, unreadOnly);
	}
	
	route("edit/:feedId", {post})
	shared Feed editFeed(Integer feedId, String url, String title, Integer? folderId,
	    session Integer userId) {
		
		value sub = subscriptionDao.findSubscription(userId, feedId)
                else halt(404, "Subscription not found");
		
		sub.feed.url = url;
		sub.feed.name = title;

		Folder? folder;
		if (exists folderId) {
			value f = Folder();
			f.id = folderId;
			folder = f;
		} else {
			folder = null;
		}
		
		value newSub = Subscription(sub.feed, folder, sub.user);
		subscriptionDao.update(newSub);
		
		return newSub.feed;
	}
	
	route ("subscribe")
	shared Feed subscribe(String url, String title, Integer? folderId, session Integer userId) {
		return feedsDao.subscribe(userId, url, title, folderId);
	}
	
	route("countUnread")
	shared Map<Integer, Integer> countUnreadItems(session Integer userId)
	       => feedsDao.countUnreadItems(userId);
	
	route("markAsRead")
	shared Boolean markItemAsRead(Integer itemId, session Integer userId)
			=> feedsDao.markItemAsRead(itemId, userId) == 1;
}
