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
	
	JTextField fname;
	JTextField lname;
	JTextField age;
	JTextArea cinfo;
	
	public AccountCreationScreen(Runner run) {
		super(run);
		run.frame.setTitle("Account Creation");
		run.frame.setSize(run.SCR_WIDTH, run.SCR_HEIGHT);
		run.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pnl = new JPanel();
		
		lblfname = new JLabel("First Name");
		lblfname.setSize(100, 20);
		lblfname.setLocation(50, 50);
		
		fname = new JTextField();
		fname.setSize(100, 20);
		fname.setLocation(125, 52);
		
		lbllname = new JLabel("Last Name");
		lbllname.setSize(100, 20);
		lbllname.setLocation(50, 80);
		
		lname = new JTextField();
		lname.setSize(100, 20);
		lname.setLocation(125, 82);
		
		pnl.add(lblfname);
		pnl.add(lbllname);
		
		pnl.add(fname);
		pnl.add(lname);
		
		pnl.setLayout(null);		
		run.frame.setContentPane(pnl);
		run.frame.setVisible(true);
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
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
