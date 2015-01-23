package com.LeagueOfStones.net.packets;

import com.LeagueOfStones.net.GameClient;

public class Packet02Enqueue extends Packet{

	String username;
	public Packet02Enqueue(byte[] data) {
		super(02);
		this.username = readData(data).split(";")[0];
	}
	
	public Packet02Enqueue(String username){
		super(02);
		this.username = username;
	}

	@Override
	public void writeData(GameClient client) {
		client.sendData(getData());
	}

	@Override
	public byte[] getData() {
		return ("02"+username).getBytes();
	}

}
