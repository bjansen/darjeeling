import com.github.bjansen.gyokuro { Application }
import com.mysql.jdbc.jdbc2.optional {
	MysqlDataSource
}
import ceylon.logging {
	addLogWriter,
	Priority,
	info,
	Category,
	defaultPriority,
	trace
}
import com.github.bjansen.gyokuro.json {
	jsonSerializer
}
import ceylon.net.http.server {
    Request,
    Response
}
import ceylon.net.http.server.endpoints {
    serveStaticFile
}

shared void run() {
	defaultPriority = trace;
	
	addLogWriter {
		void log(Priority p, Category c, String m, Throwable? e) {
			value print = p<=info
			then process.writeLine
			else process.writeError;
			print("[``system.milliseconds``] ``p.string`` ``m``");
			if (exists e) {
				printStackTrace(e, print);
			}
		}		
	};

	value mysqlDS = MysqlDataSource();
	//darjeelingDS.dataSourceClassName = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource";
	//darjeelingDS.jdbcUrl = "jdbc:mysql://localhost:3306/feedzee2";
	mysqlDS.databaseName = "feedzee2";
	mysqlDS.user = "root";
	mysqlDS.setPassword("");

	darjeelingDS.dataSource = mysqlDS; 

	jsonSerializer.customSerializers = [dateTimeSerializer];

	value application = Application(8080, "/rest", `package com.github.bjansen.darjeeling.controller`);
	
	application.assetsPath = "assets";
	application.filters = [authenticationFilter];
	
	application.run();
}

Boolean authenticationFilter(Request req, Response resp) {
    if ((req.path == "/index.html" || req.path == "/") && req.session.get("userUid") is Null) {
        serveStaticFile("assets/login.html")(req, resp, () => {});
        return false;
    }
    return true;
}
