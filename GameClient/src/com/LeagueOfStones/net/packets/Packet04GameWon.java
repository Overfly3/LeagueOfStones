package com.LeagueOfStones.net.packets;

import com.LeagueOfStones.net.GameClient;

public class Packet04GameWon extends Packet{

	public Packet04GameWon(int packetId) {
		super(04);
	}

	@Override
	public void writeData(GameClient client) {
		
	}

	@Override
	public byte[] getData() {
		return null;
	}

}
