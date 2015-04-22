package com.groupc.game2;

import com.groupc.game.Asset;
import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
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
		upgradeAxes = new Rectangle(300, 200, 50, 50);
		upgradeLives = new Rectangle(300, 150, 50, 50);
		lblAxes = new Rectangle(200, 200, 100, 50);
		lblLives = new Rectangle(200, 150, 100, 50);
		buyGems = new Rectangle(300, 100, 50, 50);
		lblGems = new Rectangle(200, 100, 100, 50);
		back = new Rectangle(350, 0, 50, 50);
		displayGems = new Rectangle(0, 350, 100, 50);
		displayTokens = new Rectangle(200, 350, 100, 50);
		displayLives = new Rectangle(0, 300, 100, 50);
		displayAxes = new Rectangle(200, 300, 100, 50);
		
		axes = Integer.parseInt(assets.getProps().getProperty("mazeAxes"));
		lives = Integer.parseInt(assets.getProps().getProperty("mazeLives"));
		tokens = Integer.parseInt(assets.getProps().getProperty("Tokens"));
		gems = Integer.parseInt(assets.getProps().getProperty("mazeGems"));
	}

	@Override
	public void update(float deltaTime) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void present(float deltaTime) 
	{
		// TODO Auto-generated method stub
		
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
		assets.save(assets.getFilename());
	}

	@Override
	public GameScreen getNext()
	{
		return new MainMenu(assets);
	}

}
