import com.github.bjansen.darjeeling.model {
	Item,
	Folder,
    Feed
}
import com.github.bjansen.gyokuro {
	route,
	controller
}
import com.github.bjansen.darjeeling.dao {
	FeedsDao
}

route ("feeds")
controller
class FeedController() {
	
	value feedsDao = FeedsDao();
	
	route ("all")
	shared <Feed|Folder>[] listAll() {
		return feedsDao.listFoldersAndFeeds();
	}
	
	route ("items")
	shared Item[] listItems(Integer? feedId) {
		return feedsDao.listItemsByFeed(feedId);
	}
	
	route ("itemsInFolder")
	shared Item[] listItemsInFolder(Integer folderId) {
		return feedsDao.listItemsByFolder(folderId);
	}
	
	route ("subscribe")
	shared Feed subscribe(String url, String title, Integer? folderId) {
		return feedsDao.subscribe(url, title, folderId);
	}
}
