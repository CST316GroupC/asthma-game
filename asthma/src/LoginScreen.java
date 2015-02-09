/*
 * LoginScreen.java
 * displays and runs elements for the login screen
 */

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;



public class LoginScreen extends Screen
{
	//Variables
	int type = 0;
	boolean loginErrorDrawn = false;
	boolean redraw = false;
	double wratio = 1.0;
	double hratio = 1.0;
	
	//Display Elements
	JPanel loginPanel;
	JTextField userNameTF;
	JPasswordField passwordTF;
	JLabel loginErrorMessage = new JLabel("Incorrect Username/Password");
	JRadioButton saveLoginRadio;

	
	public LoginScreen(Runner run) 
	{
		super(run);
		
		//resize stuff
		run.frame.addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent e)
			{
				redraw = true;
			}
		});
		
		// Set layout manager
				
		//Basic Frame Settings
		run.frame.setTitle("Login");
		run.frame.setSize(run.SCR_WIDTH, run.SCR_HEIGHT);
		run.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		loginPanel = new JPanel();
		//loginPanel.setLayout(new BorderLayout());
		loginPanel.setBorder(BorderFactory.createTitledBorder("Login"));
		
		////Set up display properties for elements
		//UserName
		JLabel userNameLabel = new JLabel("Username");
		userNameLabel.setSize(100, 20);
		userNameLabel.setLocation(200, 200);
		
		userNameTF = new JTextField();
		userNameTF.setSize(100, 20);
		userNameTF.setLocation(200, 220);
		
		//Password
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setSize(100, 20);
		passwordLabel.setLocation(200, 240);
		
		passwordTF = new JPasswordField();
		passwordTF.setSize(100, 20);
		passwordTF.setLocation(200, 260);
		
		//Radio
		saveLoginRadio = new JRadioButton("Remember Password");
		saveLoginRadio.setSize((int)(200 * wratio), (int)(20 * hratio));
		saveLoginRadio.setLocation(200, 280);
		
		
		//Login Button
		JButton loginButton = new JButton("Login");
		loginButton.setSize(100, 20);
		loginButton.setLocation(200, 300);
		
		//Test if Login Button is pushed
		loginButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try {
					checkLogin();
				} catch (IOException e1) {
					e1.printStackTrace();
				}			
			}
		});		
		
		//add things to the panel
		loginPanel.add(userNameLabel);
		loginPanel.add(userNameTF);
		loginPanel.add(passwordLabel);
		loginPanel.add(passwordTF);
		loginPanel.add(saveLoginRadio);
		loginPanel.add(loginButton);
		loginPanel.setLayout(null);
		run.frame.setContentPane(loginPanel);
		run.frame.setVisible(true);
		
		//music stuff
		run.player.loadSong("AMemoryAway.ogg");
		run.player.playMusic(true);
	}

	@Override
	public void update(float deltaTime)
	{
		if(redraw)
		{
			wratio = (double)run.frame.getWidth() / run.SCR_WIDTH;
			hratio = (double)run.frame.getHeight() / run.SCR_HEIGHT;
			 
			//Radio
			saveLoginRadio.setSize((int)(200 * wratio), (int)(20 * hratio));	
			saveLoginRadio.setFont(new Font(saveLoginRadio.getFont().getFontName(), saveLoginRadio.getFont().getStyle(), saveLoginRadio.getFont().getSize() + 1));
			saveLoginRadio.setLocation((int)(200 *wratio), (int)(280*hratio));
			
			run.frame.repaint();
			redraw = false;
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
		
	}
	
	//Private Methods
	public void checkLogin() throws IOException
	{
		String line = null;
		String[] firstNames = new String [10];
		char[][] passWords = new char[10][30];
		int counter = 0;
		String tempString;
		char tempChar;
		
		FileReader fr = new FileReader("login_information.txt");  //maybe create an 'onStart()' function for runner
		BufferedReader br = new BufferedReader(fr);
		StringTokenizer st;
		
		
		while((line = br.readLine()) != null)
		{
			st = new StringTokenizer(line, " | ");
			firstNames[counter] = st.nextToken();
			st.nextToken();
			st.nextToken();
			tempString = st.nextToken();
			for(int i = 0; i < tempString.length(); ++i)
			{
				 passWords[counter][i] = tempString.charAt(i);
			}
			counter += 1;
		}
		br.close();
		for(int i = 0; i < 10; ++i)
		{
			tempString = new String (passWords[i]);
			tempString = tempString.trim();
			passWords[i] = tempString.toCharArray();
			
			if(userNameTF.getText().equals(firstNames[i]) && Arrays.equals(passwordTF.getPassword(),passWords[i]))
			{
				//Use variable type at the top to switch between doctor login and patient login
				//Doctors
				if(type == 0)
				{
					run.setScreen(new DoctorScreen(run));
				}
				//Patients
				if(type == 1)
				{
					run.setScreen(new TutorialScreen(run));
				}
		}
		
			if(!loginErrorDrawn)
			{
				loginErrorMessage.setSize(200, 20);
				loginErrorMessage.setLocation(150, 180);
				loginPanel.add(loginErrorMessage);
				run.frame.repaint();
				loginErrorDrawn = true;
			}
		}
	}
}
