package com.groupc.game;

public abstract class GameScreen
{

	public boolean isClosing = false;
	public Asset assets;
	
	public GameScreen(Asset asset)
	{
		this.assets = asset;
	}
	
	public abstract void update(float deltaTime);	
	public abstract void present(float deltaTime);
	public abstract void pause();
	public abstract void resume();
	public abstract void dispose();
	public abstract GameScreen getNext();
}
