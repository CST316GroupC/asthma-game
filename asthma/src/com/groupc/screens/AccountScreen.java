package com.groupc.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.groupc.Database;
import com.groupc.Runner;
import com.groupc.math.Resize;

public class AccountScreen extends Screen 
{
	boolean redraw			= true;
	int		butPressed		= 0;
	Resize  resize     		= new Resize(run);
	String 	user 			= run.getUserName();
	NavigationBar 	navBar  = new NavigationBar(run,true,false,"Account Settings");
	Properties 		props 	= new Properties();
	
	
	//Display Elements
	JPanel		accountBox    	= new JPanel();
	
	JLabel 		passwordChangeLabel 	= new JLabel("Change Password",SwingConstants.CENTER);
	JLabel 		passwordCurrentLabel	= new JLabel("Current: ",SwingConstants.RIGHT);
	JLabel 		passwordNewLabel 		= new JLabel("New: ",SwingConstants.RIGHT);
	JLabel 		passwordNewRepeatLabel 	= new JLabel("Retype New: ",SwingConstants.RIGHT);
	JLabel 		pinChangeLabel 			= new JLabel("Change Parent Pin",SwingConstants.CENTER);
	JLabel 		pinCurrentLabel			= new JLabel("Current: ",SwingConstants.RIGHT);
	JLabel 		pinNewLabel 			= new JLabel("New: ",SwingConstants.RIGHT);
	JLabel 		pinNewRepeatLabel 		= new JLabel("Retype New: ",SwingConstants.RIGHT);
	JLabel 		highScoresLabel 		= new JLabel("HighScores",SwingConstants.CENTER);
	JLabel 		highScoresGame1Label 	= new JLabel("Game 1:",SwingConstants.CENTER);
	JLabel 		highScoresGame2Label 	= new JLabel("Game 2:",SwingConstants.CENTER);
	JLabel 		highScoresGame3Label 	= new JLabel("Game 3:",SwingConstants.CENTER);
	JLabel 		highScoresGame4Label 	= new JLabel("Game 4:",SwingConstants.CENTER);
	JLabel 		passwordErrorLabel 		= new JLabel("Password Error",SwingConstants.CENTER);
	JLabel 		pinErrorLabel 			= new JLabel("Pin Error",SwingConstants.CENTER);
	
	JLabel 		highScoresGame1OutPut 	= new JLabel("0",SwingConstants.CENTER);
	JLabel 		highScoresGame2OutPut 	= new JLabel("0",SwingConstants.CENTER);
	JLabel 		highScoresGame3OutPut 	= new JLabel("0",SwingConstants.CENTER);
	JLabel 		highScoresGame4OutPut 	= new JLabel("0",SwingConstants.CENTER);
	
	JPasswordField passwordOldPF   	= new JPasswordField();
	JPasswordField passwordNewPF   	= new JPasswordField();
	JPasswordField passwordRetypePF = new JPasswordField();
	JPasswordField pinOldPF   		= new JPasswordField();
	JPasswordField pinNewPF   		= new JPasswordField();
	JPasswordField pinRetypePF   	= new JPasswordField();
	
	JButton		changePasswordButton 	= new JButton("Change");
	JButton		changePinButton 		= new JButton("Change");
	
	JSeparator highScoreSep			= new JSeparator();
	JSeparator passwordChangeSep	= new JSeparator();
	JSeparator pinChangeSep			= new JSeparator();
	
	public AccountScreen(Runner run) 
	{
		super(run);
		
		//Basic Frame Settings
		run.setTitle(run.getUserName()+" | Account Settings");
		
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
		accountBox.setBackground(Color.LIGHT_GRAY);
		passwordErrorLabel.setForeground(Color.RED);
		pinErrorLabel.setForeground(Color.RED);
		
		//setFont
		Font boldFont = highScoresGame1Label.getFont().deriveFont(Font.BOLD);
		highScoresGame1Label.setFont(boldFont);
		highScoresGame2Label.setFont(boldFont);
		highScoresGame3Label.setFont(boldFont);
		highScoresGame4Label.setFont(boldFont);
		
		//Get Player Highscores Scores
		loadHighScores();
		
		//Button Presses
		changePasswordButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				butPressed = 1;				
			}
		});
		changePinButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				butPressed = 2;				
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
		this.add(highScoreSep);
		this.add(passwordOldPF);
		this.add(passwordNewPF);
		this.add(passwordRetypePF);
		this.add(pinOldPF);
		this.add(pinNewPF);
		this.add(pinRetypePF);
		this.add(passwordErrorLabel);
		this.add(pinErrorLabel);
		this.add(passwordChangeSep);
		this.add(pinChangeSep);
		this.add(changePasswordButton);
		this.add(changePinButton);
		this.add(accountBox);
		this.add(navBar);
		
		this.setLayout(null);
		run.setContentPane(this);
		run.setVisible(true);
	}
	
	private void loadHighScores()
	{
		try 
		{
			FileInputStream in = new FileInputStream("resources/interface/parent_controls/"+user+".properties");
			props.load(in);
			in.close();
			/*String temp = props.getProperty("tokenfirstTime", "true");
			if("true".equals(temp))
			{
				props.setProperty("tokens", ""+tokenTotal);
				props.setProperty("tokenfirstTime", "false");
			}*/
			highScoresGame1OutPut.setText(props.getProperty("joeyscore", "0"));
			highScoresGame2OutPut.setText(props.getProperty("mazeScore", "0"));
			highScoresGame3OutPut.setText(props.getProperty("game3MaxScore", "0"));
			highScoresGame4OutPut.setText(props.getProperty("game4MaxScore", "0"));
			
			//tokenTotal = Integer.parseInt(props.getProperty("tokens", "0"));
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		} 
	}

	@Override
	public void update(float deltaTime) 
	{
		
		if(redraw)
		{	
			//navBar
			navBar.redrawUpdate();
			accountBox.setBounds(resize.locationX(50), resize.locationY(100), resize.width(400), resize.height(350));
			accountBox.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			
			//Password Change
			passwordChangeLabel.setBounds(resize.locationX(50), resize.locationY(275), resize.width(200), resize.height(20));
			passwordChangeLabel.setFont(new Font(passwordChangeLabel.getFont().getFontName(),passwordChangeLabel.getFont().getStyle(), resize.font(12)));
			passwordChangeSep.setBounds(resize.locationX(55), resize.locationY(295), resize.width(190), resize.height(20));
			passwordCurrentLabel.setBounds(resize.locationX(50), resize.locationY(315), resize.width(100), resize.height(20));
			passwordCurrentLabel.setFont(new Font(passwordChangeLabel.getFont().getFontName(),passwordChangeLabel.getFont().getStyle(), resize.font(12)));
			passwordNewLabel.setBounds(resize.locationX(50), resize.locationY(335), resize.width(100), resize.height(20));
			passwordNewLabel.setFont(new Font(passwordChangeLabel.getFont().getFontName(),passwordChangeLabel.getFont().getStyle(), resize.font(12)));
			passwordNewRepeatLabel.setBounds(resize.locationX(50), resize.locationY(355), resize.width(100), resize.height(20));
			passwordNewRepeatLabel.setFont(new Font(passwordChangeLabel.getFont().getFontName(),passwordChangeLabel.getFont().getStyle(), resize.font(12)));
			changePasswordButton.setBounds(resize.locationX(100), resize.locationY(395), resize.width(100), resize.height(30));
			changePasswordButton.setFont(new Font(passwordChangeLabel.getFont().getFontName(),passwordChangeLabel.getFont().getStyle(), resize.font(12)));
			
			passwordOldPF.setBounds(resize.locationX(150), resize.locationY(315), resize.width(95), resize.height(20));
			passwordOldPF.setFont(new Font(passwordChangeLabel.getFont().getFontName(),passwordChangeLabel.getFont().getStyle(), resize.font(12)));
			passwordNewPF.setBounds(resize.locationX(150), resize.locationY(335), resize.width(95), resize.height(20));
			passwordNewPF.setFont(new Font(passwordChangeLabel.getFont().getFontName(),passwordChangeLabel.getFont().getStyle(), resize.font(12)));
			passwordRetypePF.setBounds(resize.locationX(150), resize.locationY(355), resize.width(95), resize.height(20));
			passwordRetypePF.setFont(new Font(passwordChangeLabel.getFont().getFontName(),passwordChangeLabel.getFont().getStyle(), resize.font(12)));
			
			passwordErrorLabel.setBounds(resize.locationX(50), resize.locationY(375), resize.width(200), resize.height(20));
			passwordErrorLabel.setFont(new Font(passwordChangeLabel.getFont().getFontName(),passwordChangeLabel.getFont().getStyle(), resize.font(12)));
			
			//Pin Change
			pinChangeLabel.setBounds(resize.locationX(250), resize.locationY(275), resize.width(200), resize.height(20));
			pinChangeLabel.setFont(new Font(pinChangeLabel.getFont().getFontName(),pinChangeLabel.getFont().getStyle(), resize.font(12)));
			pinChangeSep.setBounds(resize.locationX(255), resize.locationY(295), resize.width(190), resize.height(20));
			pinCurrentLabel.setBounds(resize.locationX(250), resize.locationY(315), resize.width(100), resize.height(20));
			pinCurrentLabel.setFont(new Font(pinChangeLabel.getFont().getFontName(),pinChangeLabel.getFont().getStyle(), resize.font(12)));
			pinNewLabel.setBounds(resize.locationX(250), resize.locationY(335), resize.width(100), resize.height(20));
			pinNewLabel.setFont(new Font(pinChangeLabel.getFont().getFontName(),pinChangeLabel.getFont().getStyle(), resize.font(12)));
			pinNewRepeatLabel.setBounds(resize.locationX(250), resize.locationY(355), resize.width(100), resize.height(20));
			pinNewRepeatLabel.setFont(new Font(pinChangeLabel.getFont().getFontName(),pinChangeLabel.getFont().getStyle(), resize.font(12)));
			changePinButton.setBounds(resize.locationX(300), resize.locationY(395), resize.width(100), resize.height(30));
			changePinButton.setFont(new Font(pinChangeLabel.getFont().getFontName(),pinChangeLabel.getFont().getStyle(), resize.font(12)));
			
			pinOldPF.setBounds(resize.locationX(350), resize.locationY(315), resize.width(95), resize.height(20));
			pinOldPF.setFont(new Font(pinChangeLabel.getFont().getFontName(),pinChangeLabel.getFont().getStyle(), resize.font(12)));
			pinNewPF.setBounds(resize.locationX(350), resize.locationY(335), resize.width(95), resize.height(20));
			pinNewPF.setFont(new Font(pinChangeLabel.getFont().getFontName(),pinChangeLabel.getFont().getStyle(), resize.font(12)));
			pinRetypePF.setBounds(resize.locationX(350), resize.locationY(355), resize.width(95), resize.height(20));
			pinRetypePF.setFont(new Font(pinChangeLabel.getFont().getFontName(),pinChangeLabel.getFont().getStyle(), resize.font(12)));
			
			pinErrorLabel.setBounds(resize.locationX(250), resize.locationY(375), resize.width(200), resize.height(20));
			pinErrorLabel.setFont(new Font(pinChangeLabel.getFont().getFontName(),pinChangeLabel.getFont().getStyle(), resize.font(12)));
			
			//HighScores
			highScoresLabel.setBounds(resize.locationX(50), resize.locationY(100), resize.width(400), resize.height(20));
			highScoresLabel.setFont(new Font(highScoresLabel.getFont().getFontName(),highScoresLabel.getFont().getStyle(), resize.font(12)));
			highScoreSep.setBounds(resize.locationX(55), resize.locationY(120), resize.width(390), resize.height(20));
			
			highScoresGame1Label.setBounds(resize.locationX(50), resize.locationY(140), resize.width(200), resize.height(20));
			highScoresGame1Label.setFont(new Font(highScoresGame1Label.getFont().getFontName(),highScoresGame1Label.getFont().getStyle(), resize.font(12)));
			highScoresGame2Label.setBounds(resize.locationX(250), resize.locationY(140), resize.width(200), resize.height(20));
			highScoresGame2Label.setFont(new Font(highScoresGame1Label.getFont().getFontName(),highScoresGame1Label.getFont().getStyle(), resize.font(12)));
			highScoresGame3Label.setBounds(resize.locationX(50), resize.locationY(200), resize.width(200), resize.height(20));
			highScoresGame3Label.setFont(new Font(highScoresGame1Label.getFont().getFontName(),highScoresGame1Label.getFont().getStyle(), resize.font(12)));
			highScoresGame4Label.setBounds(resize.locationX(250), resize.locationY(200), resize.width(200), resize.height(20));
			highScoresGame4Label.setFont(new Font(highScoresGame1Label.getFont().getFontName(),highScoresGame1Label.getFont().getStyle(), resize.font(12)));
			highScoresGame1OutPut.setBounds(resize.locationX(50), resize.locationY(160), resize.width(200), resize.height(20));
			highScoresGame1OutPut.setFont(new Font(highScoresGame1Label.getFont().getFontName(),highScoresGame1Label.getFont().getStyle(), resize.font(12)));
			highScoresGame2OutPut.setBounds(resize.locationX(250), resize.locationY(160), resize.width(200), resize.height(20));
			highScoresGame2OutPut.setFont(new Font(highScoresGame1Label.getFont().getFontName(),highScoresGame1Label.getFont().getStyle(), resize.font(12)));
			highScoresGame3OutPut.setBounds(resize.locationX(50), resize.locationY(220), resize.width(200), resize.height(20));
			highScoresGame3OutPut.setFont(new Font(highScoresGame1Label.getFont().getFontName(),highScoresGame1Label.getFont().getStyle(), resize.font(12)));
			highScoresGame4OutPut.setBounds(resize.locationX(250), resize.locationY(220), resize.width(200), resize.height(20));
			highScoresGame4OutPut.setFont(new Font(highScoresGame1Label.getFont().getFontName(),highScoresGame1Label.getFont().getStyle(), resize.font(12)));
			
			run.repaint();
			redraw = false;
		}
		
		
		if(navBar.backButtonPressed)
		{
			run.setScreen(new GameHubScreen(run));
		}
		else if(butPressed == 1) //Change password button
		{
			Database.updatepass(run.getUserName(), passwordOldPF.getText(), passwordNewPF.getText());
		}
		else if(butPressed == 2) //Change pin button
		{
			Database.updatepin(run.getUserName(), pinOldPF.getText(), pinNewPF.getText());
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
