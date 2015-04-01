package com.groupc.game1;

import com.groupc.TextDrawer;
import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.math.Rectangle;

public class Instructions extends GameScreen
{
	private Camera cam;
	private Rectangle back;
	
	public Instructions()
	{
		Assets.load();
		cam = new Camera(300, 100);
		cam.setCamera();
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void present(float deltaTime)
	{
		TextDrawer.drawString("Press w or up to flap wings", 20, 10, 10, 10);
		TextDrawer.drawString("Press s or down to glide wings", 20, 20, 10, 10);
		TextDrawer.drawString("Press p to pause", 20, 30, 10, 10);
		TextDrawer.drawString("Press q to quit", 20, 40, 10, 10);
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
		return new MainMenu();
	}

}
