import ceylon.file {
	parsePath,
	Nil,
	File
}
import ceylon.locale {
	systemLocale
}
import ceylon.logging {
	addLogWriter,
	Priority,
	info,
	Category,
	defaultPriority,
	trace,
    warn
}
import ceylon.net.http.server {
	Request,
	Response
}
import ceylon.net.http.server.endpoints {
	serveStaticFile
}
import ceylon.openshift {
	openshift
}
import ceylon.time {
	now
}

import com.github.bjansen.darjeeling.task {
	feedFetcherTask
}
import com.github.bjansen.gyokuro {
	Application
}
import com.github.bjansen.gyokuro.json {
	jsonSerializer
}
import com.mysql.jdbc.jdbc2.optional {
	MysqlDataSource
}

import java.util.concurrent {
	Executors,
	TimeUnit
}

shared void run() {
	
	value isProd = process.environmentVariableValue("DARJEELING_LOG_DIR") exists;
	
	value resource = if (exists d = process.environmentVariableValue("DARJEELING_LOG_DIR"))
	                then parsePath(d).childPath("darjeeling.log").resource
	                else null;
	value logFile = if (is Nil resource)
	                then resource.createFile().Appender()
	                else if (is File resource) then resource.Appender()
	                else null;
	
	addLogWriter {
		void log(Priority p, Category c, String m, Throwable? e) {
			value print = p<=info
			then process.writeLine
			else process.writeError;
			
			value msg = "[``systemLocale.formats.mediumFormatTime(now().dateTime())``] ``p.string`` ``m``";
			print(msg);
			if (exists logFile) {
				logFile.writeLine(msg);
				logFile.flush();
			}
			if (exists e) {
				print("\n");
				printStackTrace(e, print);

				if (exists logFile) {
					logFile.writeLine("\n");
				    printStackTrace(e, logFile.write);
				    logFile.flush();
				}
			}
		}		
	};

	value mysqlDS = MysqlDataSource();
	mysqlDS.databaseName = "feedzee2";
	
	if (isProd) {
		defaultPriority = warn;
		mysqlDS.serverName = env("DARJEELING_DB_HOST");
		mysqlDS.user = env("DARJEELING_DB_USER");
		mysqlDS.setPassword(env("DARJEELING_DB_PASSWORD"));
	} else {
		defaultPriority = trace;
		mysqlDS.serverName = "localhost";
		mysqlDS.user = "root";
		mysqlDS.setPassword("");
	}

	darjeelingDS.dataSource = mysqlDS; 

	jsonSerializer.customSerializers = [dateTimeSerializer];

    String address;
    Integer port;
    
    if (isProd) {
        address = env("DARJEELING_APP_HOST");
        port = parseInteger(env("DARJEELING_APP_PORT")) else -1;
    } else {
        address = "0.0.0.0";
        port = 8080;
    }

    value assetsPath = 	if (openshift.running)
		then openshift.repository + "/assets"
    	else "assets";

	value application = Application(address, port,
		  ["/rest", `package com.github.bjansen.darjeeling.controller`],
		  [assetsPath, ""], [authenticationFilter(assetsPath)]);

	value scheduler = Executors.newScheduledThreadPool(1);
	feedFetcherTask.setup();
	scheduler.scheduleWithFixedDelay(feedFetcherTask, 0, 2, TimeUnit.\iMINUTES);
	
	application.run();
}

String env(String name) {
    if (exists val = process.environmentVariableValue(name)) {
        return val;
    }
    throw Exception("Missing environment variable ``name``");
}

Boolean authenticationFilter(String assetsPath)(Request req, Response resp) {
	if (req.session.get("userUid") is Null) {	
	    if (req.path.startsWith("/rest")) {
	        if (!req.path.startsWith("/rest/auth")) {
	        	resp.responseStatus = 401;
	        	resp.writeString("401 - Unauthorized. Please log in.");
	        }
	    } else {
	        serveStaticFile(assetsPath + "/login.html")(req, resp, () => {});
	        return false;
	    }
	}
    return true;
}
