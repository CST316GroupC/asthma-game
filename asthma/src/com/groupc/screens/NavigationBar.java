package com.groupc.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.groupc.Runner;
import com.groupc.math.Resize;


public class NavigationBar extends JPanel 
{
	//Variables
	private Runner run;
	private Resize resize;
	private int    buttonPressed      = 0; //0 is none, 1 is add patient, 2 is back and logout for now
	public  boolean backButtonPressed = false;
	
	//Display Elements
	JPanel  navBox               = new JPanel();
	JButton backButton           = new JButton("Back");
	JButton logoutButton         = new JButton("Logout");
	JButton muteButton           = new JButton("Mute");
	JButton parentControlsButton = new JButton("Parental Controls");
	JLabel  pageTitle            = new JLabel("",SwingConstants.CENTER);
	
	public NavigationBar(Runner run, boolean backOn, boolean parentControlsOn, String title) 
	{
		this.run = run;
		resize   = new Resize(run);
		
		//set settings
		if(backOn == false)
		{
			backButton.setVisible(false);
		}
		if(parentControlsOn == false)
		{
			parentControlsButton.setVisible(false);
		}
		pageTitle.setText(title);
		
		//Set colors
		this.setOpaque(false);
		navBox.setBackground(Color.LIGHT_GRAY);
		
		//Set fonts
		pageTitle.setFont(new Font("Serif", Font.BOLD, 25));
		
		////Buttons////
		//logoutButton
		logoutButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				buttonPressed = 1;				
			}
		});
		//muteButton
		muteButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				buttonPressed = 2;				
			}
		});
		//parentControlsButton
		parentControlsButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				buttonPressed = 3;				
			}
		});
		//backButton
		backButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				backButtonPressed = true;				
			}
		});
		
		//add things to the panel
		navBox.add(backButton);
		navBox.add(parentControlsButton);
		navBox.add(muteButton);
		navBox.add(logoutButton);
		navBox.setLayout(null);
		
		this.add(pageTitle);
		this.add(navBox);		
		this.setLayout(null);
	}
	
	public void redrawUpdate()
	{
		//main bounding box
		this.setBounds(resize.locationX(0), resize.locationY(0), resize.width(500), resize.height(100));
		
		//navBox
		navBox.setBounds(resize.width(20), resize.height(-2), resize.width(460), resize.height(52));
		navBox.setBorder(BorderFactory.createLineBorder(Color.black, resize.height(1)));
		
		//backButton
		backButton.setBounds(resize.width(10), resize.height(10), resize.width(80), resize.height(30));
		backButton.setFont(new Font(backButton.getFont().getFontName(),backButton.getFont().getStyle(), resize.font(12)));
		
		//parentControlsButton
		parentControlsButton.setBounds(resize.width(190), resize.height(10), resize.width(80), resize.height(30));
		parentControlsButton.setFont(new Font(parentControlsButton.getFont().getFontName(),parentControlsButton.getFont().getStyle(), resize.font(12)));
		
		//MuteButton
		muteButton.setBounds(resize.width(280), resize.height(10), resize.width(80), resize.height(30));
		muteButton.setFont(new Font(muteButton.getFont().getFontName(),muteButton.getFont().getStyle(), resize.font(12)));
		
		//logoutButton
		logoutButton.setBounds(resize.width(370), resize.height(10), resize.width(80), resize.height(30));
		logoutButton.setFont(new Font(logoutButton.getFont().getFontName(),logoutButton.getFont().getStyle(), resize.font(12)));
		
		//pageTitle
		pageTitle.setBounds(resize.width(0), resize.height(55), resize.width(500), resize.height(40));
		pageTitle.setFont(new Font(pageTitle.getFont().getFontName(),pageTitle.getFont().getStyle(), resize.font(25)));
	}
	
	public void update()
	{
		//logout
		if(buttonPressed == 1)
		{
			run.setScreen(new LoginScreen(run));
		}
		//mute
		else if(buttonPressed == 2)
		{
			if(run.player.music.isPlaying())
			{
				run.player.pauseMusic();
			}
			else
			{
				run.player.resume();
			}
			buttonPressed = 0;
		}
		//parentControls
		else if(buttonPressed == 3)
		{
			run.setScreen(new ParentControlsScreen(run));
		}
	}
}
