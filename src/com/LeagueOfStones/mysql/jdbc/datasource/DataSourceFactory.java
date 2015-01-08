package com.LeagueOfStones.mysql.jdbc.datasource;

import javax.sql.DataSource;

import com.LeagueOfStones.properties.Properties;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataSourceFactory {
	public static DataSource getMySQLDataSource(){
		MysqlDataSource mysqlDS = null;
		
		mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(Properties.connectionUrl);
		mysqlDS.setUser(Properties.dbUser);
		mysqlDS.setPassword(Properties.dbPassword);
		
		return mysqlDS;
			
	}
}
