package com.games.wordgames.gameobjects;

import java.awt.Rectangle;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.games.wordgames.main.Game;

public class Meteor {
	private Rectangle rect;
	
	private int startX;
	private int startY;
	private int textureWidth;
	private int textureHeight;
	
	private int xPos;
	private int yPos;
	private int xVel;
	private double yVel;
	
	private boolean direction; 
	private Random rand = new Random();
	private boolean hit;
	private int frameSkip =0;
	
	private String meteorWord;
	
	private boolean explode;
	
	public Meteor(){
		hit = false;
		xPos = 200;
		yPos = (rand.nextInt(350)+120);
		rect = new Rectangle(-xPos,yPos,100,100);
		
		if(yPos<270){
			yVel = 1;
		}else if(yPos>420){
			yVel = -1;
		}
		else
		{
			yVel = 0;
		}
		//System.out.println("Y: "+yPos+"  Yvel:"+yVel);
		xVel = 2;
		//yVel = 0;
	}
	
		public Meteor(String w){
		meteorWord = w;
		resetRect();
		//System.out.println("Y: "+yPos+"  Yvel:"+yVel);
		//xVel = 2;
		//yVel = 0;
	}
	
	//Splash Screen MiniMeteor Constructor
	public Meteor(boolean miniM){
		this.startX = 700;
		this.startY = 50;
		this.xPos = startX;
		this.yPos = startY;
		this.textureWidth = 75;
		this.textureHeight = 75;
		this.yVel = 0;
		this.xVel = -1;
		this.rect = new Rectangle(xPos,yPos,textureWidth,textureHeight);
		explode = false;
		
	}
	public void hit(){
		hit = true;
	}
	
	public boolean isHit(){
		return hit;
	}
	
	public String getWord(){
		return meteorWord;
	}
	
	public boolean containts(int x, int y){
		Rectangle r = new Rectangle(-xPos,yPos,100,100);
		return (rect.contains(x,y));
	}
	
	public void updateMiniMeteor(){
		if(rect.x <=488){
			rect.x = startX;
			explode = true;
		}else{
			explode = false;
		}
		rect.x += xVel;
	}
	
	public boolean explode(){
		return explode;
	}
	public void updatePos(){
		if(frameSkip%3==0){
			if(!inBounds()){
				resetRect();
			}
			rect.x += xVel;
			
		}
		if(frameSkip%5==0){
			rect.y += yVel;
		}
		frameSkip+=1;
		
	}
	
	public void resetRect(){
		if(rand.nextBoolean()){
			xPos = 0;
			xVel = 2;
		}
		else{
			xPos = 800;
			xVel = -2;
		}
		//xPos = x;
		yPos = (rand.nextInt(350)+120);
		rect = new Rectangle(xPos,yPos,100,100);
		if(yPos<200){
			yVel = 1;
		}else if(yPos>450){
			yVel = -1;
		}
		else
		{
			yVel = 0;
		}
	}
	
	public int getX(){
		return rect.x;
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
	
	public Rectangle getRect(){
		return rect;
	}
	
	
	public boolean inBounds(){
		//(Gdx.graphics.getWidth()-Game.V_WIDTH)/2
		boolean in = true;
		if(getX()>(((Gdx.graphics.getWidth()-Game.V_WIDTH)/2)+Game.V_WIDTH)|
		getX()<(-50)){
			in = false;
		}if(getY()>Gdx.graphics.getHeight()|getY()<-10){
			in = false;
		}
		
		return in;
	}

}
