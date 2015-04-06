package com.groupc.screens;

/*
 * Author(s):		Team C
 * Course: 			CST 316 Spring
 * Instructor:		Dr. Gary
 * Date Changed:	4/1/2015
 * 
 * Description:		NavigationBar appears at the top of all screens for back, logout, and parental controls button.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import org.lwjgl.opengl.Display;

import com.groupc.Runner;
import com.groupc.math.Resize;


public class NavigationBar extends JPanel 
{
	//Variables
	private Runner run;
	private final Resize resize;
	private int    buttonPressed      = 0; //0 is none, 1 is add patient, 2 is back and logout for now
	public  boolean backButtonPressed = false;
	boolean played 					  = true;
	
	//Display Elements
	JPanel  navBox               = new JPanel();
	JButton backButton           = new JButton("Back");
	JButton logoutButton         = new JButton("Logout");
	JButton parentControlsButton = new JButton("Parental Controls");
	JToggleButton muteButton     = new JToggleButton();
	JLabel  pageTitle            = new JLabel("",SwingConstants.CENTER);
	
	ImageIcon muteOffIcon = new ImageIcon("UnMuteIcon.png");
	ImageIcon muteOnIcon  = new ImageIcon("MuteIcon.png");

	public NavigationBar(Runner run, boolean backOn, boolean parentControlsOn, String title) 
	{
		this.run = run;
		resize   = new Resize(run);
		
		muteButton.setIcon(new ImageIcon("UnMuteIcon.png"));
		muteButton.setSelectedIcon(new ImageIcon("MuteIcon.png"));
		
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
		
		//Mute on/off
		if(run.player.getPausedMusic())
		{
			muteButton.setSelected(true);
		}
		else
		{
			muteButton.setSelected(false);
		}
		
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
		navBox.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		//backButton
		backButton.setBounds(resize.width(10), resize.height(10), resize.width(80), resize.height(30));
		backButton.setFont(new Font(backButton.getFont().getFontName(),backButton.getFont().getStyle(), resize.font(12)));
		
		//parentControlsButton
		parentControlsButton.setBounds(resize.width(155), resize.height(10), resize.width(150), resize.height(30));
		parentControlsButton.setFont(new Font(parentControlsButton.getFont().getFontName(),parentControlsButton.getFont().getStyle(), resize.font(12)));
		
		//MuteButton
		muteButton.setBounds(resize.width(330), resize.height(10), resize.width(30), resize.height(30));
		muteButton.setFont(new Font(muteButton.getFont().getFontName(),muteButton.getFont().getStyle(), resize.font(12)));
		muteButton.setIcon(new ImageIcon(muteOffIcon.getImage().getScaledInstance(resize.width(30), resize.height(30), java.awt.Image.SCALE_SMOOTH)));
		muteButton.setSelectedIcon(new ImageIcon(muteOnIcon.getImage().getScaledInstance(resize.width(30), resize.height(30), java.awt.Image.SCALE_SMOOTH)));
		
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
			Display.destroy();
			run.setScreen(new LoginScreen(run));
		}
		//mute
		else if(buttonPressed == 2)
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
		//parentControls
		else if(buttonPressed == 3)
		{
			run.setScreen(new ParentControlsScreen(run));
		}
		buttonPressed = 0;
	}
}
