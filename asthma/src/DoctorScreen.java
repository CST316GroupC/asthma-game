import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class DoctorScreen extends Screen
{

	JButton addPatient;
	ArrayList<Patient> patients;
	JLabel lbl;
	JPanel pnl;
	JLabel pageTitle;
	JPanel box;
	JPanel boxBorder;
	JButton back;
	JButton logout;
	
	int butPressed = 0; //0 is none, 1 is add patient, 2 is back and logout for now
	
	public DoctorScreen(Runner run) 
	{
		super(run);
		run.frame.setTitle("Doctor");
		pnl = new JPanel();
		
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
		
		
		pnl.add(pageTitle);
		pnl.add(addPatient);
		pnl.add(back);
		pnl.add(logout);
		pnl.add(box);
		pnl.add(boxBorder);
		pnl.setLayout(null);		
		run.frame.setContentPane(pnl);
		run.frame.setVisible(true);
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
