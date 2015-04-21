package com.groupc.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.groupc.Runner;
import com.groupc.math.Resize;

public class AccountScreen extends Screen 
{
	boolean redraw			= true;
	int		butPressed		= 0;
	Resize  resize     		= new Resize(run);
	NavigationBar navBar    = new NavigationBar(run,true,false,"User Account");
	
	//Display Elements
	JPanel 		passwordBox     = new JPanel();
	JPanel      pinBox      	= new JPanel();
	JPanel      highScoreBox    = new JPanel();
	
	JLabel 		passwordChangeLabel 	= new JLabel("Change Password");
	JLabel 		passwordCurrentLabel	= new JLabel("Current Password",SwingConstants.RIGHT);
	JLabel 		passwordNewLabel 		= new JLabel("New Password",SwingConstants.RIGHT);
	JLabel 		passwordNewRepeatLabel 	= new JLabel("Retype New Password",SwingConstants.RIGHT);
	JLabel 		pinChangeLabel 			= new JLabel("Change Parent Pin");
	JLabel 		pinCurrentLabel			= new JLabel("Current Pin",SwingConstants.RIGHT);
	JLabel 		pinNewLabel 			= new JLabel("New Pin",SwingConstants.RIGHT);
	JLabel 		pinNewRepeatLabel 		= new JLabel("Retype New Pin",SwingConstants.RIGHT);
	JLabel 		highScoresLabel 		= new JLabel("HighScores");
	JLabel 		highScoresGame1Label 	= new JLabel("Game 1:",SwingConstants.RIGHT);
	JLabel 		highScoresGame2Label 	= new JLabel("Game 2:",SwingConstants.RIGHT);
	JLabel 		highScoresGame3Label 	= new JLabel("Game 3:",SwingConstants.RIGHT);
	JLabel 		highScoresGame4Label 	= new JLabel("Game 4:",SwingConstants.RIGHT);
	JLabel 		highScoresGame1OutPut 	= new JLabel("0");
	JLabel 		highScoresGame2OutPut 	= new JLabel("0");
	JLabel 		highScoresGame3OutPut 	= new JLabel("0");
	JLabel 		highScoresGame4OutPut 	= new JLabel("0");
	
	JButton		changePasswordButton 	= new JButton("Change Password");
	JButton		changePinButton 		= new JButton("Change Pin");
	
	public AccountScreen(Runner run) 
	{
		super(run);
		
		//Basic Frame Settings
		run.setTitle(run.getUserName()+" | User Account");
		
		//resize stuff
		run.addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent e)
			{
				redraw = true;
			}
		});
		
		this.add(passwordChangeLabel);
		this.add(passwordCurrentLabel);
		this.add(passwordNewLabel);
		this.add(passwordNewRepeatLabel);
		this.add(pinChangeLabel);
		this.add(pinCurrentLabel);
		this.add(pinNewLabel);
		this.add(pinNewRepeatLabel);
		this.add(highScoresLabel);
		this.add(highScoresGame1Label);
		this.add(highScoresGame2Label);
		this.add(highScoresGame3Label);
		this.add(highScoresGame4Label);
		this.add(highScoresGame1OutPut);
		this.add(highScoresGame2OutPut);
		this.add(highScoresGame3OutPut);
		this.add(highScoresGame4OutPut);
		this.add(changePasswordButton);
		this.add(changePinButton);
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
			
			//Password Change
			/*passwordChangeLabel.setBounds(resize.locationX(0), resize.locationY(0), resize.width(300), resize.height(30));
			passwordCurrentLabel
			passwordNewLabel
			passwordNewRepeatLabel
			changePasswordButton
			
			//Pin Change
			pinChangeLabel
			pinCurrentLabel
			pinNewLabel
			pinNewRepeatLabel
			changePinButton
			
			//HighScores
			highScoresLabel
			highScoresGame1Label
			highScoresGame2Label
			highScoresGame3Label
			highScoresGame4Label
			highScoresGame1OutPut
			highScoresGame2OutPut
			highScoresGame3OutPut
			highScoresGame4OutPut*/
			
			run.repaint();
			redraw = false;
		}
		
		
		if(navBar.backButtonPressed)
		{
			run.setScreen(new GameHubScreen(run));
		}
		else if(butPressed == 2)
		{
			run.setScreen(new LoginScreen(run));
		}
		
		butPressed = 0;
		navBar.update();
	}

	@Override
	public void present(float deltaTime) 
	{
		// TODO Auto-generated method stub
		
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

}
