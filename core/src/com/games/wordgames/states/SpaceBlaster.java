package com.games.wordgames.states;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.games.wordgames.gameobjects.Animation;
import com.games.wordgames.gameobjects.BambooBlasterTarget;
import com.games.wordgames.gameobjects.Coconut;
import com.games.wordgames.gameobjects.Lazer;
import com.games.wordgames.gameobjects.MenuAnimation;
import com.games.wordgames.gameobjects.Meteor;
import com.games.wordgames.gameobjects.MeteorExplosionAnimation;
import com.games.wordgames.gameobjects.SpaceBlasterQuestion;
import com.games.wordgames.handlers.*;

public class SpaceBlaster extends GameState {
	
	private long lastTimeCheck;
	private long checkInterval;
	private Timer timer;
	
	private long lastTimeMeteorSpawnCheck;
	private long checkMeteorSpawnTimeInterval;
	
	
	private Texture spaceBackground;
	private Texture cockPit;
	//private Texture bambooBlaster;
	private Texture crossHair;
	private Texture lazerBeam;
	private Texture meteorImage;
	private Texture lazerGunLeft;
	private Texture lazerGunRight;
	private Texture spaceBlasterLogo;
	private Texture spaceShip;
	
	
	private Texture fuelTankTextures[];
	
	private Animation explosionAnimation;
	
	private Sound explosionSound;

	private Sound correctSound;
	private Sound incorrectSound;
	
	
	
	private Meteor met;
	private Meteor flyingMeteor; 
	
	private MeteorExplosionAnimation metExp;
	public static final float LEFT_CANON_BASE_X = -25;
	public static final float LEFT_CANON_BASE_Y = 150;
	public static final float RIGHT_CANON_BASE_X = 645;
	public static final float RIGHT_CANON_BASE_Y = 150;
	
	private Lazer lazer;
	
	private SpaceBlasterQuestion sbQuestion;
	
	private String meteorWord;
	
	private int fuelLeft;
	private int incorrectAnswers;
	//private float cannonTip_x;
	//private float cannonTip_y;
	
	//double current_position_x;
	//double current_position_y;
	//double direction_x;
	//double direction_y;
	//double direction_length;
	
	//private Coconut cocoNut;
	
	private int lazer_beam_angle;
	
	private boolean hit;
	
	private Rectangle testTarget;
	
	private ArrayList<Meteor> targets;
	private ArrayList<Meteor> flyingMeteorArray;
	
	private boolean advanceFrame;
	
	private int score;
	
	
	private boolean gameOver;
	
	private Sound gameOversound;
	
	private SplashScreen splashScreen;
	private MenuAnimation splashScreenLazer;
	private MenuAnimation splashScreenShip;
	
	private boolean gameStart;
	
	private Meteor miniMeteor; 
	
	private int shotsFired;
	
	private Table musicToggleTable;
	private ImageButton toggleMusicImgButton;
	private BackGroundMusic mainMusic;
	private Rectangle soundToggleRect;
	private boolean disableFire;
	
	private Table exitButtonTable;
	private ImageButton exitButton;
	private Rectangle exitButtonRect;
	private boolean isExitConfirmation;
	private ExitConfirmation exitConfirmation;
	
	public SpaceBlaster(GameStateManager gsm) {
		super(gsm);
		
		Gdx.input.setCursorCatched(true);
		
		timer = new Timer();
		
		advanceFrame = false;
		
		checkInterval = 70000000;
		checkMeteorSpawnTimeInterval = 6000;
		
		initTextures();
		hit= false;
		lazer_beam_angle = 0;
		shotsFired = 0;
		score = 0;
		explosionAnimation = new Animation(7,"images/Space Blaster/meteor/explosion/",100,100);
		sbQuestion = new SpaceBlasterQuestion();
		meteorWord = sbQuestion.getRandomWord();
		
		initSplashScreen();
		
		//cannonTip_x = 0;
		//cannonTip_y = 0;
		
		//current_position_x = CANON_BASE_X;
		//current_position_y = CANON_BASE_Y;
		
		//cocoNut = new Coconut(cannonTip_x,cannonTip_y);
		//testTarget = new Rectangle(Gdx.graphics.getWidth()/6,400,60,130);
		
		flyingMeteorArray = new ArrayList<Meteor>();
		
		
		met = new Meteor();
		flyingMeteor = new Meteor("TEST");
		
		metExp = new MeteorExplosionAnimation();
		
		lazer = new Lazer();

		explosionSound= Assets.manager.get("sounds/lazerBlast.mp3", Sound.class);
		
		correctSound = Assets.manager.get("sounds/correctAnswer.mp3", Sound.class);
		incorrectSound =  Assets.manager.get("sounds/warningSound.mp3", Sound.class);
		gameOversound  =  Assets.manager.get("sounds/gameOverSound.mp3", Sound.class);
		
		initMusicToggle();
		
		initExitObjects();
		
		initNewGame();
		
		gameOver = true;
	}
	
	private void initMusicToggle(){
		mainMusic = new BackGroundMusic("sounds/spaceBlasterTheme.mp3");
		soundToggleRect = new Rectangle(720,515,75,75);
		musicToggleTable = new Table();
		musicToggleTable.setPosition(750, 550);
		
		toggleMusicImgButton = HelpMethods.createImageButtonToggleSwitch("images/global/musicIcon/soundOn.png", "images/global/musicIcon/soundOff.png");
		
		musicToggleTable.add(toggleMusicImgButton).size(50,50);
		
		stage.addActor(musicToggleTable);
		
		musicToggleTable.addListener(new InputListener(){
			public  void  checked(InputEvent event, float x, float y, int pointer, Actor fromActor){
				
			}
		public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
			}
		public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			//musicToggleTable.clear();
			//.background(HelpMethods.getTextureRegionDrawable( "images/global/musicIcon/soundOff.png"));
			//musicToggleTable.add(toggleMusicImgButton).size(50,50);
			mainMusic.toggleSound();
			toggleMusicImgButton.getImage().setDrawable(toggleMusicImgButton.getStyle().imageChecked);
			return false;
				}
			
			});
		
		
		mainMusic.play();
	}
	
	private void initExitObjects(){
		exitConfirmation = new ExitConfirmation();
		exitButtonTable = new Table();
		exitButtonTable.setPosition(50, 550);
		
		exitButton =HelpMethods.createImageButton("imageButton/exitButtonIcon.png","imageButton/exitButtonIcon.png");
		exitButtonTable.add(exitButton).width(50).height(50);; 
		
		exitButton.addListener(new InputListener(){
			 public  void  enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
				// showExitButtonToolTip();
					}
				public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
					//hideExitButtonToolTip();
					}
				public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
					System.out.println("EXIT");
					//confirmExit();
					//showExitConfirm = true;
					//
					disableFire = true;
					isExitConfirmation = true;
					setExitConfirm();
					
					return false;
				}
			});
		exitButtonRect = new Rectangle(0, 500,75,75);
		
		isExitConfirmation = false;
		
		stage.addActor(exitButtonTable);
		
	}
	
	private void setExitConfirm(){
		isExitConfirmation = true;
		stage.clear();
		stage.addActor(exitConfirmation.getTable());
		
	}
	
	
	
	private void initSplashScreen(){
		spaceBlasterLogo =  Assets.manager.get("images/Space Blaster/spaceBlasterLogo.png",Texture.class);
		String instructions = "      Find the correct parts \n      of speech and shoot \nthe meteors with your lazer!";
		splashScreen = new SplashScreen(spaceBackground, instructions, 40,350);		
		miniMeteor = new Meteor(true);
		splashScreenLazer = new MenuAnimation(lazerBeam,180,75,30,8,8,0,488);
		splashScreenLazer.setMoveRight();
		
		splashScreenShip = new MenuAnimation(spaceShip,180,75,60,100,0,1,488);
		splashScreenShip.activateTexture();
		splashScreenShip.setMoveUpDown();
	}
	
	private void initNewGame(){
		generateTargetArray();
		spawnMeteor();
		gameOver = false;
		fuelLeft = 5;
		score = 0;
		shotsFired = 0;
		sbQuestion.setNextQuestion();
		incorrectAnswers = 0;
	}
	
	private void spawnMeteor(){
		if(flyingMeteorArray.size()<6){
			flyingMeteorArray.add(new Meteor(sbQuestion.getRandomWordFromLevelBank()));
		}
		
	}

	private void generateTargetArray(){
		targets = new ArrayList<Meteor>();
		for(int i = 0;i<750;i+=150){
			//targets.add(new Meteor(i+50,400,sbQuestion.getRandomWord()));
			
		}
		
	}

	private void initTextures(){
		spaceBackground =  Assets.manager.get("images/Space Blaster/starBackground.jpg",Texture.class);
		
		//bambooBlaster = new Texture(Gdx.files.internal("assets/images/Bamboo blaster/bambooGun.png"));
		crossHair =  Assets.manager.get("images/Space Blaster/crossHair.png",Texture.class);
		lazerBeam =  Assets.manager.get("images/Space Blaster/lazerBeamGreen.png",Texture.class);
		meteorImage =  Assets.manager.get("images/Space Blaster/meteor.png",Texture.class);
		cockPit =  Assets.manager.get("images/Space Blaster/cockpit.png",Texture.class);
		lazerGunLeft  =Assets.manager.get("images/Space Blaster/leftGun.png",Texture.class);
		lazerGunRight  =Assets.manager.get("images/Space Blaster/rightGun.png",Texture.class);
		spaceShip = Assets.manager.get("images/Space Blaster/ship.png",Texture.class);
		//brokenLeaf = new Texture(Gdx.files.internal("assets/images/Bamboo blaster/brokenLeaf.png"));
		fuelTankTextures = new Texture[6];
		for(int i = 0; i<6;i++){
			fuelTankTextures[i] = Assets.manager.get("images/Space Blaster/fuelTank/"+i+".png",Texture.class);
		}
		
	}

	@Override
	public void handleInput() {
		if(Gdx.input.justTouched()){
			if(gameStart){
				if(!disableFire){
					lazer.fireLazer();
					shotsFired +=1;
					
					if(!lazer.inBounds()){  
						
						//calculateLazerBeamAngle();
						//lazer = new Lazer(400,50);
						
						//sbQuestion.nextQuestion();
					}
						
					
					
					
					if(hit){
						hit = false;
					}
				}
				
				//if(!cocoNut.inBounds())
				//	cocoNut = new Coconut(cannonTip_x,cannonTip_y);
				//leafTarget= new Texture(Gdx.files.internal("assets/images/Bamboo blaster/leaf.png"));
				
			}
			}
			
		if(Gdx.input.isKeyJustPressed(Keys.B)){
			if(!splashScreen.isStart()){
				//System.out.println("X: "+miniMeteor.getX());
			}
		}
		if(MyInput.isPressed(MyInput.ESCAPE)){
			
				stage.clear();
				Gdx.input.setCursorCatched(false);
				dispose();
				gsm.setState(GameStateManager.MASTER_MENU);
				
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			generateTargetArray();
			flyingMeteor.resetRect();
			//System.out.println("SPACE!");
			if(!gameStart){
				gameStart = true;
				splashScreen.startGame();
				
				
			}
		}
		
		if(gameOver){
			if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
				initNewGame();
			}
		}
		
	}
	
	private void explode(){
		
	}

	@Override
	public void update(float delta) {
		if(!splashScreen.isStart()){
			updateSplashScreenAnimations();
		}
		handleInput();
		//met.updatePos();
		checkExitConfirmation();
		updateFlyingMeteor();
		if(!lazer.hasHit()){
			
			checkHitsFlyingMeteor();
		}else{
			//System.out.println("LazerHIT!");
		}
		
		checkDisableFireSoundToggle();
		updateClock();
		//updateCocoPos();
		//cocoNut.updatePos();
		//checkHits();
		lazer.updatePos();
		//checkHits();
		checkInBounds();
		
		checkGameOver();
		
		
		
		stage.act(Gdx.graphics.getDeltaTime());
		
		
	}
	
	private void checkExitConfirmation(){
		if(exitConfirmation.clickYes()){
			stage.clear();
			Gdx.input.setCursorCatched(false);
			dispose();
			gsm.setState(GameStateManager.MASTER_MENU);
		}
		if(exitConfirmation.clickNo()){
			exitConfirmation.reset();
			isExitConfirmation = false;
			stage.clear();
			stage.addActor(exitButtonTable);
			stage.addActor(musicToggleTable);
		}
	}
	
	private void checkDisableFireSoundToggle(){
		
		if(soundToggleRect.contains(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY())|
				exitButtonRect.contains(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY())){
			disableFire = true;
			//Gdx.input.setCursorCatched(false);
			
		}else{
			disableFire = false;
			//Gdx.input.setCursorCatched(true);
		}
	}
	
	private void updateQuestionLevel(){
		if(shotsFired%3==0){
			if(shotsFired!=0){
				sbQuestion.setNextQuestion();
				shotsFired+=1;
			}
			
		}
		if(score%5==0){
			if(score!=0){
				sbQuestion.nextLevel();
				score+=1;
			}
			
		}
	}
	
	private void updateFlyingMeteor(){
		for(Meteor meteor: flyingMeteorArray){
			meteor.updatePos();
		}
		//flyingMeteor.updatePos();
		
		
	}
	
	private void updateSplashScreenAnimations(){
		updateSplashMeteor();
		updateSplashLazer();
		updateSplashShip();
	}
	
	private void updateSplashShip(){
		splashScreenShip.update();
	}
	
	private void updateSplashMeteor(){
		miniMeteor.updateMiniMeteor();
		
	}
	
	private void updateSplashLazer(){
		splashScreenLazer.update();
	}
	
	private void checkGameOver(){
		if(!gameOver){
			if(fuelLeft<1){
				gameOver = true;
				gameOversound.play();
				
			}
			
		}
		
	}

	private void updateClock(){
		if(TimeUtils.nanoTime() - lastTimeCheck > checkInterval){
			
			//System.out.println("TICK");
			advanceFrame = true;
			explosionAnimation.setAdvanceFrame();
			 lastTimeCheck = TimeUtils.nanoTime();
		 }
		
		if(TimeUtils.nanosToMillis(TimeUtils.nanoTime()) - lastTimeMeteorSpawnCheck > checkMeteorSpawnTimeInterval){
			lastTimeMeteorSpawnCheck = TimeUtils.nanosToMillis(TimeUtils.nanoTime());
			//System.out.println("SPAWN");
			spawnMeteor();
		}
	}
	
	public void checkInBounds(){
		if(!met.inBounds()){
			met = new Meteor();
			meteorWord = sbQuestion.getRandomWordFromLevelBank();
		}
	}
	
	public void checkHitsFlyingMeteor(){
		boolean spawnNew = false;
		Iterator<Meteor> iterator = flyingMeteorArray.iterator();
		while(iterator.hasNext()){
			Meteor tempM = iterator.next();
			
				if(tempM.getRect().contains(lazer.getXpos(), lazer.getYpos())){
					//lazer.resetLazer();
					lazer = new Lazer();
					//metExp.setPos(tempM.getX(), tempM.getY());
					explosionAnimation.setPos(tempM.getX(), tempM.getY());
					explosionSound.play();
					explosionAnimation.start();
					//metExp.explode();
					iterator.remove();
					checkAnswer(tempM.getWord());
					System.out.println("HIT: "+tempM.getWord());
				//	System.out.println("HIT!");
					lazer.setHit();
					spawnNew = true;
					//break;
					updateQuestionLevel();
				}
				if(!tempM.inBounds()){
					iterator.remove();
				}
			
		}
		if(spawnNew){
			spawnMeteor();
		}
		
		/*

		if(flyingMeteor.getRect().contains(lazer.getXpos(), lazer.getYpos())){
			metExp.setPos(flyingMeteor.getX(), flyingMeteor.getY());
			explosionAnimation.setPos(flyingMeteor.getX(), flyingMeteor.getY());
			explosionAnimation.start();
			metExp.explode();
			flyingMeteor.resetRect();
		}
		*/
	}

	public void checkHits(){
		
		/*
		 * for(Meteor m: targets){
			Rectangle r = new Rectangle(m.getX(),m.getY(),m.getWidth(),m.getHeight());
			if(!m.isHit()){
				if(r.contains(lazer.getXpos(),lazer.getYpos())){
					//explosionSound.play();
					metExp.setPos(m.getX(), m.getY());
					explosionAnimation.setPos(m.getX(), m.getY());
					explosionAnimation.start();
					metExp.explode();
					//targets.remove(m);
					m.hit();
					checkAnswer(m.getWord());
				}
			}
			
		}
		 */
		
		removeTarget();
		Rectangle r = new Rectangle(met.getX(),met.getY(),100,100);
		/*
		 * if(r.contains(lazer.getXpos(),lazer.getYpos())){
			
			
			//explosionSound.play();
			metExp.setPos(met.getX(), met.getY());
			explosionAnimation.setPos(met.getX(), met.getY());
			explosionAnimation.start();
			metExp.explode();
			met = new Meteor();
			meteorWord = sbQuestion.getRandomWord();
			
			
		}
		 */
	}
	
	private void removeTarget(){
		
	}

	public void checkAnswer(String ans){
		if(sbQuestion.checkAnswerNew(ans)){
			correctSound.play();
			if(fuelLeft<5){
				fuelLeft+=1;
				score +=1;
				
			}
		}else{
			incorrectSound.play();
			if(fuelLeft>0){
				fuelLeft-=1;
				incorrectAnswers+=1;
			}
		}
	}

	@Override
	public void render() {
		//Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		sb.draw(spaceBackground,-10,-5,855,610);
		
		//sb.draw(coconut, (float)cocoNut.getXpos(),(float)cocoNut.getYpos(),20,20);
		
		//System.out.println("WID "+Gdx.graphics.getWidth());
		
		sb.end();
		stage.draw();
		if(splashScreen.isStart()){
			
			if(!hit){
				//drawCoconut();
			}
			if(!isExitConfirmation){
				if(!gameOver){
					
					drawFlyingMeteor();
					//drawMeteor();
					drawCockpit();
					
					drawHud();
					//drawTargets();
					//drawBlaster();
					drawLazer();
				}
				if(gameOver){
					drawGameOver();
				}
				
				explosionAnimation.advanceFrameNoLoop();
				if(explosionAnimation.animating()){
					sb.begin();
					//System.out.println("AN");
					sb.draw(explosionAnimation.getTexture(),
							explosionAnimation.getX(),explosionAnimation.getY(),100,100);
					sb.end();
				}
			}
			
			
			drawCross();
			if(isExitConfirmation){
				sb.begin();
				FontManager.spaceFontMedium.draw(sb,"Are you sure \nyou want to exit?",100,450);
				sb.end();
			}
			
			//drawDebug();
			
			 
			
			/*
			 * if(advanceFrame){
				metExp.nextFrame();
				advanceFrame = false;
			}
			if(!metExp.compleate()){
				sb.begin();
				//sb.draw(lazerGunLeft,LEFT_CANON_BASE_X,LEFT_CANON_BASE_Y,230,150);
				//sb.draw(metExp.getTexture(),metExp.getX(),metExp.getY(),100,100);

				sb.end();
			}
			 */
			
		}else{
			drawSplashScreen();
		}
		
		
		
		
		//System.out.println(Gdx.input.getY());
	}
	
	private void drawFlyingMeteor(){
		for(Meteor meteor:flyingMeteorArray){
			sb.begin();
			sb.draw(meteorImage,meteor.getX(),meteor.getY(),100,100);
			FontManager.fontMedium.setColor(1, 1, 1, 1);

			FontManager.spaceFontMedium.draw(sb,meteor.getWord(), meteor.getX(),meteor.getY()+130);
			
			sb.end();
			
		}
		sb.begin();
		//sb.draw(meteorImage,flyingMeteor.getX(),flyingMeteor.getY(),100,100);
		//fontMedium.setColor(1, 1, 1, 1);
	
		//fontMedium.draw(sb,flyingMeteor.getWord(), flyingMeteor.getX(),flyingMeteor.getY()+120);
		
		sb.end();
		
	}

	private void drawGameOver(){
		double accuracy = Math.round(((double)score/(double)incorrectAnswers)*100);
		sb.begin();
		FontManager.fontLarge.setColor(1, 1, 1, 1);
		FontManager.fontMedium.setColor(1, 1, 1, 1);
		FontManager.spaceFontLarge.draw(sb, "GAME OVER", (180), 450);
		//WINDOWd//fontLarge.draw(sb, "Score: "+score, (Gdx.graphics.getWidth()/2)-130, (Gdx.graphics.getHeight()/2)+20);
		FontManager.spaceFontMedium.draw(sb, "Score: "+score, 300, 300);
		FontManager.spaceFontMedium.draw(sb, "Accuracy: "+accuracy+"%", 300, 360);
		
		//Math.round(accuracy);

		FontManager.spaceFontMedium.draw(sb, "Press SPACE BAR to restart",
				60, 200);
		FontManager.spaceFontMedium.draw(sb, "Press ESCAPE to exit",
				135, 130);
		sb.end();
		
	}
	
	private void drawHud(){
		sb.begin();
		FontManager.fontLarge.setColor(0, 0, 0, 1);
		sb.draw(sbQuestion.getQuestionTexture(),240,115,325,95);
		//fontLarge.draw(sb,sbQuestion.getQuestion(),320,180);
		sb.end();
	}

	public void drawLazer(){
		
		sb.begin();
		sb.draw(lazerBeam, (float)lazer.getXpos(),(float)lazer.getYpos(),15,15);
		//sb.draw(lazerBeam, (float)lazer.getXpos(),(float)lazer.getYpos(),
		//		7,20, 20, 30, 1, 1,lazer_beam_angle, 0, 0, 100, 100, false, false);
		sb.end();
	}
	
	private void drawLazerGuns(){
		//calculateCannonAngleLeft();
		drawLeftGun();
		drawRightGun();
	}
	
	private void drawRightGun(){
		sb.begin();
		//sb.draw(lazerGunLeft,LEFT_CANON_BASE_X,LEFT_CANON_BASE_Y,230,150);
		sb.draw(lazerGunRight, RIGHT_CANON_BASE_X,RIGHT_CANON_BASE_Y, 
				120, 80, 230, 180, 1, 1f,330, 0, 0, 300, 200, false, false);
		

		sb.end();
	}

	private void drawLeftGun(){
		sb.begin();
		//sb.draw(lazerGunLeft,LEFT_CANON_BASE_X,LEFT_CANON_BASE_Y,230,150);
		sb.draw(lazerGunLeft, LEFT_CANON_BASE_X,LEFT_CANON_BASE_Y, 
				0, 80, 230, 180, 1f, 1f, 30, 0, 0, 300, 200, false, false);
		//sb.draw
		//System.out.println(canon_angle);
		sb.end();
	}
	
	public void drawMeteor(){
		sb.begin();
		sb.draw(meteorImage,met.getX(),met.getY(),100,100);
		FontManager.spaceFontMedium.setColor(1, 1, 1, 1);

		FontManager.spaceFontMedium.draw(sb,meteorWord, met.getX(),met.getY()+120);
		
		sb.end();
	}
	
	public void drawCockpit(){
		sb.begin();
		sb.draw(cockPit,0,-70,800,900);
		//System.out.println(cockPit.getWidth());
		sb.draw(fuelTankTextures[fuelLeft],265,35);
		FontManager.fontMedium.setColor(0, 0, 0, 1);
		FontManager.fontMedium.draw(sb,"FUEL",285,100);
		FontManager.fontLarge.setColor(1, 1, 1, 1);
		FontManager.spaceFontMedium.draw(sb,"SCORE: "+score,280,585);
		sb.end();
		drawLazerGuns();
	}
	
	public void drawCoconut(){
		sb.begin();
		//sb.draw(lazerBeam, (float)cocoNut.getXpos(),(float)cocoNut.getYpos(),20,20);
		sb.end();
	}

	public void drawTargets(){
		sb.begin();
		
		for(Meteor t:targets){
				if(!t.isHit()){
					sb.draw(meteorImage, t.getX(),t.getY(),t.getWidth(),t.getHeight());
					FontManager.fontMedium.setColor(1, 1, 1, 1);

					FontManager.fontMedium.draw(sb,t.getWord(), t.getX(),t.getY()+120);
				}
				
				//hit = false;
			
			
		}
		sb.end();
		
	}
	
	private void drawSplashScreen(){
		
		if(splashScreenLazer.showTexture()){
			sb.begin();
			sb.draw(splashScreenLazer.getTexture(),splashScreenLazer.getXPos(),splashScreenLazer.getYPos(),splashScreenLazer.getSize().x,splashScreenLazer.getSize().y);
			sb.end();
		}
		
		sb.begin();
		sb.draw(spaceBlasterLogo,170,380);
		sb.draw(meteorImage,miniMeteor.getX(),miniMeteor.getY(),miniMeteor.getHeight(),miniMeteor.getWidth());

		FontManager.spaceFontMedium.draw(sb,splashScreen.getInstructions(), splashScreen.getPos().x,splashScreen.getPos().y+30);
		FontManager.spaceFontMedium.draw(sb, "Press SPACE to start",150,180);
		sb.draw(splashScreenShip.getTexture(),splashScreenShip.getXPos(),splashScreenShip.getYPos(),splashScreenShip.getSize().y,splashScreenShip.getSize().x);
		sb.end();
		
		
		
		if(miniMeteor.explode()){
			explosionAnimation.setPos(488, 50);
			explosionAnimation.start();
		//	System.out.println("EXPLODE");
			splashScreenLazer.hideTexture();
			splashScreenLazer.reset();
			
		}
		
		
		if(miniMeteor.getX()==528){
			//splashScreenLazer = new MenuAnimation(lazerBeam,0,65,30,8,8,0,488);
			
			//splashScreenLazer.setMoveRight();
			splashScreenLazer.reset();
			splashScreenLazer.activateTexture();
		}
		explosionAnimation.advanceFrameNoLoop();
		if(explosionAnimation.animating()){
			sb.begin();
			//System.out.println("AN");
			sb.draw(explosionAnimation.getTexture(),
					explosionAnimation.getX(),explosionAnimation.getY(),75,75);
			sb.end();
		}
	}

	private void drawBlaster(){
		
		
		
		
		sb.begin();
		//sb.draw(bambooBlaster,CANON_BASE_X-22,CANON_BASE_Y,
		//		16, 0, 30,120, 1, 1, canon_angle, 0, 0, 80, 300, false, false);
		sb.end();
	}

	public void drawCross(){
		Vector3 mp3 = HelpMethods.getMousePosInGameWorld(cam, Gdx.input.getX(), Gdx.input.getY());
		Vector2 mp2 = HelpMethods.scaleMouse(Gdx.input.getX(), Gdx.input.getY());
		sb.begin();
		sb.draw(crossHair,Gdx.input.getX()-25,Gdx.graphics.getHeight()- Gdx.input.getY()-25,51,51);
		//sb.draw(crossHair,Gdx.input.getX()-16,Gdx.graphics.getHeight()-Gdx.input.getY()-16,34,34);
		sb.end();
	}
	
	private void drawDebug(){
		Vector2 mp2 = HelpMethods.scaleMouse(Gdx.input.getX(), Gdx.input.getY());
		sb.begin();
		FontManager.fontMedium.setColor(1,1,1,1);
		FontManager.fontMedium.draw(sb, "DIRX: "+mp2.x+" DIRY: "+mp2.y,300,400);
		sb.end();
		/*
		 * shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setAutoShapeType(true);
		shapeRenderer.circle(CANON_BASE_X-5,  CANON_BASE_Y, 10);
		shapeRenderer.circle(cannonTip_x,cannonTip_y, 10);
		shapeRenderer.rect(CANON_BASE_X, CANON_BASE_Y, 30, 120);
		shapeRenderer.rect(testTarget.x,testTarget.y,testTarget.width,testTarget.height);
		shapeRenderer.end();
		 */
	}
	@Override

	public void dispose() {
		//spaceBlasterLogo.dispose();
		//spaceShip.dispose();
		mainMusic.dispose();
		
	}

}
