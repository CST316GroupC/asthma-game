package com.groupc.game2;

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
	private final Rectangle upgradeAxes;
	private final Rectangle upgradeLives;
	private final Rectangle buyGems;
	private final Rectangle displayGems;
	private final Rectangle displayAxes;
	private final Rectangle displayLives;
	private final Rectangle displayTokens;
	private final Rectangle lblAxes;
	private final Rectangle lblLives;
	private final Rectangle lblGems;
	private final Rectangle lblWarning;
	private final Rectangle back;
	
	//upgrades
	private int axes;
	private int lives;
	private int gems;
	private int tokens;

	public UpgradeScreen(Asset asset) 
	{
		super(asset);
		cam = new Camera(400, 400);
		cam.setCamera();
		mouseClick = new Vector();
		upgradeAxes = new Rectangle(300, 250, 50, 50);
		upgradeLives = new Rectangle(300, 200, 50, 50);
		lblAxes = new Rectangle(50, 250, 200, 50);
		lblLives = new Rectangle(50, 200, 200, 50);
		buyGems = new Rectangle(300, 150, 50, 50);
		lblGems = new Rectangle(50, 150, 200, 50);
		back = new Rectangle(325, 5, 75, 75);
		displayGems = new Rectangle(0, 350, 100, 50);
		displayTokens = new Rectangle(200, 350, 100, 50);
		displayLives = new Rectangle(0, 300, 100, 50);
		displayAxes = new Rectangle(200, 300, 100, 50);
		lblWarning = new Rectangle(0, 0, 300, 50);
		
		axes = Integer.parseInt(assets.getProps().getProperty("mazeMaxAxes"));
		lives = Integer.parseInt(assets.getProps().getProperty("mazeMaxLives"));
		tokens = Integer.parseInt(assets.getProps().getProperty("tokens"));
		gems = Integer.parseInt(assets.getProps().getProperty("mazeGems"));
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
		            System.out.println("Left button released");
		            mouseClick.set(Mouse.getX(), Mouse.getY());
		            cam.click(mouseClick);
					if(CollisionChecker.PointToRect(mouseClick, upgradeAxes))
					{
						if(gems >= 10)
						{
							gems -= 10;
							axes++;
						}
					}
					if(CollisionChecker.PointToRect(mouseClick, back))
					{
						dispose();
					}
					if(CollisionChecker.PointToRect(mouseClick, upgradeLives))
					{
						if(gems >= 25)
						{
							gems -= 25;
							lives++;
						}
					}
					if(CollisionChecker.PointToRect(mouseClick, buyGems))
					{
						if(tokens >= 1)
						{
							tokens -= 1;
							gems+= 5;
						}
					}
					saveUpgrades();
		        }
		    }
		    
		}
	}

	@Override
	public void present(float deltaTime) 
	{
		cam.setCamera();
		
		assets.getTexture().bind();
		assets.getImage("background").draw(new Rectangle(0, 0, cam.frustumWidth, cam.frustumHeight));
		TextDrawer.drawStringinRect("Buy an Axe for 10 Gems", lblAxes);
		TextDrawer.drawStringinRect("Buy a Life for 25 Gems", lblLives);
		TextDrawer.drawStringinRect("Buy 5 Gems for 1 token", lblGems);
		TextDrawer.drawStringinRect("Buy", upgradeAxes);
		TextDrawer.drawStringinRect("Buy", upgradeLives);
		TextDrawer.drawStringinRect("Buy", buyGems);
		TextDrawer.drawStringinRect("Tokens "+tokens, displayTokens);
		TextDrawer.drawStringinRect("Gems " + gems, displayGems);
		TextDrawer.drawStringinRect("Axes " + axes, displayAxes);
		TextDrawer.drawStringinRect("Lives " + lives, displayLives);
		TextDrawer.drawStringinRect("Back", back, true);
		TextDrawer.drawStringinRect("Upgrades apply at Game Over", lblWarning);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
	public void saveUpgrades()
	{
		assets.setProps("mazeGems", gems+"");
		assets.setProps("mazeMaxAxes", axes+"");
		assets.setProps("mazeMaxLives", lives+"");
		assets.setProps("tokens", tokens+"");
		assets.save();
	}

	@Override
	public void dispose() 
	{
		saveUpgrades();
		isClosing = true;
	}

	@Override
	public GameScreen getNext()
	{
		return new MainMenu(assets);
	}

}
