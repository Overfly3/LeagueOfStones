/*
 * Created by JFormDesigner on Sat Jan 24 02:41:07 CET 2015
 */

package com.LeagueOfStones.dialogs;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.LeagueOfStones.entities.Card;
import com.LeagueOfStones.managers.GameManager;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Alexander Theijs
 */
public class GameForm extends JPanel {
	GameManager myGameManager;
	ArrayList<JButton> myHandFields;
	
	public GameForm(GameManager manager, String nickNameUser, String nickNameOponent) {
		myGameManager = manager;
		initComponents();
		addHandFieldsToHandButtonList();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Alexander Theijs
		oponentField1 = new JButton();
		oponentField2 = new JButton();
		oponentField3 = new JButton();
		oponentField4 = new JButton();
		oponentField5 = new JButton();
		oponentField6 = new JButton();
		oponentField7 = new JButton();
		oponentField8 = new JButton();
		oponentField9 = new JButton();
		label1 = new JLabel();
		field1 = new JButton();
		field2 = new JButton();
		field3 = new JButton();
		field4 = new JButton();
		field5 = new JButton();
		field6 = new JButton();
		field7 = new JButton();
		field8 = new JButton();
		field9 = new JButton();
		label2 = new JLabel();
		hand1 = new JButton();
		hand2 = new JButton();
		hand3 = new JButton();
		hand4 = new JButton();
		hand5 = new JButton();
		hand6 = new JButton();
		hand7 = new JButton();
		hand8 = new JButton();
		hand9 = new JButton();
		label3 = new JLabel();
		label4 = new JLabel();

		//======== this ========

		// JFormDesigner evaluation mark
		setBorder(new javax.swing.border.CompoundBorder(
			new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
				"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
				java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

		setLayout(new FormLayout(
			"9*(120dlu), $lcgap, 60dlu",
			"3*(165dlu), $lgap, 75dlu"));

		//---- oponentField1 ----
		oponentField1.setIcon(new ImageIcon(getClass().getResource("/com/LeagueOfStones/images/1.png")));
		add(oponentField1, CC.xy(1, 1, CC.DEFAULT, CC.FILL));
		add(oponentField2, CC.xy(2, 1, CC.DEFAULT, CC.FILL));
		add(oponentField3, CC.xy(3, 1, CC.DEFAULT, CC.FILL));
		add(oponentField4, CC.xy(4, 1, CC.DEFAULT, CC.FILL));
		add(oponentField5, CC.xy(5, 1, CC.DEFAULT, CC.FILL));
		add(oponentField6, CC.xy(6, 1, CC.DEFAULT, CC.FILL));
		add(oponentField7, CC.xy(7, 1, CC.DEFAULT, CC.FILL));
		add(oponentField8, CC.xy(8, 1, CC.DEFAULT, CC.FILL));
		add(oponentField9, CC.xy(9, 1, CC.DEFAULT, CC.FILL));

		//---- label1 ----
		label1.setText("Oponent cards");
		add(label1, CC.xy(11, 1, CC.CENTER, CC.DEFAULT));
		add(field1, CC.xy(1, 2, CC.DEFAULT, CC.FILL));
		add(field2, CC.xy(2, 2, CC.DEFAULT, CC.FILL));
		add(field3, CC.xy(3, 2, CC.DEFAULT, CC.FILL));
		add(field4, CC.xy(4, 2, CC.DEFAULT, CC.FILL));
		add(field5, CC.xy(5, 2, CC.DEFAULT, CC.FILL));
		add(field6, CC.xy(6, 2, CC.DEFAULT, CC.FILL));
		add(field7, CC.xy(7, 2, CC.DEFAULT, CC.FILL));
		add(field8, CC.xy(8, 2, CC.DEFAULT, CC.FILL));
		add(field9, CC.xy(9, 2, CC.DEFAULT, CC.FILL));

		//---- label2 ----
		label2.setText("Your cards");
		add(label2, CC.xy(11, 2, CC.CENTER, CC.DEFAULT));
		add(hand1, CC.xy(1, 3, CC.DEFAULT, CC.FILL));
		add(hand2, CC.xy(2, 3, CC.DEFAULT, CC.FILL));
		add(hand3, CC.xy(3, 3, CC.DEFAULT, CC.FILL));
		add(hand4, CC.xy(4, 3, CC.DEFAULT, CC.FILL));
		add(hand5, CC.xy(5, 3, CC.DEFAULT, CC.FILL));
		add(hand6, CC.xy(6, 3, CC.DEFAULT, CC.FILL));
		add(hand7, CC.xy(7, 3, CC.DEFAULT, CC.FILL));
		add(hand8, CC.xy(8, 3, CC.DEFAULT, CC.FILL));
		add(hand9, CC.xy(9, 3, CC.DEFAULT, CC.FILL));

		//---- label3 ----
		label3.setText("Your hand");
		add(label3, CC.xy(11, 3, CC.CENTER, CC.DEFAULT));

		//---- label4 ----
		label4.setText("Stats");
		add(label4, CC.xy(11, 5, CC.CENTER, CC.DEFAULT));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Alexander Theijs
	private JButton oponentField1;
	private JButton oponentField2;
	private JButton oponentField3;
	private JButton oponentField4;
	private JButton oponentField5;
	private JButton oponentField6;
	private JButton oponentField7;
	private JButton oponentField8;
	private JButton oponentField9;
	private JLabel label1;
	private JButton field1;
	private JButton field2;
	private JButton field3;
	private JButton field4;
	private JButton field5;
	private JButton field6;
	private JButton field7;
	private JButton field8;
	private JButton field9;
	private JLabel label2;
	private JButton hand1;
	private JButton hand2;
	private JButton hand3;
	private JButton hand4;
	private JButton hand5;
	private JButton hand6;
	private JButton hand7;
	private JButton hand8;
	private JButton hand9;
	private JLabel label3;
	private JLabel label4;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public void PutDrawnCardsIntoHand(){
		clearHand();
		for(int i = 0; i < myGameManager.myHand.size(); i++)
		{
			Card handCard = myGameManager.myHand.get(i);
			String iconUrl = myGameManager.GenerateIconUrl(handCard.Id);
			myHandFields.get(i).setIcon(new ImageIcon(getClass().getResource(iconUrl)));
		}
	}

	private void addHandFieldsToHandButtonList() {
		myHandFields = new ArrayList<JButton>();
		myHandFields.addAll(Arrays.asList(new JButton[]{hand1, hand2, hand3, hand4, hand5, hand6, hand7, hand8, hand9}));
	}

	private void clearHand() {
		for(JButton handCard : myHandFields)
		{
			handCard.setIcon(null);
		}
	}
}
