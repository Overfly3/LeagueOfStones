
public class Start {
	public static void main(String[] args){
		
		GameServer server = new GameServer();
		GameClient client = new GameClient("localhost");
		
		server.run();
		client.run();
		
		while(true){
			
		}
		
	}
}
