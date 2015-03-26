import com.github.bjansen.darjeeling {
	darjeelingDS
}
import org.jooq.impl {
	DSL
}
import org.jooq {
	SQLDialect
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
		feeds=\iFEEDS
	},
	Items {
		items=\iITEMS
	},
	Folders {
		folders=\iFOLDERS
	}
}
import java.lang {
	JInteger=Integer
}

shared class FeedsDao() {
	value feedzee = DSL.using(darjeelingDS, SQLDialect.\iMYSQL);
	
	shared Feed[] listFeeds() {
		return CeylonList(feedzee.select().from(feeds).fetch().into(javaClass<Feed>())).sequence();
	}
	
	shared Folder[] listFoldersAndFeeds() {
		return CeylonList(
			feedzee.select()
				.from(folders)
				.join(feeds).onKey()
				.fetch().into(javaClass<Folder>())
		).sequence();
	}
	
	shared Item[] listItemsByFeed(Integer feedId) {
		return CeylonList(
			feedzee.select()
				.from(items)
				.where(items.\iFEED_ID.eq(JInteger(feedId)))
				.limit(10)
				.fetch().into(javaClass<Item>())
		).sequence();
	}
}
