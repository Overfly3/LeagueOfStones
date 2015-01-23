import com.LeagueOfStones.net.GameClient;
import com.LeagueOfStones.net.packets.Packet00Login;
import com.LeagueOfStones.net.packets.Packet02Enqueue;
import com.LeagueOfStones.net.packets.Packet03StartGame;


public class StartClient{
	public static void main(String[] args){
		
		GameClient client = new GameClient("localhost");
		client.start();

		Packet00Login loginPacket = new Packet00Login("Lulu");
		loginPacket.writeData(client);
		
//		Packet02Enqueue queuePacket = new Packet02Enqueue("Lulu");
//		queuePacket.writeData(client);
//		
//		Packet00Login loginPacket2 = new Packet00Login("Test");
//		loginPacket2.writeData(client);
//		
//		Packet02Enqueue queuePacket2 = new Packet02Enqueue("Test");
//		queuePacket2.writeData(client);
		
		

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
