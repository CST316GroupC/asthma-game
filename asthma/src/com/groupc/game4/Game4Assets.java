package com.groupc.game4;

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
import com.groupc.math.Rectangle;

public class Game4Assets extends Asset
{	
	private static Texture sheet;
	private static Texture background;
	private static Texture g4texture;
	
	public Game4Assets(String user)
	{
		super(user);
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("resources/game1/atlas.png"));
			sheet = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("resources/game1/mainmenu.png"));
			background = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("resources/game4/table.png"));
			g4texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("resources/game4/game4assets.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sprites = new Hashtable<String, TextureRegion>();
		load();
	}
	
	public void load()
	{
		reload();
		sprites = new Hashtable<String, TextureRegion>();
		sprites.put("title", new TextureRegion(sheet, 0, 226, 235, 30));
		sprites.put("back", new TextureRegion(texture, 128, 512 - 64, 32, 32));
		sprites.put("upgrade", new TextureRegion(texture, 160, 512 - 64, 32, 32));
		sprites.put("table", new TextureRegion(background, 400, 400, 80, 60));
		
		sprites.put("broccoli", new TextureRegion(g4texture, 0, 256 - 1*68, 75, 75));
		sprites.put("orange", new TextureRegion(g4texture, 75, 256 - 1*68, 75, 75));
		sprites.put("cake", new TextureRegion(g4texture, 0, 256 - 2*68, 73, 70));
		sprites.put("cookie", new TextureRegion(g4texture, 75, 256 - 2*68, 70, 70));
	
		TextDrawer.prepare();
	}
	
	public void reload()
	{
		//TODO: update for game4
		try 
		{
			FileInputStream in = new FileInputStream(getFilename());
			props.load(in);
			in.close();

			String temp = props.getProperty("game4FirstTime", "true");
			if("true".equals(temp))
			{
				props.setProperty("game4Timer", "60");
				props.setProperty("game4BonusTime", "0");
				props.setProperty("game4Sound", "true");
				props.setProperty("game4Score", "0");
				props.setProperty("game4MaxScore", "0");
				props.setProperty("game4FirstTime", "false");
				props.setProperty("healthyFoodScoreBonus", "0");
				props.setProperty("healthyFoodTimeBonus", "0");
				save();

			}
		} 
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}

}