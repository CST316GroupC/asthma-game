package com.groupc.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import com.groupc.Runner;
import com.groupc.math.Resize;

public class ResizeTest 
{
	private Runner run = new Runner();
	private Resize resize = new Resize(run);

	@Test
	public void locationTest() 
	{
		//Values are slightly different since the screen is set to size with window border added
		assertEquals(resize.locationX(50),57);
		assertEquals(resize.locationX(0),11);
		assertEquals(resize.locationX(500),473);
		
		assertEquals(resize.locationY(50),46);
		assertEquals(resize.locationY(0),0);
		assertEquals(resize.locationY(500),462);
		
	}
	
	@Test
	public void widthHeightTest() 
	{
		assertEquals(resize.height(0),0);
		assertEquals(resize.height(50),46);
		assertEquals(resize.height(171),158);
		assertEquals(resize.height(500),462);
		
		assertEquals(resize.width(0),0);
		assertEquals(resize.width(50),46);
		assertEquals(resize.width(171),158);
		assertEquals(resize.width(500),462);
	}
	
	@After
	public void cleanUp()
	{
		run.close();
	}

}
