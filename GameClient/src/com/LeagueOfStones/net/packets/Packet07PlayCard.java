package com.LeagueOfStones.net.packets;

import com.LeagueOfStones.net.GameClient;

public class Packet07PlayCard extends Packet{

	private int myCardId;
	private String myUsername;
	public Packet07PlayCard(byte[] data) {
		super(07);
		myCardId = Integer.parseInt(readData(data).split(";")[0]);
		myUsername =readData(data).split(";")[1];
	}
	
	public Packet07PlayCard(int myCardId){
		super(07);
		this.myCardId = myCardId;
	}
	
	public String getUsername(){
		return myUsername;
	}
	
	@Override
	public void writeData(GameClient client) {
		client.sendData(getData());
	}

	@Override
	public byte[] getData() {
		// TODO Auto-generated method stub
		return ("07"+myCardId+";"+myUsername).getBytes();
	}

}
