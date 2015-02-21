package com.groupc.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	
	//Display Elements
	NavigationBar navBar           = new NavigationBar(run,false,false,"Doctor Page");
	JButton       addPatientButton = new JButton("Add Patient");
	
	public DoctorScreen(Runner run) 
	{
		super(run);
		
		//Basic Frame Settings
		run.setTitle("Doctor");
		
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
			
			//add patientButton
			addPatientButton.setBounds(resize.locationX(180), resize.locationY(200), resize.width(120), resize.height(40));
			addPatientButton.setFont(new Font(addPatientButton.getFont().getFontName(),addPatientButton.getFont().getStyle(), resize.font(12)));
			
			run.repaint();
			redraw = false;
		}
		
		if(butPressed == 1)
		{
			run.setScreen(new AccountCreationScreen(run));
		}
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
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
