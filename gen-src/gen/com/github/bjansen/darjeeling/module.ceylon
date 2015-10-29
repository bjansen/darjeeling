native("jvm")
module gen.com.github.bjansen.darjeeling "0.1" {
    shared import "org.jooq:jooq" "3.7.0";
    shared import "org.jooq:jooq-meta" "3.7.0";
    import com.github.bjansen.ceylon.jooqadapter "1.0.0";
	import javax.annotation "8";
	import ceylon.interop.java "1.2.0";
	
	shared import ceylon.time "1.2.0";
}