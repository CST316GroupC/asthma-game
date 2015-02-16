package com.groupc;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class AudioPlayer 
{
	public Audio music;
	public ArrayList<Audio> sounds;
	
	public AudioPlayer()
	{
		sounds = new ArrayList<Audio>();
	}
	
	public boolean playMusic(boolean looping)
	{
		music.playAsMusic(1.0f, 1.0f, looping);
		return music.isPlaying();
	}
	
	public boolean pauseMusic()
	{
		AudioImpl.pauseMusic();
		return music.isPlaying();
	}
	
	public boolean resume()
	{
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
		type = type.toUpperCase();
		return type;
	}

}
