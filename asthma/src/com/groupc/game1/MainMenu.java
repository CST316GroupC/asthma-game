package com.groupc.game1;

import org.lwjgl.input.Mouse;

import com.groupc.TextDrawer;
import com.groupc.game.Asset;
import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.math.CollisionChecker;
import com.groupc.math.Rectangle;
import com.groupc.math.Vector;

public class MainMenu extends GameScreen
{	
	private final Camera cam;
	private final Rectangle title;
	private final Rectangle play;
	private final Rectangle options;
	private final Rectangle upgrade;
	private final Rectangle instruct;
	private final Vector mouseClick;
	private int next;
	
	public MainMenu(Asset asset)
	{
		super(asset);
		cam = new Camera(400, 400);
		cam.setCamera();
		title = new Rectangle(50, 300, 300, 100);
		play = new Rectangle(25, 200, 150, 75);
		options = new Rectangle(200, 200, 150, 75);
		upgrade = new Rectangle(25, 100, 150, 75);
		instruct = new Rectangle(200, 100, 150, 75);
		mouseClick = new Vector();
	}

	@Override
	public void update(float deltaTime) {	
		if(Mouse.isButtonDown(0))
		{
			mouseClick.set(Mouse.getX(), Mouse.getY());
			cam.click(mouseClick);
			if(CollisionChecker.PointToRect(mouseClick, play))
			{
				this.dispose();
				next = 1;
			}
			if(CollisionChecker.PointToRect(mouseClick, options))
			{
				this.dispose();
				next = 2;
				
			}
			if(CollisionChecker.PointToRect(mouseClick, upgrade))
			{
				this.dispose();
				next = 3;
			}
			if(CollisionChecker.PointToRect(mouseClick, instruct))
			{
				this.dispose();
				next = 4;
			}
		}
		
	}

	@Override
	public void present(float deltaTime)
	{
		cam.setCamera();
		assets.getTexture().bind();
		assets.getImage("grass").draw(new Rectangle(0, 0, cam.frustumWidth, cam.frustumHeight));
		assets.getImage("title").draw(title);
		TextDrawer.drawStringinRect("Start", play, true);
		TextDrawer.drawStringinRect("Options", options, true);
		TextDrawer.drawStringinRect("Upgrade", upgrade, true);
		TextDrawer.drawStringinRect("Instructions", instruct, true);
		//Assets.getImage("playBut").draw(play);
		//Assets.getImage("optionsBut").draw(options);
		//Assets.getImage("upgradeBut").draw(upgrade);
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
		if(next == 1)
		{
			return new World(assets);
		}
		if(next == 2)
		{
			return new Options(assets, this.cam);
		}
		if(next == 3)
		{
			return new UpgradeScreen(assets);
		}
		if(next == 4)
		{
			return new Instructions(assets);
		}
		return null;
	}
}
