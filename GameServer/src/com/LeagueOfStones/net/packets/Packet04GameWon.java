package com.LeagueOfStones.net.packets;

import com.LeagueOfStones.net.GameServer;

public class Packet04GameWon extends Packet{

	public Packet04GameWon(int packetId) {
		super(04);
	}

	@Override
	public void writeData(GameServer server) {
		server.sendDataToAllClients(getData());
		
	}

	@Override
	public byte[] getData() {
		return "04".getBytes();
	}

}
