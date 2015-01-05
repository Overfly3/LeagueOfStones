
public class Packet00Login extends Packet{
	
	private String username;
	//private String password;
	
	public Packet00Login(byte[] data){
		super(00);
		this.username = readData(data);		
	}

	@Override
	public void writeData(GameClient client) {
		client.sendData(getData());	
	}

	@Override
	public void writeData(GameServer server){
		server.sendDataToAllClients(getData());
	}

	@Override
	public byte[] getData() {
		return ("00" + this.username).getBytes();
	}
}
