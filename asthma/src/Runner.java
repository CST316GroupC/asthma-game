import javax.swing.JFrame;


public class Runner 
{
	JFrame frame;
	Screen screen;
	long startTime = System.nanoTime();
	
	public final int SCR_WIDTH = 500;
	public final int SCR_HEIGHT = 500;
	
	boolean isClosing = false;
	
	public Runner()
	{
		frame = new JFrame();
		screen = new LoginScreen(this);
	}
	
	public void run()
	{
		float deltaTime = (System.nanoTime()-startTime) / 1000000000.0f;
		startTime = System.nanoTime();
		screen.update(deltaTime);
		screen.present(deltaTime);
	}
	
	public void setScreen(Screen screen)
	{
		if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
	}
	
	public void close()
	{
		isClosing = true;
	}

}
