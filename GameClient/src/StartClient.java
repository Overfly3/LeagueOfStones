import com.LeagueOfStones.net.GameClient;
import com.LeagueOfStones.net.packets.Packet00Login;


public class StartClient{
	public static void main(String[] args){
		
		GameClient client = new GameClient("localhost");
		client.start();

		Packet00Login loginPacket = new Packet00Login("Lulu");
		loginPacket.writeData(client);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
