package com.groupc.game1;

import org.lwjgl.input.Mouse;

import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.junit.CollisionChecker;
import com.groupc.math.Rectangle;
import com.groupc.math.Vector;

public class Options extends GameScreen 
{
	Camera cam;
	Rectangle soundLabel;
	Rectangle soundOnOff;
	Rectangle back;
	boolean sound;
	Vector mouseClick;
	
	public Options(Camera cam)
	{
		this.cam = cam;
		this.cam.setCamera();
		soundLabel = new Rectangle(50, 250, 200, 100);
		soundOnOff = new Rectangle(250, 250, 100, 100);
		back = new Rectangle(180, 50, 100, 100);
		sound = true;
		mouseClick = new Vector();
	}
	
	@Override
	public void update(float deltaTime)
	{
		while(Mouse.next())
		{
			if (Mouse.getEventButtonState()) {
				if (Mouse.getEventButton() == 0) {
					System.out.println("Left button pressed");
				}
			}else {
				if (Mouse.getEventButton() == 0) {
					System.out.println("Left button released");
					mouseClick.set(Mouse.getX(), Mouse.getY());
					if(CollisionChecker.PointToRect(mouseClick, soundOnOff))
					{
						sound = !sound;
					}
					if(CollisionChecker.PointToRect(mouseClick, back))
					{
						this.dispose();
						System.out.println("back");
					}
				}
			}
		}
	}

	@Override
	public void present(float deltaTime)
	{
		cam.setCamera();
		Assets.sheet.bind();
		Assets.soundLbl.draw(soundLabel);
		if(sound)
		{
			Assets.soundOn.draw(soundOnOff);
		}
		else
		{
			Assets.soundOff.draw(soundOnOff);
		}
		
		Rectangle temp = new Rectangle(back.lowerLeft.x + back.width, back.lowerLeft.y, -1*back.width, back.height);
		Assets.arrowBut.draw(temp);
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
		return new MainMenu();
	}

}
