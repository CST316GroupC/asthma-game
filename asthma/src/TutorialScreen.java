
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


public class TutorialScreen extends Screen
{
	//Variables
	boolean redraw = true;
	Resize resize = new Resize(run);
	boolean logout = false;
	
	//Display Elements
	JPanel tutorialPanel = new JPanel();
	JPanel testBox = new JPanel();
	JPanel menuBar = new JPanel();
	JButton logoutButton = new JButton("Logout");
	
	
	public TutorialScreen(Runner run) 
	{
		super(run);
		
		//Basic Frame Settings
		run.setTitle("Tutorial");
		run.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//resize stuff
		run.addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent e)
			{
				redraw = true;
			}
		});
		
		//Set colors
		tutorialPanel.setBackground(Color.LIGHT_GRAY);
		testBox.setBackground(Color.WHITE);
		menuBar.setBackground(Color.LIGHT_GRAY);
		
		//Set fonts
		
		//logoutButton is pushed
		logoutButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				logout = true;	
			}
		});
		
		//add things to the panel
		tutorialPanel.add(logoutButton);
		tutorialPanel.add(menuBar);
		tutorialPanel.add(testBox);
		tutorialPanel.setLayout(null);		
		run.setContentPane(tutorialPanel);
		run.setVisible(true);
	}

	@Override
	public void update(float deltaTime)
	{
		if(redraw)
		{
			//test Box
			testBox.setBounds(resize.locationX(0), resize.locationY(0), resize.width(500), resize.height(500));
			
			//menuBar
			menuBar.setBounds(resize.locationX(10), resize.locationY(0), resize.width(480), resize.height(50));
			
			//logoutButton
			logoutButton.setBounds(resize.locationX(380), resize.locationY(10), resize.width(100), resize.height(30));
			logoutButton.setFont(new Font(logoutButton.getFont().getFontName(),logoutButton.getFont().getStyle(), resize.font(12)));
			
			run.repaint();
			redraw = false;
		}
		
		if(logout)
		{
			run.setScreen(new LoginScreen(run));
			logout = false;
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
