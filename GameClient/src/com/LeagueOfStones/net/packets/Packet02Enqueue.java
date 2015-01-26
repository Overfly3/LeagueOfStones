package com.LeagueOfStones.net.packets;

import com.LeagueOfStones.net.GameClient;

public class Packet02Enqueue extends Packet{

	private String username;
	
	public Packet02Enqueue(byte[] data) {
		super(02);
		this.username = readData(data).split(";")[0];
	}
	
	public Packet02Enqueue(String username){
		super(02);
		this.username = username;
	}
	
	public Packet02Enqueue(int id){
		super(id);
	}

	@Override
	public void writeData(GameClient server) {
	}

	@Override
	public byte[] getData() {
		return (super.packetId+username).getBytes();
	}

	public String getUsername() {
		return username;
	}

}
