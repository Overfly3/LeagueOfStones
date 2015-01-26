package com.LeagueOfStones.net.packets;

import com.LeagueOfStones.net.GameClient;

public class Packet06Attack extends Packet{

	private int myCardId;
	private int enemyCardId;
	private String myName;
	public Packet06Attack(byte[] data) {
		super(06);
		try
		{
			this.myCardId = Integer.parseInt(readData(data).split(";")[0]);
			this.enemyCardId = Integer.parseInt(readData(data).split(";")[1]);
			this.myName = readData(data).split(";")[2];
			
		}catch(NumberFormatException e){
			e.printStackTrace();
			myCardId = 0;
			enemyCardId = 0;
			//TODO how to handle errors with needed data
		}
		
		
	}
	
	public Packet06Attack(int myCardId, int enemyCardId, String attacker)
	{
		super(06);
		this.myCardId = myCardId;
		this.enemyCardId = enemyCardId;
	}

	@Override
	public void writeData(GameClient client) {
		client.sendData(getData());
	}

	@Override
	public byte[] getData() {
		return ("06"+myCardId+";"+enemyCardId).getBytes();
	}
	
	public int getMyCardId(){
		return myCardId;
	}
	
	public int getEnemyCardId(){
		return enemyCardId;
	}
	
	public String getMyName(){
		return myName;
	}

}
