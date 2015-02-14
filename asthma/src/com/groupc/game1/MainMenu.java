package com.groupc.game1;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.groupc.game.GameScreen;
import com.groupc.game.MovingGameObject;

public class MainMenu extends GameScreen
{
	ObjectTest player;
	
	public MainMenu()
	{
		player = new ObjectTest(5, 5, 50, 50, 0, 0, 0, 0, 30, 30);
	}

	@Override
	public void update(float deltaTime) {		
		while (Keyboard.next()) {
		    if (Keyboard.getEventKeyState()) {
		        if (Keyboard.getEventKey() == Keyboard.KEY_W) {
		        System.out.println("A Key Pressed");
		        player.velY = 25;
		        player.velX = 0;
		        }
		    }
		    else {
		        if (Keyboard.getEventKey() == Keyboard.KEY_W) {
		        System.out.println("A Key Released");
		        }
		    }
		    if (Keyboard.getEventKeyState()) {
		        if (Keyboard.getEventKey() == Keyboard.KEY_A) {
		        System.out.println("A Key Pressed");
		        player.velX = -25;
		        player.velY = 0;
		        }
		    }
		    else {
		        if (Keyboard.getEventKey() == Keyboard.KEY_A) {
		        System.out.println("A Key Released");
		        }
		    }
		    if (Keyboard.getEventKeyState()) {
		        if (Keyboard.getEventKey() == Keyboard.KEY_S) {
		        System.out.println("A Key Pressed");
		        player.velY = -25;
		        player.velX = 0;
		        }
		    }
		    else {
		        if (Keyboard.getEventKey() == Keyboard.KEY_S) {
		        System.out.println("A Key Released");
		        }
		    }
		    if (Keyboard.getEventKeyState()) {
		        if (Keyboard.getEventKey() == Keyboard.KEY_D) {
		        System.out.println("A Key Pressed");
		        player.velX = 25;
		        player.velY = 0;
		        }
		    }
		    else {
		        if (Keyboard.getEventKey() == Keyboard.KEY_D) {
		        System.out.println("A Key Released");
		        }
		    }
		}
		player.update(deltaTime);
	}

	@Override
	public void present(float deltaTime)
	{
		 // set the color of the quad (R,G,B,A)
        GL11.glColor3f(0.75f,0.75f,0.75f);
             
        // draw quad
        GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2f(0, 0);
            GL11.glVertex2f(400, 0);
            GL11.glVertex2f(400, 400);
            GL11.glVertex2f(0, 400);
        GL11.glEnd();
        
        player.draw();
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
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
