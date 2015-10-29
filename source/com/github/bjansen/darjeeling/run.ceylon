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
	trace
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
	defaultPriority = trace;
	
	value resource = if (exists d = process.environmentVariableValue("OPENSHIFT_LOG_DIR"))
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
	
	if (openshift.running) {
		value db = openshift.mysql;
		mysqlDS.serverName = db.host;
		mysqlDS.user = db.user;
		mysqlDS.setPassword(db.password);
	} else {
		mysqlDS.serverName = "localhost";
		mysqlDS.user = "root";
		mysqlDS.setPassword("");
	}

	darjeelingDS.dataSource = mysqlDS; 

	jsonSerializer.customSerializers = [dateTimeSerializer];

    String address;
    Integer port;
    
    if (openshift.running) {
        address = openshift.ceylon.ip;
        port = openshift.ceylon.port;
    } else {
        address = "0.0.0.0";
        port = 8080;
    }
    
	value application = Application(address, port,
		  ["/rest", `package com.github.bjansen.darjeeling.controller`]);
	
	if (openshift.running) {
		application.assetsPath = openshift.repository + "assets";
	} else {
		application.assetsPath = "assets";
	}

	application.filters = [authenticationFilter];

	value scheduler = Executors.newScheduledThreadPool(1);
	feedFetcherTask.setup();
	scheduler.scheduleWithFixedDelay(feedFetcherTask, 0, 2, TimeUnit.\iMINUTES);
	
	application.run();
}

Boolean authenticationFilter(Request req, Response resp) {
	if (req.session.get("userUid") is Null) {	
	    if (req.path.startsWith("/rest")) {
	        if (!req.path.startsWith("/rest/auth")) {
	        	resp.responseStatus = 401;
	        	resp.writeString("401 - Unauthorized. Please log in.");
	        }
	    } else {
	        serveStaticFile("assets/login.html")(req, resp, () => {});
	        return false;
	    }
	}
    return true;
}
