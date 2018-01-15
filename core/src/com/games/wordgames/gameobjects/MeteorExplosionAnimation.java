package com.games.wordgames.gameobjects;



import java.awt.Rectangle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class MeteorExplosionAnimation {
	private Texture[] textures;
	private Rectangle rect;
	private int count;
	private boolean compleate;
	private int x;
	private int y;
	public MeteorExplosionAnimation(){
		rect = new Rectangle(0,0,100,100);
		textures = new Texture[7];
		initTextures();
		count = 0;
		compleate = false;
	}
	
	private void initTextures(){
		for(int i = 0; i<7;i++){
			textures[i] = new Texture(Gdx.files.internal("images/Space Blaster/meteor/explosion/"+i+".png"));
			
		}
	}
	public void nextFrame(){
		count ++;
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
	public void explode(){
		compleate = false;
		count = 0;
	}
	
	public Texture getTexture(){
		if(count ==6){
			count = 0;
			compleate = true;
		}
		if(!compleate){
			return textures[count];
		}
		else {
			return  textures[6];
		}
		
		
	}

	public boolean compleate() {
		return(compleate);
	}

}
