package com.LeagueOfStones.net.packets;

import com.LeagueOfStones.net.GameServer;

public class Packet11CardUpdate extends Packet{

	private int myCardId;
	private int myHealth;
	
	public Packet11CardUpdate(byte[] data) {
		super(11);
		myCardId = Integer.parseInt(readData(data).split(";")[0]);
		myHealth = Integer.parseInt(readData(data).split(";")[2]);
		
		
	}
	
	public Packet11CardUpdate(int myCardId, int myHealth){
		super(11);
		this.myCardId = myCardId;
		this.myHealth = myHealth;
	}

	@Override
	public void writeData(GameServer server) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] getData() {
		// TODO Auto-generated method stub
		return ("11"+myCardId+";"+myHealth).getBytes();
	}

}
