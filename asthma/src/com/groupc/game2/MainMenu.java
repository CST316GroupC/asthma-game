package com.groupc.game2;

import org.lwjgl.input.Mouse;

import com.groupc.TextDrawer;
import com.groupc.game.Asset;
import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.math.CollisionChecker;
import com.groupc.math.Rectangle;
import com.groupc.math.Vector;

public class MainMenu extends GameScreen
{	
	private final Camera cam;
	private final Rectangle title;
	private final Rectangle play;
	//private final Rectangle options;
	private final Rectangle upgrade;
	private final Rectangle instruct;
	private final Vector mouseClick;
	private int next;
	
	public MainMenu(Asset assets)
	{
		super(assets);
		cam = new Camera(400, 400);
		cam.setCamera();
		title = new Rectangle(50, 300, 300, 100);
		play = new Rectangle(105, 210, 170, 75);
		//options = new Rectangle(200, 200, 150, 75);
		upgrade = new Rectangle(105, 125, 170, 75);
		instruct = new Rectangle(105, 40, 170, 75);
		mouseClick = new Vector();
	}

	@Override
	public void update(float deltaTime) 
	{	
		while (Mouse.next()){
		    if (Mouse.getEventButtonState())
		    {
		        if (Mouse.getEventButton() == 0) 
		        {
		            System.out.println("Left button pressed");
		        }
		    }
		    else
		    {
		    	if (Mouse.getEventButton() == 0) 
		    	{
		    		mouseClick.set(Mouse.getX(), Mouse.getY());
		    		cam.click(mouseClick);
		    		if(CollisionChecker.PointToRect(mouseClick, play))
		    		{
		    			this.dispose();
		    			next = 1;
		    		}
					//if(CollisionChecker.PointToRect(mouseClick, options))
					//{
					//	this.dispose();
					//	next = 2;
					//}
					if(CollisionChecker.PointToRect(mouseClick, upgrade))
					{
						this.dispose();
						next = 2;
					}
					if(CollisionChecker.PointToRect(mouseClick, instruct))
					{
						this.dispose();
						next = 3;
					}
				}				
			}
		}
	}

	@Override
	public void present(float deltaTime)
	{
		cam.setCamera();
		assets.getTexture().bind();
		assets.getImage("background").draw(new Rectangle(0, 0, cam.frustumWidth, cam.frustumHeight));
		TextDrawer.drawStringinRect("Maze Game", title, false);
		TextDrawer.drawStringinRect("Play", play, true);
		TextDrawer.drawStringinRect("upgrade", upgrade, true);
		TextDrawer.drawStringinRect("Instructions", instruct, true);
	}
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() 
	{
		isClosing = true;
	}

	@Override
	public GameScreen getNext() 
	{
		if(next == 1)
		{
			return new MazeWorld(assets);
		}
		else if(next == 2)
		{
			return new UpgradeScreen(assets);
		}
		else
		{
			return new InstructionScreen(assets);
		}
	}
}

