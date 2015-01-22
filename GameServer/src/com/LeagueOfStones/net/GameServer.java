package com.LeagueOfStones.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import com.LeagueOfStones.entities.Player;
import com.LeagueOfStones.net.packets.Packet;
import com.LeagueOfStones.net.packets.Packet.PacketTypes;
import com.LeagueOfStones.net.packets.Packet00Login;
import com.LeagueOfStones.net.packets.Packet01Disconnect;
import com.LeagueOfStones.properties.Properties;

public class GameServer extends Thread{
	private DatagramSocket socket;
	private List<Player> connectedPlayers = new ArrayList<Player>();
	
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
	        	break;
	        case STARTGAME:
	        	break;
	        case GAMEWON:
	        	break;
	        case GAMELOST:
	        	break;
	        }
	    }

	    public void addConnection(Player player, Packet00Login packet) {
	        boolean alreadyConnected = false;
	        for (Player p : this.connectedPlayers) {
	            if (player.getUsername().equalsIgnoreCase(p.getUsername())) {
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

	    public void sendDataToAllClients(byte[] data) {
	        for (Player p : connectedPlayers) {
	            sendData(data, p.ipAddress, p.port);
	        }
	    }
	    
	    public int countConnectedPlayers(){
	    	return this.connectedPlayers.size();
	    }
}