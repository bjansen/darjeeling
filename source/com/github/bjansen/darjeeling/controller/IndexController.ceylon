import ceylon.interop.java {
	javaClass,
	CeylonList
}

import com.github.bjansen.darjeeling {
	darjeelingDS
}
import com.github.bjansen.darjeeling.model {
	Feed,
	Item
}
import com.github.bjansen.gyokuro {
	route,
	controller
}

import gen.com.github.bjansen.darjeeling.tables {
	Feeds {
		feeds=\iFEEDS
	},
	Items {
		items = \iITEMS
	}
}

import org.jooq {
	SQLDialect
}
import org.jooq.impl {
	DSL
}
import java.lang {
	JInteger = Integer
}

route ("feeds")
controller class FeedController() {
	
	route ("all")
	shared Feed[] listAll() {
		value feedzee = DSL.using(darjeelingDS, SQLDialect.\iMYSQL);

		return CeylonList(feedzee.select().from(feeds).fetch().into(javaClass<Feed>())).sequence();
	}
	
	route("items")
	shared Item[] listItems(Integer feedId) {
		value feedzee = DSL.using(darjeelingDS, SQLDialect.\iMYSQL);

		return CeylonList(feedzee.select().from(items).where(items.\iFEED_ID.eq(JInteger(feedId))).limit(10).fetch().into(javaClass<Item>())).sequence();
	}
}
