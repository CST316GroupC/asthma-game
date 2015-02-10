package com.groupc.screens;

import com.groupc.Runner;


public abstract class Screen
{	
	protected final Runner run;
	
	boolean isClosing = false;
	
	public Screen(Runner run)
	{
		isClosing = false;
		this.run = run;
	}
	
	public abstract void update(float deltaTime);	
	public abstract void present(float deltaTime);
	public abstract void pause();
	public abstract void resume();
	public abstract void dispose();
}
