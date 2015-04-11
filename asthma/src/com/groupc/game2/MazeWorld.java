package com.groupc.game2;

import com.groupc.game.Asset;
import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.math.Rectangle;

public class MazeWorld extends GameScreen
{
	public static final float FRUSTUM_WIDTH = 15;
	public static final float FRUSTUM_HEIGHT = 15;
	public static final float WORLD_WIDTH = FRUSTUM_WIDTH * 10; //400 (the * 100 means the total width is 5000 pixels but only 400 shown)
	public static final float WORLD_HEIGHT = FRUSTUM_HEIGHT * 5; //400 (the * 20 means the total height is 200 pixels but only 400 shown)
	
	public static final int WORLD_STATE_PAUSED = 0;
	public static final int WORLD_STATE_PLAYING = 1;
	public static final int WORLD_STATE_OVER = 2;
	
	private Camera cam;
	public final Player player;
	
	public MazeWorld(Asset asset) 
	{
		super(asset);
		cam = new Camera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		player = new Player(.5f, .5f);
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void present(float deltaTime) 
	{
		cam.setCamera();
		renderBackground();
		renderPlayer();
	}
	
	public void renderBackground()
	{
		assets.getImage("background").draw(new Rectangle(0, 0, WORLD_WIDTH, WORLD_HEIGHT));
	}
	
	public void renderPlayer()
	{
		Rectangle rect = new Rectangle(player.position.x - Player.WIDTH/2, player.position.y - Player.HEIGHT/2, 1f, 1f);
		
		switch(player.getState())
		{
		default:
			assets.getImage("player").draw(rect);
		}
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
