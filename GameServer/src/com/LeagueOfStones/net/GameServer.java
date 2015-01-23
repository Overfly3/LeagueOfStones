package com.LeagueOfStones.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import com.LeagueOfStones.entities.Game;
import com.LeagueOfStones.entities.Player;
import com.LeagueOfStones.net.packets.Packet;
import com.LeagueOfStones.net.packets.Packet.PacketTypes;
import com.LeagueOfStones.net.packets.Packet00Login;
import com.LeagueOfStones.net.packets.Packet01Disconnect;
import com.LeagueOfStones.net.packets.Packet02Enqueue;
import com.LeagueOfStones.net.packets.Packet03StartGame;
import com.LeagueOfStones.net.packets.Packet06Attack;
import com.LeagueOfStones.net.packets.Packet12AreYouThere;
import com.LeagueOfStones.properties.Properties;

public class GameServer extends Thread{
	private DatagramSocket socket;
	private List<Player> connectedPlayers = new ArrayList<Player>();
	private List<Game> games = new ArrayList<Game>();
	private List<Player> queue = new ArrayList<Player>();
	
	 public GameServer() {
	        try {
	            this.socket = new DatagramSocket(Properties.port);
	        } catch (SocketException e) {
	            e.printStackTrace();
	        }
	    }

	    public void run() {
	        while (true) {
	            byte[] data = new byte[Properties.packetDataSize];
	            DatagramPacket packet = new DatagramPacket(data, data.length);
	            try {
	                socket.receive(packet);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
	        }
	    }
	    
	    private void parsePacket(byte[] data, InetAddress address, int port) {
	        String message = new String(data).trim();
	        PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
	        Packet packet = null;
	        switch (type) {
	        default:
	        case INVALID:
	            break;
	        case LOGIN:
	            packet = new Packet00Login(data);
	            System.out.println("[" + address.getHostAddress() + ":" + port + "] "
	                    + ((Packet00Login) packet).getUsername() + " has connected...");
	            
	            Player player = new Player(address, port,((Packet00Login) packet).getUsername());
	            this.addConnection(player, (Packet00Login) packet);
	            break;
	        case DISCONNECT:
	            packet = new Packet01Disconnect(data);
	            System.out.println("[" + address.getHostAddress() + ":" + port + "] "
	                    + ((Packet01Disconnect) packet).getUsername() + " has left...");
	            this.removeConnection((Packet01Disconnect) packet);
	            break;
	        case ENQUEUE:
	        	packet = new Packet02Enqueue(data);
	        	this.addQueue((Packet02Enqueue)packet, address, port);
	        	break;
	        case ATTACK:
	        	packet = new Packet06Attack(data);
	        	this.attack((Packet06Attack)packet, address, port);
	        }
	    }

	    private void attack(Packet06Attack packet, InetAddress address, int port) {
			//TODO calculate the damage/health and update the clients		
	    	
		}

		public void checkQueue(){
			while(true){
				if(queue.size() >= 2){
					Player player1 = queue.get(0);
					Player player2 = queue.get(1);
					
					games.add(new Game(player1, player2, this));
					
					//TODO start game
					Packet packet = new Packet03StartGame(player1,player2);	
					
					sendData(packet.getData(), player1.ipAddress, player1.port);
					sendData(packet.getData(), player2.ipAddress, player2.port);
					
					this.queue.remove(1); System.out.println(player2.getUsername()+" removed from queue");
					this.queue.remove(0); System.out.println(player1.getUsername()+" removed from queue");
					
					System.out.println(player1.getUsername()+" is playing against "+ player2.getUsername());
					
				}
				
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

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
	    		packet = new Packet02Enqueue(-1);
				sendData(packet.getData(),address, port);
	    	}
		}

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
	            System.out.println("[SERVER]Added " +player.getUsername() +" to the connected players list.");
	        }
	    }

	    public void removeConnection(Packet01Disconnect packet) {
	        this.connectedPlayers.remove(getPlayerIndex(packet.getUsername()));
	        packet.writeData(this);
	    }

	    public Player getPlayer(String username) {
	        for (Player player : this.connectedPlayers) {
	            if (player.getUsername().equals(username)) {
	                return player;
	            }
	        }
	        return null;
	    }

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

	    public void sendDataToAllClients(byte[] data) {
	        for (Player p : connectedPlayers) {
	            sendData(data, p.ipAddress, p.port);
	        }
	    }
	    
	    public void sendDataToGamePlayers(byte[] data){
	    	
	    }
	    
	    public int countConnectedPlayers(){
	    	return this.connectedPlayers.size();
	    }
}