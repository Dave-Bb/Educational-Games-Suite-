package com.games.wordgames.states;
/*
Loads all assets and displays loading screen
Once all assets are loaded, game starts
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.games.wordgames.handlers.Assets;
import com.games.wordgames.handlers.FontManager;
import com.games.wordgames.handlers.GameStateManager;
import com.games.wordgames.handlers.HelpMethods;

public class LoadingScreen extends GameState{
	
	private BitmapFont loadingFont;
	private BitmapFont loadingFontSmall;
	private String loadPersent;
	private String currentFile;
	private boolean isLoaded;
	private double loadProgress;

	private FontManager fontManager;

	public LoadingScreen (GameStateManager gsm) {
		super(gsm);

		loadingFont =  HelpMethods.createFont("assets/fonts/Slackey.ttf", 50);
		loadingFont.setColor(1,1,1,1);
		
		loadingFontSmall = HelpMethods.createFont("assets/fonts/DroidSans-Bold.ttf", 10);
		loadingFontSmall.setColor(1,1,1,1);

		loadPersent = "";
		currentFile = "";

		Assets.load();

		isLoaded = false;
		loadProgress = 0;
		
		
	}
	

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		loadProgress = (Math.round((double)(Assets.manager.getProgress()*100)));
		if(Assets.manager.update()){
			if(!isLoaded){
				System.out.println("All assets successfully loaded");
				initGlobalSkins();
				isLoaded = true;
				gsm.setState(GameStateManager.MASTER_MENU);

			}
		}
		loadPersent = loadProgress+"%";
	}

	@Override
	public void render() {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		   sb.begin();
		   loadingFont.draw(sb,"Loading...",270,360);
	       loadingFont.draw(sb,loadPersent+"",330,280);
	       if(Assets.manager.getAssetNames().size>=1){
	    	   currentFile = Assets.manager.getAssetNames().random();
	       }
	       loadingFontSmall.draw(sb,"Loading file: "+currentFile,250,120);
	       sb.end();

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

}
