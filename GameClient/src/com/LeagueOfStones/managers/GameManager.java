package com.LeagueOfStones.managers;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.LeagueOfStones.dialogs.GameForm;
import com.LeagueOfStones.entities.Card;

public class GameManager {
	private List<Card> myDeck;
	private GameForm myGameForm;
	
	public List<Card> myHand;
	
	public void StartGame(String nickNameOponent, String nickNameUser) {
		openGameFrame(nickNameUser, nickNameOponent);
		preparateGame();
	}

	private void openGameFrame(String nickNameUser, String nickNameOponent) {
		myGameForm = new GameForm(this, nickNameUser, nickNameOponent);
		final JFrame gameFrame = new JFrame("League Of Stones");
		gameFrame.add(myGameForm);
		gameFrame.pack();
		gameFrame.setVisible(true);
	}

	private void preparateGame() {
		getDeck();
		drawInitialCards();
	}

	private void drawInitialCards() {
		myHand = new ArrayList<Card>();
		//player gets at beginning four cards
		for(int i = 0; i < 4; i++)
		{
			DrawCard();
		}
	}

	private void getDeck() {
		myDeck = new ArrayList<Card>();
		File fXmlFile = new File("src\\com\\LeagueOfStones\\decks\\cards.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		Document doc = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			doc = dBuilder.parse(fXmlFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();
		
		//get all card nodes
		NodeList cardNodeList = doc.getElementsByTagName("card");
		for (int i = 0; i < cardNodeList.getLength(); i++) {
			Node card = cardNodeList.item(i);
			int id =  Integer.parseInt(((Element)card).getElementsByTagName("Id").item(0).getTextContent());
			int attack = Integer.parseInt(((Element)card).getElementsByTagName("AttackDamage").item(0).getTextContent());
			int health =  Integer.parseInt(((Element)card).getElementsByTagName("Health").item(0).getTextContent());
			int mana =  Integer.parseInt(((Element)card).getElementsByTagName("ManaCosts").item(0).getTextContent());
			myDeck.add(new Card(id, attack, health, mana));
		}
	}

	public void DrawCard() {
		//int id, int attackDamage, int health, int manaCosts
		int amountOfCards = myDeck.size();
		
		//get Random card index
		Random rand = new Random();
		int randomIndex = rand.nextInt(amountOfCards) + 1;
		
		//draw card by random index
		Card drawedCard = myDeck.get(randomIndex);
		
		//put drawn card into hand when possible to draw card else throw it away
		if(myHand.size() < 9)
		{
			myHand.add(new Card(drawedCard.Id, drawedCard.AttackDamage, drawedCard.Health, drawedCard.ManaCosts));
			//update changes to ui
			myGameForm.PutDrawnCardsIntoHand();
		}
		
		//remove card from deck
		myDeck.remove(drawedCard);
	}

	public String GenerateIconUrl(int id) {
		
		String iconUrl = "/com/LeagueOfStones/images/" + id + ".png";
		return iconUrl;
	}
}
