package com.LeagueOfStones.net.packets;

import com.LeagueOfStones.net.GameClient;

public class Packet03StartGame extends Packet{

	public Packet03StartGame(int packetId) {
		super(03);
	}

	@Override
	public void writeData(GameClient client) {
		client.sendData(getData());
	}

	@Override
	public byte[] getData() {
		return "03".getBytes();
	}

}
