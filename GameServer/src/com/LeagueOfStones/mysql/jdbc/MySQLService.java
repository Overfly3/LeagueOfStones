package com.LeagueOfStones.mysql.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.LeagueOfStones.mysql.datasource.DataSourceFactory;

public class MySQLService {
	private Connection connection = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private DataSource ds = null;
	private int count = 0;
	
	public MySQLService(){
		this.ds = DataSourceFactory.getMySQLDataSource();
	}
	/**
	 * Query on the database. 
	 * Adds the result-count to the class.count variable.
	 * @param stmt
	 * @return
	 */
	public ResultSet query(String stmt){
		this.prepare();
		try {
			this.rs = this.stmt.executeQuery(stmt);
			this.setCount();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				if(this.rs != null){this.rs.close();}
				if(this.stmt != null){this.stmt.close();}
				if(this.connection != null){this.connection.close();}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private void prepare(){
		try {
			this.connection = ds.getConnection();
			this.stmt = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void setCount(){
		int i = 0;
		try {
			while(this.rs.next()){
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.count = i;
	}
	
	public int count(){
		return this.count;
	}
	
}
