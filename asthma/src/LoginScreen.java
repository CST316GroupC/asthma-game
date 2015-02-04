/*
 * LoginScreen.java
 * displays and runs elements for the login screen
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class LoginScreen extends Screen
{
	//Variables
	String testUser = "test";
	char[] testPass = {'p','a','s','s'};
	int type = 0;
	boolean loginErrorDrawn = false;
	
	//Display Elements
	JPanel loginPanel;
	JTextField userNameTF;
	JPasswordField passwordTF;
	JLabel loginErrorMessage = new JLabel("Incorrect Username/Password");
	JRadioButton saveLoginRadio;

	
	public LoginScreen(Runner run) 
	{
		super(run);
		// Set layout manager
				
		//Basic Frame Settings
		run.frame.setTitle("Login");
		run.frame.setSize(run.SCR_WIDTH, run.SCR_HEIGHT);
		run.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		loginPanel = new JPanel();
		//loginPanel.setLayout(new BorderLayout());
		loginPanel.setBorder(BorderFactory.createTitledBorder("Login"));
		
		////Set up display properties for elements
		//UserName
		JLabel userNameLabel = new JLabel("Username");
		userNameLabel.setSize(100, 20);
		userNameLabel.setLocation(200, 200);
		
		userNameTF = new JTextField();
		userNameTF.setSize(100, 20);
		userNameTF.setLocation(200, 220);
		
		//Password
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setSize(100, 20);
		passwordLabel.setLocation(200, 240);
		
		passwordTF = new JPasswordField();
		passwordTF.setSize(100, 20);
		passwordTF.setLocation(200, 260);
		
		//Radio
		saveLoginRadio = new JRadioButton("Remember Password");
		saveLoginRadio.setSize(200,20);
		saveLoginRadio.setLocation(200, 280);
		
		//Login Button
		JButton loginButton = new JButton("Login");
		loginButton.setSize(100, 20);
		loginButton.setLocation(200, 300);
		
		//Test if Login Button is pushed
		loginButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				checkLogin();			
			}
		});		
		
		//add things to the panel
		loginPanel.add(userNameLabel);
		loginPanel.add(userNameTF);
		loginPanel.add(passwordLabel);
		loginPanel.add(passwordTF);
		loginPanel.add(saveLoginRadio);
		loginPanel.add(loginButton);
		loginPanel.setLayout(null);
		run.frame.setContentPane(loginPanel);
		run.frame.setVisible(true);
	}

	@Override
	public void update(float deltaTime)
	{

		// TODO Auto-generated method stub

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
		if(userNameTF.getText().equals(testUser) && Arrays.equals(passwordTF.getPassword(), testPass))
		{
			//Use variable type at the top to switch between doctor login and patient login
			//Doctors
			if(type == 0)
			{
				run.setScreen(new DoctorScreen(run));
			}
			//Patients
			if(type == 1)
			{
				run.setScreen(new TutorialScreen(run));
			}
		}
		else
		{
			if(!loginErrorDrawn)
			{
				loginErrorMessage.setSize(200, 20);
				loginErrorMessage.setLocation(150, 180);
				loginPanel.add(loginErrorMessage);
				run.frame.repaint();
				loginErrorDrawn = true;
			}
		}
	}
}
