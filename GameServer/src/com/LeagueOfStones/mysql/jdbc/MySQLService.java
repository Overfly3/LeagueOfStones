package com.LeagueOfStones.mysql.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.LeagueOfStones.entities.Card;
import com.LeagueOfStones.mysql.datasource.DataSourceFactory;
import com.LeagueOfStones.properties.Properties;


public class MySQLService {
	private Connection connection = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private DataSource ds = null;
	private List<Card> cards = new ArrayList<Card>();
	
	public MySQLService(){
		this.ds = DataSourceFactory.getMySQLDataSource();		
	}
	/**
	 * Query on the database. 
	 * Adds the result-count to the class.count variable.
	 * @param stmt
	 * @return
	 */
	public List<Card> queryCards(String stmt){
		this.prepare();
		try {
			this.rs = this.stmt.executeQuery(stmt);
			cards = extractCards(rs);
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
		return cards;
	}
	
	private List<Card> extractCards(ResultSet rs) {
		try {
			while(rs.next()){
				Card card =  new Card(
								rs.getInt("id"), 
								rs.getString("name"), 
								rs.getString("description"), 
								rs.getInt("ad"), 
								rs.getInt("hp"), 
								rs.getInt("cost"), 
								rs.getInt("race_id"), 
								rs.getInt("type_id")
								);
				cards.add(card);
			}
			return cards;
		} catch (SQLException e) {
			e.printStackTrace();
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
}
