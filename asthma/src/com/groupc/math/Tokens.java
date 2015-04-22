/*
 * Author(s):     Team C
 * Course:        CST 316 Spring
 * Instructor:    Dr. Gary
 * Date Changed:  4/19/2015
 * Description:   Calculates and adds to a users tokens
 */

package com.groupc.math;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

public class Tokens 
{
	//Variables
	private int 	tokenTotal	= 0;
	private String 	user;
	Properties props = new Properties();
	
	//Constructor
	public Tokens(String user)
	{
		this.user = user;
		load();
	}
	
	public int getTokens()
	{
		return tokenTotal;
	}
	
	public void setTokens(int amount)
	{
		tokenTotal = amount;
	}
	
	public void addTokens(int amount)
	{
		tokenTotal += amount;
	}
	
	public int getRewardTokens()
	{
		int 	count 		= 0;
		int 	monthPast 	= 0;
		
		Calendar calCurrent;
		Calendar calPast;
		
		String line 		= null;
		
		Vector<String> userNames = new Vector<String>();
		Vector<String> week = new Vector<String>();
		Vector<String> month = new Vector<String>();
		Vector<String> day = new Vector<String>();
		Vector<String> year = new Vector<String>();
		StringTokenizer stringT;
		
		try
		{
			FileReader fileReader = new FileReader("spirometer_readings.txt");
			BufferedReader bufferReader = new BufferedReader(fileReader);
			
			
			while((line = bufferReader.readLine()) != null)
			{
				stringT = new StringTokenizer(line, " | ");
				userNames.add(stringT.nextToken()); //user
				stringT.nextToken(); 			//volume
				stringT.nextToken(); 			//force
				week.add(stringT.nextToken()); 	//day of week
				month.add(stringT.nextToken()); //month
				day.add(stringT.nextToken()); 	//day 
				stringT.nextToken(); 			//time
				stringT.nextToken(); 			//timezone
				year.add(stringT.nextToken()); 	//year
			}
			bufferReader.close();
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		for(int i = 0; i < userNames.size(); i++)
		{
			if(userNames.elementAt(i).equals(user))
			{
				
				/////////get days TODO
				calCurrent = Calendar.getInstance();
				calPast = Calendar.getInstance();
				
				//getMonth
				switch(month.elementAt(i))
				{
					case "Jan":
						monthPast = 0;
						break;
					case "Feb":
						monthPast = 1;
						break;
					case "Mar":
						monthPast = 2;
						break;
					case "Apr":
						monthPast = 3;
						break;
					case "May":
						monthPast = 4;
						break;
					case "Jun":
						monthPast = 5;
						break;
					case "Jul":
						monthPast = 6;
						break;
					case "Aug":
						monthPast = 7;
						break;
					case "Sep":
						monthPast = 8;
						break;
					case "Oct":
						monthPast = 9;
						break;
					case "Nov":
						monthPast = 10;
						break;
					case "Dec":
						monthPast = 11;
						break;
				}

				calPast.set(Integer.parseInt(year.elementAt(i)), monthPast, Integer.parseInt(day.elementAt(i)));
				for(int maxTokens=0;maxTokens<5;maxTokens++)
				{
					if(calCurrent.get(Calendar.YEAR) == calPast.get(Calendar.YEAR) && calCurrent.get(Calendar.MONTH) == calPast.get(Calendar.MONTH) && calCurrent.get(Calendar.DAY_OF_MONTH) == calPast.get(Calendar.DAY_OF_MONTH))
					{
						count += 1;
					}
					calCurrent.add(Calendar.DAY_OF_YEAR, -1);
				}
			}
		}
		return count;
	}
	
	public void load()
	{
		try 
		{
			FileInputStream in = new FileInputStream("resources/interface/parent_controls/"+user+".properties");
			props.load(in);
			in.close();
			String temp = props.getProperty("tokenfirstTime", "true");
			if("true".equals(temp))
			{
				props.setProperty("tokens", ""+tokenTotal);
				props.setProperty("tokenfirstTime", "false");
				save();
			}
			tokenTotal = Integer.parseInt(props.getProperty("tokens", "0"));
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		} 
	}
	
	public void save()
	{
		FileOutputStream out;
		try
		{
			props.setProperty("tokens", ""+tokenTotal);
			out = new FileOutputStream("resources/interface/parent_controls/"+user+".properties");
			props.store(out, "---No Comment---");
			out.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
