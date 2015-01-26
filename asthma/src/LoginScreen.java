
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class LoginScreen extends Screen
{

	public LoginScreen(Runner run) {
		super(run);
		run.frame.setTitle("Login");
		run.frame.setSize(run.SCR_WIDTH, run.SCR_HEIGHT);
		run.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnl = new JPanel();
		
		JButton login = new JButton();
		login.setSize(50, 50);
		login.setLocation(250, 250);
		
		login.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				isClosing = true;				
			}
		});
		
		pnl.add(login);
		pnl.setLayout(null);		
		run.frame.setContentPane(pnl);
		run.frame.setVisible(true);
	}

	@Override
	public void update(float deltaTime)
	{
		if(isClosing)
		{
			run.setScreen(new TutorialScreen(run));
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
