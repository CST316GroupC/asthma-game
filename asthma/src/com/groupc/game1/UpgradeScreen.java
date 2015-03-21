package com.groupc.game1;

import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.math.Rectangle;
import com.groupc.math.Vector;

public class UpgradeScreen extends GameScreen
{
	Camera cam;
	Vector mouseClick;
	Rectangle upgradeSpeed;
	Rectangle upgradeFlaps;
	int next;
	
	public UpgradeScreen()
	{
		Assets.reload();
		cam = new Camera(400, 400);
		cam.setCamera();
		upgradeSpeed = new Rectangle(300, 300, 50, 50);
		upgradeFlaps = new Rectangle(300, 350, 50, 50);
		mouseClick = new Vector();
	}

	
	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void present(float deltaTime)
	{
		Assets.joeyBou.draw(upgradeSpeed);
		Assets.joeyBou.draw(upgradeFlaps);
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
		return null;
	}

}
