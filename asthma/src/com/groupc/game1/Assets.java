package com.groupc.game1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.groupc.game.TextureRegion;

public class Assets
{
	public static Texture atlas;
	public static Texture sheet;
	public static TextureRegion playBut;
	public static TextureRegion optionsBut;
	public static TextureRegion upgradeBut;
	public static TextureRegion title;
	public static TextureRegion soundOn;
	public static TextureRegion soundOff;
	public static TextureRegion soundLbl;
	public static TextureRegion arrowBut;
	
	//background
	public static TextureRegion sky;
	public static TextureRegion grass;
	
	//Cows
	public static TextureRegion cow;
	public static TextureRegion cowhit;
	
	//Joey the rooster
	public static TextureRegion joeysk;
	public static TextureRegion joeyfly;
	public static TextureRegion joeyfall;
	public static TextureRegion joeyRamp;
	public static TextureRegion joeyBou;
	public static TextureRegion joeyflap;
	
	//ramp
	public static TextureRegion ramp;
	
	//seeds
	public static TextureRegion seed1;
	
	public static Properties joeyProps;
	
	public static void load()
	{
		joeyProps = new Properties();
		try {
			FileInputStream in = new FileInputStream("res/joey.properties");
			joeyProps.load(in);
			in.close();
			String temp = joeyProps.getProperty("firstTime", "true");
			if(temp.equals("true"))
			{
				joeyProps.setProperty("speedMult", "1");
				joeyProps.setProperty("statima", "3");
				joeyProps.setProperty("score", "0");
				joeyProps.setProperty("maxDistance", "0");
				joeyProps.setProperty("seeds", "0");
				joeyProps.setProperty("firstTime", "false");
				save();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		try {
			sheet = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("mainmenu.png"));
			atlas = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/atlas.png"));
			
			title = new TextureRegion(sheet, 0, 226, 235, 30);
			playBut = new TextureRegion(sheet, 0, 128, 116, 43);
			upgradeBut = new TextureRegion(sheet, 0, 176, 116, 42);
			optionsBut = new TextureRegion(sheet, 0, 84, 113, 42);
			soundOn = new TextureRegion(sheet, 0, 24, 54, 55);
			soundOff = new TextureRegion(sheet, 54, 24, 54, 55);
			soundLbl = new TextureRegion(sheet, 0, 0, 65, 23);
			arrowBut = new TextureRegion(sheet, 110, 24, 54, 55);
			
			sky = new TextureRegion(atlas, 0, 512 - 96, 32, 96);
			grass = new TextureRegion(atlas, 32, 512 - 32, 32, 32);
			cow = new TextureRegion(atlas, 64, 512 - 32, 64, 32);
			cowhit = new TextureRegion(atlas, 64, 512 - 32 - 64, 64, 64);
			seed1 = new TextureRegion(atlas, 128, 512 - 32, 32, 32);
			ramp = new TextureRegion(atlas, 160, 512 - 32, 32, 32);
			joeysk = new TextureRegion(atlas, 192, 512 - 32, 32, 32);
			joeyfly = new TextureRegion(atlas, 192, 512 - 32 - 32, 32, 32);
			joeyfall = new TextureRegion(atlas, 192, 512 - 64 - 32, 32, 32);
			joeyBou = new TextureRegion(atlas, 192,  512 - 96 - 32, 32,32);
			joeyflap = new TextureRegion(atlas, 192, 512 - 128 - 32, 32, 32);
			joeyRamp = new TextureRegion(atlas, 192, 512 - 160 - 32, 32, 32);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void save()
	{
		FileOutputStream out;
		try {
			out = new FileOutputStream("res/joey.properties");
			joeyProps.store(out, "---No Comment---");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
