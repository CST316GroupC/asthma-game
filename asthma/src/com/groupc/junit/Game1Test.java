package com.groupc.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import com.groupc.game1.World;

public class Game1Test 
{
	World world = new World();	
	
	@Before
	public void setUp()
	{
		try {
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		world = new World();
		world.update(0);
		world.render();
	}

	
	@Test
	public void creationTest()
	{
		assert(world.joey != null);
		assert(world.state == World.WORLD_STATE_PLAYING);
	}
	
	@Test
	public void updateTest()
	{
		world.update(1);
		assert(world.joey.velocity.x == world.joey.accel.x);
		assert(world.joey.position.x != 1 + world.joey.velocity.x);
	}
	
	@Test
	public void renderTest()
	{
		world.render();
	}
	
	@After
	public void cleanUp()
	{
		Display.destroy();
	}
 
}
