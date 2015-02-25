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

public class RewardScreen extends Screen
{

	JLabel pageTitle;
	
	JPanel box;
	JPanel boxBorder;
	JPanel rewardBox;
	JPanel rewardBorder;
	
	JButton back;
	JButton logout;
	JToggleButton mute;
	JButton continueButton;
	
	int butPressed = 0;
	boolean played = true;
	
	public RewardScreen(Runner run) {
		super(run);
		run.setTitle("Rewards");
		
		
		// Page title
		pageTitle = new JLabel("Rewards Page");
		pageTitle.setFont(new Font("Serif", Font.BOLD, 25));
		pageTitle.setBounds(180, 60, 350, 40);
		
		
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
		
		
		// Box display and border for buttons
		box = new JPanel();
		boxBorder = new JPanel();
		
		box.setBackground(Color.LIGHT_GRAY);
		boxBorder.setBackground(Color.BLACK);
		
		box.setBounds(18, 0, 450, 60);
		boxBorder.setBounds(17, 0, 452, 61);
		
		
		// Reward display and border
		rewardBox = new JPanel();
		rewardBorder = new JPanel();
		
		rewardBox.setBackground(Color.LIGHT_GRAY);
		rewardBorder.setBackground(Color.BLACK);
		
		rewardBox.setBounds(100, 110, 285, 250);
		rewardBorder.setBounds(99, 109, 287, 252);
		
		
		// Add continue button
		continueButton = new JButton("Continue");
		continueButton.setBounds(195, 420, 90, 25);
		
		
		
		this.add(pageTitle);
		this.add(back);
		this.add(logout);
		this.add(mute);
		this.add(continueButton);
		
		// Panels have to be added last for it to show 
		this.add(box);
		this.add(boxBorder);
		this.add(rewardBox);
		this.add(rewardBorder);
		
		
		// Add back button listener
		back.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 1;				
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
		
		// Add mute button listener
		mute.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 3;				
			}
		});
		
		this.setLayout(null);
		run.setContentPane(this);
		run.setVisible(true);
	}

	@Override
	public void update(float deltaTime)
	{
		if(butPressed == 1)
		{
			run.setScreen(new RecordingScreen(run));
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
	public void dispose() 
	{
		// TODO Auto-generated method stub
	}
}
