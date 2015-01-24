package com.LeagueOfStones.managers;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

	public void getDeck() {
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
}
