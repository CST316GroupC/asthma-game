
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


public class GameScreen extends Screen
{
	public GameScreen(Runner run)
	{
		super(run);
		try {
			Display.setDisplayMode(new DisplayMode(run.SCR_WIDTH, run.SCR_HEIGHT));
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
	}

	@Override
	public void update(float deltaTime) 
	{
		Display.update();
		if(Display.isCloseRequested())
		{
			Display.destroy();
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
