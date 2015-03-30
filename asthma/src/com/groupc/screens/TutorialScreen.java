package com.groupc.screens;

/*
 * Author(s):		Team C
 * Course: 			CST 316 Spring
 * Instructor:		Dr. Gary
 * Date Changed:	3/29/2015
 * 
 * Description:		TutorialScreen is the first page after the patient logs in. 
 * 					This page only appears the first time the patient logs in.
 * 					Instructions on how to use the Spirometer and how to operate/navigate the software.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.groupc.Runner;
import com.groupc.math.Resize;


public class TutorialScreen extends Screen
{
	//Variables
	boolean redraw				= true;
	boolean played				= true;
	int 	butPressed			= 0;	
	int 	step				= 0;
	Resize  resize				= new Resize(run);
	
	//Display Elements
	NavigationBar navBar		= new NavigationBar(run,false,false,"Tutorial");
	JLabel skipText				= new JLabel("* To skip, press Start",SwingConstants.CENTER);;

	//Panels
	JPanel videoBox				= new JPanel();
	JPanel descriptionBox		= new JPanel();
	JPanel stepsBox				= new JPanel();
	
	//Buttons
	JButton step1				= new JButton("Step 1");
	JButton step2				= new JButton("Step 2");
	JButton step3				= new JButton("Step 3");
	JButton step4				= new JButton("Step 4");
	JButton step5				= new JButton("Step 5");
	JButton step6				= new JButton("Step 6");
	JButton step7				= new JButton("Step 7");
	JButton start				= new JButton("Start");
	
	ImageIcon image1 			= new ImageIcon("tutorial1.png");
	ImageIcon image2 			= new ImageIcon("tutorial2.png");
	ImageIcon image3 			= new ImageIcon("tutorial3.png");
	ImageIcon image4 			= new ImageIcon("tutorial4.png");
	ImageIcon image5 			= new ImageIcon("tutorial5.png");
	ImageIcon image6 			= new ImageIcon("tutorial6.png");
	ImageIcon image7 			= new ImageIcon("tutorial7.png");
	
	JLabel step1Image			= new JLabel(image1);
	JLabel step2Image			= new JLabel(image2);
	JLabel step3Image			= new JLabel(image3);
	JLabel step4Image			= new JLabel(image4);
	JLabel step5Image			= new JLabel(image5);
	JLabel step6Image			= new JLabel(image6);
	JLabel step7Image			= new JLabel(image7);
	
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
		
		////Buttons////
		start.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				butPressed = 1;
			}
		});
		
		step1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				step = 1;
			}
		});
				

		step2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				step = 2;
			}
		});
		
		step3.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				step = 3;
			}
		});
		
		step4.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				step = 4;
			}
		});
		
		step5.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				step = 5;
			}
		});
		step6.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				step = 6;
			}
		});
		
		step7.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				step = 7;
			}
		});
								
		this.add(skipText);
		this.add(step1);
		this.add(step2);
		this.add(step3);
		this.add(step4);
		this.add(step5);
		this.add(step6);
		this.add(step7);
		this.add(start);
		this.add(step1Image);
		this.add(step2Image);
		this.add(step3Image);
		this.add(step4Image);
		this.add(step5Image);
		this.add(step6Image);
		this.add(step7Image);
		
		// Panels have to be added last for it to show 
		this.add(videoBox);
		this.add(descriptionBox);
		this.add(stepsBox);
		this.add(navBar);
		this.setLayout(null);		
		run.setContentPane(this);
		run.setVisible(true);
		stepVisibility();
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
			descriptionBox.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			
			//step
			stepsBox.setBounds(resize.locationX(310), resize.locationY(120), resize.width(150), resize.height(260));
			stepsBox.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			step1.setBounds(resize.locationX(345), resize.locationY(140), resize.width(80), resize.height(25));
			step1.setFont(new Font(step1.getFont().getFontName(),step1.getFont().getStyle(), resize.font(12)));
			step2.setBounds(resize.locationX(370), resize.locationY(170), resize.width(75), resize.height(20));
			step2.setFont(new Font(step2.getFont().getFontName(),step2.getFont().getStyle(), resize.font(12)));
			step3.setBounds(resize.locationX(370), resize.locationY(205), resize.width(75), resize.height(20));
			step3.setFont(new Font(step3.getFont().getFontName(),step3.getFont().getStyle(), resize.font(12)));
			step4.setBounds(resize.locationX(370), resize.locationY(240), resize.width(75), resize.height(20));
			step4.setFont(new Font(step4.getFont().getFontName(),step4.getFont().getStyle(), resize.font(12)));
			step5.setBounds(resize.locationX(370), resize.locationY(275), resize.width(75), resize.height(20));
			step5.setFont(new Font(step5.getFont().getFontName(),step5.getFont().getStyle(), resize.font(12)));
			step6.setBounds(resize.locationX(370), resize.locationY(310), resize.width(75), resize.height(20));
			step6.setFont(new Font(step6.getFont().getFontName(),step6.getFont().getStyle(), resize.font(12)));
			step7.setBounds(resize.locationX(370), resize.locationY(345), resize.width(75), resize.height(20));
			step7.setFont(new Font(step7.getFont().getFontName(),step7.getFont().getStyle(), resize.font(12)));
			
			//image
			step1Image.setBounds(resize.locationX(30), resize.locationY(120), resize.width(250), resize.height(180));
			
			
			//skipText
			skipText.setBounds(resize.locationX(0), resize.locationY(395), resize.width(500), resize.height(25));
			skipText.setFont(new Font(skipText.getFont().getFontName(),skipText.getFont().getStyle(), resize.font(12)));
			
			//Start Button
			start.setBounds(resize.locationX(200), resize.locationY(420), resize.width(100), resize.height(30));
			start.setFont(new Font(start.getFont().getFontName(),start.getFont().getStyle(), resize.font(12)));
			
			run.repaint();
			redraw = false;
		}
		navBar.update();
		
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
		
		if(step == 1)
		{
			stepVisibility();
			videoBox.setVisible(false);
			step1Image.setVisible(true);
			step = 0;
		}
		else if(step == 2)
		{
			stepVisibility();
			videoBox.setVisible(false);
			step2Image.setVisible(true);
			step = 0;
		}
		else if(step == 3)
		{
			stepVisibility();
			videoBox.setVisible(false);
			step3Image.setVisible(true);
			step = 0;
		}
		else if(step == 4)
		{
			stepVisibility();
			videoBox.setVisible(false);
			step4Image.setVisible(true);
			step = 0;
		}
		else if(step == 5)
		{
			stepVisibility();
			videoBox.setVisible(false);
			step5Image.setVisible(true);
			step = 0;
		}
		else if(step == 6)
		{
			stepVisibility();
			videoBox.setVisible(false);
			step6Image.setVisible(true);
			step = 0;
		}
		else if(step == 7)
		{
			stepVisibility();
			videoBox.setVisible(false);
			step7Image.setVisible(true);
			step = 0;
		}
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
	
	public void stepVisibility()
	{
		step1Image.setVisible(false);
		step2Image.setVisible(false);
		step3Image.setVisible(false);
		step4Image.setVisible(false);
		step5Image.setVisible(false);
		step6Image.setVisible(false);
		step7Image.setVisible(false);
	}

}
