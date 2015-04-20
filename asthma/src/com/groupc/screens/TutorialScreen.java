package com.groupc.screens;

/*
 * Author(s):		Team C
 * Course: 			CST 316 Spring
 * Instructor:		Dr. Gary
 * Date Changed:	3/29/2015
 * 
 * Description:		TutorialScreen is the first screen that appears after a patient logs in. 
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
	String patientUserName;
	
	//Display Elements
	NavigationBar navBar		= new NavigationBar(run,false,false,"Tutorial");
	JLabel skipText				= new JLabel("* To skip, press Start",SwingConstants.CENTER);
	JLabel citation				= new JLabel("http://www.drugs.com/cg/how-to-use-an-incentive-spirometer.html",SwingConstants.CENTER);
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
	
	ImageIcon imageSet			= new ImageIcon("resources/interface/tutorial/tutorial1.png");
	ImageIcon image1 			= new ImageIcon("resources/interface/tutorial/tutorial1.png");
	ImageIcon image2 			= new ImageIcon("resources/interface/tutorial/tutorial2.png");
	ImageIcon image3 			= new ImageIcon("resources/interface/tutorial/tutorial3.png");
	ImageIcon image4 			= new ImageIcon("resources/interface/tutorial/tutorial4.png");
	ImageIcon image5 			= new ImageIcon("resources/interface/tutorial/tutorial5.png");
	ImageIcon image6 			= new ImageIcon("resources/interface/tutorial/tutorial6.png");
	ImageIcon image7 			= new ImageIcon("resources/interface/tutorial/tutorial7.png");
	
	JLabel stepImage			= new JLabel();
	
	public TutorialScreen(Runner run)
	{
		super(run);
		
		//Basic Frame Settings
		run.setTitle(run.getUserName()+" | Tutorial");
		
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
		
		stepImage.setIcon(new ImageIcon(image1.getImage().getScaledInstance(resize.width(315), resize.height(215), java.awt.Image.SCALE_SMOOTH)));
		
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
				//stepImage.setIcon(image1);
			}
		});
				

		step2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				step = 2;
				//stepImage.setIcon(image2);
			}
		});
		
		step3.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				step = 3;
				//stepImage.setIcon(image3);
			}
		});
		
		step4.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				step = 4;
				//stepImage.setIcon(image4);
			}
		});
		
		step5.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				step = 5;
				//stepImage.setIcon(image5);
			}
		});
		step6.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				step = 6;
				//stepImage.setIcon(image6);
			}
		});
		
		step7.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				step = 7;
				//stepImage.setIcon(image7);
			}
		});
								
		this.add(skipText);
		this.add(citation);
		this.add(step1);
		this.add(step2);
		this.add(step3);
		this.add(step4);
		this.add(step5);
		this.add(step6);
		this.add(step7);
		this.add(start);
		this.add(stepImage);
		this.add(videoBox);
		this.add(descriptionBox);
		this.add(stepsBox);
		this.add(navBar);
		this.setLayout(null);		
		run.setContentPane(this);
		run.setVisible(true);
	}
	
	public void setPatient(String pUserName)
	{
		if(pUserName != null)
		{
			patientUserName = pUserName;
			//run.setTitle(patientUserName + " Tutorial");
			stepImage.setIcon(image1);
		} 
		else
		{
			//run.setTitle("Tutorial");
		}
	}

	@Override
	public void update(float deltaTime)
	{
		if(redraw)
		{	
			//navBar
			navBar.redrawUpdate();
			
			//video
			//videoBox.setBounds(resize.locationX(20), resize.locationY(120), resize.width(315), resize.height(215));
			
			//description
			/*
			descriptionBox.setBounds(resize.locationX(30), resize.locationY(320), resize.width(250), resize.height(60));
			descriptionBox.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			*/
			
			citation.setBounds(resize.locationX(15), resize.locationY(350), resize.width(330), resize.height(25));
			citation.setFont(new Font(citation.getFont().getFontName(),citation.getFont().getStyle(), resize.font(10)));
			
			//step
			stepsBox.setBounds(resize.locationX(360), resize.locationY(120), resize.width(120), resize.height(260));
			stepsBox.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			step1.setBounds(resize.locationX(385), resize.locationY(135), resize.width(75), resize.height(20));
			step1.setFont(new Font(step1.getFont().getFontName(),step1.getFont().getStyle(), resize.font(12)));
			step2.setBounds(resize.locationX(385), resize.locationY(170), resize.width(75), resize.height(20));
			step2.setFont(new Font(step2.getFont().getFontName(),step2.getFont().getStyle(), resize.font(12)));
			step3.setBounds(resize.locationX(385), resize.locationY(205), resize.width(75), resize.height(20));
			step3.setFont(new Font(step3.getFont().getFontName(),step3.getFont().getStyle(), resize.font(12)));
			step4.setBounds(resize.locationX(385), resize.locationY(240), resize.width(75), resize.height(20));
			step4.setFont(new Font(step4.getFont().getFontName(),step4.getFont().getStyle(), resize.font(12)));
			step5.setBounds(resize.locationX(385), resize.locationY(275), resize.width(75), resize.height(20));
			step5.setFont(new Font(step5.getFont().getFontName(),step5.getFont().getStyle(), resize.font(12)));
			step6.setBounds(resize.locationX(385), resize.locationY(310), resize.width(75), resize.height(20));
			step6.setFont(new Font(step6.getFont().getFontName(),step6.getFont().getStyle(), resize.font(12)));
			step7.setBounds(resize.locationX(385), resize.locationY(345), resize.width(75), resize.height(20));
			step7.setFont(new Font(step7.getFont().getFontName(),step7.getFont().getStyle(), resize.font(12)));
			
			//image
			stepImage.setBounds(resize.locationX(20), resize.locationY(120), resize.width(315), resize.height(215));
			stepImage.setIcon(new ImageIcon(imageSet.getImage().getScaledInstance(resize.width(315), resize.height(215), java.awt.Image.SCALE_SMOOTH)));
			
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
			RecordingScreen rs = new RecordingScreen(run);
			rs.setPatient(patientUserName);
			run.setScreen(rs);
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
		switch(step)
		{
			case 1: imageSet = image1;
				stepImage.setIcon(new ImageIcon(image1.getImage().getScaledInstance(resize.width(315), resize.height(215), java.awt.Image.SCALE_SMOOTH)));
				break;
			case 2: imageSet = image2;
				stepImage.setIcon(new ImageIcon(image2.getImage().getScaledInstance(resize.width(315), resize.height(215), java.awt.Image.SCALE_SMOOTH)));
				break;
			case 3: imageSet = image3;
				stepImage.setIcon(new ImageIcon(image3.getImage().getScaledInstance(resize.width(315), resize.height(215), java.awt.Image.SCALE_SMOOTH)));
				break;
			case 4: imageSet = image4;
				stepImage.setIcon(new ImageIcon(image4.getImage().getScaledInstance(resize.width(315), resize.height(215), java.awt.Image.SCALE_SMOOTH)));
				break;
			case 5: imageSet = image5;
				stepImage.setIcon(new ImageIcon(image5.getImage().getScaledInstance(resize.width(315), resize.height(215), java.awt.Image.SCALE_SMOOTH)));
				break;
			case 6: imageSet = image6;
				stepImage.setIcon(new ImageIcon(image6.getImage().getScaledInstance(resize.width(315), resize.height(215), java.awt.Image.SCALE_SMOOTH)));
				break;
			case 7: imageSet = image7;
				stepImage.setIcon(new ImageIcon(image7.getImage().getScaledInstance(resize.width(315), resize.height(215), java.awt.Image.SCALE_SMOOTH)));
				break;
		}
		step = 0;
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
