/*
 * Author(s):     Team C
 * Course:        CST 316 Spring
 * Instructor:    Dr. Gary
 * Date Changed:  3/27/2015
 * Description:   Screen that will bring you to the individual games
 */

package com.groupc.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.groupc.Runner;
import com.groupc.math.Resize;
import com.groupc.math.Tokens;

public class GameHubScreen extends Screen
{
	//Variables
	private boolean	redraw		= true;
	private boolean	played		= true;
	private Resize	resize		= new Resize(run);
	private int 	butPressed	= 0;
	private	Tokens 	tokens 		= new Tokens(run.getUserName());
	private String  userName = run.getUserName();
	private Properties props 	= new Properties();
	
	
	//Display Elements
	private NavigationBar	navBar				= new NavigationBar(run,false,true,"Game Hub");	
	private TokenPanel		tokenPanel			= new TokenPanel(run);
	
	private JButton			game1Button 		= new JButton("");
	private JButton			game2Button 		= new JButton("");
	private JButton			game3Button 		= new JButton("");
	private JButton			game4Button 		= new JButton("");
	private JButton			LeaderBoardButton	= new JButton("Leader Board");
	private JButton			accountButton		= new JButton();
	private JButton			parentControlsButton = new JButton("Parental Controls");
	private JLabel			game1Label 			= new JLabel("Game 1",SwingConstants.CENTER);
	private JLabel 			game2Label 			= new JLabel("Game 2",SwingConstants.CENTER);
	private JLabel 			game3Label 			= new JLabel("Game 3",SwingConstants.CENTER);
	private JLabel 			game4Label 			= new JLabel("Game 4",SwingConstants.CENTER);
	
	private ImageIcon 		game1Icon 			= new ImageIcon("resources/interface/Game1Button.png");
	private ImageIcon 		game2Icon 			= new ImageIcon("resources/interface/Game2Button.png");
	private ImageIcon 		game3Icon 			= new ImageIcon("resources/interface/Game3Button.png");
	private ImageIcon 		game4Icon 			= new ImageIcon("resources/interface/Game4Button.png");
	private ImageIcon		accountIcon			= new ImageIcon("resources/interface/accountIcon.png");
	
	public GameHubScreen(Runner run)
	{
		super(run);	
		//Basic Frame Settings
		run.setTitle(run.getUserName()+" | Game Hub");
		
		checkProperties();
		
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
		
		//get token count
		tokenPanel.RefreshCount(tokens.getTokens());
		
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
		parentControlsButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 6;				
			}
		});
		
		accountButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 5;
			}
		});
		
		//deactivate buttons
		//deactivate buttons in checkProperties();
		//game2Button.setEnabled(false);
		//game3Button.setEnabled(false);
		//game4Button.setEnabled(false);
		
		this.add(game1Button);
		this.add(game2Button);
		this.add(game3Button);
		this.add(game4Button);
		this.add(accountButton);
		this.add(parentControlsButton);
		this.add(game1Label);
		this.add(game2Label);
		this.add(game3Label);
		this.add(game4Label);
		this.add(LeaderBoardButton);
		this.add(tokenPanel);
		this.add(navBar);
		
		this.setLayout(null);		
		run.setContentPane(this);
		run.setVisible(true);
	}
	
	private void checkProperties()
	{
		try
		{
			FileInputStream in = new FileInputStream("resources/interface/parent_controls/" + userName + ".properties");
			props.load(in);
			in.close();
		}catch(Exception e)
		{
			System.out.println("Failed gamehub properties load");
		}
		if(props.getProperty("Game_1").equals("false"))
		{
			game1Button.setEnabled(false);
		}
		if(props.getProperty("Game_2").equals("false"))
		{
			game2Button.setEnabled(false);
		}
		if(props.getProperty("Game_3").equals("false"))
		{
			game3Button.setEnabled(false);
		}
		if(props.getProperty("Game_4").equals("false"))
		{
			game4Button.setEnabled(false);
		}
	}
	
	@Override
	public void update(float deltaTime)
	{
		if(redraw)
		{
			//navBar
			navBar.redrawUpdate();
			tokenPanel.redrawUpdate();
			
			//Game 1
			game1Button.setBounds(resize.locationX(50), resize.locationY(125), resize.width(175), resize.height(100));
			game1Button.setIcon(new ImageIcon(game1Icon.getImage().getScaledInstance(resize.width(175), resize.height(100), java.awt.Image.SCALE_SMOOTH)));
			game1Label.setBounds(resize.locationX(50), resize.locationY(105), resize.width(175), resize.height(20));
			game1Label.setFont(new Font(game1Label.getFont().getFontName(),game1Label.getFont().getStyle(), resize.font(12)));
			
			//Game 2
			game2Button.setBounds(resize.locationX(275), resize.locationY(125), resize.width(175), resize.height(100));
			game2Button.setIcon(new ImageIcon(game2Icon.getImage().getScaledInstance(resize.width(175), resize.height(100), java.awt.Image.SCALE_SMOOTH)));
			game2Label.setBounds(resize.locationX(275), resize.locationY(105), resize.width(175), resize.height(20));
			game2Label.setFont(new Font(game2Label.getFont().getFontName(),game2Label.getFont().getStyle(), resize.font(12)));
			
			//Game 3
			game3Button.setBounds(resize.locationX(50), resize.locationY(275), resize.width(175), resize.height(100));
			game3Button.setIcon(new ImageIcon(game3Icon.getImage().getScaledInstance(resize.width(175), resize.height(100), java.awt.Image.SCALE_SMOOTH)));
			game3Label.setBounds(resize.locationX(50), resize.locationY(255), resize.width(175), resize.height(20));
			game3Label.setFont(new Font(game3Label.getFont().getFontName(),game3Label.getFont().getStyle(), resize.font(12)));
			
		    //Game 4
			game4Button.setBounds(resize.locationX(275), resize.locationY(275), resize.width(175), resize.height(100));
			game4Button.setIcon(new ImageIcon(game4Icon.getImage().getScaledInstance(resize.width(175), resize.height(100), java.awt.Image.SCALE_SMOOTH)));
			game4Label.setBounds(resize.locationX(275), resize.locationY(255), resize.width(175), resize.height(20));
			game4Label.setFont(new Font(game4Label.getFont().getFontName(),game4Label.getFont().getStyle(), resize.font(12)));
			
			//Account button
			accountButton.setBounds(resize.locationX(20), resize.locationY(410), resize.width(45), resize.height(50));
			accountButton.setIcon(new ImageIcon(accountIcon.getImage().getScaledInstance(resize.width(65), resize.height(75), java.awt.Image.SCALE_SMOOTH)));
			
			//LeaderBoardButton
			LeaderBoardButton.setBounds(resize.locationX(175), resize.locationY(420), resize.width(150), resize.height(30));
			LeaderBoardButton.setFont(new Font(LeaderBoardButton.getFont().getFontName(),LeaderBoardButton.getFont().getStyle(), resize.font(12)));
			
			//parent controls button
			parentControlsButton.setBounds(resize.locationX(155), resize.locationY(10), resize.width(150), resize.height(30));
			parentControlsButton.setFont(new Font(parentControlsButton.getFont().getFontName(),parentControlsButton.getFont().getStyle(), resize.font(12)));
			
			run.repaint();
			redraw = false;
		}
		
		if(butPressed == 1)
		{
			run.setScreen(new Game1Screen(run));
		}
		else if(butPressed == 2)
		{
			run.setScreen(new Game2Screen(run));
		}
		else if(butPressed == 3)
		{
			run.setScreen(new Game3Screen(run));
		}
		else if(butPressed == 4)
		{
			run.setScreen(new Game4Screen(run));
		}
		else if(butPressed == 5)
		{
			run.setScreen(new AccountScreen(run));
		}
		else if(butPressed == 6)
		{
			ParentControlsScreen pcs = new ParentControlsScreen(run);
			pcs.setUser(userName);
			run.setScreen(pcs);
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

	public void setUser(String uName) 
	{
		userName = uName;
		checkProperties();
	}
}
