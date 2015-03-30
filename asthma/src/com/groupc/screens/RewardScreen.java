package com.groupc.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import com.groupc.Runner;
import com.groupc.math.Resize;

public class RewardScreen extends Screen
{
	//Variables
	boolean redraw     = true;
	Resize  resize     = new Resize(run);
	int     butPressed = 0;
	boolean played = true;
	
	//Display Elements
	NavigationBar navBar         = new NavigationBar(run,false,false,"Rewards");
	JPanel        rewardBox      = new JPanel();
	JButton       continueButton = new JButton("Continue");
	
	public RewardScreen(Runner run) 
	{
		super(run);
		//Basic Frame Settings
		run.setTitle("Rewards");
		
		//resize stuff
		run.addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent e)
			{
				redraw = true;
			}
		});
		
		// Reward display and border
		rewardBox = new JPanel();

		//Set colors
		this.setBackground(Color.WHITE);
		rewardBox.setBackground(Color.LIGHT_GRAY);
		
		//Set fonts
		
		
		////Buttons////
		continueButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 1;				
			}
		});
		
		this.add(continueButton);
		this.add(rewardBox);
		this.add(navBar);
		
		this.setLayout(null);
		run.setContentPane(this);
		run.setVisible(true);
	}

	@Override
	public void update(float deltaTime)
	{
		if(butPressed == 1)
		{
			//run.setScreen(new Game1Screen(run));
			run.setScreen(new GameHubScreen(run));
		}
		butPressed = 0;

		if(redraw)
		{	
			//navBar
			navBar.redrawUpdate();
			
			//rewardBox
			rewardBox.setBounds(resize.locationX(100), resize.locationY(100), resize.width(285), resize.height(300));
			rewardBox.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			
			//continueButton
			continueButton.setBounds(resize.locationX(200), resize.locationY(420), resize.width(100), resize.height(30));
			continueButton.setFont(new Font(continueButton.getFont().getFontName(),continueButton.getFont().getStyle(), resize.font(12)));
			
			run.repaint();
			redraw = false;
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
	public void dispose() 
	{
		// TODO Auto-generated method stub
	}
}