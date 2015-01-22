package com.LeagueOfStones.net.packets;

import com.LeagueOfStones.net.GameServer;

public class Packet03StartGame extends Packet{

	public Packet03StartGame(int packetId) {
		super(03);
	}

	@Override
	public void writeData(GameServer server) {
		server.sendDataToAllClients(getData());
		
	}

	@Override
	public byte[] getData() {
		return "03".getBytes();		
		
	}

}
