import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class GameServer extends Thread{
	private DatagramSocket socket;
	private List<Player> connectedPlayers = new ArrayList<Player>();
	
	public GameServer(){
		try {
			this.socket = new DatagramSocket(Properties.port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(true){
			byte[] data = new byte[Properties.packetDataSize];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
		}
	}
	
	public void sendData(byte[] data, InetAddress ipAddress, int port){
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendDataToAllClients(byte[] data){
		for(Player p : connectedPlayers){
			sendData(data, p.ipAddress, p.port);
		}
	}
	
	private void parsePacket(byte[] data, InetAddress address, int port){
		String message = new String(data).trim();
		Packet.PacketTypes type = Packet.lookupPacket(message.substring(0,2));
		
		switch(type){
			default:
			case INVALID:
				break;
			case LOGIN:
				Packet00Login packet = new Packet00Login(data);
				System.out.println("Connected...");
				Player player = new Player(address, port);
				
				if(player != null){
					this.connectedPlayers.add(player);
				}
		}
	}
}