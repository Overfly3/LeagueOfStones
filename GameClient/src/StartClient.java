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
		MainFrame frame = new MainFrame("League Of Stones");

		//Optional: What happens when the frame closes?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton uiButtonForLogin = new JButton("Login");
		
		//Create components and put them in the frame.
		//...create emptyLabel...
		frame.getContentPane().add(uiButtonForLogin, BorderLayout.PAGE_END);
		
		JTextField uiTextFieldForLogin = new JTextField();
		
		//Add action listener to button
		uiButtonForLogin.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                login(uiTextFieldForLogin.getText());
            }
        });   
		
		frame.getContentPane().add(uiTextFieldForLogin, BorderLayout.PAGE_START);
		
		//Size the frame.
		frame.pack();

		//Show it.
		frame.setVisible(true);
	}

	private static void login(String nickName) {
		GameClient client = new GameClient("localhost");
		client.start();

		Packet00Login loginPacket = new Packet00Login(nickName);
		loginPacket.writeData(client);
		
		

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
			//create mainwindow
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
