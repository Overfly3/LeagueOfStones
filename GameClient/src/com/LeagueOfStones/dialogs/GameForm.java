/*
 * Created by JFormDesigner on Sat Jan 24 02:41:07 CET 2015
 */

package com.LeagueOfStones.dialogs;

import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author Alexander Theijs
 */
public class GameForm extends JPanel {
	public GameForm() {
		initComponents();
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
		field1 = new JButton();
		field2 = new JButton();
		field3 = new JButton();
		field4 = new JButton();
		field5 = new JButton();
		field6 = new JButton();
		field7 = new JButton();
		field8 = new JButton();
		field9 = new JButton();
		hand1 = new JButton();
		hand2 = new JButton();
		hand3 = new JButton();
		hand4 = new JButton();
		hand5 = new JButton();
		hand6 = new JButton();
		hand7 = new JButton();
		hand8 = new JButton();
		hand9 = new JButton();

		//======== this ========

		// JFormDesigner evaluation mark
		setBorder(new javax.swing.border.CompoundBorder(
			new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
				"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
				java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

		setLayout(new FormLayout(
			"9*(120dlu)",
			"3*(165dlu), $lgap, 75dlu"));

		//---- oponentField1 ----
		oponentField1.setIcon(null);
		add(oponentField1, CC.xy(1, 1));
		add(oponentField2, CC.xy(2, 1));
		add(oponentField3, CC.xy(3, 1));
		add(oponentField4, CC.xy(4, 1));
		add(oponentField5, CC.xy(5, 1));
		add(oponentField6, CC.xy(6, 1));
		add(oponentField7, CC.xy(7, 1));
		add(oponentField8, CC.xy(8, 1));
		add(oponentField9, CC.xy(9, 1));
		add(field1, CC.xy(1, 2));
		add(field2, CC.xy(2, 2));
		add(field3, CC.xy(3, 2));
		add(field4, CC.xy(4, 2));
		add(field5, CC.xy(5, 2));
		add(field6, CC.xy(6, 2));
		add(field7, CC.xy(7, 2));
		add(field8, CC.xy(8, 2));
		add(field9, CC.xy(9, 2));
		add(hand1, CC.xy(1, 3));
		add(hand2, CC.xy(2, 3));
		add(hand3, CC.xy(3, 3));
		add(hand4, CC.xy(4, 3));
		add(hand5, CC.xy(5, 3));
		add(hand6, CC.xy(6, 3));
		add(hand7, CC.xy(7, 3));
		add(hand8, CC.xy(8, 3));
		add(hand9, CC.xy(9, 3));
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
	private JButton field1;
	private JButton field2;
	private JButton field3;
	private JButton field4;
	private JButton field5;
	private JButton field6;
	private JButton field7;
	private JButton field8;
	private JButton field9;
	private JButton hand1;
	private JButton hand2;
	private JButton hand3;
	private JButton hand4;
	private JButton hand5;
	private JButton hand6;
	private JButton hand7;
	private JButton hand8;
	private JButton hand9;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
