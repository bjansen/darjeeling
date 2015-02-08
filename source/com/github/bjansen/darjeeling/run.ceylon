import com.github.bjansen.gyokuro { Application }
import com.mysql.jdbc.jdbc2.optional {
	MysqlDataSource
}

shared void run() {
	value mysqlDS = MysqlDataSource();
	//darjeelingDS.dataSourceClassName = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource";
	//darjeelingDS.jdbcUrl = "jdbc:mysql://localhost:3306/feedzee2";
	mysqlDS.databaseName = "feedzee2";
	mysqlDS.user = "root";
	mysqlDS.setPassword("");

	darjeelingDS.dataSource = mysqlDS; 

	value application = Application(8080, "/rest", `package com.github.bjansen.darjeeling.controller`);
	application.run();
}
