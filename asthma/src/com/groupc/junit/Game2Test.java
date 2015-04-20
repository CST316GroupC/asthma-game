package com.groupc.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import com.groupc.TextDrawer;
import com.groupc.game2.Game2Assets;
import com.groupc.game2.MazeWorld;
import com.groupc.math.CollisionChecker;
import com.groupc.math.Vector;

public class Game2Test 
{
	Game2Assets assets;
	MazeWorld world;	
	
	@Before
	public void setUp()
	{
		try {
			Display.create();
			TextDrawer.prepare();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assets = new Game2Assets();
		assets.load();
		world = new MazeWorld(assets);
		world.setLevel(0);
		world.levelDone();
		world.update(0);
		world.present(0);
	}

	
	@Test
	public void creationTest()
	{
		assert world.player != null;
		assert world.goal != null;
		assert world.player.position.x == 1+.5f;
		assert world.player.position.y == 7-.5f;
		assert world.goal.position.x == 7+.5f;
		assert world.goal.position.y == 14-.5f;
		assert world.enemies.isEmpty();
		assert !world.walls.isEmpty();
		assert world.pits.isEmpty();
		assert world.gems.isEmpty();
		assert !world.dirts.isEmpty();
		assert world.getState() == MazeWorld.WORLD_STATE_PLAYING;
		assert world.playerDig.position.x == -1;
		assert world.getLevel() == 0;
	}
	
	@Test
	public void collisionTest()
	{
		world.player.position.x = .5f;
		world.player.position.y = 14.5f;
		world.update(0);
		world.present(0);
		for(int i = 1; i < world.walls.size(); i++)
		{
			assert !CollisionChecker.RectToRect(world.player.bounds, world.walls.get(i).bounds);
		}
		
		assert CollisionChecker.RectToRect(world.player.bounds, world.walls.get(0).bounds);
	}
	
	@Test
	public void playerDirectionTest()
	{
		world.player.position.x = 7.5f;
		world.player.position.y = 4.5f;
		world.update(0);
		world.present(0);
		world.player.setDirection(new Vector(0, -10)); //left
		assert world.player.getLastDirection() == 0;
		world.player.setDirection(new Vector(0, 10)); //right
		assert world.player.getLastDirection() == 2;
		world.player.setDirection(new Vector(10, 0)); //up
		assert world.player.getLastDirection() == 1;
		world.player.setDirection(new Vector(-10, 0)); //down
		assert world.player.getLastDirection() == 3;
		assert world.player.getLastDirection() != 1;
	}
	
	@After
	public void cleanUp()
	{
		Display.destroy();
	}
}
