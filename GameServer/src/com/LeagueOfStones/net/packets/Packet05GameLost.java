package com.LeagueOfStones.net.packets;

import com.LeagueOfStones.net.GameServer;

public class Packet05GameLost extends Packet{

	public Packet05GameLost(int packetId) {
		super(05);
		
	}

	@Override
	public void writeData(GameServer server) {
		server.sendDataToAllClients(getData());
		
	}

	@Override
	public byte[] getData() {
		
		return "05".getBytes();
	}

}
