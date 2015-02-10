package com.groupc.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.groupc.Runner;


public class AccountCreationScreen extends Screen
{

	JPanel pnl;
	
	JLabel lblfname;
	JLabel lbllname;
	JLabel lblage;
	JLabel lblcinfo;
	JLabel preferredPassword;
	
	JTextField fname;
	JTextField password;
	JTextField lname;
	JTextField age;
	JTextArea cinfo;
	JButton submitButton;
	int type = 1;
	public AccountCreationScreen(Runner run) {
		super(run);
		run.setTitle("Account Creation");
		run.setSize(run.SCR_WIDTH, run.SCR_HEIGHT);
		run.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pnl = new JPanel();
		
		lblfname = new JLabel("First Name*");
		lblfname.setSize(100, 20);
		lblfname.setLocation(50, 50);
		
		fname = new JTextField();
		fname.setSize(100, 20);
		fname.setLocation(125, 52);
		
		lbllname = new JLabel("Last Name*");
		lbllname.setSize(100, 20);
		lbllname.setLocation(50, 80);
		
		lname = new JTextField();
		lname.setSize(100, 20);
		lname.setLocation(125, 82);
		
		lblage = new JLabel("Age");
		lblage.setSize(100, 20);
		lblage.setLocation(50, 110);
		
		age = new JTextField();
		age.setSize(100, 20);
		age.setLocation(125, 112);
		
		password = new JTextField();
		password.setSize(100, 20);
		password.setLocation(125, 142);
		
		preferredPassword = new JLabel("Password*");
		preferredPassword.setSize(100, 20);
		preferredPassword.setLocation(50, 140);
		
		lblcinfo = new JLabel("Contact Info");
		lblcinfo.setSize(100, 20);
		lblcinfo.setLocation(50, 170);
		
		cinfo = new JTextArea();
		cinfo.setSize(100, 100);
		cinfo.setLocation(125, 172);
		
		submitButton = new JButton("Submit");
		submitButton.setSize(75, 20);
		submitButton.setLocation(125, 302);
		
		submitButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addCurrentPatient();				
			}
		});
		
		pnl.add(lblfname);
		pnl.add(lbllname);
		pnl.add(lblage);
		pnl.add(lblcinfo);
		pnl.add(fname);
		pnl.add(lname);
		pnl.add(age);
		pnl.add(cinfo);
		pnl.add(password);
		pnl.add(preferredPassword);
		pnl.add(submitButton);
		
		pnl.setLayout(null);		
		run.setContentPane(pnl);
		run.setVisible(true);
	}

	private void addCurrentPatient() 
	{
		if(fname.getText().isEmpty()  || lname.getText().isEmpty()  || password.getText().isEmpty())  //required basic information
		{
			JLabel error = new JLabel("Missing Information*");
			error.setSize(150, 20);
			error.setLocation(125, 280);
			pnl.add(error);
			run.repaint();
			
		}else  //assumes a database will be implemented but for now will just use a text file
		{
			try 
			{
				FileWriter fWriter = new FileWriter("login_information.txt", true);
				BufferedWriter bWriter = new BufferedWriter(fWriter);
				bWriter.write(fname.getText() + " | " + lname.getText() + " | " + age.getText() +
							  " | " + password.getText() + " | " + cinfo.getText() + " | " + type + 
							  "\n"); //all patients created from doctor screen will be 0
				bWriter.newLine();
				bWriter.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			run.setScreen(new LoginScreen(run));
		}
		
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
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
