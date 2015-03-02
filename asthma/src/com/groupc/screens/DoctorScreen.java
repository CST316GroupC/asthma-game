package com.groupc.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.groupc.Patient;
import com.groupc.Runner;
import com.groupc.math.Resize;

public class DoctorScreen extends Screen
{
	//Variables
	boolean redraw     = true;
	Resize  resize     = new Resize(run);
	int     butPressed = 0; //0 is none, 1 is add patient, 2 is back and logout for now
	ArrayList<Patient> patients;
	boolean played = true;
	String doctor;
	JTable patientTable;
	JScrollPane jsp;
	
	//Display Elements
	JPanel  testBox          = new JPanel();
	JPanel  navBox           = new JPanel();
	JPanel  navBoxBorder     = new JPanel();
	JToggleButton navMuteButton    = new JToggleButton();
	JButton displayPatients  = new JButton("Display Patients"); 
	JButton addPatientButton = new JButton("Add Patient");
	NavigationBar navBar           = new NavigationBar(run,false,false,"Doctor Page");
	
	public DoctorScreen(Runner run) 
	{
		super(run);
		
		//Basic Frame Settings
		//moved screen name to setDoctor() to make doctor-specific page [Derek]
		
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
		
		//Set fonts
		
		////Buttons////

		// Add mute button
		navMuteButton.setSelectedIcon(new ImageIcon("UnMuteIcon.png"));
		navMuteButton.setIcon(new ImageIcon("MuteIcon.png"));
		navMuteButton.setBounds(340, 18, 30, 25);
		
		
		// Add patient button listener
		navMuteButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 3;				
			}
		});
		
		// Add patient button listener
		addPatientButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 1;				
			}
		});
		

		displayPatients.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 4;				
			}
		});
		
		
		//add things to the panel
		this.add(addPatientButton);
		this.add(displayPatients);
		this.add(navMuteButton);
		this.add(navBox);
		//this.add(navBoxBorder);
		this.add(testBox);
		this.add(navBar);
		
		this.setLayout(null);		
		run.setContentPane(this);
		run.setVisible(true);
	}
	
	public void setDoctor(String docName)
	{
		if(docName != null)
		{
			doctor = docName;
			run.setTitle(doctor + "s' page");
		}else
		{
			System.out.println("null doctor name");
			doctor = "doctor";
			run.setTitle(doctor);
		}
	}
	
	private void getPatients() throws IOException
	{
		String line = null;
		Vector<String> patientFirstNames = new Vector<String>(10);
		Vector<String> patientLastNames = new Vector<String>(10);
		Vector<String> types = new Vector<String>(10);
		Vector<String> patDoctors = new Vector<String>(10);
		int counter = 0;
		
		FileReader fr = new FileReader("login_information.txt");
		BufferedReader br = new BufferedReader(fr);
		StringTokenizer st;
		
		
		while((line = br.readLine()) != null)
		{
			st = new StringTokenizer(line, " | ");
			patientFirstNames.add(counter, st.nextToken()); //fname
			patientLastNames.add(st.nextToken());  //last name
			st.nextToken();  // age
			st.nextToken();  //password
			types.add(counter, st.nextToken()); //type
			patDoctors.add(counter, st.nextToken()); //doctor for patient
			
			counter += 1;
			
			
		}
		br.close();
		
		counter = 0;
		
		/*JTable testTable;
		String[] collumnNames = {"First Name", "Last Name"};
		String[][] tData = {{"bob", "bill"}, {"Joe", "schhmoe"}};	
		testTable = new JTable(tData, collumnNames);
		jsp = new JScrollPane(testTable);*/
		
		Vector<String> collumnNames = new Vector<String>();
		collumnNames.add("First Name");
		collumnNames.add("Last Name");
		//collumnNames.add("age");
		Vector<Vector<String>> rowData = new Vector<Vector<String>>(10);
		for(int i = 0; i < patientFirstNames.size(); ++i)
		{
			if(patDoctors.elementAt(i).equals(doctor))
			{
				Vector<String> tempVector = new Vector<String>(2);
				tempVector.add(patientFirstNames.elementAt(i)); 
				tempVector.add(patientLastNames.elementAt(i));
				rowData.add(tempVector);
			}
		}
		patientTable = new JTable(rowData, collumnNames);
		jsp = new JScrollPane(patientTable);
		
		tableDisplay();
		
	}
	
	private void tableDisplay()
	{
		jsp.setBounds(resize.locationX(150), resize.locationY(200), resize.width(200), resize.height(200));
		this.add(jsp);
	}

	@Override
	public void update(float deltaTime)
	{
		if(redraw)
		{	
			//navBar
			navBar.redrawUpdate();
			
			//add patientButton
			addPatientButton.setBounds(resize.locationX(190), resize.locationY(400), resize.width(120), resize.height(40));
			addPatientButton.setFont(new Font(addPatientButton.getFont().getFontName(),addPatientButton.getFont().getStyle(), resize.font(12)));
			
			//displays patients
			displayPatients.setBounds(resize.locationX(190), resize.locationY(445), resize.width(120), resize.height(40));
			displayPatients.setFont(new Font(displayPatients.getFont().getFontName(),displayPatients.getFont().getStyle(), resize.font(12)));
			
			run.repaint();
			redraw = false;
		}
		
		if(butPressed == 1)
		{
			AccountCreationScreen acs = new AccountCreationScreen(run);
			acs.setPatientDoctor(doctor);
			run.setScreen(acs);
		}
		else if(butPressed == 2)
		{
			run.setScreen(new LoginScreen(run));
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
		else if(butPressed == 4)
		{

			try {
				getPatients();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			butPressed = 0;
		}
		butPressed = 0;
		navBar.update();
	}

	@Override
	public void present(float deltaTime)
	{
		
		
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
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
