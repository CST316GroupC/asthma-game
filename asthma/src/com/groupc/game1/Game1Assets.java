package com.groupc.game1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.groupc.TextDrawer;
import com.groupc.game.Asset;
import com.groupc.game.TextureRegion;

public class Game1Assets extends Asset
{	
	public final static String FILENAME = "resources/game1/joey.properties";
	private static Texture sheet;
	
	public Game1Assets()
	{
		super("test");
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("resources/game1/atlas.png"));
			sheet = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("resources/game1/mainmenu.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sprites = new Hashtable<String, TextureRegion>();
		load();
	}
	
	protected void load()
	{
		reload();		
		sprites.put("title", new TextureRegion(sheet, 0, 226, 235, 30));
		sprites.put("playBut", new TextureRegion(sheet, 0, 128, 116, 43));
		sprites.put("upgradeBut", new TextureRegion(sheet, 0, 176, 116, 42));
		sprites.put("optionsBut", new TextureRegion(sheet, 0, 84, 113, 42));
		sprites.put("soundOn", new TextureRegion(sheet, 0, 24, 54, 55));
		sprites.put("soundOff", new TextureRegion(sheet,  54, 24, 54, 55));
		sprites.put("soundLbl", new TextureRegion(sheet,  0, 0, 65, 23));
		sprites.put("arrowBut", new TextureRegion(sheet, 110, 24, 54, 55));
		
		sprites.put("back", new TextureRegion(texture, 128, 512 - 64, 32, 32));
		sprites.put("upgrade", new TextureRegion(texture, 160, 512 - 64, 32, 32));
		
		sprites.put("sky", new TextureRegion(texture, 0, 512 - 96, 32, 96));
		sprites.put("grass", new TextureRegion(texture, 32, 512 - 32, 32, 32));
		sprites.put("hay", new TextureRegion(texture, 32, 512 - 64, 32, 32));
		sprites.put("hayHit", new TextureRegion(texture, 32, 512 - 96, 32, 32));
		sprites.put("cow", new TextureRegion(texture, 64, 512 - 32, 64, 32));
		sprites.put("cowHit", new TextureRegion(texture, 64, 512 - 32 - 64, 64, 64));
		sprites.put("seed", new TextureRegion(texture, 128, 512 - 32, 32, 32));
		sprites.put("ramp", new TextureRegion(texture, 160, 512 - 32, 32, 32));
		sprites.put("joeySk", new TextureRegion(texture, 192, 512 - 32, 32, 32));
		sprites.put("joeyFly", new TextureRegion(texture, 192, 512 - 32 - 32, 32, 32));
		sprites.put("joeyFall", new TextureRegion(texture, 192, 512 - 64 - 32, 32, 32));
		sprites.put("joeyBou", new TextureRegion(texture,192,  512 - 96 - 32, 32,32));
		sprites.put("joeyFlap", new TextureRegion(texture,192, 512 - 128 - 32, 32, 32));
		sprites.put("joeyGlide", new TextureRegion(texture, 192, 512 - 160 - 32, 32, 32));	
		
	}
	
	
	public void reload()
	{
		try {
			FileInputStream in = new FileInputStream(FILENAME);
			props.load(in);
			in.close();
			String temp = props.getProperty("firstTime", "true");
			if("true".equals(temp))
			{
				props.setProperty("speedMult", "1");
				props.setProperty("statima", "1");
				props.setProperty("score", "0");
				props.setProperty("maxDistance", "0");
				props.setProperty("seeds", "0");
				props.setProperty("firstTime", "false");
				props.setProperty("sound", "true");
				save();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}

	@Override
	public String getFilename() {
		// TODO Auto-generated method stub
		return null;
	}
}
