package com.groupc.game;

import com.groupc.Runner;

public abstract class GameScreen
{
	
	boolean isClosing = false;
	
	public abstract void update(float deltaTime);	
	public abstract void present(float deltaTime);
	public abstract void pause();
	public abstract void resume();
	public abstract void dispose();
}
