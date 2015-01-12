import com.LeagueOfStones.net.GameServer;


public class StartServer{
	public static void main(String[] args){
		
		GameServer server = new GameServer();
		server.start();
		
		//GameClient client = new GameClient("localhost");
		//client.start();
		
		//login new player
//		Packet00Login loginPacket = new Packet00Login("Lulu");
//		loginPacket.writeData(client);
//
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println(server.countConnectedPlayers()+" online.");

		
//		
//		Packet00Login loginPacket2 = new Packet00Login("Alex");
//		loginPacket2.writeData(client);
//		
//		Packet00Login loginPacket3 = new Packet00Login("TEST");
//		loginPacket3.writeData(client);		
//		MySQLService mysql = new MySQLService();
//		mysql.query("select * from user");
//		
//		System.out.println(mysql.count());
	}
}
