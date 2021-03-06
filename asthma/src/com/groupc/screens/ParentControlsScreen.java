package com.groupc.screens;

/*
 * Author(s):		Team C
 * Course: 			CST 316 Spring
 * Instructor:		Dr. Gary
 * Date Changed:	4/1/2015
 * 
 * Description:		ParentalControlsScreen appears at the top of Game Hub for parents.
 * 					A pin is required to access the controls to prevent kids from accessing.
 * 					Parents are able to set up certain restrictions for their child.
 * 					
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.groupc.Runner;
import com.groupc.math.Resize;

public class ParentControlsScreen extends Screen
{
	//Variables
	boolean redraw     = true;
	Resize  resize     = new Resize(run);
	int     butPressed = 0;
	String userName;	
	Properties props = new Properties();
	
	//Display Elements
	NavigationBar navBar      	= new NavigationBar(run,true,false,"Parental Controls");
	
	JPanel  pinBox         		= new JPanel();
	JPanel  pageBox           	= new JPanel();
	
	JButton pinButton       	= new JButton("Submit");
	JButton pinForgotButton		= new JButton("Forgot Pin?");
	JButton saveButton     		= new JButton("Submit");
	JButton cancelButton      	= new JButton("Cancel");
	JButton defaultButton     	= new JButton("default");
	
	JPasswordField pinPF   		= new JPasswordField();
	
	JTextField emailTF   		= new JTextField();
	
	JLabel  pinLabel      		= new JLabel("Enter Your Parent Pin",SwingConstants.CENTER);
	JLabel  pinErrorLabel 		= new JLabel("Incorrect Pin",SwingConstants.CENTER);
	JLabel  timeControlsLabel 	= new JLabel("Time Controls");
	JLabel  gameControlsLabel 	= new JLabel("Lock/Unlock Games");
	JLabel  alertsLabel       	= new JLabel("Alerts");
	JLabel  hourLabel         	= new JLabel("hr",SwingConstants.CENTER);
	JLabel  minuteLabel       	= new JLabel("min",SwingConstants.CENTER);
	JLabel  emailFieldLabel   	= new JLabel("Email: ",SwingConstants.RIGHT);
	JLabel  message				= new JLabel("Please insert a valid pin [xxxx]");
	
	JCheckBox timeCB   			= new JCheckBox("Time (per day)");
	JCheckBox Game1CB    		= new JCheckBox("Game 1");
	JCheckBox Game2CB    		= new JCheckBox("Game 2");
	JCheckBox Game3CB    		= new JCheckBox("Game 3");
	JCheckBox Game4CB    		= new JCheckBox("Game 4");
	JCheckBox EmailCB    		= new JCheckBox("Email me when child submits test");
	
	
	JSpinner hourTF   			= new JSpinner();
	JSpinner minuteTF 			= new JSpinner();
	
	JSeparator timeControlsSep	= new JSeparator();
	JSeparator gameControlsSep	= new JSeparator();
	JSeparator alertsSep      	= new JSeparator();
	
	public ParentControlsScreen(Runner run) 
	{
		super(run);
		
		//Basic Frame Settings
		run.setTitle(run.getUserName()+" | Parental Controls");
		
		//resize stuff
		run.addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent e)
			{
				redraw = true;
			}
		});
		
		//Enable/disable Elemenst
		pinErrorLabel.setVisible(false);
		message.setVisible(false);
		enableControls(false);
		enablePin(true);
		pinForgotButton.setEnabled(false);
		
		//Set colors
		this.setBackground(Color.WHITE);
		pinErrorLabel.setForeground(Color.RED);
		pinBox.setBackground(Color.LIGHT_GRAY);
		pageBox.setBackground(Color.LIGHT_GRAY);
		timeCB.setBackground(Color.LIGHT_GRAY);
		Game1CB.setBackground(Color.LIGHT_GRAY);
		Game2CB.setBackground(Color.LIGHT_GRAY);
		Game3CB.setBackground(Color.LIGHT_GRAY);
		Game4CB.setBackground(Color.LIGHT_GRAY);
		EmailCB.setBackground(Color.LIGHT_GRAY);
		message.setForeground(Color.RED);
		
		////Buttons////
		//Enter Key is Pressed
		pinPF.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				checkPin();
			}
		});
		
		//pinButton
		pinButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				checkPin();				
			}
		});
		
		
		//saveButton
		saveButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 1;				
			}
		});
		
		//cancelButton
		cancelButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 2;				
			}
		});
		
		//EmailCB
		EmailCB.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 3;				
			}
		});
		
		
		//add things to the panel
		this.add(pinErrorLabel);
		this.add(pinLabel);
		this.add(pinPF);
		this.add(pinButton);
		//this.add(pinForgotButton);
		this.add(message);
		this.add(pinBox);
		//this.add(timeControlsSep);
		//this.add(timeControlsLabel);
		this.add(gameControlsLabel);
		this.add(gameControlsSep);
		//this.add(alertsLabel);
		//this.add(alertsSep);
		//this.add(timeCB);
		//this.add(hourTF);
		//this.add(minuteTF);
		//this.add(hourLabel);
		//this.add(minuteLabel);
		this.add(Game1CB);
		this.add(Game2CB);
		this.add(Game3CB);
		this.add(Game4CB);
		//this.add(EmailCB);
		//this.add(emailTF);
		//this.add(emailFieldLabel);	
		this.add(cancelButton);
		this.add(saveButton);
		this.add(pageBox);
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
			
			//pinBox
			pinBox.setBounds(resize.locationX(100), resize.locationY(200), resize.width(300), resize.height(160));
			pinBox.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			
			//pinLabel
			pinLabel.setBounds(resize.locationX(170), resize.locationY(220), resize.width(160), resize.height(20));
			pinLabel.setFont(new Font(pinLabel.getFont().getFontName(),pinLabel.getFont().getStyle(), resize.font(12)));
			
			//message
			message.setBounds(resize.locationX(160), resize.locationY(260), resize.width(180), resize.height(20));
			message.setFont(new Font(message.getFont().getFontName(),message.getFont().getStyle(), resize.font(12)));
			
			
			//pinPF
			pinPF.setBounds(resize.locationX(230), resize.locationY(240), resize.width(40), resize.height(20));
			pinPF.setFont(new Font(pinPF.getFont().getFontName(),pinPF.getFont().getStyle(), resize.font(12)));
			
			//pinButton
			pinButton.setBounds(resize.locationX(170), resize.locationY(300), resize.width(160), resize.height(30));
			pinButton.setFont(new Font(pinButton.getFont().getFontName(),pinButton.getFont().getStyle(), resize.font(12)));
			
			//pinForgotButton
			pinForgotButton.setBounds(resize.locationX(170), resize.locationY(310), resize.width(160), resize.height(20));
			pinForgotButton.setFont(new Font(pinForgotButton.getFont().getFontName(),pinForgotButton.getFont().getStyle(), resize.font(12)));
			
			//pinError
			pinErrorLabel.setBounds(resize.locationX(100), resize.locationY(280), resize.width(300), resize.height(20));
			pinErrorLabel.setFont(new Font(pinErrorLabel.getFont().getFontName(),pinErrorLabel.getFont().getStyle(), resize.font(12)));
					
			//pageBox
			pageBox.setBounds(resize.locationX(80), resize.locationY(160), resize.width(340), resize.height(150));
			pageBox.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			
			//timeControls
			timeControlsLabel.setBounds(resize.locationX(100), resize.locationY(100), resize.width(300), resize.height(20));
			timeControlsLabel.setFont(new Font(timeControlsLabel.getFont().getFontName(),timeControlsLabel.getFont().getStyle(), resize.font(12)));
			timeControlsSep.setBounds(resize.locationX(100), resize.locationY(120), resize.width(300), resize.height(20));
			
			
			//timeCB
			timeCB.setBounds(resize.locationX(100), resize.locationY(150), resize.width(150), resize.height(20));
			timeCB.setFont(new Font(timeCB.getFont().getFontName(),timeCB.getFont().getStyle(), resize.font(12)));
			
			//timeExact
			hourLabel.setBounds(resize.locationX(245), resize.locationY(130), resize.width(30), resize.height(20));
			hourLabel.setFont(new Font(hourLabel.getFont().getFontName(),hourLabel.getFont().getStyle(), resize.font(12)));
			hourTF.setBounds(resize.locationX(250), resize.locationY(150), resize.width(40), resize.height(20));
			hourTF.setFont(new Font(hourTF.getFont().getFontName(),hourTF.getFont().getStyle(), resize.font(12)));
			minuteLabel.setBounds(resize.locationX(270), resize.locationY(130), resize.width(30), resize.height(20));
			minuteLabel.setFont(new Font(minuteLabel.getFont().getFontName(),minuteLabel.getFont().getStyle(), resize.font(12)));
			minuteTF.setBounds(resize.locationX(295), resize.locationY(150), resize.width(40), resize.height(20));
			minuteTF.setFont(new Font(minuteTF.getFont().getFontName(),minuteTF.getFont().getStyle(), resize.font(12)));
			
			//GameControls
			gameControlsLabel.setBounds(resize.locationX(100), resize.locationY(180), resize.width(300), resize.height(20));
			gameControlsLabel.setFont(new Font(gameControlsLabel.getFont().getFontName(),gameControlsLabel.getFont().getStyle(), resize.font(12)));
			gameControlsSep.setBounds(resize.locationX(100), resize.locationY(200), resize.width(300), resize.height(20));
			
			//GameCB
			Game1CB.setBounds(resize.locationX(100), resize.locationY(210), resize.width(150), resize.height(20));
			Game1CB.setFont(new Font(Game1CB.getFont().getFontName(),Game1CB.getFont().getStyle(), resize.font(12)));
			
			Game2CB.setBounds(resize.locationX(250), resize.locationY(210), resize.width(150), resize.height(20));
			Game2CB.setFont(new Font(Game2CB.getFont().getFontName(),Game2CB.getFont().getStyle(), resize.font(12)));
			
			Game3CB.setBounds(resize.locationX(100), resize.locationY(240), resize.width(150), resize.height(20));
			Game3CB.setFont(new Font(Game3CB.getFont().getFontName(),Game3CB.getFont().getStyle(), resize.font(12)));
			
			Game4CB.setBounds(resize.locationX(250), resize.locationY(240), resize.width(150), resize.height(20));
			Game4CB.setFont(new Font(Game4CB.getFont().getFontName(),Game4CB.getFont().getStyle(), resize.font(12)));
			
			//alerts
			alertsLabel.setBounds(resize.locationX(100), resize.locationY(300), resize.width(300), resize.height(20));
			alertsLabel.setFont(new Font(alertsLabel.getFont().getFontName(),alertsLabel.getFont().getStyle(), resize.font(12)));
			alertsSep.setBounds(resize.locationX(100), resize.locationY(320), resize.width(300), resize.height(20));
			
			//EmailCB
			EmailCB.setBounds(resize.locationX(100), resize.locationY(330), resize.width(300), resize.height(20));
			EmailCB.setFont(new Font(EmailCB.getFont().getFontName(),EmailCB.getFont().getStyle(), resize.font(12)));
			
			//EmailTF
			emailFieldLabel.setBounds(resize.locationX(100), resize.locationY(360), resize.width(50), resize.height(20));
			emailFieldLabel.setFont(new Font(emailFieldLabel.getFont().getFontName(),emailFieldLabel.getFont().getStyle(), resize.font(12)));
			emailTF.setBounds(resize.locationX(150), resize.locationY(360), resize.width(200), resize.height(20));
			emailTF.setFont(new Font(emailTF.getFont().getFontName(),emailTF.getFont().getStyle(), resize.font(12)));
			
			//DefaultButton
			
			//saveButton
			saveButton.setBounds(resize.locationX(125), resize.locationY(420), resize.width(100), resize.height(30));
			saveButton.setFont(new Font(saveButton.getFont().getFontName(),saveButton.getFont().getStyle(), resize.font(12)));
			
			//cancelButton
			cancelButton.setBounds(resize.locationX(275), resize.locationY(420), resize.width(100), resize.height(30));
			cancelButton.setFont(new Font(cancelButton.getFont().getFontName(),cancelButton.getFont().getStyle(), resize.font(12)));
			
			run.repaint();
			redraw = false;
		}
		
		if(butPressed == 1)
		{
			saveInfo();
			GameHubScreen ghs = new GameHubScreen(run);
			ghs.setUser(userName);
			run.setScreen(ghs);
		}
		else if(butPressed == 2 || navBar.backButtonPressed)
		{
			GameHubScreen ghs = new GameHubScreen(run);
			ghs.setUser(userName);
			run.setScreen(ghs);
		}
		else if(butPressed == 3 && !emailTF.isEnabled())
		{
			emailTF.setEnabled(true);
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
	
	private void checkInfo()
	{
		if(props.getProperty("Time").equals("true"))
		{
			timeCB.setSelected(true);
		}
		if(props.getProperty("Game_1").equals("false"))
		{
			Game1CB.setSelected(true);
		}
		if(props.getProperty("Game_2").equals("false"))
		{
			Game2CB.setSelected(true);
		}
		if(props.getProperty("Game_3").equals("false"))
		{
			Game3CB.setSelected(true);
		}
		if(props.getProperty("Game_4").equals("false"))
		{
			Game4CB.setSelected(true);
		}
		if(props.getProperty("Email_Alerts").equals("true"))
		{
			EmailCB.setSelected(true);
			emailTF.setText(props.getProperty("Email"));
		}
		else
		{
			EmailCB.setSelected(false);
			emailTF.setEnabled(false);
		}
		
			
	}
	
	private void saveInfo()
	{
		if(timeCB.isSelected())
		{
			props.setProperty("Time", "true");
		}
		else
		{
			props.setProperty("Time", "false");
		}
		if(Game1CB.isSelected())
		{
			props.setProperty("Game_1", "false");
		}
		else
		{
			props.setProperty("Game_1", "true");
		}
		if(Game2CB.isSelected())
		{
			props.setProperty("Game_2", "false");
		}
		else
		{
			props.setProperty("Game_2", "true");
		}
		if(Game3CB.isSelected())
		{
			props.setProperty("Game_3", "false");
		}
		else
		{
			props.setProperty("Game_3", "true");
		}
		if(Game4CB.isSelected())
		{
			props.setProperty("Game_4", "false");
		}
		else
		{
			props.setProperty("Game_4", "true");
		}
		if(EmailCB.isSelected() && !emailTF.getText().isEmpty())
		{
			props.setProperty("Email_Alerts", "true");
			props.setProperty("Email", emailTF.getText());
		}
		try
		{
			FileWriter write = new FileWriter("resources/interface/parent_controls/" + userName + ".properties");
			props.store(write, "User:" + userName);
			write.close();
		}
		catch(Exception e)
		{
			System.out.println("Failed to save");
		}
	}
	
	private void checkPin() 
	{
		try
		{
			FileInputStream in = new FileInputStream("resources/interface/parent_controls/" + userName + ".properties");
			props.load(in);
			in.close();
		}
		//String pin = props.getProperty("PIN");
		catch(Exception e)
		{
			System.out.println("Failed to load parent controls properties");
		}
		
		if(pinPF.getText().equals(props.getProperty("PIN")))
		{
			enablePin(false);
			enableControls(true);
			checkInfo();
		}
		else
		{
			pinErrorLabel.setVisible(true);
		}
	}
	
	public void enableControls(boolean yes)
	{
		pageBox.setVisible(yes);
		timeControlsLabel.setVisible(yes);
		timeControlsSep.setVisible(yes);
		timeCB.setVisible(yes);
		hourLabel.setVisible(yes);
		hourTF.setVisible(yes);
		minuteLabel.setVisible(yes);
		minuteTF.setVisible(yes);
		gameControlsLabel.setVisible(yes);
		gameControlsSep.setVisible(yes);
		Game1CB.setVisible(yes);
		Game2CB.setVisible(yes);
		Game3CB.setVisible(yes);
		Game4CB.setVisible(yes);
		alertsLabel.setVisible(yes);
		alertsSep.setVisible(yes);
		EmailCB.setVisible(yes);
		emailFieldLabel.setVisible(yes);
		emailTF.setVisible(yes);
		saveButton.setVisible(yes);
		cancelButton.setVisible(yes);
	}
	public void enablePin(boolean yes)
	{
		pinBox.setVisible(yes);
		pinLabel.setVisible(yes);
		pinPF.setVisible(yes);
		pinButton.setVisible(yes);
		pinForgotButton.setVisible(yes);
		pinErrorLabel.setVisible(false);
	}

	public void setUser(String uName)
	{
		userName = uName;
	}
}
