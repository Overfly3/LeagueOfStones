package com.LeagueOfStones.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import com.LeagueOfStones.entities.Card;
import com.LeagueOfStones.entities.Game;
import com.LeagueOfStones.entities.Player;
import com.LeagueOfStones.mysql.jdbc.MySQLService;
import com.LeagueOfStones.net.packets.Packet;
import com.LeagueOfStones.net.packets.Packet.PacketTypes;
import com.LeagueOfStones.net.packets.Packet00Login;
import com.LeagueOfStones.net.packets.Packet01Disconnect;
import com.LeagueOfStones.net.packets.Packet02Enqueue;
import com.LeagueOfStones.net.packets.Packet03StartGame;
import com.LeagueOfStones.net.packets.Packet06Attack;
import com.LeagueOfStones.net.packets.Packet07PlayCard;
import com.LeagueOfStones.net.packets.Packet10CardDied;
import com.LeagueOfStones.net.packets.Packet11CardUpdate;
import com.LeagueOfStones.net.packets.Packet12AreYouThere;
import com.LeagueOfStones.properties.Properties;

public class GameServer extends Thread{
	private DatagramSocket socket;
	private List<Player> connectedPlayers = new ArrayList<Player>();
	private List<Game> games = new ArrayList<Game>();
	private List<Player> queue = new ArrayList<Player>();
	private MySQLService mysql = new MySQLService();
	
	 public GameServer() {
	        try {
	        	//we instantiate the server datagramsocket listening on set port in the properties
	        	
	            this.socket = new DatagramSocket(Properties.port);
	        } catch (SocketException e) {
	            e.printStackTrace();
	        }
	    }
	 	
	 //since this class is a thread we need to implement this method
	 //we can run any methods at the same time
	 //the run method is the 1st method being called (main)
	    public void run() {
	        while (true) {
	            byte[] data = new byte[Properties.packetDataSize];
	            DatagramPacket packet = new DatagramPacket(data, data.length);
	            try {
	                socket.receive(packet);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            //if we get a packet we need to process it to determine what's happening next
	            this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
	        }
	    }
	    
	    private void parsePacket(byte[] data, InetAddress address, int port) {
	        String message = new String(data).trim();
	        PacketTypes type = Packet.lookupPacket(message.substring(0, 2)); //the first two numbers of each packet indicates the type of the packet
	        Packet packet = null; // for each packetId there is a corresponding enum 
	        switch (type) {
	        default:
	        case INVALID: //-1
	            break;
	        case LOGIN: //00
	            packet = new Packet00Login(data);
	            System.out.println("[" + address.getHostAddress() + ":" + port + "] "
	                    + ((Packet00Login) packet).getUsername() + " has connected...");
	            
	            Player player = new Player(address, port,((Packet00Login) packet).getUsername());
	            this.addConnection(player, (Packet00Login) packet);
	            break;
	        case DISCONNECT: //01
	            packet = new Packet01Disconnect(data);
	            System.out.println("[" + address.getHostAddress() + ":" + port + "] "
	                    + ((Packet01Disconnect) packet).getUsername() + " has left...");
	            this.removeConnection((Packet01Disconnect) packet);
	            break;
	        case ENQUEUE: //03
	        	packet = new Packet02Enqueue(data);
	        	this.addQueue((Packet02Enqueue)packet, address, port);
	        	break;
	        case ATTACK: //06
	        	packet = new Packet06Attack(data);
	        	this.attack((Packet06Attack)packet, address, port);
	        	break;
	        case PLAYCARD://07
	        	packet = new Packet07PlayCard(data);
	        	playCard((Packet07PlayCard)packet, address, port);
	        }
	    }
	    private void playCard(Packet07PlayCard packet, InetAddress address,int port) {
	    	Packet playCardPacket = new Packet07PlayCard(packet.getData());
	    	try {
				sendDataToGamePlayers(playCardPacket.getData(), packet.getUsername());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
	     * Gets the cards on DB and calculates the health and messages the clients what happened with the cards.
	     * Either they get card died packet, or update card packet
	     * @param packet
	     * @param address
	     * @param port
	     */
	    public void attack(Packet06Attack packet, InetAddress address, int port) {
	    	Card myCard = mysql.queryCard("select * from cards where id = "+ packet.getMyCardId());
	    	Card enemyCard = mysql.queryCard("select * from cards where id = "+ packet.getEnemyCardId());
	    	
	    	myCard.damageCard(enemyCard.AttackDamage); //this method substracts the health with the attack damage
	    	enemyCard.damageCard(myCard.AttackDamage);
	    	
	    	if(myCard.isDead){
	    		Packet myCardDied = new Packet10CardDied(myCard.Id);
	    		try {
					sendDataToGamePlayers(myCardDied.getData(),packet.getMyName());
				} catch (Exception e) {
					e.printStackTrace();
				}
	    	}else{
	    		Packet updateMyCard = new Packet11CardUpdate(myCard.Id, myCard.Health);
	    		try {
					sendDataToGamePlayers(updateMyCard.getData(), packet.getMyName());
				} catch (Exception e) {
					e.printStackTrace();
				}
	    	}
	    	if(enemyCard.isDead){
	    		Packet enemyCardDied = new Packet10CardDied(enemyCard.Id);
	    		try {
					sendDataToGamePlayers(enemyCardDied.getData(), packet.getMyName());
				} catch (Exception e) {
					e.printStackTrace();
				}
	    	}else{
	    		Packet updateEnemyCard = new Packet11CardUpdate(enemyCard.Id, enemyCard.Health);
	    		try {
					sendDataToGamePlayers(updateEnemyCard.getData(), packet.getMyName());
				} catch (Exception e) {
					e.printStackTrace();
				}
	    	}
		}
	    
	    //this method is needed to pair two people which are in queue for a game
		public void checkQueue(){
			while(true){
				if(queue.size() >= 2){
					Player player1 = queue.get(0);
					Player player2 = queue.get(1);
					
					games.add(new Game(player1, player2, this));
					//we add the two players to the games list
					
					Packet packet = new Packet03StartGame(player1,player2);	
					
					//here we need to send to each client that a game has been started
					sendData(packet.getData(), player1.ipAddress, player1.port);
					sendData(packet.getData(), player2.ipAddress, player2.port);
					
					//important to remove the players from the queue when they are added to the list
					//carefull in removing the player. the index has to be removed from top to bottom!
					this.queue.remove(1); System.out.println(player2.getUsername()+" removed from queue");
					this.queue.remove(0); System.out.println(player1.getUsername()+" removed from queue");
					
					System.out.println(player1.getUsername()+" is playing against "+ player2.getUsername());
				}
				
				try {
					//5 seconds of sleep time for the while loop
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		//add the players to the queue list only if they are also in the connected player list
		private void addQueue(Packet02Enqueue packet, InetAddress address, int port) {
			boolean addedQueue = false;
	    	for (Player p : this.connectedPlayers) {
				
				if(p.getUsername().trim().equals(packet.getUsername().trim())){
					System.out.println(p.getUsername()+" was added to queue");
					this.queue.add(p);
					addedQueue = true;
					sendData(packet.getData(), p.ipAddress, p.port);
				}
			}
	    	
	    	if(!addedQueue){
	    		//send invalid packet if they are not found in the list
	    		packet = new Packet02Enqueue(-1);
				sendData(packet.getData(),address, port);
	    	}
		}
		
		//check if the client connecting is not in the list
		//if he is, we need to check the port and ip
		//else add the client to teh list
		public void addConnection(Player player, Packet00Login packet) {
	        boolean alreadyConnected = false;
	        for (Player p : this.connectedPlayers) {
	            if (player.getUsername().trim().equalsIgnoreCase(p.getUsername())) {
	                if (p.ipAddress == null) {
	                    p.ipAddress = player.ipAddress;
	                }
	                if (p.port == -1) {
	                    p.port = player.port;
	                }
	                alreadyConnected = true;
	            }else{
	                sendData(packet.getData(), p.ipAddress, p.port);

	                packet = new Packet00Login(p.getUsername());
	                sendData(packet.getData(), player.ipAddress, player.port);
	            }
	        }
	        if (!alreadyConnected) {
	            this.connectedPlayers.add(player);
	            packet = new Packet00Login(player.getUsername());
	            sendData(packet.getData(), player.ipAddress, player.port);
	            System.out.println("[SERVER]Added " +player.getUsername() +" to the connected players list.");
	        }
	    }
		
	    public void removeConnection(Packet01Disconnect packet) {
	        this.connectedPlayers.remove(getPlayerIndex(packet.getUsername()));
	        packet.writeData(this);
	    }
	    
	    //return the player object by username
	    public Player getPlayer(String username) {
	        for (Player player : this.connectedPlayers) {
	            if (player.getUsername().equals(username)) {
	                return player;
	            }
	        }
	        return null;
	    }

	    //return the player index of the list by username
	    public int getPlayerIndex(String username) {
	        int index = 0;
	        for (Player player : this.connectedPlayers) {
	            if (player.getUsername().equals(username)) {
	                break;
	            }
	            index++;
	        }
	        return index;
	    }
	    
	    /**
	     * sends data to the given ip/port
	     * @param data
	     * @param ipAddress
	     * @param port
	     */
	    public void sendData(byte[] data, InetAddress ipAddress, int port) {

            DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
            try {
                this.socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

	    }
	    
	    
	    
	    public boolean checkConnection(byte[] data, InetAddress ipAddress, int port){
	    	DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
            try {
                this.socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
	    }
	    
	    /**
	     * returns true if a connections exists
	     * @return
	     */
	    public boolean checkConnection(){
	    	Packet packet = new Packet12AreYouThere();
	    	for (Player p : connectedPlayers) {
				if(checkConnection(packet.getData(), p.ipAddress, p.port)){
					System.out.println(p.getUsername()+" is connected.");
				}else{
					System.out.println("oops.."+p.getUsername()+" is not connected anymore.");
					return false;
				}
			}
	    	return true;
	    }
	    //gets all the clients in the list and sends them all a message
	    public void sendDataToAllClients(byte[] data) {
	        for (Player p : connectedPlayers) {
	            sendData(data, p.ipAddress, p.port);
	        }
	    }
	    
	    //searches the player string in the game class and sends data if found
	    public void sendDataToGamePlayers(byte[] data, String player1) throws Exception{
	    	boolean dataSent = false;
	    	for (Game game : games) {	    		
	    		if(game.isPlayerInThisGame(player1)){
	    			sendData(data, game.player1.ipAddress, game.player1.port);
	    			sendData(data, game.player2.ipAddress, game.player2.port);
	    			dataSent = true;
	    			break;
	    		}
			}
	    	if(!dataSent){
	    		throw new Exception("No user found in game with name "+player1);
	    	}
	    }
	    
	    public int countConnectedPlayers(){
	    	return this.connectedPlayers.size();
	    }
}