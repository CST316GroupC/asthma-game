package com.groupc.screens;

/*
 * Author(s):		Team C
 * Course: 			CST 316 Spring
 * Instructor:		Dr. Gary
 * Date Changed:	4/1/2015
 * 
 * Description:		
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import com.groupc.Runner;
import com.groupc.math.Resize;

public class RewardScreen extends Screen
{
	//Variables
	boolean redraw     	= true;
	boolean played 		= true;
	Resize  resize     	= new Resize(run);
	int     butPressed 	= 0;
	int		tokenCount;
	Date 	date 		= new Date();

	
	//Display Elements
	NavigationBar 	navBar         	= new NavigationBar(run,false,false,"Rewards");
	JPanel        	rewardBox      	= new JPanel();
	JButton       	continueButton 	= new JButton("Continue");
	JLabel			rewardText 		= new JLabel("5 x Tokens",SwingConstants.CENTER);
	JLabel 			token1			= new JLabel();
	JLabel 			token2			= new JLabel();
	JLabel 			token3			= new JLabel();
	JLabel 			token4			= new JLabel();
	JLabel 			token5			= new JLabel();
	
	
	private ImageIcon 	tokenImage 	= new ImageIcon("resources/interface/Token.png");
	
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
		
		//set reward Text
		tokenCount = getTokens();
		if(tokenCount == 1)
		{
			rewardText.setText(tokenCount+" Token");
		}
		else
		{
			rewardText.setText(tokenCount+" Tokens");
		}

		////Buttons////
		continueButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 1;				
			}
		});
		
		//Make amount of tokens visible
		token5.setVisible(false);
		token4.setVisible(false);
		token3.setVisible(false);
		token2.setVisible(false);
		token1.setVisible(false);
		
		switch(tokenCount)
		{
			case 5:
				token5.setVisible(true);
			case 4:
				token1.setVisible(true);
			case 3:
				token2.setVisible(true);
			case 2:
				token4.setVisible(true);
			case 1:
				token3.setVisible(true);
			break;
		}
		this.add(token5);
		this.add(token4);
		this.add(token3);
		this.add(token2);
		this.add(token1);
		this.add(rewardText);
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
			rewardBox.setBounds(resize.locationX(100), resize.locationY(100), resize.width(300), resize.height(300));
			rewardBox.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			
			//rewardText
			rewardText.setBounds(resize.locationX(100), resize.locationY(150), resize.width(300), resize.height(30));
			rewardText.setFont(new Font(rewardText.getFont().getFontName(),rewardText.getFont().getStyle(), resize.font(30)));
			
			//token1
			token1.setBounds(resize.locationX(150), resize.locationY(225), resize.width(100), resize.height(100));
			token1.setIcon(new ImageIcon(tokenImage.getImage().getScaledInstance(resize.width(100), resize.height(100), java.awt.Image.SCALE_SMOOTH)));
			//token2
			token2.setBounds(resize.locationX(175), resize.locationY(225), resize.width(100), resize.height(100));
			token2.setIcon(new ImageIcon(tokenImage.getImage().getScaledInstance(resize.width(100), resize.height(100), java.awt.Image.SCALE_SMOOTH)));
			//token3
			token3.setBounds(resize.locationX(200), resize.locationY(225), resize.width(100), resize.height(100));
			token3.setIcon(new ImageIcon(tokenImage.getImage().getScaledInstance(resize.width(100), resize.height(100), java.awt.Image.SCALE_SMOOTH)));
			//token4
			token4.setBounds(resize.locationX(225), resize.locationY(225), resize.width(100), resize.height(100));
			token4.setIcon(new ImageIcon(tokenImage.getImage().getScaledInstance(resize.width(100), resize.height(100), java.awt.Image.SCALE_SMOOTH)));
			//token5
			token5.setBounds(resize.locationX(250), resize.locationY(225), resize.width(100), resize.height(100));
			token5.setIcon(new ImageIcon(tokenImage.getImage().getScaledInstance(resize.width(100), resize.height(100), java.awt.Image.SCALE_SMOOTH)));
			
			//continueButton
			continueButton.setBounds(resize.locationX(200), resize.locationY(420), resize.width(100), resize.height(30));
			continueButton.setFont(new Font(continueButton.getFont().getFontName(),continueButton.getFont().getStyle(), resize.font(12)));
			
			run.repaint();
			redraw = false;
		}
		
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
	
	private int getTokens()
	{
		int count = 0;
		int daysAgo;
		String line = null;
		String tempString = date.toString();
		String currentDay = null;
		
		Vector<String> userNames = new Vector<String>();
		Vector<String> days = new Vector<String>();
		StringTokenizer stringT;
		
		try
		{
			FileReader fileReader = new FileReader("spirometer_readings.txt");
			BufferedReader bufferReader = new BufferedReader(fileReader);
			
			
			while((line = bufferReader.readLine()) != null)
			{
				stringT = new StringTokenizer(line, " | ");
				userNames.add(stringT.nextToken()); //user
				stringT.nextToken(); //volume
				stringT.nextToken(); //force
				stringT.nextToken(); //day of week
				stringT.nextToken(); //month
				days.add(stringT.nextToken()); //day 
				stringT.nextToken(); //time
				stringT.nextToken(); //timezone
				stringT.nextToken(); //year
			}
			bufferReader.close();
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		stringT = new StringTokenizer(tempString);
		while(stringT.hasMoreElements())
		{
			stringT.nextToken(); // day of week
			stringT.nextToken(); // month
			currentDay = stringT.nextToken(); // day
			stringT.nextToken(); // time
			stringT.nextToken(); // timezone
			stringT.nextToken(); // year
		}
		for(int i = 0; i < userNames.size(); i++)
		{
			System.out.println(userNames.size());
			if(userNames.elementAt(i).equals("user"))
			{
				/////////get days TODO
				daysAgo = 5; //days.elementAt(i).equals(currentDay);
				if(daysAgo == 4 || daysAgo == 3 || daysAgo == 2 || daysAgo == 1 || daysAgo == 0)//4 days ago
				{
					count += 1;
				}
				else
				{
					count = 0;
				}
			}
		}
		//return true;
		return count;
	}
}