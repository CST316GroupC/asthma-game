package com.groupc.game1;

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
		while (Mouse.next())
		{
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
		assets.getImage("grass").draw(new Rectangle(0, 0, cam.frustumWidth, cam.frustumHeight));
		assets.getImage("back").draw(back);
		TextDrawer.drawString("Press w or up to flap wings", 20, 40, 10, 10);
		TextDrawer.drawString("Press s or down to glide", 20, 55, 10, 10);
		TextDrawer.drawString("Press p to pause", 20, 70, 10, 10);
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
