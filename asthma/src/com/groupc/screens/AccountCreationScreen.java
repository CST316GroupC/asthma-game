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

import javax.swing.BorderFactory;
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
	NavigationBar navBar       = new NavigationBar(run,true,false,"Account Creation Page");
	JPanel     pageBox         = new JPanel();
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
		this.setBackground(Color.WHITE);
		pageBox.setBackground(Color.LIGHT_GRAY);
		errorMessage.setForeground(Color.RED);
		
		//Set fonts
		
		////Buttons////
		
		submitButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addCurrentPatient();				
			}
		});
		
		errorMessage.setVisible(false);
		
		//add things to the panel
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
		this.add(navBar);
		
		this.setLayout(null);		
		run.setContentPane(this);
		run.setVisible(true);
	}

	

	@Override
	public void update(float deltaTime) 
	{
		if(redraw)
		{	
			//navBar
			navBar.redrawUpdate();
			
			//pageBox
			pageBox.setBounds(resize.locationX(80), resize.locationY(100), resize.width(340), resize.height(360));
			pageBox.setBorder(BorderFactory.createLineBorder(Color.black, resize.height(1)));
			
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
		if(navBar.backButtonPressed)
		{
			run.setScreen(new DoctorScreen(run));
		}
		navBar.update();
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
