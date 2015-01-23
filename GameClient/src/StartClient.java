import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.LeagueOfStones.dialogs.MainFrame;
import com.LeagueOfStones.net.GameClient;
import com.LeagueOfStones.net.packets.Packet00Login;


public class StartClient{
	public static void main(String[] args){
		setUi();
		
		
	}

	private static void setUi() {
		//Create the frame.
		final JFrame loginFrame = new JFrame("League Of Stones");
		
		JButton uiButtonForLogin = new JButton("Login");
		
		//Create components and put them in the frame.
		//...create emptyLabel...
		loginFrame.getContentPane().add(uiButtonForLogin, BorderLayout.PAGE_END);
		
		final JTextField uiTextFieldForLogin = new JTextField();
		
		//Add action listener to button
		uiButtonForLogin.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                login(uiTextFieldForLogin.getText(), loginFrame);
            }
        });   
		
		loginFrame.getContentPane().add(uiTextFieldForLogin, BorderLayout.PAGE_START);
		
		//Size the frame.
		loginFrame.pack();

		//Show it.
		loginFrame.setVisible(true);
	}

	private static void login(String nickName, JFrame loginFrame) {
		GameClient client = new GameClient("localhost");
		client.start();

		Packet00Login loginPacket = new Packet00Login(nickName);
		loginPacket.writeData(client);
		
		
		//check if user has been logged in
		int countOfLoginTries = 0;
		while(true && countOfLoginTries < 5)
		{
			if(client.GetLoginStatus())
			{
				break;
			}
			else
			{
				
				System.out.println("Trying to log in... " + countOfLoginTries + " seconds.");
				try {
				    Thread.sleep(1000);                 //1000 milliseconds is one second.
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
			}
			countOfLoginTries++;
		}
		
		if(client.GetLoginStatus())
		{
			loginFrame.dispose();
			MainFrame mainFrame = new MainFrame(client, nickName);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Couldn't log in please check your internet connection or use a valid login!", "Login error", JOptionPane.ERROR_MESSAGE);
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
