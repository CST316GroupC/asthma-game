
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginScreen extends Screen
{
	//this for testing purposes
	String[] testUser = {"test" , "doctor"};
	char[] testPass = {'p','a','s','s'};
	//testing stuff done
	
	String username;
	char[] password;
	
	int butPressed = 0; //0 is none, 1 is login
	
	JTextField txtf1;
	JPasswordField txtf2;
	JPanel pnl;
	
	public LoginScreen(Runner run) {
		super(run);
		run.frame.setTitle("Login");
		run.frame.setSize(run.SCR_WIDTH, run.SCR_HEIGHT);
		run.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pnl = new JPanel();
		
		JLabel lbl1 = new JLabel("Username");
		lbl1.setSize(100, 20);
		lbl1.setLocation(200, 200);
		
		JLabel lbl2 = new JLabel("Password");
		lbl2.setSize(100, 20);
		lbl2.setLocation(200, 240);
		
		txtf1 = new JTextField();
		txtf1.setSize(100, 20);
		txtf1.setLocation(200, 220);
		
		
		txtf2 = new JPasswordField();
		txtf2.setSize(100, 20);
		txtf2.setLocation(200, 260);
		
		JButton login = new JButton("Login");
		login.setSize(75, 20);
		login.setLocation(225, 280);
		
		login.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 1;				
			}
		});
		
		pnl.add(lbl1);
		pnl.add(lbl2);
		pnl.add(txtf1);
		pnl.add(txtf2);
		pnl.add(login);
		pnl.setLayout(null);		
		run.frame.setContentPane(pnl);
		run.frame.setVisible(true);
	}

	@Override
	public void update(float deltaTime)
	{
		if(butPressed == 1)
		{
			username = txtf1.getText();
			password = txtf2.getPassword();
//testing
			if(username.equals(testUser[0]) && Arrays.equals(password, testPass))
			{
				run.setScreen(new TutorialScreen(run));
			}
			if(username.equals(testUser[1]) && Arrays.equals(password, testPass))
			{
				run.setScreen(new DoctorScreen(run));
			}
			//testing ends
			else
			{
				JLabel err = new JLabel("Incorrect User/Password");
				err.setSize(150, 20);
				err.setLocation(200, 180);
				pnl.add(err);
				run.frame.repaint();
			}
					
			butPressed = 0;
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
