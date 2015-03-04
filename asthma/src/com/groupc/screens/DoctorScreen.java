package com.groupc.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.groupc.Patient;
import com.groupc.Runner;


public class DoctorScreen extends Screen
{

	JButton addPatient;
	ArrayList<Patient> patients;
	JLabel lbl;
	JLabel pageTitle;
	JPanel box;
	JPanel boxBorder;
	JButton back;
	JButton logout;
	JButton mute;
	
	int butPressed = 0; //0 is none, 1 is add patient, 2 is back and logout for now
	
	public DoctorScreen(Runner run) 
	{
		super(run);
		run.setTitle("Doctor");
		
		// Box display for title
		box = new JPanel();
		boxBorder = new JPanel();
		
		box.setBackground(Color.LIGHT_GRAY);
		boxBorder.setBackground(Color.BLACK);
		
		box.setBounds(18, 0, 450, 60);
		boxBorder.setBounds(17, 0, 452, 61);
		
		
		// Page title
		pageTitle = new JLabel("Doctor Page");
		pageTitle.setFont(new Font("Serif", Font.BOLD, 25));
		pageTitle.setBounds(180, 60, 350, 40);
		
		
		// Add patient button
		addPatient = new JButton("Add Patient");
		addPatient.setBounds(180, 200, 120, 40);
		
		// Add back button
		back = new JButton("Back");
		back.setBounds(25, 20, 80, 35);
		
		// Add logout button
		logout = new JButton("Logout");
		logout.setBounds(380, 20, 80, 35);
		
		// Add mute button
		mute = new JButton("Mute");
		mute.setBounds(300, 24, 70, 25);
		
		mute.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 3;				
			}
		});
		
		// Add patient button listener
		addPatient.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 1;				
			}
		});
		
		// Add back button listener
		back.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 2;				
			}
		});
		
		
		// Add logout button listener
		logout.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 2;				
			}
		});
		
		
		this.add(pageTitle);
		this.add(addPatient);
		this.add(back);
		this.add(logout);
		this.add(mute);
		this.add(box);
		this.add(boxBorder);
		
		this.setLayout(null);		
		run.setContentPane(this);
		run.setVisible(true);
	}

	@Override
	public void update(float deltaTime)
	{
		if(butPressed == 1)
		{
			run.setScreen(new AccountCreationScreen(run));
		}
		else if(butPressed == 2)
		{
			run.setScreen(new LoginScreen(run));
		}
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
