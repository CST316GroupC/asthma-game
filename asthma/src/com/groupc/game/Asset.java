package com.groupc.game;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import org.newdawn.slick.opengl.Texture;

public abstract class Asset
{
	public static Texture texture;
	public static Hashtable<String, TextureRegion> sprites;	
	protected static Properties props;
	private String filename;
		
	protected abstract void load();
		
	public abstract void reload();
	
	public Asset(String username)
	{
		props = new Properties();
		setFilename(username);
	}
		
	public TextureRegion getImage(String img)
	{
		return sprites.get(img);
	}
		
	public Texture getTexture()
	{
		return texture;
	}
	
	public Properties getProps()
	{
		return props;
	}
		
	public void setProps(String key, String value)
	{
		props.setProperty(key, value);
	}
	
	public void save()
	{
		FileOutputStream out;
		try {
			out = new FileOutputStream(filename);
			props.store(out, "---No Comment---");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getFilename()
	{
		return filename;
	}
	
	public void setFilename(String user)
	{
		filename = "resources/interface/parent_controls/" + user + ".properties";
	}
}

