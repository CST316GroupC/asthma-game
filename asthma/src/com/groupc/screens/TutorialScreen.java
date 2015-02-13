package com.groupc.screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.groupc.Runner;


public class TutorialScreen extends Screen
{
	JButton logi;
	JLabel lbl;
	public TutorialScreen(Runner run) {
		super(run);
		
		//Basic Frame Settings
		run.setTitle("Tutorial");
		
		logi = new JButton("Back");
		logi.setSize(50, 50);
		logi.setLocation(0, 0);
		
		//Set fonts
		
		this.add(logi);
		this.setLayout(null);		
		run.setContentPane(this);
		run.setVisible(true);
	}

	@Override
	public void update(float deltaTime)
	{
		
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
	public void dispose() 
	{
		
	}

}
