package com.groupc.game2;

import com.groupc.game.GameObject;

/**
 * 
 * @author Edwin Avalos
 *
 */
public class Wall extends GameObject
{
	public static final float WIDTH = 1f;
	public static final float HEIGHT = 1f;
	
	public Wall(float x, float y)
	{
		super(x, y, WIDTH, HEIGHT);
	}
}
