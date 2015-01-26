package com.LeagueOfStones.net.packets;

import com.LeagueOfStones.net.GameClient;


public class Packet10CardDied extends Packet{

	public int cardId;
	public Packet10CardDied(byte[] data) {
		super(10);	
		cardId = Integer.parseInt(readData(data).split(";")[0]);
		
	}

	public Packet10CardDied(int id) {
		super(10);
		cardId = id;
	}

	@Override
	public void writeData(GameClient server) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] getData() {
		return ("07"+cardId).getBytes();
	}

}
