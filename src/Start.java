import com.LeagueOfStones.net.GameClient;
import com.LeagueOfStones.net.GameServer;
import com.LeagueOfStones.net.packets.Packet00Login;


public class Start{
	public static void main(String[] args){
		
		GameServer server = new GameServer();
		server.start();
		System.out.println("Server running...");
		
		GameClient client = new GameClient("localhost");
		client.start();
		System.out.println("Client started...");
		
		//login new player
		Packet00Login loginPacket = new Packet00Login("Lulu");
		loginPacket.writeData(client);
//		
//		Packet00Login loginPacket2 = new Packet00Login("Alex");
//		loginPacket2.writeData(client);
//		
//		Packet00Login loginPacket3 = new Packet00Login("TEST");
//		loginPacket3.writeData(client);
	}
}
