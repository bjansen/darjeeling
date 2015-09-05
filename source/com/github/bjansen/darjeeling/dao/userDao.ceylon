import org.jooq.impl {
    DSL
}
import com.github.bjansen.darjeeling {
    darjeelingDS
}
import org.jooq {
    SQLDialect
}
import gen.com.github.bjansen.darjeeling.tables {
    Users {
        users
    }
}
import ceylon.interop.java {
    javaClass
}
import com.github.bjansen.darjeeling.model {
    User
}

shared object userDao {
    value db = DSL.using(darjeelingDS, SQLDialect.\iMYSQL);

    shared User? findUserByUid(String provider, String uid) {
        return db.select(users.id, users.provider, users.uid, users.name)
            .from(users)
            .where(users.provider.eq(provider))
            .and(users.uid.eq(uid))
            .fetchOneInto(javaClass<User>());
    }
}