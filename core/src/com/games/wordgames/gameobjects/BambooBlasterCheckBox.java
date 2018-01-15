package com.games.wordgames.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class BambooBlasterCheckBox {
	
	private Rectangle rect;
	private Animation animation;
	
	public BambooBlasterCheckBox(Animation animation, int x, int y, int w, int h){
		this.animation = animation;
		this.animation.setPos(x, y);
		this.rect = new Rectangle(x,y,w,h);
	}
	
	public void setAdvanceFrame(){
		this.animation.setAdvanceFrame();
	}
	
	public Texture getTexture(){
		this.animation.advanceFrameNoLoop();
		
		if(this.animation.animating()){
			
			return this.animation.getTexture();
			
		}else{
			
			return this.animation.getFirstTexture();
			
		}
	}
	
	public boolean checkHit(Coconut cocoNut){
		if(rect.contains((float)cocoNut.getXpos(),(float) cocoNut.getYpos())){
			if(!animation.animating()){
				animation.start();
			}
			return true;
		}
		
		return false;
		
	}
	
	public int getX(){
		return (int)rect.x;
	}
	
	public int getY(){
		return (int)rect.y;
	}
	
	public int getWidth(){
		return (int)rect.width;
	}
	
	public int getHeight(){
		return (int)rect.height;
	}
	

}
