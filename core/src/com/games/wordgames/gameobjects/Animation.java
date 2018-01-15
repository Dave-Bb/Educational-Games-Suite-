package com.games.wordgames.gameobjects;

import com.badlogic.gdx.graphics.Color;
import java.awt.Rectangle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Animation {

	private String fileLocation;
	private Texture[] textures;
	
	
	private int frame; //Which frame of the animation is on
	private int lenght; // number of frames of animation
	
	private boolean animating; //If the animation is currently animating
	private boolean advanceFrame;

	//Size and dimensions of animation
	private int x;
	private int y;
	private int height;
	private int width;

	private Color color;

	public Animation(int len, String fileLocation,int w,int h){
		//Len is the number of frames
		//each frame file name should be numbered numerically from 0.
		this.fileLocation = fileLocation;
		advanceFrame = false;
		lenght = len;
		textures = setTextures(fileLocation,len);
		//initTextures();
		animating = false;
		frame = 0; //Current frame
		width = w;
		height = h;
		color = new Color(1, 1, 1, 1);
		
	}

	public Animation(){
		//Blank constructor
	}

	private Texture[] setTextures(String fileLocation, int len){
		//Returns a Texture array of each of the frames in the animation
		Texture[] tex  = new Texture[len];
		for(int i = 0; i<len;i++){
			tex[i] = new Texture(Gdx.files.internal(fileLocation+i+".png"));
		}
		return tex;
	}

	public Color getColor(){
		//This is used to change the colour of the texture
		//Used in Balloon Typer
		return color;
	}

	public void setColor(Color c){
		color = c;
	}
	public boolean animating(){
		return animating;
	}
	
	public void setLoop(){
		//Sets the animation to loop
		animating = true;
	}
	
	public void stopLoop(){
		//Stops the animation from looping
		animating = false;
	}
	public void setAdvanceFrame(){
		//IF enough time has passed to advance the frame, this will be set to true
		//If it is true, then the next frame will be set
		advanceFrame = true;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
		
	}
	public void advanceFrame(){
		if(advanceFrame){
			nextFrame();
			advanceFrame = false;
		}
		
	}
	public void advanceFrameNoLoop(){
		if(advanceFrame){
			nextFrameNoLoop();
			advanceFrame = false;
		}
		
	}

	private void nextFrameNoLoop(){
		//Once the frame index has been reset to zero
		//The animation will no longer animate and the loop will end
		if(frame+1==lenght-1){
			frame=0;
			animating = false;
		}else{
			frame ++;
		}
	}
	
	
	private void nextFrame(){
		//Iterates the frame index
		//This index is used to return the current frame texture
		if(frame+1==lenght-1){
			frame=0;
			
		}else{
			frame ++;
		}
	}
	
	public void setPos(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void start(){
		animating = true;
		frame = 0;
	}
	
	public Texture getTextureLoop(){
		
		if(animating){
			return textures[frame];
		}else{
			return textures[0];
		}
		
	}
	
	public Texture getFirstTexture(){
		return textures[0];
	}
	
	public Texture getTexture(){
		if(frame ==lenght-1){
			frame = 0;
			animating = false;
		}
		if(animating){
			//System.out.println("RETURN TX FRAME:"+frame+"AT X:"+x+" y:"+y);
			return textures[frame];
		}
		else {
			return  textures[lenght-1];
		}
		
		
	}
	
	public void dispose(){
		for(Texture t:textures){
			t.dispose();
		}
	}
	
	

}
