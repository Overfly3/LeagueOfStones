package com.LeagueOfStones.mysql.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.LeagueOfStones.entities.Card;
import com.LeagueOfStones.mysql.datasource.DataSourceFactory;


public class MySQLService {
	private Connection connection = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private DataSource ds = null;
	private List<Card> cards = new ArrayList<Card>();
	private Card card;
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
	/**
	 * Returns a single card object of the performed query
	 * @param stmt
	 * @return
	 */
	public Card queryCard(String stmt){
		this.prepare();
		try {
			this.rs = this.stmt.executeQuery(stmt);
			card = extractCard(rs);
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
		return card;
	}
	
	/**
	 * takes the resultset and returns a single object
	 * @param rs
	 * @return
	 */
	private Card extractCard(ResultSet rs) {
		try {
			while(rs.next()){
				card = new Card(
					rs.getInt("ID"),
					rs.getInt("attack"),
					rs.getInt("health"),
					rs.getInt("mana")
					);			
				}
			return card;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Returns all the cards in the resultset
	 * @param rs
	 * @return
	 */
	private List<Card> extractCards(ResultSet rs) {
		try {
			while(rs.next()){
				Card card =  new Card(
							rs.getInt("id"),
							rs.getInt("attack"),
							rs.getInt("health"),
							rs.getInt("mana")						
						);
				cards.add(card);
			}
			return cards;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Prepares for the query statement
	 */
	private void prepare(){
		try {
			this.connection = ds.getConnection();
			this.stmt = connection.createStatement();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
