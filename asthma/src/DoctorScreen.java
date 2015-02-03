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
	
	int butPressed = 0; //0 is none, 1 is add patient
	
	public DoctorScreen(Runner run) 
	{
		super(run);
		run.frame.setTitle("Doctor");
		pnl = new JPanel();
		
		addPatient = new JButton("Add Patient");
		addPatient.setSize(150, 50);
		addPatient.setLocation(250, 50);
		
		addPatient.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				butPressed = 1;				
			}
		});
		
		pnl.add(addPatient);
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
