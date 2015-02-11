/*
 * LoginScreen.java
 * displays and runs elements for the login screen
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

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
	int type = 0;
	boolean loginErrorDrawn = false;
	boolean redraw = true;
	boolean elementMoved = false;
	Resize resize = new Resize(run);
	
	//Display Elements
	JPanel loginPanel = new JPanel();
	JPanel testBox = new JPanel();
	JLabel title = new JLabel("Team C's Asthma Game");
	JPanel loginBox = new JPanel();
	JPanel loginBoxBorder = new JPanel();
	JLabel userNameLabel = new JLabel("Username");
	JTextField userNameTF = new JTextField();
	JLabel passwordLabel = new JLabel("Password");
	JPasswordField passwordTF = new JPasswordField();
	JRadioButton saveLoginRadio = new JRadioButton("Remember Password");
	JButton loginButton = new JButton("Login");
	JLabel passRetrievalLabel = new JLabel("");
	JButton passRetrievalButton = new JButton("Forgot Password?");
	JLabel loginErrorMessage = new JLabel("Incorrect Username/Password");
	
	public LoginScreen(Runner run) 
	{
		super(run);
		
		//Basic Frame Settings
		run.setTitle("Login");
		run.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		run.setMinimumSize(new Dimension(run.SCR_WIDTH, run.SCR_HEIGHT));
		
		//resize stuff
		run.addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent e)
			{
				redraw = true;
			}
		});
		
		
		//Set colors
		loginPanel.setBackground(Color.LIGHT_GRAY);
		testBox.setBackground(Color.WHITE);
		loginBox.setBackground(Color.LIGHT_GRAY);
		loginBoxBorder.setBackground(Color.BLACK);
		saveLoginRadio.setBackground(Color.LIGHT_GRAY);
		
		//Set fonts
		title.setFont(new Font("Serif", Font.BOLD, 40));
		loginErrorMessage.setForeground(Color.RED);
				
		
		
		//Test if Login Button is pushed
		loginButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try {
					checkLogin();
				} catch (IOException e1) {
					e1.printStackTrace();
				}			
			}
		});	
		
		//Enter Key is Pressed
		userNameTF.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				userNameTF.transferFocus();
			}
		});
		
		passwordTF.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					checkLogin();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		loginErrorMessage.setVisible(false);
		
		//add things to the panel
		loginPanel.add(title);
		loginPanel.add(userNameLabel);
		loginPanel.add(userNameTF);
		loginPanel.add(passwordLabel);
		loginPanel.add(passwordTF);
		loginPanel.add(saveLoginRadio);
		loginPanel.add(loginButton);
		loginPanel.add(passRetrievalLabel);
		loginPanel.add(passRetrievalButton);
		loginPanel.add(loginErrorMessage);
		loginPanel.add(loginBox);
		loginPanel.add(loginBoxBorder);
		loginPanel.add(testBox);
		loginPanel.setLayout(null);
		run.setContentPane(loginPanel);
		run.setVisible(true);
		
		
		//music stuff
		run.player.loadSong("AMemoryAway.ogg");
		run.player.playMusic(true);
	}

	@Override
	public void update(float deltaTime)
	{
		if(redraw)
		{
			//test Box
			testBox.setBounds(resize.locationX(0), resize.locationY(0), resize.width(500), resize.height(500));
			
			//title
			title.setBounds(resize.locationX(25), resize.locationY(80), resize.width(500), resize.height(50));
			title.setFont(new Font(loginButton.getFont().getFontName(),loginButton.getFont().getStyle(), resize.font(40)));
			
			//loginBox
			loginBox.setBounds(resize.locationX(100), resize.locationY(200), resize.width(300), resize.height(200));
			
			//loginBoxBorder
			loginBoxBorder.setBounds(resize.locationX(99), resize.locationY(199), resize.width(302), resize.height(202));
			
			//UserName
			userNameLabel.setBounds(resize.locationX(170), resize.locationY(220), resize.width(160), resize.height(20));
			userNameLabel.setFont(new Font(loginButton.getFont().getFontName(),loginButton.getFont().getStyle(), resize.font(12)));
			userNameTF.setBounds(resize.locationX(170), resize.locationY(240), resize.width(160), resize.height(20));
			userNameTF.setFont(new Font(loginButton.getFont().getFontName(),loginButton.getFont().getStyle(), resize.font(12)));
			
			//Password
			passwordLabel.setBounds(resize.locationX(170), resize.locationY(260), resize.width(160), resize.height(20));
			passwordLabel.setFont(new Font(loginButton.getFont().getFontName(),loginButton.getFont().getStyle(), resize.font(12)));
			passwordTF.setBounds(resize.locationX(170), resize.locationY(280), resize.width(160), resize.height(20));
			passwordTF.setFont(new Font(loginButton.getFont().getFontName(),loginButton.getFont().getStyle(), resize.font(12)));
			
			//Radio			
			saveLoginRadio.setBounds(resize.locationX(170), resize.locationY(300), resize.width(160), resize.height(20));		
			saveLoginRadio.setFont(new Font(saveLoginRadio.getFont().getFontName(), saveLoginRadio.getFont().getStyle(), resize.font(12)));
			
			//loginButton
			loginButton.setBounds(resize.locationX(170), resize.locationY(320), resize.width(160), resize.height(30));
			loginButton.setFont(new Font(loginButton.getFont().getFontName(),loginButton.getFont().getStyle(), resize.font(12)));
			
			//Password Retrieval
			passRetrievalLabel.setBounds(resize.locationX(190), resize.locationY(370), resize.width(120), resize.height(20));
			passRetrievalLabel.setFont(new Font(loginButton.getFont().getFontName(),loginButton.getFont().getStyle(), resize.font(12)));
			passRetrievalButton.setBounds(resize.locationX(170), resize.locationY(360), resize.width(160), resize.height(20));
			passRetrievalButton.setFont(new Font(loginButton.getFont().getFontName(),loginButton.getFont().getStyle(), resize.font(12)));
			
			//login Error Message
			loginErrorMessage.setBounds(resize.locationX(160), resize.locationY(200), resize.width(200), resize.height(20));
			loginErrorMessage.setFont(new Font(loginButton.getFont().getFontName(),loginButton.getFont().getStyle(), resize.font(12)));
			
			run.repaint();
			redraw = false;
		}
	}

	@Override
	public void present(float deltaTime) 
	{
		
		
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
	public void checkLogin() throws IOException
	{
		String line = null;
		String[] firstNames = new String [10];
		char[][] passWords = new char[10][30];
		int counter = 0;
		String tempString;
		char tempChar;
		
		FileReader fr = new FileReader("login_information.txt");  //maybe create an 'onStart()' function for runner
		BufferedReader br = new BufferedReader(fr);
		StringTokenizer st;
		
		
		while((line = br.readLine()) != null)
		{
			st = new StringTokenizer(line, " | ");
			firstNames[counter] = st.nextToken();
			st.nextToken();
			st.nextToken();
			tempString = st.nextToken();
			for(int i = 0; i < tempString.length(); ++i)
			{
				 passWords[counter][i] = tempString.charAt(i);
			}
			counter += 1;
		}
		br.close();
		for(int i = 0; i < 10; ++i)
		{
			tempString = new String (passWords[i]);
			tempString = tempString.trim();
			passWords[i] = tempString.toCharArray();
			
			if(userNameTF.getText().equals(firstNames[i]) && Arrays.equals(passwordTF.getPassword(),passWords[i]))
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
		
			if(!loginErrorDrawn)
			{
				loginErrorMessage.setVisible(true);
				run.repaint();
				loginErrorDrawn = true;
			}
		}
	}
	
}
