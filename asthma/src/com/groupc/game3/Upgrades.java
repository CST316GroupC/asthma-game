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
	public static final int		HEALTHGLOBE_COUNT_MAX	= 3;
	public static final int		TREASURE_COUNT_MAX		= 3;
	
	private final Camera cam;
	private final Vector mouseClick;
	private final Rectangle increaseChest;
	private final Rectangle increaseHealthGlobe;
	private final Rectangle back;
	private final Rectangle convertTokens;
	
	private int score;
	private int chestAmount;
	private int healthGlobeAmount;
	private int tokens;
	
	private int[] chestPrice = {0, 250, 500, 1000};
	private int[] healthPrice = {0, 300, 600, 1200};
	
	public Upgrades(Asset assets)
	{
		super(assets);
		assets.reload();
		cam = new Camera(400, 400);
		cam.setCamera();
		increaseChest = new Rectangle(310, 240-20, 50, 50);
		increaseHealthGlobe = new Rectangle(310, 180-20, 50, 50);
		convertTokens = new Rectangle(310, 130-20, 50, 50);

		back = new Rectangle(300, 0, 100, 100);
		
		mouseClick = new Vector();
		
		//get the current saved data
		score = Integer.parseInt(assets.getProps().getProperty("game3Score"));
		chestAmount = Integer.parseInt(assets.getProps().getProperty("game3Chest"));
		healthGlobeAmount= Integer.parseInt(assets.getProps().getProperty("game3HealthGlobe"));
		tokens = Integer.parseInt(assets.getProps().getProperty("tokens"));
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
						if(score > chestPrice[chestAmount])
						{	
							if(chestAmount < TREASURE_COUNT_MAX)
							{
								score -= chestPrice[chestAmount];	
								chestAmount++;;
							}
							assets.getProps().setProperty("game3Chest", "" + chestAmount);
						}
					}
					
					if(CollisionChecker.PointToRect(mouseClick, increaseHealthGlobe))
					{
						if(score > healthPrice[healthGlobeAmount])
						{
							if(healthGlobeAmount < HEALTHGLOBE_COUNT_MAX)
							{
								score -= healthPrice[healthGlobeAmount];
								healthGlobeAmount++;
							}
							assets.getProps().setProperty("game3HealthGlobe", "" + healthGlobeAmount);
						}
					}
					if(CollisionChecker.PointToRect(mouseClick, convertTokens))
					{
						if(tokens >= 1)
						{
							tokens -= 1;
							score+= 50;
						}
						assets.getProps().setProperty("tokens", "" + tokens);
					}

					assets.getProps().setProperty("game3Score", "" +score);
					assets.save();
				
					
					if(CollisionChecker.PointToRect(mouseClick, back))
					{
						dispose();
					}
					
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
		assets.getImage("upgrade").draw(convertTokens);
		
		TextDrawer.drawString("Score", 25, 350, 40, 40);
		TextDrawer.drawInt(score, 250, 350, 20, 40, 5);
		
		TextDrawer.drawString("Tokens", 25, 300, 40, 40);
		TextDrawer.drawInt(tokens, 250, 300, 20, 40, 5);
		
		TextDrawer.drawString("Chest Amount", 50, 240, 15, 20);
		if(chestAmount < TREASURE_COUNT_MAX)
			TextDrawer.drawInt(chestPrice[chestAmount], 250, 240, 10, 20, 4);
		else
			TextDrawer.drawString("MAX", 250, 240, 10, 20);
		
		TextDrawer.drawString("Health Globe", 50, 180, 15, 20);
		if(healthGlobeAmount < HEALTHGLOBE_COUNT_MAX)
			TextDrawer.drawInt(healthPrice[healthGlobeAmount], 250, 180, 10, 20, 4);
		else
			TextDrawer.drawString("MAX", 250, 180, 10, 20);
		
		TextDrawer.drawString("Convert 1 token", 50, 130, 12, 15);
		TextDrawer.drawString("into 50 points", 50, 115, 12, 15);
		TextDrawer.drawInt(1, 250, 130, 15, 15, 2);
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
