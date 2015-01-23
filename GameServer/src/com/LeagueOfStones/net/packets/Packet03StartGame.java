package com.LeagueOfStones.net.packets;

import com.LeagueOfStones.entities.Player;
import com.LeagueOfStones.net.GameServer;

public class Packet03StartGame extends Packet{

	public Player player1;
	public Player player2;
	
	public Packet03StartGame(Player player1, Player player2) {
		super(03);
		this.player1 = player1;
		this.player2 = player2;
		
	}

	@Override
	public void writeData(GameServer server) {
		server.sendDataToAllClients(getData());
		
	}

	@Override
	public byte[] getData() {
		return ("03"+player1.getUsername()+";"+player2.getUsername()).getBytes();		
		
	}

}
