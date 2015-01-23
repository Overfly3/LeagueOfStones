package com.LeagueOfStones.entities;

public class Card {
	private int myId;
	private String myName;
	private String myDescription;
	private int myAttack;
	private int myHealth;
	private int myCost;
	private int myRace;
	private int myType;
	
	public Card(int myId, String myName, String myDescription, int myAttack,int myHealth, int myCost, int myRace, int myType) {
		
		this.myId = myId;
		this.myName = myName;
		this.myDescription = myDescription;
		this.myAttack = myAttack;
		this.myHealth = myHealth;
		this.myCost = myCost;
		this.myRace = myRace;
		this.myType = myType;
	}

	public int getMyId() {
		return myId;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public String getMyDescription() {
		return myDescription;
	}

	public void setMyDescription(String myDescription) {
		this.myDescription = myDescription;
	}

	public int getMyAttack() {
		return myAttack;
	}

	public void setMyAttack(int myAttack) {
		this.myAttack = myAttack;
	}

	public int getMyHealth() {
		return myHealth;
	}

	public void setMyHealth(int myHealth) {
		this.myHealth = myHealth;
	}

	public int getMyCost() {
		return myCost;
	}

	public void setMyCost(int myCost) {
		this.myCost = myCost;
	}

	public int getMyRace() {
		return myRace;
	}

	public void setMyRace(int myRace) {
		this.myRace = myRace;
	}

	public int getMyType() {
		return myType;
	}

	public void setMyType(int myType) {
		this.myType = myType;
	}
	
}
