package com.groupc;

import javax.swing.JFrame;

import com.groupc.screens.LoginScreen;
import com.groupc.screens.Screen;


public class Runner extends JFrame
{
	Screen screen;
	long startTime = System.nanoTime();
	public AudioPlayer player;
	
	public final int SCR_WIDTH = 500;
	public final int SCR_HEIGHT = 500;
	
	boolean isClosing = false;
	
	public Runner()
	{
		player = new AudioPlayer();
		screen = new LoginScreen(this);
		
	}
	
	public void run()
	{
		SoundStore.get().poll(0);
		float deltaTime = (System.nanoTime()-startTime) / 1000000000.0f;
		startTime = System.nanoTime();
		screen.update(deltaTime);
		screen.present(deltaTime);
	}
	
	public void setScreen(Screen screen)
	{
		if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
	}
	
	public void close()
	{
		isClosing = true;
	}

}
