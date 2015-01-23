package com.LeagueOfStones.managers;

import java.util.ArrayList;
import java.util.List;

import com.LeagueOfStones.entities.Card;

public class GameManager {
	List<Card> myDeck;
	public void StartGame(String nickNameOponent, String nickNameUser) {
		preparateGame();
		//open gameDialog
		
	}

	private void preparateGame() {
		getDeck();
	}

	private void getDeck() {
		//TODO DeckLogik
		myDeck = new ArrayList<Card>();
	}
}
