//The game state that each state is dirived from
//The game states share some objects
//SpriteBatch, ShapeRenderer, Stage and some skins for example
package com.games.wordgames.states;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.games.wordgames.handlers.FontManager;
import com.games.wordgames.handlers.GameStateManager;
import com.games.wordgames.handlers.HelpMethods;
import com.games.wordgames.handlers.Assets;
import com.games.wordgames.main.Game;


public abstract class GameState {
	protected GameStateManager gsm;
	protected Game game;
	
	protected SpriteBatch sb;

	protected OrthographicCamera cam;



	protected ShapeRenderer shapeRenderer;
	
	
	
	protected Stage stage;

	protected Random random;

	protected static Texture menuBackgroundTx;
	protected static Texture offWhiteBackgroundTx;
	
	protected SpriteBatch animationSB;
	

	protected Rectangle gameStateViewPort;
	

	
	
	
	protected static Skin rainbowSkin;
	protected static Skin comicSkin;
	protected static Skin levelPane;
	protected static Skin flatEarthSkin;
	protected static Skin cleanCrispySkin;
	
    protected static boolean firstStart;

	protected GameState(GameStateManager gsm){
		this.gsm = gsm;
		game = gsm.game();
		cam = game.getCamera();
		sb = game.getSpriteBatch();
		gameStateViewPort = game.getViewPort();
		cam.update();
		animationSB = new SpriteBatch();
		animationSB.setProjectionMatrix(cam.combined);

		firstStart = true;


		random = game.getRandom();
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		

		stage = game.getStage();
		
	}
	
	public abstract void handleInput();
	public abstract void update(float delta);
	public abstract void render();
	public abstract void dispose();
	
	public static void initGlobalSkins(){
		//This method is used to initialise the skins that are used
        //With Scene2D
		rainbowSkin = Assets.manager.get(Assets.rainbowSkinFileName,Skin.class);
		comicSkin = Assets.manager.get(Assets.comicSkinFileName, Skin.class);
		levelPane = Assets.manager.get(Assets.levelPaneSkinFileName,Skin.class);
		flatEarthSkin = Assets.manager.get(Assets.flatEarthSkinFileName, Skin.class);
		menuBackgroundTx = Assets.manager.get("assets/images/global/purpleMenu.png",Texture.class);
		cleanCrispySkin = Assets.manager.get(Assets.cleanCrispySkinFileName, Skin.class);
		offWhiteBackgroundTx = Assets.manager.get(Assets.offWhiteBackgroundFileName, Texture.class);
	}
	


}
