package com.games.wordgames.gameobjects;

import com.badlogic.gdx.Gdx;

public class Coconut {
	
	private double xPos;
	private double yPos;
	private double startX;
	private double startY;
	
	private int xVel;
	private int yVel;
	
	double direction_x;
	double direction_y;
	double direction_length;
	
	private boolean hitTarget;
	
	public Coconut(double startX, double startY){
		this.startX = startX;
		this.startY = startY;
		xPos = startX;
		yPos = startY;
		xVel = 1;
		yVel = 1;
		
		hitTarget = false;
		
		direction_length =0;
		calcDirection();
	}
	
	public Coconut(){
		this.startX = -50;
		this.startY = -50;
		xPos = startX;
		yPos = startY;
		xVel = 1;
		yVel = 1;
		
		hitTarget = false;
		
		direction_length =0;
	
		direction_y =-1;
		
	}
	
	public void kill(){
		xPos = -100;
	}
	
	public void update(){
		updatePos();
	}
	public boolean hasHitTarget(){
		return hitTarget;
	}
	
	public void setHitTrue(){
		hitTarget = true;
		kill();
	}
	
	public double getXpos(){
		if(direction_x>0){
			return xPos-10;
		}else{
			return xPos;
		}
		
	}
	
	public double getYpos(){
		if(direction_x>0){
			return yPos-10;
		}else{
			return yPos;
		}
		
	}
	
	public void updatePos(){
		//System.out.println(direction_x*8);
		xPos -= direction_x*8;
		yPos -= direction_y*8;
		
	}
	
	public boolean inBounds(){
		boolean in = true;
		if(getXpos()<0||getXpos()>Gdx.graphics.getWidth()){
			in = false;
		}if(getYpos()>Gdx.graphics.getHeight()){
			in = false;
		}
		
		return in;
	}
	
	private void calcDirection(){
		 direction_x = (( Gdx.graphics.getWidth()/2-15) - Gdx.input.getX()+16);
		 direction_y = (Gdx.input.getY()+16 - ((Gdx.graphics.getHeight())- Gdx.graphics.getHeight()/6.5f));
		 direction_length = Math.sqrt((direction_x *direction_x) + (direction_y*direction_y));
		direction_x = direction_x / direction_length;
		direction_y = direction_y / direction_length;
	}

}
