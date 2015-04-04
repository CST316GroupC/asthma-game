package com.groupc;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.openal.SoundStore;

import com.groupc.screens.Game1Screen;
import com.groupc.screens.LoginScreen;
import com.groupc.screens.Screen;


public class Runner extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Screen screen;
	private long startTime = System.nanoTime();
	public static AudioPlayer player;
	
	public final static int SCR_WIDTH = 500;
	public final static int SCR_HEIGHT = 500;
	public final static long FPS = (long) (1.0/60.0);
	
	boolean isClosing = false;
	
	public Runner()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(SCR_WIDTH, SCR_HEIGHT));
		setLocationRelativeTo(null);
		
		player = new AudioPlayer();
		player.loadSong("resources/sounds/AMemoryAway.ogg");
		player.playMusic(true);
		screen = new LoginScreen(this);
		//screen = new Game1Screen(this);
		
	}
	
	public void run() throws InterruptedException
	{
		SoundStore.get().poll(0);
		float deltaTime = (System.nanoTime()-startTime) / 1000000000.0f;
		startTime = System.nanoTime();
		if(deltaTime < FPS)
		{
			Thread.sleep((long) (FPS - deltaTime));
		}
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
		Display.destroy();
		AL.destroy();
	}

}
