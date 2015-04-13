package com.groupc.game1;

import org.lwjgl.input.Mouse;

import com.groupc.game.Asset;
import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.math.CollisionChecker;
import com.groupc.math.Rectangle;
import com.groupc.math.Vector;

public class Options extends GameScreen 
{
	private final Camera cam;
	private final Rectangle soundLabel;
	private final Rectangle soundOnOff;
	private final Rectangle back;
	private String sound;
	private final Vector mouseClick;
	
	public Options(Asset assets, Camera cam)
	{
		super(assets);
		assets.reload();
		this.cam = cam;
		this.cam.setCamera();
		soundLabel = new Rectangle(50, 250, 200, 100);
		soundOnOff = new Rectangle(250, 250, 100, 100);
		back = new Rectangle(180, 50, 100, 100);
		sound = "true";
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
					cam.click(mouseClick);
					if(CollisionChecker.PointToRect(mouseClick, soundOnOff))
					{
						if(sound.equals("true"))
						{
							sound = "false";
						}
						else
						{
							sound = "true";
						}
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
		assets.getTexture("sheet").bind();
		assets.getImage("grass").draw(new Rectangle(0, 0, cam.frustumWidth, cam.frustumHeight));
		assets.getImage("soundLbl").draw(soundLabel);
		if(sound.equals("true"))
		{
			assets.getImage("soundOn").draw(soundOnOff);
		}
		else
		{
			assets.getImage("soundOff").draw(soundOnOff);
		}
		
		assets.getImage("back").draw(back);
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
		assets.setProps("sound", sound);
		assets.save();
		isClosing = true;
	}

	@Override
	public GameScreen getNext()
	{
		return new MainMenu(assets);
	}

}
