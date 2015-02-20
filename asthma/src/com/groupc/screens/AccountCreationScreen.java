package com.groupc.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.groupc.Runner;
import com.groupc.math.Resize;


public class AccountCreationScreen extends Screen
{
	//Variables
	boolean redraw     = true;
	Resize  resize     = new Resize(run);
	int     butPressed = 0;
	int     type       = 1;
	
	//Display Elements
	JPanel     testBox         = new JPanel();
	JPanel     navBox          = new JPanel();
	JPanel     navBoxBorder    = new JPanel();
	JPanel     pageBox         = new JPanel();
	JButton    navBackButton   = new JButton("Back");
	JButton    navLogoutButton = new JButton("Logout");
	JButton    navMuteButton   = new JButton("Mute");
	JLabel     pageTitle       = new JLabel("Account Creation Page",SwingConstants.CENTER);
	JLabel     firstNameLabel  = new JLabel("First Name*:",SwingConstants.RIGHT);
	JLabel     lastNameLabel   = new JLabel("Last Name*:",SwingConstants.RIGHT);
	JLabel     ageLabel        = new JLabel("Age:",SwingConstants.RIGHT);
	JLabel     infoLabel       = new JLabel("Contact Info:",SwingConstants.RIGHT);
	JLabel     passwordLabel   = new JLabel("Password*:",SwingConstants.RIGHT);
	JTextField firstNameTF     = new JTextField();
	JTextField passwordTF      = new JTextField();
	JTextField lastNameTF      = new JTextField();
	JTextField ageTF           = new JTextField();
	JTextArea  infoTA          = new JTextArea();
	JButton    submitButton    = new JButton("Submit");
	JLabel     errorMessage    = new JLabel("Missing Information*",SwingConstants.CENTER);
	
	public AccountCreationScreen(Runner run) 
	{
		super(run);
		
		run.setTitle("Account Creation");
		
		//Basic Frame Settings
		run.setTitle("Account Creation");
		
		//resize stuff
		run.addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent e)
			{
				redraw = true;
			}
		});
		
		//Set colors
		this.setBackground(Color.LIGHT_GRAY);
		testBox.setBackground(Color.WHITE);
		navBox.setBackground(Color.LIGHT_GRAY);
		navBoxBorder.setBackground(Color.BLACK);
		pageBox.setBackground(Color.LIGHT_GRAY);
		errorMessage.setForeground(Color.RED);
		
		//Set fonts
		pageTitle.setFont(new Font("Serif", Font.BOLD, 25));
		
		////Buttons////
		
		submitButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addCurrentPatient();				
			}
		});

				
		//navBackButton listener
		navBackButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 1;				
			}
		});
		
		//navLogoutButton listener
		navLogoutButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 2;				
			}
		});
		
		//navMuteButton listener
		navMuteButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 3;				
			}
		});
		
		errorMessage.setVisible(false);
		
		//add things to the panel
		this.add(pageTitle);
		this.add(firstNameLabel);
		this.add(firstNameTF);
		this.add(lastNameLabel);
		this.add(lastNameTF);
		this.add(ageLabel);
		this.add(ageTF);
		this.add(passwordLabel);
		this.add(passwordTF);
		this.add(infoLabel);
		this.add(infoTA);
		this.add(errorMessage);
		this.add(submitButton);
		this.add(pageBox);
		this.add(navBackButton);
		this.add(navLogoutButton);
		this.add(navMuteButton);
		this.add(navBox);
		//this.add(navBoxBorder);
		this.add(testBox);
		
		this.setLayout(null);		
		run.setContentPane(this);
		run.setVisible(true);
	}

	

	@Override
	public void update(float deltaTime) 
	{
		if(redraw)
		{	
			//test Box
			testBox.setBounds(resize.locationX(0), resize.locationY(0), resize.width(500), resize.height(500));
			
			////Nav Box Elements////
			navBox.setBounds(resize.locationX(20), resize.locationY(0), resize.width(460), resize.height(50));
			navBoxBorder.setBounds(resize.locationX(17), resize.locationY(0), resize.width(452), resize.height(61));
			
			navLogoutButton.setBounds(resize.locationX(390), resize.locationY(10), resize.width(80), resize.height(30));
			navLogoutButton.setFont(new Font(navLogoutButton.getFont().getFontName(),navLogoutButton.getFont().getStyle(), resize.font(12)));
			
			navMuteButton.setBounds(resize.locationX(300), resize.locationY(10), resize.width(80), resize.height(30));
			navMuteButton.setFont(new Font(navMuteButton.getFont().getFontName(),navMuteButton.getFont().getStyle(), resize.font(12)));
			
			navBackButton.setBounds(resize.locationX(30), resize.locationY(10), resize.width(80), resize.height(30));
			navBackButton.setFont(new Font(navBackButton.getFont().getFontName(),navBackButton.getFont().getStyle(), resize.font(12)));
			
			//pageTitle
			pageTitle.setBounds(resize.locationX(0), resize.locationY(55), resize.width(500), resize.height(40));
			pageTitle.setFont(new Font(pageTitle.getFont().getFontName(),pageTitle.getFont().getStyle(), resize.font(25)));
			
			//pageBox
			pageBox.setBounds(resize.locationX(80), resize.locationY(100), resize.width(340), resize.height(360));
			
			//firstName
			firstNameLabel.setBounds(resize.locationX(80), resize.locationY(150), resize.width(165), resize.height(20));
			firstNameLabel.setFont(new Font(firstNameLabel.getFont().getFontName(),firstNameLabel.getFont().getStyle(), resize.font(12)));
			firstNameTF.setBounds(resize.locationX(255), resize.locationY(150), resize.width(100), resize.height(20));
			firstNameTF.setFont(new Font(firstNameTF.getFont().getFontName(),firstNameTF.getFont().getStyle(), resize.font(12)));
			
			//lastName
			lastNameLabel.setBounds(resize.locationX(80), resize.locationY(180), resize.width(165), resize.height(20));
			lastNameLabel.setFont(new Font(lastNameLabel.getFont().getFontName(),lastNameLabel.getFont().getStyle(), resize.font(12)));
			lastNameTF.setBounds(resize.locationX(255), resize.locationY(180), resize.width(100), resize.height(20));
			lastNameTF.setFont(new Font(lastNameTF.getFont().getFontName(),lastNameTF.getFont().getStyle(), resize.font(12)));
			
			//age
			ageLabel.setBounds(resize.locationX(80), resize.locationY(240), resize.width(165), resize.height(20));
			ageLabel.setFont(new Font(ageLabel.getFont().getFontName(),ageLabel.getFont().getStyle(), resize.font(12)));
			ageTF.setBounds(resize.locationX(255), resize.locationY(240), resize.width(100), resize.height(20));
			ageTF.setFont(new Font(ageTF.getFont().getFontName(),ageTF.getFont().getStyle(), resize.font(12)));
			
			//password
			passwordLabel.setBounds(resize.locationX(80), resize.locationY(210), resize.width(165), resize.height(20));
			passwordLabel.setFont(new Font(passwordLabel.getFont().getFontName(),passwordLabel.getFont().getStyle(), resize.font(12)));
			passwordTF.setBounds(resize.locationX(255), resize.locationY(210), resize.width(100), resize.height(20));
			passwordTF.setFont(new Font(passwordTF.getFont().getFontName(),passwordTF.getFont().getStyle(), resize.font(12)));
			
			//info
			infoLabel.setBounds(resize.locationX(80), resize.locationY(270), resize.width(165), resize.height(20));
			infoLabel.setFont(new Font(infoLabel.getFont().getFontName(),infoLabel.getFont().getStyle(), resize.font(12)));
			infoTA.setBounds(resize.locationX(255), resize.locationY(270), resize.width(100), resize.height(100));
			infoTA.setFont(new Font(infoTA.getFont().getFontName(),infoTA.getFont().getStyle(), resize.font(12)));
			
			//submitButton
			submitButton.setBounds(resize.locationX(200), resize.locationY(400), resize.width(100), resize.height(30));
			submitButton.setFont(new Font(submitButton.getFont().getFontName(),submitButton.getFont().getStyle(), resize.font(12)));
			
			//errorMessage
			errorMessage.setBounds(resize.locationX(200), resize.locationY(380), resize.width(150), resize.height(20));
			errorMessage.setFont(new Font(errorMessage.getFont().getFontName(),errorMessage.getFont().getStyle(), resize.font(12)));
			
			run.repaint();
			redraw = false;
		}
		if(butPressed == 1)
		{
			run.setScreen(new DoctorScreen(run));
		}
		else if(butPressed == 2)
		{
			run.setScreen(new LoginScreen(run));
		}
		else if(butPressed == 3)
		{
			if(run.player.music.isPlaying())
			{
				run.player.pauseMusic();
			}
			else
			{
				run.player.resume();
				
			}
			butPressed = 0;
		}
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	private void addCurrentPatient() 
	{
		if(firstNameTF.getText().isEmpty()  || lastNameTF.getText().isEmpty()  || passwordTF.getText().isEmpty())  //required basic information
		{
			errorMessage.setVisible(true);
			run.repaint();
			
		}else  //assumes a database will be implemented but for now will just use a text file
		{
			try 
			{
				FileWriter fWriter = new FileWriter("login_information.txt", true);
				BufferedWriter bWriter = new BufferedWriter(fWriter);
				bWriter.write(firstNameTF.getText() + " | " + lastNameTF.getText() + " | " + ageTF.getText() +
							  " | " + passwordTF.getText() + " | " + type + " | " + infoTA.getText() +
							  "\n"); //all patients created from doctor screen will be 0
				bWriter.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			run.setScreen(new LoginScreen(run));
		}
		
		
	}
}
