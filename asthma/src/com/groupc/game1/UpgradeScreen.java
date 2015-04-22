package com.groupc.game1;

import org.lwjgl.input.Mouse;

import com.groupc.TextDrawer;
import com.groupc.game.Asset;
import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.math.CollisionChecker;
import com.groupc.math.Rectangle;
import com.groupc.math.Vector;

public class UpgradeScreen extends GameScreen
{
	private final Camera cam;
	private final Vector mouseClick;
	private final Rectangle upgradeSpeed;
	private final Rectangle upgradeFlaps;
	private final Rectangle back;
	
	private int seeds;
	private int speedMult;
	private int flaps;
	
	private int[] prices = {10, 25, 50, 100, 200, 500, 1000, 2500, 5000, 10000};
	
	public UpgradeScreen(Asset assets)
	{
		super(assets);
		assets.reload();
		cam = new Camera(400, 400);
		cam.setCamera();
		upgradeSpeed = new Rectangle(300, 300, 50, 50);
		upgradeFlaps = new Rectangle(300, 250, 50, 50);

		back = new Rectangle(300, 0, 100, 100);
		
		mouseClick = new Vector();
		
		//get the current saved data
		seeds = Integer.parseInt(assets.getProps().getProperty("seeds"));
		speedMult = Integer.parseInt(assets.getProps().getProperty("speedMult"));
		flaps = Integer.parseInt(assets.getProps().getProperty("statima"));
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
		            cam.click(mouseClick);
					if(CollisionChecker.PointToRect(mouseClick, upgradeSpeed))
					{
						if(seeds > prices[speedMult])
						{
							seeds -= prices[speedMult];
							speedMult++;
							assets.getProps().setProperty("speedMult", "" + speedMult);
						}
					}
					if(CollisionChecker.PointToRect(mouseClick, upgradeFlaps))
					{
						if(seeds > prices[flaps])
						{
							seeds -= prices[flaps];
							flaps++;
							assets.getProps().setProperty("statima", "" + flaps);
						}
					}
					if(CollisionChecker.PointToRect(mouseClick, back))
					{
						dispose();
					}
					
					assets.getProps().setProperty("seeds", "" + seeds);
		        }
		    }
		}
	}

	@Override
	public void present(float deltaTime)
	{
		cam.setCamera();
		
		assets.getTexture().bind();

		assets.getImage("grass").draw(new Rectangle(0, 0, cam.frustumWidth, cam.frustumHeight));
		
		//draw arrows
		assets.getImage("upgrade").draw(upgradeSpeed);
		assets.getImage("upgrade").draw(upgradeFlaps);
		assets.getImage("back").draw(back);
		
		TextDrawer.drawString("Seeds", 25, 350, 40, 40);
		TextDrawer.drawInt(seeds, 250, 350, 20, 40, 5);
		
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
		assets.save();
		isClosing = true;
	}

	@Override
	public GameScreen getNext() 
	{
		// TODO Auto-generated method stub
		return new MainMenu(assets);
	}

}
