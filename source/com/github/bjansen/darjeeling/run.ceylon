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
import java.util.concurrent {
    Executors,
    TimeUnit
}
import com.github.bjansen.darjeeling.task {
    feedFetcherTask
}
import ceylon.time {
    now
}
import ceylon.locale {
    systemLocale
}

shared void run() {
	defaultPriority = trace;
	
	addLogWriter {
		void log(Priority p, Category c, String m, Throwable? e) {
			value print = p<=info
			then process.writeLine
			else process.writeError;
			print("[``systemLocale.formats.mediumFormatTime(now().dateTime())``] ``p.string`` ``m``");
			if (exists e) {
				print("\n");
				printStackTrace(e, print);
			}
		}		
	};

	value mysqlDS = MysqlDataSource();
	mysqlDS.databaseName = "feedzee2";
	mysqlDS.user = "root";
	mysqlDS.setPassword("");

	darjeelingDS.dataSource = mysqlDS; 

	jsonSerializer.customSerializers = [dateTimeSerializer];

	value application = Application(8080, "/rest", `package com.github.bjansen.darjeeling.controller`);
	
	application.assetsPath = "assets";
	application.filters = [authenticationFilter];

	value scheduler = Executors.newScheduledThreadPool(1);
	feedFetcherTask.setup();
	scheduler.scheduleWithFixedDelay(feedFetcherTask, 0, 1, TimeUnit.\iMINUTES);
	
	application.run();
}

Boolean authenticationFilter(Request req, Response resp) {
    if ((req.path == "/index.html" || req.path == "/") && req.session.get("userUid") is Null) {
        serveStaticFile("assets/login.html")(req, resp, () => {});
        return false;
    }
    return true;
}
