package com.groupc.game2;

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

import com.groupc.Runner;
import com.groupc.TextDrawer;
import com.groupc.game.Asset;
import com.groupc.game.TextureRegion;

public class Game2Assets extends Asset
{	
	private String filename;
	public Game2Assets(Runner run)
	{
		super();
		setFilename("resources/interface/parent_controls/"+run.getUserName() + ".properties");
	}
	
	public void load()
	{
		reload();
		sprites = new Hashtable<String, TextureRegion>();
		
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("resources/game2/maze.png"));
			
			sprites.put("playerLeft", new TextureRegion(texture, 0, 512-32, 32, 32));
			sprites.put("playerUp", new TextureRegion(texture, 32, 512-32, 32, 32));
			sprites.put("playerRight", new TextureRegion(texture, 32*2, 512-32, 32, 32));
			sprites.put("playerDown", new TextureRegion(texture, 32*3, 512-32, 32, 32));
			sprites.put("playerDigUp", new TextureRegion(texture, 32*4, 512-32, 32, 32));
			sprites.put("playerDigRight", new TextureRegion(texture, 32*5, 512-32, 32, 32));
			sprites.put("playerDigDown", new TextureRegion(texture, 32*6, 512-32, 32, 32));
			sprites.put("playerDigLeft", new TextureRegion(texture, 32*7, 512-32, 32, 32));
			sprites.put("wall", new TextureRegion(texture, 0, 512-32*2, 32, 32));
			sprites.put("grass", new TextureRegion(texture, 0, 512-32*3, 32, 32));
			sprites.put("dirt", new TextureRegion(texture, 0, 512-32*4, 32, 32));
			sprites.put("spikeDown", new TextureRegion(texture, 0, 512-32*5, 32, 32));
			sprites.put("spikeUp", new TextureRegion(texture, 32, 512-32*5, 32, 32));
			sprites.put("background", new TextureRegion(texture, 0, 512-(32*6), 32, 32));
			sprites.put("pit", new TextureRegion(texture, 0, 512-32*7, 32, 32));
			sprites.put("exit", new TextureRegion(texture, 0, 512-32*8, 32, 32));
			sprites.put("gem", new TextureRegion(texture, 0, 512-32*9, 32, 32));
			sprites.put("LREnemy", new TextureRegion(texture, 0, 512-32*10, 32, 32));
			sprites.put("UDEnemy", new TextureRegion(texture, 0, 512-32*11, 32, 32));
			
						
		} catch (IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}		
		TextDrawer.prepare();
	}
	
	public void reload()
	{
		FileInputStream in = null;
		try 
		{
			in = new FileInputStream(filename);
			props.load(in);
			String temp = props.getProperty("mazefirstTime", "true");
			if("true".equals(temp))
			{
				props.setProperty("mazefirstTime", "false");
				props.setProperty("mazeLevel", "1");
				props.setProperty("mazeScore", "0");
				props.setProperty("mazeLives", "3");
				save(filename);
			}
		} 
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		finally
		{
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}