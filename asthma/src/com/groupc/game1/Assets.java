package com.groupc.game1;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.groupc.game.TextureRegion;

public class Assets
{
	public static Texture sheet;
	public static Texture joeySK;
	public static Texture joeyF;
	public static TextureRegion playBut;
	public static TextureRegion optionsBut;
	public static TextureRegion upgradeBut;
	public static TextureRegion title;
	public static TextureRegion soundOn;
	public static TextureRegion soundOff;
	public static TextureRegion soundLbl;
	public static TextureRegion arrowBut;
	public static TextureRegion ramp;
	public static TextureRegion ball;
	public static TextureRegion joey_SK;
	public static TextureRegion joey_fly;
	
	public static void load()
	{
		try {
			sheet = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("mainmenu.png"));
			joeySK = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("JoeyRooster.png"));
			joeyF = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("JoeyRoosterFlying.png"));
			
			title = new TextureRegion(sheet, 0, 226, 235, 30);
			playBut = new TextureRegion(sheet, 0, 128, 116, 43);
			upgradeBut = new TextureRegion(sheet, 0, 176, 116, 42);
			optionsBut = new TextureRegion(sheet, 0, 84, 113, 42);
			soundOn = new TextureRegion(sheet, 0, 24, 54, 55);
			soundOff = new TextureRegion(sheet, 54, 24, 54, 55);
			soundLbl = new TextureRegion(sheet, 0, 0, 65, 23);
			arrowBut = new TextureRegion(sheet, 110, 24, 54, 55);
			ramp = new TextureRegion(sheet, 121, 175, 40, 41);
			ball = new TextureRegion(sheet, 184, 183, 20, 20);
			joey_SK = new TextureRegion(joeySK, 0, 0, 64, 64);
			joey_fly = new TextureRegion(joeyF, 0, 0, 64, 64);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
