package com.LeagueOfStones.entities;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;


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
