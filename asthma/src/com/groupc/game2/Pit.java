package com.groupc.game2;

import com.groupc.game.GameObject;

/**
 * Bounding box for the pit is slightly smaller than drawn area. This is so the player can touch the 
 * Pit and not fall in 
 * @author Edwin Avalos
 *
 */
public class Pit extends GameObject
{
	public static final float WIDTH = .05f;
	public static final float HEIGHT = .05f;
	
	public Pit(float x, float y)
	{
		super(x, y, WIDTH, HEIGHT);
	}
}
