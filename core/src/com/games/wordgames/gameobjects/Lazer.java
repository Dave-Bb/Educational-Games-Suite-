package com.games.wordgames.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.games.wordgames.handlers.Assets;
import com.games.wordgames.handlers.HelpMethods;
import com.games.wordgames.main.Game;

public class Lazer{
	
	private double xPos;
	private double yPos;
	private double startX;
	private double startY;
	
	private int xVel;
	private int yVel;
	double direction_x;
	double direction_y;
	double direction_length;
	private boolean lazerFire;
	private Sound lazerSound;
	private boolean hasHit;
	public Lazer(double startX, double startY){
		hasHit = false;
		this.startX = startX;
		this.startY = startY;
		xPos = startX;
		yPos = startY;
		xVel = 1;
		yVel = 1;
		lazerSound= Assets.manager.get("sounds/lazerBlast.mp3",Sound.class);
		direction_length =0;
		calcDirection();
	}
	
	public Lazer(){
		hasHit = false;
		this.startX = 400;
		this.startY = 100;
		xPos = startX;
		yPos = startY;
		xVel = 0;
		yVel = 0;
		lazerSound= Assets.manager.get("sounds/lazerBlast.mp3",Sound.class);
		direction_length =0;
		
	}
	
	public void fireLazer(){
		if(!lazerFire){
			lazerFire = true;
			xVel = 1;
			yVel = 1;
			calcDirection();
			lazerSound.play();
		}
		
	}
	
	public boolean hasHit(){
		return hasHit;
	}
	
	public void setHit(){
		hasHit = true;
	}
	public void update(){
		
	}
	
	public double getXpos(){
		return xPos;
	}
	
	public double getYpos(){
		return yPos;
	}
	
	public void updatePos(){
		if(!hasHit){
			xPos -= direction_x*10;
			yPos -= direction_y*10;
		}
		
		if(!inBounds()||hasHit){
			resetLazer();
		}
	}
	
	public void resetLazer(){
		System.out.println("RESET LAZER");
			xVel = 0;
			yVel = 0;
			xPos = 400;
			yPos = 100;
			direction_x = 0;
			direction_y = 0;
			lazerFire = false;
			hasHit = false;
		
	}
	
	public boolean inBounds(){
		boolean in = true;
		if(getXpos()<0||getXpos()>800){
			in = false;
		}if(getYpos()>600){
			in = false;
		}
		
		return in;
	}
	
	private void calcDirection(){
		 direction_x = (400 - Gdx.input.getX());
		 direction_y = (Gdx.input.getY()+16 - 600);
		//Vector2 mp2 = HelpMethods.scaleMouse(Gdx.input.getX(), Gdx.input.getY());
		// direction_x = (400 - mp2.x);
		 //direction_y = (mp2.y+16 - 600);
		 direction_length = Math.sqrt((direction_x *direction_x) + (direction_y*direction_y));
		direction_x = direction_x / direction_length;
		direction_y = direction_y / direction_length;
	}
	
	public double[] calcDirectionDebug(){
		double[] xY = {direction_x,direction_y};
		return xY;
	}

}
