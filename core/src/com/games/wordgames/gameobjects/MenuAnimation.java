package com.games.wordgames.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.games.wordgames.handlers.GameStateManager;

public class MenuAnimation {
	
	private int startX;
	private int startY;
	
	private int xPos;
	private int yPos;
	
	private float yPosf;
	private float yChangef;
	private boolean isFloat;
	
	private int width;
	private int height;
	
	private Animation animation;
	private Texture texture;
	
	private boolean moveRightOffScreen;
	private boolean moveRightLeft; 
	private boolean movingRight;
	private boolean movingLeft;
	private boolean moveUpDown;
	
	private int rightLimit;
	
	private int xChange;
	private int yChange;
	
	private boolean showTexture;

	private String name;

	private long lastTimeCheck;
	private long frameRate;
	
	public MenuAnimation(int startX, int startY, Animation ani){
		this.startX = startX;
		this.startY = startY;
		this.xPos = startX;
		this.yPos = startY;
		this.xChange = 1;
		this.animation = ani;
		this.rightLimit = 820;
		this.frameRate = 200000000;
		
	}
	
	public MenuAnimation(Texture tex, int startX, int startY, 
			int w, int h, int xVel, int yVel, int rL){
		this.startX = startX;
		this.startY = startY;
		this.width = w;
		this.height = h;
		this.xPos = this.startX;
		this.yPos = this.startY;
		this.xChange = xVel;
		this.yChange = yVel;
		this.animation = new Animation();
		this.rightLimit = rL; //400
		this.texture = tex;
		this.showTexture = false;
		this.frameRate = 200000000;
	}
	
	public MenuAnimation(Texture tex, int startX, int startY, 
			int w, int h, int xVel, float yVel, int rL){
		this.startX = startX;
		this.startY = startY;
		this.width = w;
		this.height = h;
		this.xPos = this.startX;
		this.yPosf = this.startY;
		this.xChange = xVel;
		this.yChangef = yVel;
		this.animation = new Animation();
		this.rightLimit = rL; //400
		this.texture = tex;
		this.showTexture = false;
		this.isFloat = true;
		this.frameRate = 200000000;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){

		return name;
	}
	public Vector2 getSize(){
		return new Vector2(width,height);
	}

	public void setSize(int w, int h){
		this.width = w;
		this.height = h;
	}
	public void setMoveRight(){
		moveRightOffScreen = true;
	}
	
	public void setMoveUpDown(){
		moveUpDown = true;
	}
	
	public boolean showTexture(){
		return showTexture;
	}
	
	public void hideTexture(){
		showTexture = false;
	}
	
	public void activateTexture(){
		showTexture = true;
	}
	
	public void setMoveRightLeft(){
		moveRightLeft = true;
	}
	public int getXPos(){
		return this.xPos;
	}
	
	public int getYPos(){
		return this.yPos;
	}
	
	public float getYPosf(){
		return this.yPosf;
	}
	
	public void reset(){
		showTexture = false;
		this.xPos = this.startX;
		this.yPos = this.startY;
	}
	
	private boolean onScreen(){
		return (this.xPos<rightLimit);
	}
	
	public void setAdvanceFrameAnimation(){
		this.animation.setAdvanceFrame();
	}
	
	public void update(){
		updateClock();
		this.animation.advanceFrame();
		if(moveRightOffScreen){
			moveRight();
		}
		
		if(moveRightLeft){
			moveRightLeft();
		}
		if(moveUpDown){
			moveUpDown();
		}
	}

	private void updateClock(){
		if(TimeUtils.nanoTime() - this.lastTimeCheck > this.frameRate){
			// robotMenuAnimation.setAdvanceFrameAnimation();
			// gameTypeBaloonAnimation.update();
			//robotAnimation.setAdvanceFrame();

			this.setAdvanceFrameAnimation();
			this.lastTimeCheck = TimeUtils.nanoTime();
		}
	}
	
	private void moveUpDown(){
		if(isFloat){
			if(yPosf+ yChangef >= startY+24){
				yChangef *= -1;
			}
			if(yPosf+yChangef <= startY-24){
				yChangef *=-1;
			}
			
			yPosf += yChangef;
			
		}else{
			if(yPos+ yChange/2 >= startY+34){
				yChange *= -1;
			}
			if(yPos+yChange/2 <= startY-34){
				yChange *=-1;
			}
			
			yPos += yChange;
		}
		
	}
	public void moveRight(){
		if(onScreen()){
			this.xPos +=xChange;
		}else{
			this.xPos = this.startX;
		}
		
	}
	
	public void moveRightLeft(){
		if(xPos > rightLimit){
			xChange *=-1;
			movingRight = true;
			movingLeft = false;
		}
		if(xPos<-0){
			xChange *=-1;
			movingRight = false;
			movingLeft = true;
		}
		this.xPos += xChange;
	}
	
	
	public Texture getAnimationTexture(){
		Sprite sprite = new Sprite(animation.getTextureLoop());
	    sprite.flip(true, true);
	    
		return sprite.getTexture();
	}
	
	public Sprite getAnimationSprite(){
		Sprite sprite = new Sprite(animation.getTextureLoop());
	    sprite.flip(movingRight, false);
	    
		return sprite;
	}
	
	public Texture getTexture(){
		return texture;
	}
	
}
