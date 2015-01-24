package com.LeagueOfStones.net.packets;

import com.LeagueOfStones.net.GameServer;

public class Packet07PlayCard extends Packet{

	private int myCardId;
	public Packet07PlayCard(byte[] data) {
		super(07);
		myCardId = Integer.parseInt(readData(data).split(";")[0]);
	}

	@Override
	public void writeData(GameServer server) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] getData() {
		// TODO Auto-generated method stub
		return null;
	}

}
