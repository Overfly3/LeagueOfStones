package com.LeagueOfStones.net.packets;

import com.LeagueOfStones.net.GameClient;

public class Packet02Enqueue extends Packet{

	public Packet02Enqueue(int packetId) {
		super(02);
	}

	@Override
	public void writeData(GameClient client) {
		
	}

	@Override
	public byte[] getData() {
		return null;
	}

}
