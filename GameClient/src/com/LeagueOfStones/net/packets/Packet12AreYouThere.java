package com.LeagueOfStones.net.packets;

import com.LeagueOfStones.net.GameClient;


public class Packet12AreYouThere extends Packet{

	private String username;
	
	public Packet12AreYouThere(byte[] data) {
		super(12);
		this.username = readData(data).split(";")[0];
	}
	
	public Packet12AreYouThere(String username){
		super(12);
		this.username = username;
	}
	
	public Packet12AreYouThere(){
		super(12);
		this.username = "none";
	}
	
	@Override
	public void writeData(GameClient server) {
	}
	
	@Override
	public byte[] getData() {
		
		return ("12"+username).getBytes();
	}
	
	public String getUsername(){
		return username;
	}
}
