package com.groupc.game1;

import org.lwjgl.input.Mouse;

import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.math.CollisionChecker;
import com.groupc.math.Rectangle;
import com.groupc.math.Vector;

public class MainMenu extends GameScreen
{	
	Camera cam;
	Rectangle title;
	Rectangle play;
	Rectangle options;
	Rectangle upgrade;
	Vector mouseClick;
	int next;
	
	public MainMenu()
	{
		Assets.load();
		cam = new Camera(400, 400);
		cam.setCamera();
		title = new Rectangle(50, 300, 300, 100);
		play = new Rectangle(25, 200, 150, 75);
		options = new Rectangle(200, 200, 150, 75);
		upgrade = new Rectangle(25, 100, 150, 75);
		mouseClick = new Vector();
	}

	@Override
	public void update(float deltaTime) {	
		if(Mouse.isButtonDown(0))
		{
			mouseClick.set(Mouse.getX(), Mouse.getY());
			
			if(CollisionChecker.PointToRect(mouseClick, play))
			{
				System.out.println("play");
				this.dispose();
				next = 1;
			}
			if(CollisionChecker.PointToRect(mouseClick, options))
			{
				System.out.println("options");
				this.dispose();
				next = 2;
				
			}
			if(CollisionChecker.PointToRect(mouseClick, upgrade))
			{
				System.out.println("upgrade");
			}
		}
		
	}

	@Override
	public void present(float deltaTime)
	{
		cam.setCamera();
		Assets.sheet.bind();
		Assets.title.draw(title);
		Assets.playBut.draw(play);
		Assets.optionsBut.draw(options);
		Assets.upgradeBut.draw(upgrade);
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
			return new World();
		if(next == 2)
			return new Options(this.cam);
		return null;
	}
}
