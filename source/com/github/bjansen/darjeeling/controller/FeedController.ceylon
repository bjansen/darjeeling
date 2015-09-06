import com.github.bjansen.darjeeling.model {
	Item,
	Folder,
    Feed
}
import com.github.bjansen.gyokuro {
	route,
	controller,
    session
}
import com.github.bjansen.darjeeling.dao {
	FeedsDao
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
	
	route ("subscribe")
	shared Feed subscribe(String url, String title, Integer? folderId, session Integer userId) {
		return feedsDao.subscribe(userId, url, title, folderId);
	}
	
	route("countUnread")
	shared Map<Integer, Integer> countUnreadItems(session Integer userId)
	       => feedsDao.countUnreadItems(userId);
}
