import com.github.bjansen.darjeeling.model {
	Feed,
	Item,
	Folder
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
	shared Folder[] listAll() {
		return feedsDao.listFoldersAndFeeds();
	}
	
	route ("items")
	shared Item[] listItems(Integer feedId) {
		return feedsDao.listItemsByFeed(feedId);
	}
}
