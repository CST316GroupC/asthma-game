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
		back = new Rectangle(250, 0, 50, 30);
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

		TextDrawer.drawString("Use Left or A and Right or D keys to move", 14, 76, 5, 5);
		TextDrawer.drawString("Dodge the water droplets to survive", 14, 70, 5, 5);
		TextDrawer.drawString("Heart shape increases health by 1", 14, 59, 5, 5);
		TextDrawer.drawString("Treasure chest decreased water droplets speed", 14, 52, 5, 5);
		TextDrawer.drawString("Water droplets speed increases if paper gets hit 5 times", 14, 40, 5, 5);
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
