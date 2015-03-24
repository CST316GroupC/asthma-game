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

import com.groupc.Runner;
import com.groupc.math.Resize;

public class GameHubScreen extends Screen
{
	//Variables
	boolean redraw		= true;
	Resize  resize		= new Resize(run);
	int		butPressed	= 0;
	boolean	played		= true;
	
	//Display Elements
	NavigationBar 	navBar		= new NavigationBar(run,false,true,"Game Hub");	
	JButton         game1Button = new JButton("");
	JButton         game2Button = new JButton("Game 2");
	JButton         game3Button = new JButton("Game 3");
	JButton         game4Button = new JButton("Game 4");
	JButton         LeaderBoardButton = new JButton("Leader Board");
	
	ImageIcon game1Icon = new ImageIcon("Game1Button.png");
	
	public GameHubScreen(Runner run)
	{
		super(run);
		//Basic Frame Settings
		
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
		
		////Buttons////
		game1Button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 1;
			}
		});
		game2Button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 2;
			}
		});
		game3Button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 3;
			}
		});
		game4Button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 4;
			}
		});
		
		//deactivate buttons
		game2Button.setEnabled(false);
		game3Button.setEnabled(false);
		game4Button.setEnabled(false);
		
		this.add(game1Button);
		this.add(game2Button);
		this.add(game3Button);
		this.add(game4Button);
		this.add(LeaderBoardButton);
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
			
			//Game 1
			game1Button.setBounds(resize.locationX(50), resize.locationY(125), resize.width(175), resize.height(100));
			game1Button.setIcon(new ImageIcon(game1Icon.getImage().getScaledInstance(resize.width(175), resize.height(100), java.awt.Image.SCALE_SMOOTH)));
			
			//Game 2
			game2Button.setBounds(resize.locationX(275), resize.locationY(125), resize.width(175), resize.height(100));
			
			//Game 3
			game3Button.setBounds(resize.locationX(50), resize.locationY(275), resize.width(175), resize.height(100));
			
		    //Game 4
			game4Button.setBounds(resize.locationX(275), resize.locationY(275), resize.width(175), resize.height(100));
			
			//LeaderBoardButton
			LeaderBoardButton.setBounds(resize.locationX(175), resize.locationY(420), resize.width(150), resize.height(30));
			LeaderBoardButton.setFont(new Font(LeaderBoardButton.getFont().getFontName(),LeaderBoardButton.getFont().getStyle(), resize.font(12)));
			
			run.repaint();
			redraw = false;
		}
		if(butPressed == 1)
		{
			run.setScreen(new Game1Screen(run));
		}
		else if(butPressed == 2)
		{
			//run.setScreen(new Game1Screen(run));
		}
		else if(butPressed == 3)
		{
			//run.setScreen(new Game1Screen(run));
		}
		else if(butPressed == 4)
		{
			//run.setScreen(new Game1Screen(run));
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
