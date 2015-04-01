package com.groupc.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.groupc.AudioPlayer;

public class SoundTest {
	AudioPlayer player;
	String[] files = {"AMemoryAway.ogg", "badfile.txt", "music.wav"};
	@Before
	public void setUp()
	{
		player = new AudioPlayer();
	}
	
	@Test
	public void OGGfileTypeTest() 
	{
		String temp = "OGG";
		assert player.getFileType(files[0]) == temp;
		System.out.println(player.getFileType(files[0]));
		assert player.getFileType(files[1]) != temp;
		System.out.println(player.getFileType(files[1]));
		assert player.getFileType(files[2]) != temp;
		System.out.println(player.getFileType(files[2]));
	}
	
	@Test
	public void WAVfileTypeTest() 
	{
		String temp = "WAV";
		assert player.getFileType(files[0]) != temp;
		System.out.println(player.getFileType(files[0]));
		assert player.getFileType(files[1]) != temp;
		System.out.println(player.getFileType(files[1]));
		assert player.getFileType(files[2]) == temp;
		System.out.println(player.getFileType(files[2]));
	}
	
	@Test
	public void LoadMusicTest()
	{
		//only ogg files for music
		assertTrue(player.loadSong(files[0]));
		assertTrue(!player.loadSong(files[1]));
		assertTrue(!player.loadSong(files[2]));
	}
	
	//@Test
	//public void AddSoundTest()
	//{
		//ogg and wav for sound effects
//		assertTrue(player.addSound(files[0]));
//		assertTrue(!player.addSound(files[1]));
//		assertTrue(player.addSound(files[2]));
//	}
	
	@After
	public void cleanUp()
	{
		
	}

}
