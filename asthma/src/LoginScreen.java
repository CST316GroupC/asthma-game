/*
 * LoginScreen.java
 * displays and runs elements for the login screen
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginScreen extends Screen
{
	//Variables
	private String testUser = "test";
	private char[] testPass = {'p','a','s','s'};
	
	private int loginButtonPressed = 0; //0 is none, 1 is login
	
	private JTextField userNameTF;
	private JPasswordField passwordTF;
	private JPanel loginPanel;
	
	public LoginScreen(Runner run) 
	{
		super(run);
		run.frame.setTitle("Login");
		run.frame.setSize(run.SCR_WIDTH, run.SCR_HEIGHT);
		run.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		loginPanel = new JPanel();
		
		JLabel userNameLabel = new JLabel("Username");
		userNameLabel.setSize(100, 20);
		userNameLabel.setLocation(200, 200);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setSize(100, 20);
		passwordLabel.setLocation(200, 240);
		
		userNameTF = new JTextField();
		userNameTF.setSize(100, 20);
		userNameTF.setLocation(200, 220);
		
		
		passwordTF = new JPasswordField();
		passwordTF.setSize(100, 20);
		passwordTF.setLocation(200, 260);
		
		JButton loginButton = new JButton("Login");
		loginButton.setSize(75, 20);
		loginButton.setLocation(225, 280);
		
		loginButton.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				checkLogin();
				//loginButtonPressed = 1;				
			}
		});
		
		loginPanel.add(userNameLabel);
		loginPanel.add(passwordLabel);
		loginPanel.add(userNameTF);
		loginPanel.add(passwordTF);
		loginPanel.add(loginButton);
		loginPanel.setLayout(null);		
		run.frame.setContentPane(loginPanel);
		run.frame.setVisible(true);
	}

	@Override
	public void update(float deltaTime)
	{
		//checkLogin();
	}

	@Override
	public void present(float deltaTime) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() 
	{
		
	}
	
	//Private Methods
	public void checkLogin()
	{
		if(userNameTF.equals(testUser) && Arrays.equals(passwordTF.getPassword(), testPass))
		{
			run.setScreen(new TutorialScreen(run));
		}
		else
		{
			JLabel loginErrorMessage = new JLabel("Incorrect Username/Password");
			loginErrorMessage.setSize(150, 20);
			loginErrorMessage.setLocation(200, 180);
			loginPanel.add(loginErrorMessage);
			run.frame.repaint();
		}
	}
}
