package com.groupc.game1;

import org.lwjgl.input.Mouse;

import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.math.CollisionChecker;
import com.groupc.math.Rectangle;
import com.groupc.math.Vector;

public class UpgradeScreen extends GameScreen
{
	private Camera cam;
	private Vector mouseClick;
	private Rectangle upgradeSpeed;
	private Rectangle upgradeFlaps;
	
	private int seeds;
	private int speedMult;
	private int flaps;
	
	private int[] prices = {10, 25, 50, 100, 200, 500, 1000, 2500, 5000, 10000};
	
	public UpgradeScreen()
	{
		Assets.reload();
		cam = new Camera(400, 400);
		cam.setCamera();
		upgradeSpeed = new Rectangle(300, 300, 50, 50);
		upgradeFlaps = new Rectangle(300, 350, 50, 50);
		mouseClick = new Vector();
		
		//get the current saved data
		seeds = Integer.parseInt(Assets.joeyProps.getProperty("seeds"));
		speedMult = Integer.parseInt(Assets.joeyProps.getProperty("speedMult"));
		flaps = Integer.parseInt(Assets.joeyProps.getProperty("statima"));
	}

	
	@Override
	public void update(float deltaTime) 
	{
		if(Mouse.isButtonDown(0))
		{
			mouseClick.set(Mouse.getX(), Mouse.getY());
			
			if(CollisionChecker.PointToRect(mouseClick, upgradeSpeed))
			{
				if(seeds > prices[speedMult])
				{
					seeds -= prices[speedMult];
					speedMult++;
					Assets.joeyProps.setProperty("speedMult", "" + speedMult);
				}
			}
			if(CollisionChecker.PointToRect(mouseClick, upgradeFlaps))
			{
				if(seeds > prices[flaps])
				{
					seeds -= prices[flaps];
					flaps++;
					Assets.joeyProps.setProperty("statima", "" + flaps);
				}
			}
			
			Assets.joeyProps.setProperty("seeds", "" + seeds);
		}
	}

	@Override
	public void present(float deltaTime)
	{
		cam.setCamera();
		Assets.atlas.bind();
		//draw arrows
		Assets.joeyBou.draw(upgradeSpeed);
		Assets.joeyBou.draw(upgradeFlaps);
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

	@Override
	public GameScreen getNext() {
		// TODO Auto-generated method stub
		return null;
	}

}
