package com.LeagueOfStones.entities;

import com.LeagueOfStones.net.GameServer;
import com.LeagueOfStones.net.packets.Packet;
import com.LeagueOfStones.net.packets.Packet12AreYouThere;

public class Game {
	public Player player1;
	public Player player2;	
	public String playerString;
	public boolean running = false;
	private GameServer server;
	
	public Game(Player player1, Player player2, GameServer server){
		this.player1 = player1;
		this.player2 = player2;
		playerString = player1.getUsername()+";"+player2.getUsername();
		running = true;
		this.server = server;
		//TODO give the players cards
	}
	
	public void endGame(){
		running = false;
	}
	
	public boolean isPlayerConnected(String username) throws Exception{
		boolean playerIsConnected = false;
		Packet packet = new Packet12AreYouThere(username);
		if(playerString.split(";")[0].equals(username.trim())){
			playerIsConnected = server.checkConnection(packet.getData(), player1.ipAddress, player1.port);
			
		}else if(playerString.split(";")[1].equals(username.trim())){
			playerIsConnected = server.checkConnection(packet.getData(), player2.ipAddress, player2.port);
			
		}else{
			throw new Exception("There is no user with name " + username);
		}
		return playerIsConnected;
	}
	
	public boolean checkAllConnections(){
		Packet packet = new Packet12AreYouThere();
		if(!server.checkConnection(packet.getData(), player1.ipAddress, player1.port)){
			running = false;
		}
		
		if(!server.checkConnection(packet.getData(), player2.ipAddress, player2.port)){
			running = false;
		}
		return running;		
	}	
	
	public boolean isPlayerInThisGame(String username){
		if(playerString.split(";")[0] == username.trim()){
			return true;
		}else if(playerString.split(";")[1] == username.trim())
		{
			return true;
		}
		return false;
	}
}
