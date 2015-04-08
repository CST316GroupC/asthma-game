package com.groupc.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import com.groupc.Runner;

public class AccountScreen extends Screen 
{
	boolean redraw				= true;
	int		butPressed			= 0;
	
	NavigationBar navBar        = new NavigationBar(run,true,false,"Account");
	
	public AccountScreen(Runner run) 
	{
		super(run);
		
		//resize stuff
		run.addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent e)
			{
				redraw = true;
			}
		});
		
		this.add(navBar);
		
		this.setLayout(null);
		run.setContentPane(this);
		run.setVisible(true);
	}

	@Override
	public void update(float deltaTime) 
	{
		
		if(redraw)
		{	
			//navBar
			navBar.redrawUpdate();
			
			run.repaint();
			redraw = false;
		}
		
		
		if(navBar.backButtonPressed)
		{
			run.setScreen(new GameHubScreen(run));
		}
		else if(butPressed == 2)
		{
			run.setScreen(new LoginScreen(run));
		}
		
		butPressed = 0;
		navBar.update();
	}

	@Override
	public void present(float deltaTime) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() 
	{
		// TODO Auto-generated method stub
		
	}

}
