import ceylon.interop.java {
	javaClass,
	CeylonList
}

import com.github.bjansen.darjeeling {
	darjeelingDS
}
import com.github.bjansen.darjeeling.model {
	Feed
}
import com.github.bjansen.gyokuro {
	route,
	controller
}

import gen.com.github.bjansen.darjeeling.tables {
	Feeds {
		feeds=\iFEEDS
	}
}

import org.jooq {
	SQLDialect
}
import org.jooq.impl {
	DSL
}

route ("feeds")
controller class FeedController() {
	
	route ("all")
	shared Feed[] listAll() {
		value feedzee = DSL.using(darjeelingDS, SQLDialect.\iMYSQL);

		return CeylonList(feedzee.select().from(feeds).fetch().into(javaClass<Feed>())).sequence();
	}
}
