package com.groupc.game1;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.groupc.game.TextureRegion;

public class Assets
{
	public static Texture cannonandball;
	public static Texture mainmenu;
	public static TextureRegion ball;
	
	public static void load()
	{
		try {
			cannonandball = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("cannonandball.png"));
			mainmenu = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("mainmenu.png"));
			
			ball = new TextureRegion(cannonandball, 32, 0, 32, 32);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
