package com.groupc.game1;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.groupc.game.TextureRegion;

public class Assets
{
	public static Texture buttons;
	public static TextureRegion playBut;
	public static TextureRegion optionsBut;
	public static TextureRegion upgradeBut;
	public static TextureRegion title;
	public static TextureRegion soundOn;
	public static TextureRegion soundOff;
	public static TextureRegion soundLbl;
	
	public static void load()
	{
		try {
			buttons = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("mainmenu.png"));
			
			title = new TextureRegion(buttons, 0, 226, 235, 30);
			playBut = new TextureRegion(buttons, 0, 128, 116, 43);
			upgradeBut = new TextureRegion(buttons, 0, 176, 116, 42);
			optionsBut = new TextureRegion(buttons, 0, 84, 113, 42);
			soundOn = new TextureRegion(buttons, 0, 24, 54, 55);
			soundOff = new TextureRegion(buttons, 54, 24, 54, 55);
			soundLbl = new TextureRegion(buttons, 0, 0, 65, 23);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
