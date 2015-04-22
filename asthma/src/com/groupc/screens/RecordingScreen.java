package com.groupc.screens;

/*
 * Author(s):		Team C
 * Course: 			CST 316 Spring
 * Instructor:		Dr. Gary
 * Date Changed:	4/1/2015
 * 
 * Description:		RecordingScreen appears after patient completes the tutorial.
 * 					RecordingScreen only appears once a day, after the patient has done their daily recording
 * 					the RecordingScreen does not appear until the next day.
 * 					Current state of functionality for RecordingScreen is only with Manual Input.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.groupc.Runner;
import com.groupc.math.Resize;

public class RecordingScreen extends Screen
{
	//Variables
	boolean redraw		= true;
	boolean	played		= true;
	Resize  resize		= new Resize(run);
	int		butPressed	= 0;

	String patientUserName = run.getUserName();
	
	
	Date date = new Date();
	
	//Display Elements
	NavigationBar 	navBar			= new NavigationBar(run,true,false,"Spirometer Input");
	
	JLabel 		text2					= new JLabel("No Spirometer detected");
	JLabel 		text3					= new JLabel("Check connection or submit manual input");
	JLabel     	errorMessage    		= new JLabel("Missing Information*",SwingConstants.CENTER);
	JLabel     	invalidInputMessage    	= new JLabel("Please insert a number*",SwingConstants.CENTER);
	JLabel 		volumeLabel				= new JLabel("Volume*: ",SwingConstants.RIGHT);
	JLabel 		forceLabel				= new JLabel("Force*: ",SwingConstants.RIGHT);
	
	JButton 		manualInputButton		= new JButton("Manual Input");
	
	JTextField 		volumeTF           = new JTextField();
	JTextField 		forceTF            = new JTextField();
	
	
	public RecordingScreen(Runner run)
	{
		super(run);
		//Basic Frame Settings
		run.setTitle(run.getUserName()+" | Spirometer Input");
		
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
		errorMessage.setForeground(Color.RED);
		invalidInputMessage.setForeground(Color.RED);
		
		
		////Buttons////
		
		
		manualInputButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 4;
			}
		});	
		
		errorMessage.setVisible(false);
		invalidInputMessage.setVisible(false);
		
		//this.add(text2);
		//this.add(text3);
		this.add(manualInputButton);
		this.add(volumeTF);
		this.add(forceTF);
		this.add(volumeLabel);
		this.add(forceLabel);
		this.add(errorMessage);
		this.add(invalidInputMessage);
		this.add(navBar);
		
		this.setLayout(null);		
		run.setContentPane(this);
		run.setVisible(true);		
	}
	
	private boolean validInputs()
	{
		errorMessage.setVisible(false);
		invalidInputMessage.setVisible(false);
		if(volumeTF.getText().isEmpty() || forceTF.getText().isEmpty())
		{
			errorMessage.setVisible(true);
			return false;
		}else
		{
			try
			{
				Float.parseFloat(volumeTF.getText());
				Float.parseFloat(forceTF.getText());
		
			}catch(Exception e)
			{
				volumeTF.setText("");
				forceTF.setText("");
				invalidInputMessage.setVisible(true);
				return false;
			}
		}
		
		
		return true;
	}
	
	private boolean submitReadings() //submits patient username, the readings, and the current date
	{
		if(validInputs())
		{
			try 
			{
				FileWriter fWriter = new FileWriter("spirometer_readings.txt", true);
				BufferedWriter bWriter = new BufferedWriter(fWriter);
				bWriter.write(patientUserName + " | " + volumeTF.getText() + " | " + forceTF.getText() + " | " + date + "\n"); 
				bWriter.close();

				return true;
			} catch (IOException e)
			{
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	@Override
	public void update(float deltaTime)
	{
		if(redraw)
		{
			//navBar
			navBar.redrawUpdate();
			
			//text2
			text2.setBounds(resize.locationX(150), resize.locationY(200), resize.width(150), resize.height(25));
			text2.setFont(new Font(text2.getFont().getFontName(),text2.getFont().getStyle(), resize.font(12)));
			
			//text3
			text3.setBounds(resize.locationX(150), resize.locationY(220), resize.width(250), resize.height(25));
			text3.setFont(new Font(text3.getFont().getFontName(),text3.getFont().getStyle(), resize.font(12)));	
			
			//manualInputButton
			manualInputButton.setBounds(resize.locationX(175), resize.locationY(400), resize.width(150), resize.height(30));
			manualInputButton.setFont(new Font(manualInputButton.getFont().getFontName(),manualInputButton.getFont().getStyle(), resize.font(12)));	
			
			errorMessage.setBounds(resize.locationX(175), resize.locationY(380), resize.width(150), resize.height(20));
			errorMessage.setFont(new Font(errorMessage.getFont().getFontName(),errorMessage.getFont().getStyle(), resize.font(12)));
			
			invalidInputMessage.setBounds(resize.locationX(175), resize.locationY(380), resize.width(150), resize.height(20));
			invalidInputMessage.setFont(new Font(invalidInputMessage.getFont().getFontName(),invalidInputMessage.getFont().getStyle(), resize.font(12)));
			
			volumeTF.setBounds(resize.locationX(175), resize.locationY(250), resize.width(150), resize.height(20));
			volumeTF.setFont(new Font(volumeTF.getFont().getFontName(),volumeTF.getFont().getStyle(), resize.font(12)));
			
			forceTF.setBounds(resize.locationX(175), resize.locationY(280), resize.width(150), resize.height(20));
			forceTF.setFont(new Font(forceTF.getFont().getFontName(),forceTF.getFont().getStyle(), resize.font(12)));
			
			volumeLabel.setBounds(resize.locationX(75), resize.locationY(250), resize.width(100), resize.height(20));
			volumeLabel.setFont(new Font(volumeLabel.getFont().getFontName(),volumeLabel.getFont().getStyle(), resize.font(12)));
			
			forceLabel.setBounds(resize.locationX(75), resize.locationY(280), resize.width(100), resize.height(20));
			forceLabel.setFont(new Font(forceLabel.getFont().getFontName(),forceLabel.getFont().getStyle(), resize.font(12)));
			
			run.repaint();
			redraw = false;
		}
		if(navBar.backButtonPressed)
		{	
			TutorialScreen ts = new TutorialScreen(run);
			run.setScreen(ts);
		}
		else if(butPressed == 2)
		{
			run.setScreen(new LoginScreen(run));
		}
		else if(butPressed == 4)
		{
			if(submitReadings())
			{
				RewardScreen rewardScreen = new RewardScreen(run);
				run.setScreen(rewardScreen);
			}
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
