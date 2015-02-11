import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class AccountCreationScreen extends Screen
{

	JPanel pnl;
	
	JLabel lblfname;
	JLabel lbllname;
	JLabel lblage;
	JLabel lblcinfo;
	JLabel pageTitle;
	JPanel box;
	JPanel boxBorder;
	JButton back;
	JButton logout;
	JButton mute;
	
	JTextField fname;
	JTextField lname;
	JTextField age;
	JTextArea cinfo;
	
	int butPressed = 0; //0 is none, 1 is go back a page, 2 is back and logout for now
	
	public AccountCreationScreen(Runner run) {
		super(run);
		run.frame.setTitle("Account Creation");
		run.frame.setSize(run.SCR_WIDTH, run.SCR_HEIGHT);
		run.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pnl = new JPanel();
		
		// Box display for title
		
		box = new JPanel();
		boxBorder = new JPanel();
		box.setBackground(Color.LIGHT_GRAY);
		boxBorder.setBackground(Color.BLACK);
				
		box.setBounds(18, 0, 450, 60);
		boxBorder.setBounds(17, 0, 452, 61);
		
		
		// Page title
		pageTitle = new JLabel("Account Creation Page");
		pageTitle.setFont(new Font("Serif", Font.BOLD, 25));
		pageTitle.setBounds(120, 60, 350, 40);
		
		
		lblfname = new JLabel("First Name:");
		lblfname.setBounds(125, 150, 100, 20);
		
		fname = new JTextField();
		fname.setBounds(200, 150, 100, 20);
		
		lbllname = new JLabel("Last Name:");
		lbllname.setBounds(125, 180, 100, 20);
		
		lname = new JTextField();
		lname.setBounds(200, 180, 100, 20);
		
		lblage = new JLabel("Age:");
		lblage.setBounds(125, 210, 100, 20);
		
		age = new JTextField();
		age.setBounds(200, 210, 100, 20);
		
		lblcinfo = new JLabel("Contact Info:");
		lblcinfo.setBounds(125, 240, 100, 20);
		
		cinfo = new JTextArea();
		cinfo.setBounds(200, 240, 100, 100);
		
		// Add back button
		back = new JButton("Back");
		back.setBounds(25, 20, 80, 35);
				
		// Add logout button
		logout = new JButton("Logout");
		logout.setBounds(380, 20, 80, 35);
		
		// Add mute button
		mute = new JButton("Mute");
		mute.setBounds(300, 24, 70, 25);
		
		
		// Add back button listener
		back.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 1;				
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
		pnl.add(lblfname);
		pnl.add(lbllname);
		pnl.add(lblage);
		pnl.add(lblcinfo);
		pnl.add(fname);
		pnl.add(lname);
		pnl.add(age);
		pnl.add(cinfo);
		pnl.add(back);
		pnl.add(logout);
		pnl.add(mute);
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
			run.setScreen(new DoctorScreen(run));
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
