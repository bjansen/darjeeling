native("jvm")
module gen.com.github.bjansen.darjeeling "0.1" {
	shared import "org.jooq:jooq" "3.5.0";
	shared import "org.jooq:jooq-meta" "3.5.0";
	shared import "org.jooq:jooq-codegen" "3.5.0";

	import java.base "8";
	import java.jdbc "8";
	import javax.annotation "8";
	import ceylon.interop.java "1.1.1";
	
	import ceylon.time "1.1.1";
}