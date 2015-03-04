package com.groupc.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import com.groupc.Runner;
import com.groupc.math.Resize;


public class TutorialScreen extends Screen
{
	//Variables
	boolean redraw     = true;
	Resize  resize     = new Resize(run);
	int     butPressed = 0;	
	boolean played = true;
	
	//Display Elements
	NavigationBar navBar = new NavigationBar(run,false,false,"Tutorial");
	JLabel skipText      = new JLabel("* To skip, press Start",SwingConstants.CENTER);;
	JToggleButton mute;

	
	JPanel videoBox       = new JPanel();
	JPanel descriptionBox = new JPanel();
	JPanel stepsBox       = new JPanel();
	
	JButton step1 = new JButton("Step 1");
	JButton step2 = new JButton("Step 2");
	JButton step3 = new JButton("Step 3");
	JButton step4 = new JButton("Step 4");
	JButton step5 = new JButton("Step 5");
	JButton start = new JButton("Start");
	
	public TutorialScreen(Runner run) 
	{
		super(run);
		
		//Basic Frame Settings
		run.setTitle("Tutorial");
		
		//resize stuff
		run.addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent e)
			{
				redraw = true;
			}
		});
		
		//Color
		this.setBackground(Color.WHITE);
		videoBox.setBackground(Color.BLACK);
		descriptionBox.setBackground(Color.LIGHT_GRAY);
		stepsBox.setBackground(Color.GRAY);
		skipText.setForeground(Color.RED);
		
		//Font 
		videoBox.setBounds(30, 120, 250, 180);
		descriptionBox.setBounds(30, 320, 250, 60);
		stepsBox.setBounds(310, 120, 150, 260);
		

		
		// Add mute button
		mute = new JToggleButton();
		mute.setSelectedIcon(new ImageIcon("UnMuteIcon.png"));
		mute.setIcon(new ImageIcon("MuteIcon.png"));
		mute.setBounds(340, 18, 30, 25);
		
		
		// Add Steps button
		step1 = new JButton("Step 1");
		step2 = new JButton("Step 2");
		step3 = new JButton("Step 3");
		step4 = new JButton("Step 4");
		step5 = new JButton("Step 5");
	
		step1.setBounds(345, 140, 80, 25);
		step2.setBounds(345, 190, 80, 25);
		step3.setBounds(345, 240, 80, 25);
		step4.setBounds(345, 290, 80, 25);
		step5.setBounds(345, 340, 80, 25);

		start.setBounds(200, 420, 80, 25 );
		
		skipText.setBounds(180, 395, 120, 25);
		
		
		////Buttons////
		start.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 1;
			}
		});

//		// Add start button listener
//		start.addActionListener(new ActionListener()
//		{
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				butPressed = 4;
//			}
//		});
		
		this.add(mute);
		this.add(skipText);
		this.add(step1);
		this.add(step2);
		this.add(step3);
		this.add(step4);
		this.add(step5);
		this.add(start);
		
		// Panels have to be added last for it to show 
		this.add(videoBox);
		this.add(descriptionBox);
		this.add(stepsBox);
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
			
			//video
			videoBox.setBounds(resize.locationX(30), resize.locationY(120), resize.width(250), resize.height(180));
			
			//description
			descriptionBox.setBounds(resize.locationX(30), resize.locationY(320), resize.width(250), resize.height(60));
			descriptionBox.setBorder(BorderFactory.createLineBorder(Color.black, resize.height(1)));
			
			//step
			stepsBox.setBounds(resize.locationX(310), resize.locationY(120), resize.width(150), resize.height(260));
			stepsBox.setBorder(BorderFactory.createLineBorder(Color.black, resize.height(1)));
			step1.setBounds(resize.locationX(345), resize.locationY(140), resize.width(80), resize.height(25));
			step1.setFont(new Font(step1.getFont().getFontName(),step1.getFont().getStyle(), resize.font(12)));
			step2.setBounds(resize.locationX(345), resize.locationY(190), resize.width(80), resize.height(25));
			step2.setFont(new Font(step2.getFont().getFontName(),step2.getFont().getStyle(), resize.font(12)));
			step3.setBounds(resize.locationX(345), resize.locationY(240), resize.width(80), resize.height(25));
			step3.setFont(new Font(step3.getFont().getFontName(),step3.getFont().getStyle(), resize.font(12)));
			step4.setBounds(resize.locationX(345), resize.locationY(290), resize.width(80), resize.height(25));
			step4.setFont(new Font(step4.getFont().getFontName(),step4.getFont().getStyle(), resize.font(12)));
			step5.setBounds(resize.locationX(345), resize.locationY(340), resize.width(80), resize.height(25));
			step5.setFont(new Font(step5.getFont().getFontName(),step5.getFont().getStyle(), resize.font(12)));
			
			//skipText
			skipText.setBounds(resize.locationX(0), resize.locationY(395), resize.width(500), resize.height(25));
			skipText.setFont(new Font(skipText.getFont().getFontName(),skipText.getFont().getStyle(), resize.font(12)));
			
			//Start Button
			start.setBounds(resize.locationX(200), resize.locationY(420), resize.width(100), resize.height(30));
			start.setFont(new Font(start.getFont().getFontName(),start.getFont().getStyle(), resize.font(12)));
			
			run.repaint();
			redraw = false;
		}
		
		if(butPressed == 1)
		{
			run.setScreen(new RecordingScreen(run));
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
