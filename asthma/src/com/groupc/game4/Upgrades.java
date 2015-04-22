package com.groupc.game4;

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
	private final Rectangle increaseTimer;
	private final Rectangle increaseFoodBonus;
	private final Rectangle increaseFoodTime;
	private final Rectangle back;
	
	private int score;
	private int timer;
	private int foodBonus;
	private int foodTime;
	private int chestAmount;
	private int healthGlobeAmount;
	private int originalTime;
	
	private int[] prices = {10, 20, 40, 70, 110, 160, 220, 290, 370, 500};
	
	public Upgrades(Asset assets)
	{
		super(assets);
		assets.reload();
		cam = new Camera(400, 400);
		cam.setCamera();
		increaseTimer = new Rectangle(310, 270, 50, 50);
		increaseFoodBonus = new Rectangle(310, 210, 50, 50);
		increaseFoodTime = new Rectangle(310, 210 - 60, 50, 50);
		
		back = new Rectangle(300, 0, 100, 100);
		
		mouseClick = new Vector();
		
		//get the current saved data
		
		score = Integer.parseInt(assets.getProps().getProperty("game4Score"));
		originalTime = Integer.parseInt(assets.getProps().getProperty("game4Timer"));
		timer = Integer.parseInt(assets.getProps().getProperty("game4BonusTime"));
		foodBonus = Integer.parseInt(assets.getProps().getProperty("healthyFoodScoreBonus"));
		foodTime = Integer.parseInt(assets.getProps().getProperty("healthyFoodTimeBonus"));
		
		
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
		            
					if(CollisionChecker.PointToRect(mouseClick, increaseTimer))
					{
						if(score > prices[timer])
						{	
							if(timer < prices.length - 1)
							{
								score -= prices[timer];	
								timer++;;
							}
							//created new time to add original + bonus. if done in set properties... originalTime + 10*timer will 
							//append instead of add. ex: 60 + 10*1 would be  6010 instead of 70
							int newTime = originalTime + 10*timer;
							assets.getProps().setProperty("game4Timer", "" + newTime);
							assets.getProps().setProperty("game4BonusTime", "" + timer);
						}
					}
					
					if(CollisionChecker.PointToRect(mouseClick, increaseFoodBonus))
					{
						if(score > prices[foodBonus])
						{	
							if(score > prices[foodBonus])
							{	
								if(foodBonus < prices.length - 1)
								{
									score -= prices[foodBonus];	
									foodBonus++;;
								}
								assets.getProps().setProperty("healthyFoodScoreBonus", "" + foodBonus);
							}
						 }
					}
					
					if(CollisionChecker.PointToRect(mouseClick, increaseFoodTime))
					{
						if(score > prices[foodTime])
						{	
							if(score > prices[foodTime])
							{	
								if(foodTime < prices.length - 1)
								{
									score -= prices[foodTime];	
									foodTime++;;
								}
								assets.getProps().setProperty("healthyFoodTimeBonus", "" + foodTime);
							}
						 }
					}
					assets.getProps().setProperty("game4Score", "" +score);
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
		assets.getImage("upgrade").draw(increaseTimer);
		assets.getImage("upgrade").draw(increaseFoodBonus);
		assets.getImage("upgrade").draw(increaseFoodTime);
		assets.getImage("back").draw(back);
		
		TextDrawer.drawString("Score", 25, 350, 40, 40);
		TextDrawer.drawInt(score, 250, 350, 20, 40, 5);
		
		TextDrawer.drawString("Extra time", 50, 290, 15, 20);
		TextDrawer.drawInt(prices[timer], 250, 290, 10, 20, 4);
		
		TextDrawer.drawString("Bonus score for healthy food", 50, 230, 6, 20);
		TextDrawer.drawInt(prices[foodBonus], 250, 230, 10, 20, 4);
		
		TextDrawer.drawString("Extra time for healthy food", 50, 230-60, 6, 20);
		TextDrawer.drawInt(prices[foodTime], 250, 230-60, 10, 20, 4);
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
		assets.save(Game4Assets.FILENAME);
		isClosing = true;
	}

	@Override
	public GameScreen getNext() 
	{
		// TODO Auto-generated method stub
		return new MainMenu(assets);
	}

}
