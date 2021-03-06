package com.groupc.game2;

import org.lwjgl.input.Mouse;

import com.groupc.TextDrawer;
import com.groupc.game.Asset;
import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.game2.MainMenu;
import com.groupc.math.CollisionChecker;
import com.groupc.math.Rectangle;
import com.groupc.math.Vector;

public class InstructionScreen extends GameScreen
{

	private final Camera cam;
	private final Rectangle back;
	private final Rectangle lblMoving;
	private final Rectangle lblAxe;
	private final Rectangle lblPosition;
	private final Vector mouseClick;
	
	public InstructionScreen(Asset assets)
	{
		super(assets);
		cam = new Camera(400, 400);
		cam.setCamera();
		back = new Rectangle(175, 0, 75, 75);
		lblMoving = new Rectangle(50, 300, 300, 75);
		lblAxe = new Rectangle(50, 200, 300, 75);
		lblPosition = new Rectangle(50, 100, 300, 75);
		mouseClick = new Vector();
	}

	@Override
	public void update(float deltaTime)
	{
		while (Mouse.next()){
		    if (Mouse.getEventButtonState())
		    {
		        if (Mouse.getEventButton() == 0) 
		        {
		            System.out.println("Left button pressed");
		        }
		    }
		    else
		    {
		    	if (Mouse.getEventButton() == 0) 
		    	{
		    		mouseClick.set(Mouse.getX(), Mouse.getY());
		    		cam.click(mouseClick);
		    		if(CollisionChecker.PointToRect(mouseClick, back))
		    		{
		    			this.dispose();
		    		}			
		    	}
		    }
		}
	}

	@Override
	public void present(float deltaTime)
	{
		cam.setCamera();
		assets.getImage("background").draw(new Rectangle(0, 0, 400, 400));
		TextDrawer.drawStringinRect("Press Arrow Keys or WASD to move", lblMoving);
		TextDrawer.drawStringinRect("Press v to use Axes if you have one", lblAxe);
		TextDrawer.drawStringinRect("Pressing space will help", lblPosition);
		TextDrawer.drawStringinRect("Back", back, true);
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
		isClosing = true;
	}

	@Override
	public GameScreen getNext() 
	{
		return new MainMenu(assets);
	}
}
