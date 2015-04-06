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

import com.groupc.Runner;
import com.groupc.math.Resize;

public class RecordingScreen extends Screen
{
	//Variables
	boolean redraw		= true;
	boolean	played		= true;
	Resize  resize		= new Resize(run);
	int		butPressed	= 0;

	String patientUserName;
	
	Date date = new Date();
	
	//Display Elements
	NavigationBar 	navBar				= new NavigationBar(run,true,false,"Spirometer Input");	
	JLabel 			text2				= new JLabel("No Spirometer detected");
	JLabel 			text3				= new JLabel("Check connection or submit manual input");
	JLabel     		errorMessage   		= new JLabel("Missing Information*");
	JButton 		manualInputButton	= new JButton("Manual Input");
	JTextField 		manualReadingTF     = new JTextField();
	
	
	public RecordingScreen(Runner run)
	{
		super(run);
		//Basic Frame Settings moved to setpatient
		
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
		
		
		////Buttons////
		
		
		manualInputButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 4;
			}
		});	
		
		errorMessage.setVisible(false);
		
		this.add(text2);
		this.add(text3);
		this.add(manualInputButton);
		this.add(manualReadingTF);
		this.add(errorMessage);
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
			run.setTitle(patientUserName + " Spirometer Input");
			
		} 
		else
		{
			run.setTitle("Spriometer Input");
		}
		
		if(hasTakenReadings(patientUserName))
		{
			run.setScreen(new GameHubScreen(run));
		}
	}
	
	private boolean hasTakenReadings(String user)
	{
		String line = null;
		String tempString = date.toString();
		String currentDay = null;
		
		Vector<String> userNames = new Vector<String>();
		Vector<String> days = new Vector<String>();
		StringTokenizer st;
		
		try
		{
			FileReader fr = new FileReader("spirometer_readings.txt");
			BufferedReader br = new BufferedReader(fr);
			
			
			while((line = br.readLine()) != null)
			{
				st = new StringTokenizer(line, " | ");
				userNames.add(st.nextToken()); //user
				st.nextToken(); //input
				st.nextToken(); //day of week
				st.nextToken(); //month
				days.add(st.nextToken()); //day 
				st.nextToken(); //time
				st.nextToken(); //timezone
				st.nextToken(); //year
				
				
			}
			br.close();
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		st = new StringTokenizer(tempString);
		
		while(st.hasMoreElements())
		{
			st.nextToken(); // day of week
			st.nextToken(); // month
			currentDay = st.nextToken(); // day
			st.nextToken(); // time
			st.nextToken(); // timezone
			st.nextToken(); // year
		}
		
		for(int i = 0; i < userNames.size(); i++)
		{
			if(userNames.elementAt(i).equals(patientUserName))
			{
				if(days.elementAt(i).equals(currentDay))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean submitReadings() //submits patient username, the readings, and the current date
	{
		if(manualReadingTF.getText().isEmpty())
		{
			errorMessage.setVisible(true);
			return false;
		}
		else
		{
			try 
			{
				FileWriter fWriter = new FileWriter("spirometer_readings.txt", true);
				BufferedWriter bWriter = new BufferedWriter(fWriter);
				bWriter.write(patientUserName + " | " + manualReadingTF.getText() + " | " + date + "\n"); 
				bWriter.close();
			} 
			catch (IOException e)
			{
				e.printStackTrace();
				return false;
			}
		}
		return true;
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
			manualInputButton.setBounds(resize.locationX(170), resize.locationY(350), resize.width(130), resize.height(25));
			manualInputButton.setFont(new Font(manualInputButton.getFont().getFontName(),manualInputButton.getFont().getStyle(), resize.font(12)));	
			
			errorMessage.setBounds(resize.locationX(170), resize.locationY(330), resize.width(150), resize.height(20));
			errorMessage.setFont(new Font(errorMessage.getFont().getFontName(),errorMessage.getFont().getStyle(), resize.font(12)));
			
			manualReadingTF.setBounds(resize.locationX(170), resize.locationY(310), resize.width(130), resize.height(20));
			manualReadingTF.setFont(new Font(manualReadingTF.getFont().getFontName(),manualReadingTF.getFont().getStyle(), resize.font(12)));
			
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
			if(submitReadings())
			{
				run.setScreen(new RewardScreen(run));
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
