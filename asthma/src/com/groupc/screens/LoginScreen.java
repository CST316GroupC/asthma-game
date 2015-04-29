package com.groupc.screens;

/*
 * Author(s):		Team C
 * Course: 			CST 316 Spring
 * Instructor:		Dr. Gary
 * Date Changed:	3/29/2015
 * 
 * Description:		LoginScreen is the first page the patient/doctor/parent will see when they first open the software.
 * 					Just a simple login screen that ask for the user's email and password.
 * 					Additional feature allows user to remember their password as well as retrieve their forgotten password.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import com.groupc.Database;
import com.groupc.Runner;
import com.groupc.math.Resize;


public class LoginScreen extends Screen
{
	//Variables
	boolean redraw						= true;
	boolean loginErrorDrawn				= false;
	boolean elementMoved				= false;
	boolean mutePressed					= false;
	boolean played 					  	= true;
	int 	type						= 0;
	Resize 	resize						= new Resize(run);
	
	Date date = new Date();
	
	//Display Elements
	
	JPanel loginBox 				= new JPanel();
	
	JPasswordField passwordTF		= new JPasswordField();
	
	JTextField userNameTF			= new JTextField();
		
	//Labels
	JLabel title					= new JLabel("Team C's Asthma Game",SwingConstants.CENTER);
	JLabel userNameLabel			= new JLabel("Email:");
	JLabel passwordLabel			= new JLabel("Password:");
	JLabel passRetrievalLabel		= new JLabel("");
	JLabel loginErrorMessage		= new JLabel("Incorrect Username/Password");
	JLabel background				= new JLabel();
	
	//Buttons
	JRadioButton saveLoginRadio			= new JRadioButton("Remember Password");
	JButton	loginButton					= new JButton("Login");
	JButton	passRetrievalButton			= new JButton("Forgot Password?");
	JToggleButton muteButton     		= new JToggleButton();
	
	private ImageIcon 	backgroundImage 	= new ImageIcon("resources/interface/BackgroundArt.png");
	private ImageIcon 	muteOffIcon 		= new ImageIcon("resources/interface/UnMuteIcon.png");
	private ImageIcon 	muteOnIcon  		= new ImageIcon("resources/interface/MuteIcon.png");
	
	public LoginScreen(Runner run) 
	{
		super(run);
		
		//unload user
		run.setUserName("null");
		
		//Basic Frame Settings
		run.setTitle("Login");
		
		//resize stuff
		run.addComponentListener(new ComponentAdapter()
		{
			@Override
			public void componentResized(ComponentEvent e)
			{
				redraw = true;
			}
		});
		
		
		//Set colors
		this.setBackground(Color.WHITE);
		loginBox.setBackground(Color.LIGHT_GRAY);
		saveLoginRadio.setBackground(Color.LIGHT_GRAY);
		loginErrorMessage.setForeground(Color.RED);
		title.setOpaque(true);
		title.setBackground(Color.WHITE);
		
		//Set fonts
		title.setFont(new Font("Serif", Font.BOLD, 40));	
		
		muteButton.setToolTipText("Toggle Sound");
		
		////Buttons////
		//Test if Login Button is pushed
		loginButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					checkLogin();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}			
			}
		});	
		
		//Enter Key is Pressed
		userNameTF.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				userNameTF.transferFocus();
			}
		});
		
		passwordTF.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					checkLogin();
				} 
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		muteButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mutePressed = true;				
			}
		});
		
		loginErrorMessage.setVisible(false);
		
		//add things to the panel
		this.add(title);
		this.add(muteButton);
		this.add(userNameLabel);
		this.add(userNameTF);
		this.add(passwordLabel);
		this.add(passwordTF);
		//this.add(saveLoginRadio);
		this.add(loginButton);
		//this.add(passRetrievalLabel);
		//this.add(passRetrievalButton);
		this.add(loginErrorMessage);
		this.add(loginBox);
		this.add(background);
		
		this.setLayout(null);
		run.setContentPane(this);
		run.setVisible(true);
		
		//Mute on/off
		if(run.player.getPausedMusic())
		{
			muteButton.setSelected(true);
		}
		else
		{
			muteButton.setSelected(false);
		}
	}
	
	private boolean hasNotTakenReadings(String user)
	{
		String line = null;
		String tempString = date.toString();
		String currentDay = null;
		
		Vector<String> emails = new Vector<String>();
		Vector<String> days = new Vector<String>();
		StringTokenizer st;
		
		try
		{
			FileReader fr = new FileReader("spirometer_readings.txt");
			BufferedReader br = new BufferedReader(fr);
			
			
			while((line = br.readLine()) != null)
			{
				st = new StringTokenizer(line, " | ");
				emails.add(st.nextToken()); //user
				st.nextToken(); //volume
				st.nextToken(); //force
				st.nextToken(); //day of week
				st.nextToken(); //month
				days.add(st.nextToken()); //day 
				st.nextToken(); //time
				st.nextToken(); //timezone
				st.nextToken(); //year
				
				
			}
			br.close();
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
		st = new StringTokenizer(tempString);
		while(st.hasMoreElements())
		{
			st.nextToken(); // day of week
			st.nextToken(); // month
			currentDay = st.nextToken(); // day
			st.nextToken(); // time
			st.nextToken(); // timezone
			st.nextToken(); // year
		}
		for(int i = 0; i < emails.size(); i++)
		{
			if(emails.elementAt(i).equals(run.getUserName()))
			{		
				if(days.elementAt(i).equals(currentDay))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	private void newProperties()
	{

		try
		{
			FileInputStream in = new FileInputStream("resources/interface/parent_controls/" + run.getUserName() + ".properties");
			in.close();
				
			
		}catch(Exception e)
		{
			Properties properties = new Properties();
			properties.setProperty("PIN", "9999"); //default settings
			properties.setProperty("Time", "false");
			properties.setProperty("Game_1", "true");
			properties.setProperty("Game_2", "true");
			properties.setProperty("Game_3", "true");
			properties.setProperty("Game_4", "true");
			properties.setProperty("Email_Alerts", "false");
			properties.setProperty("Email", userNameTF.getText());
			System.out.println("failed to read file");
			
			try
			{
				FileWriter write = new FileWriter("resources/interface/parent_controls/" + run.getUserName() + ".properties");
				properties.store(write, "User:" + userNameTF.getText());
				write.close();
			}catch(Exception ex)
			{
				System.out.println("Failed new properties");
			}
			
		}
	}

	@Override
	public void update(float deltaTime)
	{
		if(redraw)
		{
			//title
			title.setBounds(resize.locationX(0), resize.locationY(80), resize.width(500), resize.height(50));
			title.setFont(new Font(loginButton.getFont().getFontName(),loginButton.getFont().getStyle(), resize.font(40)));
			title.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			
			//MuteButton
			muteButton.setBounds(resize.locationX(460), resize.locationY(10), resize.width(30), resize.height(30));
			muteButton.setFont(new Font(muteButton.getFont().getFontName(),muteButton.getFont().getStyle(), resize.font(12)));
			muteButton.setIcon(new ImageIcon(muteOffIcon.getImage().getScaledInstance(resize.width(30), resize.height(30), java.awt.Image.SCALE_SMOOTH)));
			muteButton.setSelectedIcon(new ImageIcon(muteOnIcon.getImage().getScaledInstance(resize.width(30), resize.height(30), java.awt.Image.SCALE_SMOOTH)));
			
			//Background
			background.setBounds(resize.locationX(0), resize.locationY(0), resize.width(500), resize.height(500));
			background.setIcon(new ImageIcon(backgroundImage.getImage().getScaledInstance(resize.width(500), resize.height(500), java.awt.Image.SCALE_SMOOTH)));
			
			//loginBox
			loginBox.setBounds(resize.locationX(100), resize.locationY(200), resize.width(300), resize.height(175));
			loginBox.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			
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
		//mute
		if(mutePressed)
		{
			if(muteButton.getSelectedObjects() != null)
			{
				played = !run.player.pauseMusic();
			}
			else
			{
				played = run.player.resume();
			}
		}
		mutePressed = false;	
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
		// TODO Auto-generated method stub
	}
	
	//Private Methods

	public void checkLogin() throws IOException
	{
		if(Database.getDoctor(userNameTF.getText(), passwordTF.getText()))
		{
			if(userNameTF.getText().indexOf(".com") == -1)
			{
				run.setUserName(userNameTF.getText());
			}
			else
			{
				run.setUserName(userNameTF.getText().substring(0, userNameTF.getText().indexOf(".com")));
			}
			DoctorScreen ds = new DoctorScreen(run);
			ds.getPatients();
			run.setScreen(ds);
		}
		else if(Database.getPatient(userNameTF.getText(), passwordTF.getText()))
		{
			if(userNameTF.getText().indexOf(".com") == -1)
			{
				run.setUserName(userNameTF.getText());
			}
			else
			{
				run.setUserName(userNameTF.getText().substring(0, userNameTF.getText().indexOf(".com")));
			}
			newProperties();
			if(hasNotTakenReadings(run.getUserName()))
			{
				TutorialScreen ts = new TutorialScreen(run);
				run.setScreen(ts);
			} 
			else 
			{
				GameHubScreen ghs = new GameHubScreen(run);
				run.setScreen(ghs);
			}
			
		}
		else
		{
			String line = null;
			char[][] passWords = new char[80][30];  //need to make dynamic otherwise only 20 patients allowed!!
			int counter = 0;
			String tempString;
			char tempChar;
			//can create a patients' doctor with new text file
			
			Vector<String> types = new Vector<String>();
			Vector<String> emails = new Vector<String>();
			
			
			FileReader fr = new FileReader("login_information.txt");  //maybe create an 'onStart()' function for runner
			BufferedReader br = new BufferedReader(fr);
			StringTokenizer st;
			
			
			while((line = br.readLine()) != null)
			{
				st = new StringTokenizer(line, " | ");
				emails.add(counter, st.nextToken());
				st.nextToken();  //first name
				st.nextToken();  //last name
				st.nextToken();  //dob
				tempString = st.nextToken();  //password
				types.add(counter, st.nextToken()); //type
				for(int i = 0; i < tempString.length(); ++i)
				{
					 passWords[counter][i] = tempString.charAt(i);
	
				}
				counter += 1;
			}
			br.close();
			
			for(int i = 0; i < emails.size(); ++i)
			{
				tempString = new String(passWords[i]);
				tempString = tempString.trim();
				passWords[i] = tempString.toCharArray();
				
				if(userNameTF.getText().equals(emails.elementAt(i)) && Arrays.equals(passwordTF.getPassword(), passWords[i]))
				{
					//Use variable type at the top to switch between doctor login and patient login
					//Doctors
					if(types.elementAt(i).equals("0"))
					{
						if(userNameTF.getText().indexOf(".com") == -1)
	
						{
							run.setUserName(userNameTF.getText());
						}
						else
						{
							run.setUserName(userNameTF.getText().substring(0, userNameTF.getText().indexOf(".com")));
						}
						DoctorScreen ds = new DoctorScreen(run);
						ds.getPatients();
						run.setScreen(ds);
					}
					else
					{//Patients
						if(types.elementAt(i).equals("1"))
						{
						
							if(userNameTF.getText().indexOf(".com") == -1)
	
							{
								newProperties();
								run.setUserName(userNameTF.getText());
	
							}
							else
							{
								run.setUserName(userNameTF.getText().substring(0, userNameTF.getText().indexOf(".com")));
							}
							newProperties();
							if(hasNotTakenReadings(run.getUserName()))
							{
								TutorialScreen ts = new TutorialScreen(run);
								run.setScreen(ts);
							} 
							else 
							{
								GameHubScreen ghs = new GameHubScreen(run);
								run.setScreen(ghs);
							}
						}
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
}
