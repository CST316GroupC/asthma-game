package com.groupc;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioImpl;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;


public class AudioPlayer 
{
	private boolean pausedMusic = true;
	public Audio music;
	public ArrayList<Audio> sounds;
	
	public AudioPlayer() 
	{
		try {
			AL.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sounds = new ArrayList<Audio>();
	}
	
	public boolean playMusic(boolean looping)
	{
		pausedMusic = false;
		music.playAsMusic(1.0f, 1.0f, looping);
		return music.isPlaying();
	}
	
	public boolean pauseMusic()
	{
		pausedMusic = true;
		AudioImpl.pauseMusic();
		return music.isPlaying();
	}
	
	public boolean resume()
	{
		pausedMusic = false;
		AudioImpl.restartMusic();
		return music.isPlaying();
	}
	
	public boolean loadSong(String filename)
	{
		try {
			music = AudioLoader.getStreamingAudio(getFileType(filename), ResourceLoader.getResource(filename));
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean getPausedMusic()
	{
		return pausedMusic;
	}
	
	//public boolean addSound(String filename)
	//{
	//	InputStream in = ResourceLoader.getResourceAsStream(filename);
	//	if (in == null) {
	//	  try {
	//		throw new IOException("The resource " + filename + " does not exist at the expected location");
	//	} catch (IOException e) {
	//		// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}
	//	}
	//	try {
	//		sounds.add(AudioLoader.getAudio(getFileType(filename),in));
	//		return true;
	//	} catch (IOException e) {
			// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}
	//	return false;
	//}
	
	public boolean playSound(int index)
	{
		if(sounds.size() > index)
		{
			sounds.get(index).playAsSoundEffect(1.0f, 1.0f, false);
			return true;
		}
		return false;
	}
	
	public String getFileType(String filename)
	{
		String type;
		type = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
		type = type.toUpperCase(new Locale("US"));
		return type;
	}

}
