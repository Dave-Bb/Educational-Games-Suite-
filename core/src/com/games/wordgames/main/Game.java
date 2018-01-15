package com.games.wordgames.main;

import java.util.Random;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.games.wordgames.handlers.*;


public class Game implements ApplicationListener {
	
	public static final String TITLE = "The Gaming Suite";
	public static final int V_WIDTH = 800;
	public static final int V_HEIGHT = 600;
	public static final float STEP = 1/60f;
	
	
	
	public static final float ASPECT_RATIO = (float) V_WIDTH/(float)V_HEIGHT;
	public static Rectangle viewport;
	
	private float runTime;
	
	private SpriteBatch sb;
	private OrthographicCamera cam;
	private GameStateManager gsm;

	private Stage stage;
	private Random random;
	

	private Assets assetManager;
	private Viewport vPort;
	private FontManager fontManager;

	private boolean showStartScreen;

	public void create () {

		fontManager = fontManager.getInstance();


		stage = new Stage(new StretchViewport(V_WIDTH,V_HEIGHT));
		random = new Random();
		MyInputProcessor myInputProcessor = new MyInputProcessor();
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(inputMultiplexer);
		inputMultiplexer.addProcessor(stage);
		inputMultiplexer.addProcessor(myInputProcessor);

		sb = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false,V_WIDTH,V_HEIGHT);
		sb.setProjectionMatrix(cam.combined);
		gsm = new GameStateManager(this);

		vPort = new FitViewport(V_WIDTH, V_HEIGHT, cam);

		showStartScreen = true;
	}

	public void render () {
		//Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		runTime += Gdx.graphics.getDeltaTime();
		cam.update();

        // set viewport
        Gdx.gl20.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
		while(runTime>= STEP){
			runTime -= STEP;
			gsm.update(STEP);
			gsm.render();
			MyInput.update();
		}
	}
	



	@Override
	public void resize(int width, int height) {
		// calculate new viewport
        float aspectRatio = (float)width/(float)height;
        float scale = 1f;
        Vector2 crop = new Vector2(0f, 0f);
        if(aspectRatio > ASPECT_RATIO)
        {
            scale = (float)height/(float)V_HEIGHT;
            crop.x = (width - V_WIDTH*scale)/2f;
        }
        else if(aspectRatio < ASPECT_RATIO)
        {
            scale = (float)width/(float)V_WIDTH;
            crop.y = (height - V_HEIGHT*scale)/2f;
        }
        else
        {
            scale = (float)width/(float)V_WIDTH;
        }

        float w = (float)V_WIDTH*scale;
        float h = (float)V_HEIGHT*scale;
        viewport = new Rectangle(crop.x, crop.y, w, h);
        
        vPort.update(width, height);
        stage.getViewport().update(width, height, false);

        //ystem.out.println("ASPECT: "+aspectRatio+" SCALE: "+scale+"CropX:"+crop.x+" CropY:"+crop.y+" W:"+w+" H:"+h);
		
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}
	

	public static Rectangle getViewPort(){
		return viewport;
	}
	
	public Assets GetAssetManager(){
		return assetManager;
	}

	public FontManager getFontManager() {
		return fontManager;
	}

	public Stage getStage(){
		return stage;
	}
	public Random getRandom(){
		return random;
	}
	public SpriteBatch getSpriteBatch(){
		return sb;
		}
	public OrthographicCamera getCamera(){
		return cam;
		}
	public void stopStartScreen(){
		showStartScreen = false;
	}

	public boolean isShowStartScreen(){
		return showStartScreen;
	}
    public void dispose () {

    }
}

