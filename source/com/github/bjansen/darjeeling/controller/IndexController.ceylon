import com.github.bjansen.gyokuro {
	route,
	controller
}
import gen.com.github.bjansen.darjeeling.tables {
	Feeds {feeds = FEEDS}
}
import com.github.bjansen.darjeeling.model {
	Feed
}
import org.jooq.impl {
	DSL
}
import org.jooq {
	SQLDialect
}
import ceylon.interop.java {
	javaClass,
	CeylonList
}
import com.github.bjansen.darjeeling {
	darjeelingDS
}

route ("feeds")
controller class FeedController() {
	
	route ("all")
	shared Feed[] listAll() {
		value feedzee = DSL.using(darjeelingDS, SQLDialect.\iMYSQL);

		return CeylonList(feedzee.select().from(feeds).fetch().into(javaClass<Feed>())).sequence();
	}
}
