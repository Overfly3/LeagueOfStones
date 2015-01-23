import com.LeagueOfStones.net.GameServer;

public class StartServer{

	public static void main(String[] args){		
		
		GameServer server = new GameServer();
		
		//services running on the server
		server.start();
		server.checkQueue();
		
//		MySQLService mysql= new MySQLService();
//		List<Card> cards = new ArrayList<Card>();
//		
//		cards = mysql.queryCards("select * from cards");
//		
//		for (Card card : cards) {
//			System.out.println(card.getMyId());
//			
//		}
		
	}
	
	public static void sleep(int ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
