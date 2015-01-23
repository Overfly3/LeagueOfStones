package com.LeagueOfStones.dialogs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.LeagueOfStones.net.GameClient;
import com.LeagueOfStones.net.packets.Packet01Disconnect;
import com.LeagueOfStones.net.packets.Packet02Enqueue;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private GameClient myGameClient;
	private String myNickName;
	
	//Optional: What happens when the frame closes?
	public MainFrame(GameClient gameClient, String nickName) {
		super("League Of Stones");
		myNickName = nickName;
		myGameClient = gameClient;
		setUi();
	}

	private void setUi() {
		addSearchGameButton();
		addExitGameButton();
		
		//Optional: What happens when the frame closes?
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		//Size the frame.
		pack();

		//Show it.
		setVisible(true);
	}

	private void addExitGameButton() {
		//add search game button
		JButton uiButtonForExitGame = new JButton("Exit Game");
		
		//Create components and put them in the frame.
		//...create emptyLabel...
		getContentPane().add(uiButtonForExitGame, BorderLayout.PAGE_END);
		
		//Add action listener to button
		uiButtonForExitGame.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e)
	        {
	        	logOut();
	        }
	    });
	}

	private void logOut() {
		Packet01Disconnect loginPacket = new Packet01Disconnect(myNickName);
		loginPacket.writeData(myGameClient);
	}

	private void addSearchGameButton() {
		//add search game button
		JButton uiButtonForSearchGame = new JButton("Search Game");
		
		//Create components and put them in the frame.
		//...create emptyLabel...
		getContentPane().add(uiButtonForSearchGame, BorderLayout.PAGE_END);
		
		//Add action listener to button
		uiButtonForSearchGame.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e)
	        {
	            searchGame();
	        }
	    });
	}

	private void searchGame() {
		Packet02Enqueue packet = new Packet02Enqueue(myNickName);
		myGameClient.sendData(packet.getData());
	}
}
