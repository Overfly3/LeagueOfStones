package com.LeagueOfStones.net.packets;

import com.LeagueOfStones.net.GameServer;

public class Packet03StartGame extends Packet{

	public String player1;
	public String player2;
	
	public Packet03StartGame(String player1, String player2) {
		super(03);
		this.player1 = player1;
		this.player2 = player2;
		
	}
	
	public Packet03StartGame(byte[] data) {
		super(03);
		this.player1 = readData(data).split(";")[0];
		this.player2 = readData(data).split(";")[1];
	}

	@Override
	public void writeData(GameServer server) {
		server.sendDataToAllClients(getData());
		
	}

	@Override
	public byte[] getData() {
		return ("03"+player1+";"+player2).getBytes();		
		
	}

}
