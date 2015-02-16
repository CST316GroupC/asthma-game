package com.groupc.game1;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Assets
{
	public static Texture cannonandball;
	public static Texture mainmenu;
	
	public static void load()
	{
		try {
			cannonandball = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("cannonandball.png"));
			mainmenu = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("mainmenu.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
