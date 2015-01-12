package com.LeagueOfStones.entities;

import java.net.InetAddress;


public class Player {
	public InetAddress ipAddress;
	public int port;
	private String username;
	
	public Player(InetAddress ipAddress, int port, String username){
		this.ipAddress = ipAddress;
		this.port = port;
		this.username = username;
	}
	
	public String getUsername(){
		return username;
	}
}
