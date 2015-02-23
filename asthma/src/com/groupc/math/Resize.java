package com.groupc.math;

import com.groupc.Runner;

public class Resize 
{
	//Variables
	double ratioX = 1;
	double ratioY = 1;
	
	Runner run;
	public Resize(Runner run)
	{
		this.run=run;
	}
	
	//Gets the exact X location on the screen were it should be drawn
	public int locationX(int value)
	{
		ratioX = (double)run.getContentPane().getWidth() / (double)run.SCR_WIDTH;
		ratioY =(double)run.getContentPane().getHeight() / (double)run.SCR_HEIGHT;
		
		if(ratioY<ratioX)
		{
			int screenXPosition = (int)Math.round((run.getContentPane().getWidth()-(ratioY*run.SCR_WIDTH))/2.0);
			return (int)Math.round(((double)value*ratioY)+screenXPosition);
		}
		else
		{
			return (int)Math.round((double)value*ratioX);
		}
	}
	
	//Gets the exact Y location on the screen were it should be drawn
	public int locationY(int value)
	{
		ratioX = (double)run.getContentPane().getWidth() / (double)run.SCR_WIDTH;
		ratioY =(double)run.getContentPane().getHeight() / (double)run.SCR_HEIGHT;
		
		if(ratioX<ratioY)
		{
			int screenYPosition = (int)Math.round((run.getContentPane().getHeight()-(ratioX*run.SCR_HEIGHT))/2.0);
			return (int)Math.round((double)value*ratioX)+screenYPosition;
		}
		else
		{
			return (int)Math.round((double)value*ratioY);
		}
	}
	
	//Gets the width it should be drawn at
	public int width(int value)
	{
		ratioX = (double)run.getContentPane().getWidth() / (double)run.SCR_WIDTH;
		ratioY =(double)run.getContentPane().getHeight() / (double)run.SCR_HEIGHT;
		
		if(ratioY<ratioX)
		{
			return (int)Math.round((double)value*ratioY);
		}
		else
		{
			return (int)Math.round((double)value*ratioX);
		}
	}
	
	//Gets the height it should be drawn at
	public int height(int value)
	{
		ratioX = (double)run.getContentPane().getWidth() / (double)run.SCR_WIDTH;
		ratioY =(double)run.getContentPane().getHeight() / (double)run.SCR_HEIGHT;
		
		if(ratioX<ratioY)
		{
			return (int)Math.round((double)value*ratioX);
		}
		else
		{
			return (int)Math.round((double)value*ratioY);
		}
	}
	
	//Gets the fonts height it should be drawn at
	public int font(int value)
	{
		ratioX = (double)run.getContentPane().getWidth() / (double)run.SCR_WIDTH;
		ratioY =(double)run.getContentPane().getHeight() / (double)run.SCR_HEIGHT;
		
		if(ratioX<ratioY)
		{
			return (int)Math.round((double)value*ratioX);
		}
		else
		{
			return (int)Math.round((double)value*ratioY);
		}
	}
}
