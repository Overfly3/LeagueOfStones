package com.LeagueOfStones.dialogs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.LeagueOfStones.net.GameClient;
import com.LeagueOfStones.net.packets.Packet01Disconnect;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private GameClient myGameClient;
	
	//Optional: What happens when the frame closes?
	public MainFrame(GameClient gameClient, String nickName) {
		super("League Of Stones");
		setUi(nickName);
		myGameClient = gameClient;
	}

	private void setUi(String nickName) {
		addSearchGameButton();
		addExitGameButton(nickName);
		
		//Optional: What happens when the frame closes?
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		//Size the frame.
		pack();

		//Show it.
		setVisible(true);
	}

	private void addExitGameButton(final String nickName) {
		//add search game button
		JButton uiButtonForExitGame = new JButton("Exit Game");
		
		//Create components and put them in the frame.
		//...create emptyLabel...
		getContentPane().add(uiButtonForExitGame, BorderLayout.PAGE_END);
		
		//Add action listener to button
		uiButtonForExitGame.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e)
	        {
	        	logOut(nickName);
		        dispose();
	        }
	    });
	}

	private void logOut(String nickName) {
		Packet01Disconnect loginPacket = new Packet01Disconnect(nickName);
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
		//ENQUEUE(02),
		//
	}
}
