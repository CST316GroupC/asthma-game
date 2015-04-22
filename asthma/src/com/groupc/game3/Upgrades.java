package com.groupc.game3;

import org.lwjgl.input.Mouse;

import com.groupc.TextDrawer;
import com.groupc.game.Asset;
import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.math.CollisionChecker;
import com.groupc.math.Rectangle;
import com.groupc.math.Vector;

public class Upgrades extends GameScreen
{
	private final Camera cam;
	private final Vector mouseClick;
	private final Rectangle increaseChest;
	private final Rectangle increaseHealthGlobe;
	private final Rectangle back;
	
	private int score;
	private int chestAmount;
	private int healthGlobeAmount;
	
	private int[] prices = {10, 20, 40, 70, 110, 160, 220, 290, 370, 500};
	
	public Upgrades(Asset assets)
	{
		super(assets);
		assets.reload();
		cam = new Camera(400, 400);
		cam.setCamera();
		increaseChest = new Rectangle(310, 270, 50, 50);
		increaseHealthGlobe = new Rectangle(310, 210, 50, 50);

		back = new Rectangle(300, 0, 100, 100);
		
		mouseClick = new Vector();
		
		//get the current saved data
		score = Integer.parseInt(assets.getProps().getProperty("game3Score"));
		chestAmount = Integer.parseInt(assets.getProps().getProperty("game3Chest"));
		healthGlobeAmount= Integer.parseInt(assets.getProps().getProperty("game3HealthGlobe"));
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
		            //System.out.println("Left button released");
		            mouseClick.set(Mouse.getX(), Mouse.getY());
		            cam.click(mouseClick);
		            
					if(CollisionChecker.PointToRect(mouseClick, increaseChest))
					{
						if(score > prices[chestAmount])
						{	
							if(chestAmount < 4)
							{
								score -= prices[chestAmount];	
								chestAmount++;;
							}
							assets.getProps().setProperty("game3Chest", "" + chestAmount);
						}
					}
					
					if(CollisionChecker.PointToRect(mouseClick, increaseHealthGlobe))
					{
						if(score > prices[healthGlobeAmount])
						{
							score -= prices[healthGlobeAmount];
							healthGlobeAmount++;
							assets.getProps().setProperty("game3HealthGlobe", "" + healthGlobeAmount);
						}
					}
					
					if(CollisionChecker.PointToRect(mouseClick, back))
					{
						dispose();
					}
					
					assets.getProps().setProperty("game3Score", "" +score);
		        }
		    }
		}
	}

	@Override
	public void present(float deltaTime)
	{
		cam.setCamera();
		
		assets.getTexture().bind();

		assets.getImage("wall").draw(new Rectangle(0, 0, cam.frustumWidth, cam.frustumHeight));
		
		//draw arrows
		assets.getImage("upgrade").draw(increaseChest);
		assets.getImage("upgrade").draw(increaseHealthGlobe);
		assets.getImage("back").draw(back);
		
		TextDrawer.drawString("Score", 25, 350, 40, 40);
		TextDrawer.drawInt(score, 250, 350, 20, 40, 5);
		
		TextDrawer.drawString("Chest Amount", 50, 290, 15, 20);
		TextDrawer.drawInt(prices[chestAmount], 250, 290, 10, 20, 4);
		
		TextDrawer.drawString("Health Globe", 50, 230, 15, 20);
		TextDrawer.drawInt(prices[healthGlobeAmount], 250, 230, 10, 20, 4);
	}

	@Override
	public void pause() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() 
	{
		assets.save(Game3Assets.FILENAME);
		isClosing = true;
	}

	@Override
	public GameScreen getNext() 
	{
		// TODO Auto-generated method stub
		return new MainMenu(assets);
	}

}
