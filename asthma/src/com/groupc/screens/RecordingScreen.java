package com.groupc.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import com.groupc.Runner;

public class RecordingScreen extends Screen
{
	JLabel pageTitle;
	JLabel text1;
	JLabel text2;
	JLabel text3;
	
	JPanel box;
	JPanel boxBorder;
	
	JButton back;
	JButton logout;
	JToggleButton mute;
	JButton start;
	JButton airQualityInfo;
	JButton manualInput;
	
	int butPressed = 0;
	boolean played = true;
	
	public RecordingScreen(Runner run)
	{
		super(run);
		run.setTitle("Recording");
		
		// Box display and border for buttons
		box = new JPanel();
		boxBorder = new JPanel();
		
		box.setBackground(Color.LIGHT_GRAY);
		boxBorder.setBackground(Color.BLACK);
		
		box.setBounds(18, 0, 450, 60);
		boxBorder.setBounds(17, 0, 452, 61);
		
		// Page title
		pageTitle = new JLabel("Recording Page");
		pageTitle.setFont(new Font("Serif", Font.BOLD, 25));
		pageTitle.setBounds(180, 60, 350, 40);
		
		
		// Add text for Start button
		text1 = new JLabel("* Air quality readings auto taken during test");
		text1.setFont(new Font("Normal", Font.ITALIC, 13));
		text1.setForeground(Color.RED);
		text1.setBounds(200, 160, 250, 25);
		
		// Add text for detecting Spirometer
		text2 = new JLabel("No Spirometer detected");
		text2.setFont(new Font("Normal", Font.ITALIC, 13));
		text2.setForeground(Color.RED);
		text2.setBounds(200, 270, 150, 25);
		
		// Add text for detecting Spirometer
		text3 = new JLabel("Check connection or click manual input");
		text3.setFont(new Font("Normal", Font.ITALIC, 13));
		text3.setForeground(Color.RED);
		text3.setBounds(200, 290, 250, 25);
		
		
		// Add Start button
		start = new JButton("Start");
		start.setBounds(250, 190, 75, 25);
		
		
		// Add Air Quality Info Button
		airQualityInfo = new JButton("Air Quality Info");
		airQualityInfo.setBounds(250, 220, 120, 25);
		
		
		// Add Manual Input
		manualInput = new JButton("Manual Input");
		manualInput.setBounds(250, 320, 110, 25);;
		
		
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
				butPressed = 2;				
			}
		});
		
		// Add logout button listener
		logout.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 1;				
			}
		});
		
		// Add mute button listener
		mute.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 3;				
			}
		});
		
		// Add start button listener
		start.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 4;
			}
		});		
		
		
		
		this.add(pageTitle);
		this.add(text1);
		this.add(text2);
		this.add(text3);
		this.add(back);
		this.add(logout);
		this.add(mute);
		this.add(start);
		this.add(airQualityInfo);
		this.add(manualInput);
		
		// Panels have to be added last for it to show 
		this.add(box);
		this.add(boxBorder);	
		
		this.setLayout(null);		
		run.setContentPane(this);
		run.setVisible(true);		
	}
	
	@Override
	public void update(float deltaTime)
	{
		if(butPressed == 1)
		{
			run.setScreen(new LoginScreen(run));
			
		}
		else if(butPressed == 2)
		{
			run.setScreen(new TutorialScreen(run));
		}
		else if(butPressed == 4)
		{
			run.setScreen(new RewardScreen(run));
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
	public void dispose() 
	{
		// TODO Auto-generated method stub
	}
	
}
