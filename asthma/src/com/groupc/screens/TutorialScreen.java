package com.groupc.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.groupc.Runner;


public class TutorialScreen extends Screen
{

	JButton logi;
	JLabel lbl;
	public TutorialScreen(Runner run) {
		super(run);
		run.setTitle("Tutorial");
		
		logi = new JButton();
		logi.setSize(50, 50);
		logi.setLocation(250, 50);
		
		logi.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				isClosing = true;				
			}
		});
		
		this.add(logi);
		this.setLayout(null);		
		run.setContentPane(this);
		run.setVisible(true);
	}

	@Override
	public void update(float deltaTime)
	{
		if(isClosing)
		{
			this.remove(logi);
			lbl = new JLabel("hello");
			lbl.setSize(50, 50);
			lbl.setLocation(250, 50);
			this.add(lbl);
			
			run.repaint();
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
	public void dispose() 
	{
	}

}
