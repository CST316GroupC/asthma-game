
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class TutorialScreen extends Screen
{

	public TutorialScreen(Runner run) {
		super(run);
		run.frame.setTitle("Tutorial");
		JPanel pnl = new JPanel();
		
		JButton logi = new JButton();
		logi.setSize(50, 50);
		logi.setLocation(250, 50);
		
		logi.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				isClosing = true;				
			}
		});
		
		pnl.add(logi);
		pnl.setLayout(null);		
		run.frame.setContentPane(pnl);
		run.frame.setVisible(true);
	}

	@Override
	public void update(float deltaTime)
	{
		if(isClosing)
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
	public void dispose() 
	{
	}

}
