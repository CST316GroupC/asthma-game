package com.groupc.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import com.groupc.Runner;


public class AccountCreationScreen extends Screen
{
	int butPressed = 0;
	JLabel lblfname;
	JLabel lbllname;
	JLabel lblage;
	JLabel lblcinfo;
	JLabel preferredPassword;
	JLabel pageTitle;
	JPanel box;
	JPanel boxBorder;
	JButton back;
	JButton logout;
	JToggleButton mute;
	JTextField fname;
	JTextField password;
	JTextField lname;
	JTextField age;
	JTextArea cinfo;
	JButton submitButton;
	
	int type = 1;
	boolean played = true;
	
	public AccountCreationScreen(Runner run) {
		super(run);
		run.setTitle("Account Creation");
		run.setSize(run.SCR_WIDTH, run.SCR_HEIGHT);
		run.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		// Box display and border for buttons
		box = new JPanel();
		boxBorder = new JPanel();
		box.setBackground(Color.LIGHT_GRAY);
		boxBorder.setBackground(Color.BLACK);
						
		box.setBounds(18, 0, 450, 60);
		boxBorder.setBounds(17, 0, 452, 61);
				
				
		// Page title
		pageTitle = new JLabel("Account Creation Page");
		pageTitle.setFont(new Font("Serif", Font.BOLD, 25));
		pageTitle.setBounds(120, 60, 350, 40);
		
		lblfname = new JLabel("First Name*:");
		lblfname.setBounds(125, 150, 100, 20);
		
		fname = new JTextField();
		fname.setBounds(200, 150, 100, 20);
		
		lbllname = new JLabel("Last Name*:");
		lbllname.setBounds(125, 180, 100, 20);
		
		lname = new JTextField();
		lname.setBounds(200, 180, 100, 20);
		
		lblage = new JLabel("Age:");
		lblage.setBounds(125, 240, 100, 20);
		
		age = new JTextField();
		age.setBounds(200, 240, 100, 20);
		
		lblcinfo = new JLabel("Contact Info:");
		lblcinfo.setBounds(125, 270, 100, 20);
		
		cinfo = new JTextArea();
		cinfo.setBounds(200, 270, 100, 100);
		
		password = new JTextField();
		password.setBounds(200,210, 100, 20);
		
		preferredPassword = new JLabel("Password*");
		preferredPassword.setBounds(125, 210, 100, 20);
		
		submitButton = new JButton("Submit");
		submitButton.setSize(75, 20);
		submitButton.setLocation(200, 402);
		
		submitButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addCurrentPatient();				
			}
		});
		
		// Add back button
		back = new JButton("Back");
		back.setBounds(25, 14, 80, 35);
				
		// Add logout button
		logout = new JButton("Logout");
		logout.setBounds(380, 14, 80, 35);
				
		// Add mute button
		mute = new JToggleButton();
		mute.setSelectedIcon(new ImageIcon("UnMuteIcon.png"));
		mute.setIcon(new ImageIcon("MuteIcon.png"));
		mute.setBounds(340, 18, 30, 25);
				
				
		// Add back button listener
		back.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 1;				
			}
		});
				
		mute.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 3;				
			}
		});
				
		// Add logout button listener
		logout.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 2;				
			}
		});
				
				
		this.add(pageTitle);
		this.add(lblfname);
		this.add(lbllname);
		this.add(lblage);
		this.add(lblcinfo);
		this.add(fname);
		this.add(lname);
		this.add(age);
		this.add(cinfo);
		this.add(password);
		this.add(preferredPassword);
		this.add(submitButton);
		this.add(back);
		this.add(logout);
		this.add(mute);
		this.add(box);
		this.add(boxBorder);
		
		this.setLayout(null);		
		run.setContentPane(this);
		run.setVisible(true);
	}

	private void addCurrentPatient() 
	{
		if(fname.getText().isEmpty()  || lname.getText().isEmpty()  || password.getText().isEmpty())  //required basic information
		{
			JLabel error = new JLabel("Missing Information*");
			error.setSize(150, 20);
			error.setLocation(200, 380);
			this.add(error);
			run.repaint();
			
		}else  //assumes a database will be implemented but for now will just use a text file
		{
			try 
			{
				FileWriter fWriter = new FileWriter("login_information.txt", true);
				BufferedWriter bWriter = new BufferedWriter(fWriter);
				bWriter.write(fname.getText() + " | " + lname.getText() + " | " + age.getText() +
							  " | " + password.getText() + " | " + type + " | " + cinfo.getText() +
							  "\n"); //all patients created from doctor screen will be 0
				bWriter.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			run.setScreen(new LoginScreen(run));
		}
		
		
	}

	@Override
	public void update(float deltaTime) 
	{
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
			if(run.player.music.isPlaying() && played == true)
			{
				run.player.pauseMusic();
				played = false;
			}
			else
			{
				run.player.resume();
				played = true;
			}
		}
		butPressed = 0;
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

}
