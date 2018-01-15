//This class is used to add background music to a game
//It offers the ability to add a sound icon, as well as different
//Audio files, as well as offering some control over these
package com.games.wordgames.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class BackGroundMusic {
	
	private Sound tune;
	private boolean mute;
	private Texture soundOnIcon;
	private Texture soundOffIcon;
	private Rectangle iconRect;
	
	public BackGroundMusic(String filePath){
		//EXAMPLE FILE PATH 
		//"sounds/shortPop.mp3"
		tune = Gdx.audio.newSound(Gdx.files.internal(filePath));
		
		iconRect = new Rectangle(720,20,50,50);
		soundOnIcon = Assets.manager.get("assets/images/global/musicIcon/soundOn.png", Texture.class);
		soundOffIcon =  Assets.manager.get("assets/images/global/musicIcon/soundOff.png", Texture.class);
		mute = true;
	}
	
	public BackGroundMusic(String filePath, boolean assetLoader){
		//EXAMPLE FILE PATH 
		//"sounds/shortPop.mp3"
		tune = Assets.manager.get("assets/sounds/mainMenuMusic.mp3", Sound.class);
		
		iconRect = new Rectangle(720,20,50,50);
		soundOnIcon = Assets.manager.get("assets/images/global/musicIcon/soundOn.png", Texture.class);
		soundOffIcon =  Assets.manager.get("assets/images/global/musicIcon/soundOff.png", Texture.class);
		mute = true;
	}
	
	public int getXPos(){
		return (int)iconRect.x;
	}
	
	public int getYPos(){
		return (int)iconRect.y;
	}
	
	public int getW(){
		return (int)iconRect.width;
	}
	
	public int getH(){
		return (int)iconRect.height;
	}
	
	public Texture getTexture(){
		if(!mute){
			return soundOnIcon;
		}else{
			return soundOffIcon;
		}
	}
	
	public void mute(){
		mute = true;
	}
	
	public void unMute(){
		mute = false;
	}
	
	public void play(){
		//tune.loop(0.2f);
		mute = true;
	}
	
	public void mutePlay(){
		tune.stop();
		mute = true;
	}
	
	public void toggleSound(){
		if(mute){
			
			play();
			mute = false;
			System.out.println("Un-mute sound");
		}else{
			mutePlay();
			mute = true;
			System.out.println("Mute sound");
		}
	}
	
	public boolean checkClickBox(int x, int y){
		if(iconRect.contains(x,y)){
			toggleSound();
			
			return true;
		}
		
		return false;
		
	}
	
	public boolean isMute(){
		return mute;
	}
	
	public void dispose(){
		tune.stop();
		
	}
}
