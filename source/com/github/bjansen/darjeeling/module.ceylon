native("jvm")
module com.github.bjansen.darjeeling "0.1" {
	import "mysql:mysql-connector-java" "5.1.36";
	import "com.zaxxer:HikariCP-java6" "2.3.9";
	import "org.modelmapper:modelmapper" "0.7.4";
	import "org.modelmapper.extensions:modelmapper-jooq" "0.7.4";

	import "com.rometools:rome-fetcher" "1.6.0-SNAPSHOT";
	import "com.rometools:rome" "1.6.0-SNAPSHOT";
	import "com.rometools:rome-utils" "1.6.0-SNAPSHOT";
	import "com.rometools:rome-modules" "1.6.0-SNAPSHOT";
	import "org.jdom:jdom2" "2.0.5";

	import java.base "8";
	import java.jdbc "8";
	import javax.annotation "8";
	import ceylon.interop.java "1.1.1";
	
	import ceylon.net "1.1.1";
	import ceylon.logging "1.1.1";
	import ceylon.locale "1.1.1";
	
	import gen.com.github.bjansen.darjeeling "0.1";
	import com.github.bjansen.gyokuro "0.1";
}
