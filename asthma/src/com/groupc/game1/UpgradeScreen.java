package com.groupc.game1;

import org.lwjgl.input.Mouse;

import com.groupc.TextDrawer;
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
	private Rectangle back;
	
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
		upgradeFlaps = new Rectangle(300, 250, 50, 50);

		back = new Rectangle(300, 0, 100, 100);
		
		mouseClick = new Vector();
		
		//get the current saved data
		seeds = Integer.parseInt(Assets.joeyProps.getProperty("seeds"));
		speedMult = Integer.parseInt(Assets.joeyProps.getProperty("speedMult"));
		flaps = Integer.parseInt(Assets.joeyProps.getProperty("statima"));
	}

	
	@Override
	public void update(float deltaTime) 
	{
		while (Mouse.next()){
		    if (Mouse.getEventButtonState()) {
		        if (Mouse.getEventButton() == 0) {
		            System.out.println("Left button pressed");
		        }
		    }else {
		        if (Mouse.getEventButton() == 0) {
		            System.out.println("Left button released");
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
					if(CollisionChecker.PointToRect(mouseClick, back))
					{
						dispose();
					}
					
					Assets.joeyProps.setProperty("seeds", "" + seeds);
		        }
		    }
		}
	}

	@Override
	public void present(float deltaTime)
	{
		cam.setCamera();
		
		Assets.atlas.bind();
		Assets.joeyfly.draw(new Rectangle(0, 0, 400, 400));
		
		//draw arrows
		Assets.joeyBou.draw(upgradeSpeed);
		Assets.joeyBou.draw(upgradeFlaps);
		Assets.joeyfall.draw(back);
		
		TextDrawer.drawString("Seeds", 25, 350, 40, 40);
		TextDrawer.drawInt(seeds, 300, 350, 20, 40, 5);
		
		TextDrawer.drawString("Speed", 50, 300, 20, 20);
		TextDrawer.drawInt(prices[speedMult], 225, 300, 10, 20, 5);
		
		TextDrawer.drawString("Flaps", 50, 250, 20, 20);
		TextDrawer.drawInt(prices[flaps], 225, 250, 10, 20, 5);
		
		
		
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
		Assets.save();
		isClosing = true;
	}

	@Override
	public GameScreen getNext() 
	{
		// TODO Auto-generated method stub
		return new MainMenu();
	}

}