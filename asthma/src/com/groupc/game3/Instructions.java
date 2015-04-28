package com.groupc.game3;

import org.lwjgl.input.Mouse;

import com.groupc.TextDrawer;
import com.groupc.game.Asset;
import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.math.CollisionChecker;
import com.groupc.math.Rectangle;
import com.groupc.math.Vector;

public class Instructions extends GameScreen
{
	private final Camera cam;
	private final Rectangle back;
	private final Vector mouseClick;
	
	public Instructions(Asset assets)
	{
		super(assets);
		cam = new Camera(300, 100);
		cam.setCamera();
		back = new Rectangle(280, 0, 15, 15);
		mouseClick = new Vector();
		
	}
	

	@Override
	public void update(float deltaTime)
	{
		if(Mouse.isButtonDown(0))
		{
			mouseClick.set(Mouse.getX(), Mouse.getY());
			cam.click(mouseClick);
			if(CollisionChecker.PointToRect(mouseClick, back))
			{
				this.dispose();
			}			
		}
	}

	@Override
	public void present(float deltaTime)
	{
		cam.setCamera();
		
		assets.getImage("wall").draw(new Rectangle(0, 0, cam.frustumWidth, cam.frustumHeight));
		assets.getImage("back").draw(back);
		TextDrawer.drawString("Instructions", 20, 85, 10, 10);

		assets.getImage("rain").draw(new Rectangle(34, 66, 30, 12));
		assets.getImage("treasure").draw(new Rectangle(195, 53, 30, 12));
		assets.getImage("healthGlobe").draw(new Rectangle(9, 51, 30, 12));
		assets.getImage("treasure").draw(new Rectangle(9, 37, 28, 10));
		assets.getImage("treasure").draw(new Rectangle(9, 31, 28, 10));
		assets.getImage("rain").draw(new Rectangle(84, 38, 30, 12));
		assets.getImage("rain").draw(new Rectangle(9, 20, 30, 12));
		assets.getImage("paperMan").draw(new Rectangle(127, 20, 30, 12));
		
		TextDrawer.drawString("Use Left or A and Right or D keys to move", 11, 76, 5, 5);
		TextDrawer.drawString("Dodge    to survive", 11, 70, 5, 5);
		TextDrawer.drawString("Score is gain by each second or from ", 11, 59, 5, 5);
		TextDrawer.drawString("     increases health by 1", 11, 52, 5, 5);
		TextDrawer.drawString("      decreases    speed", 11, 42, 5, 5);
		TextDrawer.drawString("      can also increase score by  2 at full health", 11, 36, 5, 5);
		TextDrawer.drawString("     speed increases if      gets hit 3 times", 9, 24, 5, 5);
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
