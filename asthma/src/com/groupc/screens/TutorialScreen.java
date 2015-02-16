package com.groupc.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import com.groupc.Runner;


public class TutorialScreen extends Screen
{
	JLabel pageTitle;
	JLabel text;
	
	JPanel box;
	JPanel boxBorder;
	JPanel videoBorder;
	JPanel descriptionBox;
	JPanel descriptionBorder;
	JPanel stepsBox;
	JPanel stepsBorder;
	
	
	
	JButton back;
	JButton logout;
	JButton mute;
	JButton step1;
	JButton step2;
	JButton step3;
	JButton step4;
	JButton step5;
	JButton start;
	
	
	int butPressed = 0;
	
	public TutorialScreen(Runner run) {
		super(run);
		run.setTitle("Tutorial");
		
		// Box display and border for buttons
		box = new JPanel();
		boxBorder = new JPanel();
		
		box.setBackground(Color.LIGHT_GRAY);
		boxBorder.setBackground(Color.BLACK);
		
		box.setBounds(18, 0, 450, 60);
		boxBorder.setBounds(17, 0, 452, 61);
		
		
		// Border display for embedded video
		videoBorder = new JPanel();

		videoBorder.setBackground(Color.BLACK);
		
		videoBorder.setBounds(30, 120, 250, 180);
		
		
		// Tutorial description box and border
		descriptionBox = new JPanel();
		descriptionBorder = new JPanel();
	
		descriptionBox.setBackground(Color.LIGHT_GRAY);
		descriptionBorder.setBackground(Color.BLACK);
		
		descriptionBox.setBounds(30, 320, 250, 60);
		descriptionBorder.setBounds(29, 319, 252, 62);
		
		
		// Steps box and border
		stepsBox = new JPanel();
		stepsBorder = new JPanel();
		
		stepsBox.setBackground(Color.GRAY);
		stepsBorder.setBackground(Color.BLACK);
		
		stepsBox.setBounds(310, 120, 150, 260);
		stepsBorder.setBounds(309, 119, 152, 262);
		
		
		
		// Page title
		pageTitle = new JLabel("Tutorial Page");
		pageTitle.setFont(new Font("Serif", Font.BOLD, 25));
		pageTitle.setBounds(180, 60, 350, 40);
		
		
		// Add back button
		back = new JButton("Back");
		back.setBounds(25, 14, 80, 35);
		
		
		// Add logout button
		logout = new JButton("Logout");
		logout.setBounds(380, 14, 80, 35);
		
		
		// Add mute button
		mute = new JButton("Mute");
		mute.setBounds(300, 18, 70, 25);
		
		
		// Add Steps button
		step1 = new JButton("Step 1");
		step2 = new JButton("Step 2");
		step3 = new JButton("Step 3");
		step4 = new JButton("Step 4");
		step5 = new JButton("Step 5");
		
		step1.setBounds(345, 140, 80, 25);
		step2.setBounds(345, 190, 80, 25);
		step3.setBounds(345, 240, 80, 25);
		step4.setBounds(345, 290, 80, 25);
		step5.setBounds(345, 340, 80, 25);
		
		
		// Add start button
		start = new JButton("Start");
		
		start.setBounds(200, 420, 80, 25 );
		
		
		// Add text above Start button
		text = new JLabel("* To skip, press Start");
		
		text.setForeground(Color.RED);
		text.setBounds(180, 395, 120, 25);
		
		
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
		
		mute.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 3;				
			}
		});
		
		start.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 4;
			}
		})
		;
		this.add(pageTitle);
		this.add(text);
		this.add(back);
		this.add(logout);
		this.add(mute);
		this.add(step1);
		this.add(step2);
		this.add(step3);
		this.add(step4);
		this.add(step5);
		this.add(start);
		
		// Panels have to be added last for it to show 
		this.add(box);
		this.add(boxBorder);
		this.add(videoBorder);
		this.add(descriptionBox);
		this.add(descriptionBorder);
		this.add(stepsBox);
		this.add(stepsBorder);

		this.setLayout(null);		
		run.setContentPane(this);
		run.setVisible(true);
	}

	@Override
	public void update(float deltaTime)
	{
		if(butPressed == 2)
		{
			run.setScreen(new LoginScreen(run));
		}
		else if(butPressed == 4)
		{
			run.setScreen(new RewardScreen(run));
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
		// TODO Auto-generated method stub
	}

}
