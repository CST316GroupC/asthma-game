package com.groupc.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.groupc.Patient;
import com.groupc.Runner;
import com.groupc.math.Resize;


public class DoctorScreen extends Screen
{
	//Variables
	boolean redraw     = true;
	Resize  resize     = new Resize(run);
	int     butPressed = 0; //0 is none, 1 is add patient, 2 is back and logout for now
	ArrayList<Patient> patients;
	
	//Display Elements
	JPanel  testBox          = new JPanel();
	JPanel  navBox           = new JPanel();
	JPanel  navBoxBorder     = new JPanel();
	JButton navBackButton    = new JButton("Back");
	JButton navLogoutButton  = new JButton("Logout");
	JButton navMuteButton    = new JButton("Mute");
	JLabel  pageTitle        = new JLabel("Doctor Page");
	JButton addPatientButton = new JButton("Add Patient");
	
	public DoctorScreen(Runner run) 
	{
		super(run);
		
		//Basic Frame Settings
		run.setTitle("Doctor");
		
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
		
		//Set fonts
		pageTitle.setFont(new Font("Serif", Font.BOLD, 25));
		
		////Buttons////
		
		navMuteButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 3;				
			}
		});
		
		// Add patient button listener
		addPatientButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 1;				
			}
		});
		
		// Add back button listener
		navBackButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 2;				
			}
		});
		
		
		// Add logout button listener
		navLogoutButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 2;				
			}
		});
		
		//add things to the panel
		this.add(pageTitle);
		this.add(addPatientButton);
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
			pageTitle.setBounds(resize.locationX(180), resize.locationY(55), resize.width(350), resize.height(40));
			pageTitle.setFont(new Font(pageTitle.getFont().getFontName(),pageTitle.getFont().getStyle(), resize.font(25)));
			
			//add patientButton
			addPatientButton.setBounds(resize.locationX(180), resize.locationY(200), resize.width(120), resize.height(40));
			addPatientButton.setFont(new Font(addPatientButton.getFont().getFontName(),addPatientButton.getFont().getStyle(), resize.font(12)));
			
			run.repaint();
			redraw = false;
		}
		
		if(butPressed == 1)
		{
			run.setScreen(new AccountCreationScreen(run));
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

}
