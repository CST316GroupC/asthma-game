package com.groupc.screens;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.groupc.Runner;
import com.groupc.math.Resize;

public class RecordingScreen extends Screen
{
	//Variables
	boolean redraw		= true;
	Resize  resize		= new Resize(run);
	int		butPressed	= 0;
	boolean	played		= true;
	
	//Display Elements
	NavigationBar 	navBar		= new NavigationBar(run,true,false,"Recording Screen Page");	
	JLabel 			text1		= new JLabel("* Air quality readings auto taken during test");
	JLabel 			text2		= new JLabel("No Spirometer detected");
	JLabel 			text3		= new JLabel("Check connection or click manual input");
	JButton 		startButton				= new JButton("Start");
	JButton 		airQualityInfoButton	= new JButton("Air Quality Info");
	JButton 		manualInputButton		= new JButton("Manual Input");
	
	
	public RecordingScreen(Runner run)
	{
		super(run);
		//Basic Frame Settings
		//run.setTitle("Recording");
		
		//resize stuff
		run.addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent e)
			{
				redraw = true;
			}
		});
		
		////Buttons////
		
		startButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 4;
			}
		});		
		
		
		
		this.add(text1);
		this.add(text2);
		this.add(text3);
		this.add(startButton);
		this.add(airQualityInfoButton);
		this.add(manualInputButton);
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
			
			//text1
			text1.setBounds(resize.locationX(200), resize.locationY(160), resize.width(260), resize.height(25));
			text1.setFont(new Font(text1.getFont().getFontName(),text1.getFont().getStyle(), resize.font(12)));
			
			//text2
			text2.setBounds(resize.locationX(200), resize.locationY(270), resize.width(150), resize.height(25));
			text2.setFont(new Font(text2.getFont().getFontName(),text2.getFont().getStyle(), resize.font(12)));
			
			//text3
			text3.setBounds(resize.locationX(200), resize.locationY(290), resize.width(250), resize.height(25));
			text3.setFont(new Font(text3.getFont().getFontName(),text3.getFont().getStyle(), resize.font(12)));	
			
			//startButton
			startButton.setBounds(resize.locationX(250), resize.locationY(190), resize.width(75), resize.height(25));
			startButton.setFont(new Font(startButton.getFont().getFontName(),startButton.getFont().getStyle(), resize.font(12)));	
			
			//airQualityInfoButton
			airQualityInfoButton.setBounds(resize.locationX(250), resize.locationY(220), resize.width(135), resize.height(25));
			airQualityInfoButton.setFont(new Font(airQualityInfoButton.getFont().getFontName(),airQualityInfoButton.getFont().getStyle(), resize.font(12)));	
			
			//manualInputButton
			manualInputButton.setBounds(resize.locationX(250), resize.locationY(320), resize.width(130), resize.height(25));
			manualInputButton.setFont(new Font(manualInputButton.getFont().getFontName(),manualInputButton.getFont().getStyle(), resize.font(12)));	
		
			run.repaint();
			redraw = false;
		}
		if(navBar.backButtonPressed)
		{	
			run.setScreen(new TutorialScreen(run));
		}
		else if(butPressed == 2)
		{
			run.setScreen(new LoginScreen(run));
		}
		else if(butPressed == 4)
		{
			run.setScreen(new RewardScreen(run));
		}
		butPressed = 0;
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
