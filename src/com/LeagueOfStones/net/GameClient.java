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
	
	public GameClient(String ipAddress) {
        try {
            this.socket = new DatagramSocket();
            System.out.println("[CLIENT]Created DatagramSocket...");
            this.ipAddress = InetAddress.getByName(ipAddress);
            System.out.println("[CLIENT]IP: "+ipAddress);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            byte[] data = new byte[Properties.packetDataSize];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            System.out.println("[CLIENT]Created new DatagramPacket with the size " + Properties.packetDataSize);
            try {
                socket.receive(packet);
                System.out.println("[CLIENT]Received new packet...");
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
        }
    }

    private void parsePacket(byte[] data, InetAddress address, int port) {
        String message = new String(data).trim();
        System.out.println("[CLIENT]Parsed Packet message: " + message);
        PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
        Packet packet = null;
        switch (type) {
        default:
        case INVALID:
            break;
        case LOGIN:
        	System.out.println("[CLIENT]The Packet is a login Packet");
            packet = new Packet00Login(data);
            handleLogin((Packet00Login) packet, address, port);
            break;
        case DISCONNECT:
            //packet = new Packet01Disconnect(data);
            
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
        //Player player = new Player(address, port);
    }
}
