package com.groupc.game1;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.groupc.game.GameObject;
import com.groupc.game.GameScreen;

public class MainMenu extends GameScreen
{
	Cannon player;
	
	public MainMenu()
	{
		Assets.load();
		player = new Cannon(5, 100, 50, 50, Assets.cannonandball);
	}

	@Override
	public void update(float deltaTime) {		
		while (Keyboard.next()) {
		    if (Keyboard.getEventKeyState()) {
		        if (Keyboard.getEventKey() == Keyboard.KEY_W) {
		        System.out.println("A Key Pressed");
		        player.angle++;
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
		        player.angle++;
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
		        player.angle--;
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
		        player.angle--;
		        
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
		Assets.mainmenu.bind();
		 // set the color of the quad (R,G,B,A)
             
        // draw quad
        GL11.glBegin(GL11.GL_QUADS);
        	GL11.glTexCoord2f(0, 1);
            GL11.glVertex2f(0, 0);
            GL11.glTexCoord2f(1, 1);
            GL11.glVertex2f(400, 0);
            GL11.glTexCoord2f(1,0);
            GL11.glVertex2f(400, 400);
            GL11.glTexCoord2f(0,0);
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
