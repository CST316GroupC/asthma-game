package com.groupc.game4;

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
		assets.getImage("table").draw(new Rectangle(0, 0, cam.frustumWidth, cam.frustumHeight));
		assets.getImage("back").draw(back);
		TextDrawer.drawString("Instructions", 20, 85, 10, 10);
		TextDrawer.drawString("Clicking on healthy food earns you points", 20, 70, 6, 10);
		TextDrawer.drawString("You lose both points and time for clicking on", 20, 55, 6, 10);
		TextDrawer.drawString("unhealthy food", 20, 45, 6, 10);
		TextDrawer.drawString("You lose points if you let healthy food rot", 20, 30, 6, 10);
		TextDrawer.drawString("You gain points if you avoid clicking", 20, 15, 6, 10);
		TextDrawer.drawString("on unhealthy food", 20, 5, 6, 10);
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
