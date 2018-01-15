package com.games.wordgames.gameobjects;

import java.awt.Rectangle;

public class BambooBlasterTarget {
	
	private Rectangle rect;
	private boolean hit;
	private int targetNumber;
	
	public BambooBlasterTarget(int x, int y, int w, int h, int tn){
		this.rect = new Rectangle(x,y,w,h);
		hit = false;
		targetNumber = tn;
	}
	
	public int getTargetNumber(){
		return targetNumber;
	}
	public int getX(){
		return rect.x;
	}
	
	public void resetHit(){
		hit = false;
	}
	
	public int getY(){
		return rect.y;
	}
	
	public int getWidth(){
		return rect.width;
	}
	
	public int getHeight(){
		return rect.height;
	}
	
	public boolean isHit(){
		return hit;
	}
	
	public void checkHit(double x, double y){
		if(rect.contains(x, y)){
			System.out.println("HIT!");
			this.hit= true;
		}
		
	}

}
