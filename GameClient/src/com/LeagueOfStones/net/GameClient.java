package com.LeagueOfStones.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.LeagueOfStones.net.packets.Packet;
import com.LeagueOfStones.net.packets.Packet.PacketTypes;
import com.LeagueOfStones.net.packets.Packet00Login;
import com.LeagueOfStones.properties.Properties;


public class GameClient extends Thread{
	private InetAddress ipAddress;
	private DatagramSocket socket;
	private boolean myIsLoggedIn = false;
	
	public GameClient(String ipAddress) {
        try {
            this.socket = new DatagramSocket();
            this.ipAddress = InetAddress.getByName(ipAddress);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
	
	public boolean GetLoginStatus()
	{
		return myIsLoggedIn;
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
            handleLogin((Packet00Login) packet, address, port);
            break;
        case DISCONNECT:
            //packet = new Packet01Disconnect(data);            
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

    public void sendData(byte[] data) {

        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, Properties.port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleLogin(Packet00Login packet, InetAddress address, int port) {
        System.out.println("[" + address.getHostAddress() + ":" + port + "] " + packet.getUsername()
                + " has logged in...");
        if(!myIsLoggedIn)
        {
            myIsLoggedIn = true;
        }
        else
        {
        	//ignore packet
        }
    }
}
