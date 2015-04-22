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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	boolean isDeleting  = false;
	Resize  resize     	= new Resize(run);
	int     butPressed 	= 0; //0 is none, 1 is add patient, 2 is back and logout for now
	
	String doctor = run.getUserName();
	
	//Patient information
	Vector<String> patientFirstNames 	= new Vector<String>();
	Vector<String> patientLastNames 	= new Vector<String>();
	Vector<String> patDoctors 			= new Vector<String>();
	Vector<Character> newInfo			= new Vector<Character>();
	
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
		
		//delete patient button
		deletePatientButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tableListener(patientTable.getSelectedRow());
				isDeleting = true;
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
	
	protected void getPatients() throws IOException
	{
		String line = null;
		int counter = 0;
		
		patientFirstNames.clear();
		patientLastNames.clear();
		patDoctors.clear();
		newInfo.clear();
		
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
			
			newInfo.add(counter, '1');
			
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
		jsp.setBounds(resize.locationX(40), resize.locationY(120), resize.width(200), resize.height(250));
		this.add(jsp);
	}
	
	private void deleteFromTable(String fname, String lname)
	{
		
		String FILENAME = "login_information.txt";
		
		String line = null;
		Vector<String> firstName = new Vector<String>();
		Vector<String> lastName = new Vector<String>();
		int counter = 0;
		
		File file = new File(FILENAME);
		File tempFile = new File(file.getAbsolutePath() + ".tmp");
		StringTokenizer st;
		
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(FILENAME));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
			
			while((line = br.readLine()) != null)
			{
				
				st = new StringTokenizer(line, " | ");
				st.nextToken(); //email
				firstName.add(st.nextToken()); //fname
				lastName.add(st.nextToken());  //last name
				st.nextToken();  //dob
				st.nextToken();  //password
				st.nextToken(); //type
				st.nextToken(); //doctor for patient
				
				if(!firstName.elementAt(counter).equals(fname) && !lastName.elementAt(counter).equals(lname))
				{
					pw.write(line + "\n");
				}
				counter += 1;
				
			}
			pw.close();
			br.close();
			
			 
		      if (!file.delete()) 
		      {
		        System.out.println("Could not delete file");
		        return;
		      }

		     
		      if (!tempFile.renameTo(file))
		      {
		        System.out.println("Could not rename file");
		      }
		      
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println(FILENAME + " is an invalid file path");
		} catch (IOException e) 
		{
			System.out.println(tempFile.getAbsolutePath() + " is an invalid file path");
		}
	      
	      
	    try 
	    {
	    	patientTable.removeAll();
			getPatients();
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void displayInformation(String fname, String lname, int row)
	{
		System.out.println(fname + " " + lname + " " + row);
	}
	
	private boolean tableListener(int row) 
	{
		
		if(isDeleting)
		{
			String fname = (String)patientTable.getValueAt(row, 0);
			String lname = (String) patientTable.getValueAt(row, 1);
			deleteFromTable(fname, lname);
			isDeleting = false;
			return false;
		}
		
		if(row >= 0 && !isDeleting)
		{
			if(newInfo.elementAt(row).equals('1'))
			{
				String fname = (String)patientTable.getValueAt(row, 0);
				String lname = (String) patientTable.getValueAt(row, 1);
				displayInformation(fname, lname, row);
				newInfo.set(row, '0');
				return true;
			}
		}
		
		return false;
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
			run.setScreen(acs);
		}
		else if(butPressed == 2)
		{
			run.setScreen(new LoginScreen(run));
		}
		
		butPressed = 0;
		navBar.update();
		
		if(tableListener(patientTable.getSelectedRow()))
		{
			
		}
		
		
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