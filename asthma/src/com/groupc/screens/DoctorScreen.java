package com.groupc.screens;

/*
 * Author(s):		Team C
 * Course: 			CST 316 Spring
 * Instructor:		Dr. Gary
 * Date Changed:	4/1/2015
 * 
 * Description:		DoctorScreen is the first screen that appears after a doctor logs in.
 * 					This screen will display a list of patients linked to the doctor that logged in.
 * 					The doctor is able to browse through a list of patients and also add a patient.
 */

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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.groupc.Patient;
import com.groupc.Runner;
import com.groupc.math.Resize;

public class DoctorScreen extends Screen
{
	//Variables
	boolean redraw		= true;
	boolean played 		= true;
	Resize  resize     	= new Resize(run);
	int     butPressed 	= 0; //0 is none, 1 is add patient, 2 is back and logout for now
	
	String doctor;
	
	//Patient information
	Vector<String> patientFirstNames 	= new Vector<String>();
	Vector<String> patientLastNames 	= new Vector<String>();
	Vector<String> patDoctors 			= new Vector<String>();
	
	//Table elements
	JTable patientTable;
	JScrollPane jsp;
	
	
	//Display Elements
	JPanel testBox          		= new JPanel();
	JPanel navBox           		= new JPanel();
	JPanel navBoxBorder     		= new JPanel();
	JPanel infoBox					= new JPanel();
	
	JButton addPatientButton 		= new JButton("Add Patient");
	JButton deletePatientButton		= new JButton("Delete Patient");
	
	NavigationBar navBar           	= new NavigationBar(run,false,false,"Doctor Page");
	
	public DoctorScreen(Runner run) 
	{
		super(run);
		
		//Basic Frame Settings
		run.setTitle(run.getUserName()+" | Doctor Page");
		
		
		//button font color
		deletePatientButton.setBackground(Color.RED);
		deletePatientButton.setForeground(Color.BLACK);
		deletePatientButton.setBorderPainted(false);

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

		
		// Add patient button listener
		addPatientButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 1;				
			}
		});		
		
		//add things to the panel
		this.add(addPatientButton);
		this.add(deletePatientButton);
		this.add(navBox);
		this.add(testBox);
		this.add(navBar);
		this.add(infoBox);
		
		this.setLayout(null);		
		run.setContentPane(this);
		run.setVisible(true);
	}
	
	protected void setDoctor(String docName)
	{
		if(docName != null)
		{
			doctor = docName;
			//run.setTitle(doctor + "s' page");
		}else
		{
			System.out.println("null doctor name");
			doctor = "doctor";
			//run.setTitle(doctor);
		}
	}
	
	protected void getPatients() throws IOException
	{
		String line = null;
		int counter = 0;
		
		FileReader fr = new FileReader("login_information.txt");
		BufferedReader br = new BufferedReader(fr);
		StringTokenizer st;
		
		
		while((line = br.readLine()) != null)
		{
			st = new StringTokenizer(line, " | ");
			st.nextToken(); //email
			patientFirstNames.add(counter, st.nextToken()); //fname
			patientLastNames.add(st.nextToken());  //last name
			st.nextToken();  //dob
			st.nextToken();  //password
			st.nextToken(); //type
			patDoctors.add(counter, st.nextToken()); //doctor for patient
			
			counter += 1;
			
			
		}
		br.close();
		
		tableDisplay();		
	}
	
	private void tableDisplay()
	{
		
		Vector<String> collumnNames = new Vector<String>();
		collumnNames.add("First Name");
		collumnNames.add("Last Name");
		//collumnNames.add("age");
		Vector<Vector<String>> rowData = new Vector<Vector<String>>(10);
		for(int i = 0; i < patientFirstNames.size(); ++i)  
		{
			if(patDoctors.elementAt(i).equals(doctor)) 
			{
				Vector<String> tempVector = new Vector<String>(2);//set values in temp vector in order for what you want to display
				tempVector.add(patientFirstNames.elementAt(i)); 
				tempVector.add(patientLastNames.elementAt(i));
				rowData.add(tempVector);
			}
		}
		
		//set table contents
		patientTable = new JTable(rowData, collumnNames);
		jsp = new JScrollPane(patientTable);
		//set table size
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
			addPatientButton.setBounds(resize.locationX(100), resize.locationY(400), resize.width(120), resize.height(40));
			addPatientButton.setFont(new Font(addPatientButton.getFont().getFontName(),addPatientButton.getFont().getStyle(), resize.font(12)));
			

			//delete patientButton
			deletePatientButton.setBounds(resize.locationX(290), resize.locationY(400), resize.width(120), resize.height(40));
			deletePatientButton.setFont(new Font(deletePatientButton.getFont().getFontName(),deletePatientButton.getFont().getStyle(), resize.font(12)));
			
			//Patient table
			jsp.setBounds(resize.locationX(40), resize.locationY(120), resize.width(200), resize.height(250));
			
			//loginBox
			infoBox.setBounds(resize.locationX(270), resize.locationY(120), resize.width(200), resize.height(250));
			infoBox.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			
			run.repaint();
			redraw = false;
		}
		
		if(butPressed == 1)
		{
			AccountCreationScreen acs = new AccountCreationScreen(run);
			acs.setPatientDoctor(doctor);
			run.setScreen(acs);
			System.out.println("changing screen");
		}
		else if(butPressed == 2)
		{
			run.setScreen(new LoginScreen(run));
		}
		
		butPressed = 0;
		navBar.update();
	}

	@Override
	public void present(float deltaTime)
	{
		
		
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