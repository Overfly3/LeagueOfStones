package com.LeagueOfStones.net.packets;

import com.LeagueOfStones.net.GameClient;

public class Packet05GameLost extends Packet{

	public Packet05GameLost(int packetId) {
		super(05);
	}

	@Override
	public void writeData(GameClient client) {
		client.sendData(getData());
		
	}

	@Override
	public byte[] getData() {
		return "05".getBytes();
	}

}
