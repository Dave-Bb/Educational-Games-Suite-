package com.games.wordgames.states;

import java.awt.Cursor;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.games.wordgames.gameobjects.Animation;
import com.games.wordgames.gameobjects.BaambooBlasterCannon;
import com.games.wordgames.gameobjects.BambooBlasterAllTargets;
import com.games.wordgames.gameobjects.BambooBlasterCheckBox;
import com.games.wordgames.gameobjects.BambooBlasterQuestion;
import com.games.wordgames.gameobjects.BambooBlasterTarget;
import com.games.wordgames.gameobjects.Coconut;
import com.games.wordgames.handlers.*;
import com.games.wordgames.main.Game;

public class BambooBlaster extends GameState {
	
	private Texture jungleBackground;
	private Texture bambooBlaster;
	private Texture crossHair;
	private Texture coconut;
	private Texture leafTarget;
	private Texture brokenLeaf;
	
	public static final float CANON_BASE_X = 400;
	public static final float CANON_BASE_Y = 600/6.5f;

	private Coconut cocoNut;
	
	private boolean hit;
	
	private Rectangle testTarget;
	
	private ArrayList<BambooBlasterTarget> targets;
	
	private BambooBlasterQuestion question;
	
	//Cannon Object 
	private BaambooBlasterCannon cannon;
	
	//SplashScreen
	private SplashScreen splashScreen;
	private boolean gameStart;
	
	private BambooBlasterAllTargets targetMaster;
	
	//player variables
	private int score; 
	private int questionNumber;
	
	private Animation checkSignAnimation;
	
	private long checkInterval;
	private long lastTimeCheck;
	
	private Rectangle checkBoxRect;
	
	private BambooBlasterCheckBox checkBoxSign;
	
	private String passFailTop;
	private String passFailBottom;
	
	private double scorePersent;
	
	private Sound fireSound;
	private Sound hitSound;
	private Sound passSound;
	private Sound failSound;
	private Sound checkSound;
	
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
	
	private boolean gameOver;
	
	public BambooBlaster(GameStateManager gsm) {
		super(gsm);
		//Gdx.input.setCursorPosition(0, 0);
		Gdx.input.setCursorCatched(true);
		
		passFailTop = "";
		passFailBottom = "";
		
		scorePersent = 0;
		score = 0;
		questionNumber = 1;
		
		checkInterval = 10000000;
		disableFire = true;
		initTextures();
		initSounds();
		initSplashScreen();
		hit= false;
		
		question = new BambooBlasterQuestion();

		
		cocoNut = new Coconut();
		
		
		testTarget = new Rectangle(Gdx.graphics.getWidth()/6,400,60,130);
		
		cannon = new BaambooBlasterCannon();
		
		targetMaster = new BambooBlasterAllTargets();
		
		
		initMusicToggle();
		
		initExitObjects();
		
	}
	
	
	
	private void initTextures(){
		checkSignAnimation= new Animation(30,"images/Bamboo blaster/animations/checkSign/",130,86);
		checkSignAnimation.setPos(50, 220);
		checkBoxRect = new Rectangle(50,220,130,86);
		//BambooBlasterCheckBox(Animation animation, int x, int y, int w, int h)
		checkBoxSign = new BambooBlasterCheckBox(checkSignAnimation,15,270,130,86);
		
		jungleBackground = Assets.manager.get(Assets.jungleBackgroundFileNameFileName, Texture.class);
		bambooBlaster = Assets.manager.get(Assets.bambooBlasterFileName, Texture.class);
		crossHair = Assets.manager.get(Assets.crossHairFileName, Texture.class);
		coconut = Assets.manager.get(Assets.coconutFileName, Texture.class);
		leafTarget= Assets.manager.get(Assets.leafTargetFileName, Texture.class);
		brokenLeaf = Assets.manager.get(Assets.brokenLeafFileName, Texture.class);
	}
	
	private void initSounds(){
		fireSound= Assets.manager.get("sounds/bambooblasterSoundBlaster.mp3", Sound.class);
		passSound= Assets.manager.get("sounds/bambooblasterSoundPass.mp3", Sound.class);
		failSound= Assets.manager.get("sounds/bambooblasterSoundFail.mp3", Sound.class);
		hitSound= Assets.manager.get("sounds/bambooblasterSoundHit.mp3", Sound.class);
		checkSound= Assets.manager.get("sounds/bambooblasterSoundCheck.mp3", Sound.class);
		mainMusic = new BackGroundMusic("sounds/bambooblasterSoundMusic.mp3");
		
	}
	
	private void initMusicToggle(){
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
		stage.clear();
		stage.addActor(exitConfirmation.getTable());
		
	}
	private void initSplashScreen(){
		String instructions = "     Solve the Maths problems \n  Use the mouse "
				+ "and shoot down \n            the correct leaves \n     with your coconut blaster!";
		splashScreen = new SplashScreen(jungleBackground, instructions, 100,400);
		
	}
	

	@Override
	public void handleInput() {
		if(Gdx.input.justTouched()){
			if(gameOver){
				restart();
			}
			System.out.println("Y: "+Gdx.input.getY());
			if(!cocoNut.inBounds()){
				if(!targetMaster.waitForHold()&!disableFire &!isExitConfirmation){
					fireSound.play();
					cocoNut = new Coconut(HelpMethods.getAspectMousePosX((int)cannon.getCannonTip_X())+10,
							HelpMethods.getAspectMousePosY(Gdx.graphics.getHeight()-cannon.getCannonTip_Y())+20);
				}
				
			}
			
			if(targetMaster.waitForHold()){
				//score+= targetMaster.getScore();
				targetMaster.resetTargets();
				questionNumber+=1;
				
				cocoNut.kill();
			}
			
			
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			
				stage.clear();
				//dispose();
				Gdx.input.setCursorCatched(false);
				gsm.setState(GameStateManager.MASTER_MENU);
		}
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
				question.setNew();
				if(!splashScreen.isStart()){
					gameStart = true;
					disableFire = false;
					splashScreen.startGame();
					targetMaster.breakHold();
				}
			}
		
		
	}
	
	private void restart(){
		gsm.setState(GameStateManager.BAMBOO_BLASTER);
	}
	
	@Override
	public void update(float delta) {
		updateClock();
		handleInput();
		stage.act(Gdx.graphics.getDeltaTime());
		cannon.updateCannonAngle();
		
		checkDisableFireSoundToggle();
		//System.out.println("COCONT X: "+cocoNut.getXpos()+" CCOCONUT Y: "+cocoNut.getYpos());
		if(gameStart){
			cocoNut.update();
		}
		
		
		if(targetMaster.checkHit(cocoNut)){
			cocoNut.setHitTrue();
			hitSound.play();
			
		}
		
		checkCheckSignHit();
		
		checkExitConfirmation();
		
		/*
		 * if(targetMaster.getAllAnsweredStatus()){
			cocoNut.kill();
			targetMaster.holdForNextQuestion();
		}
		 */
		
		
		checkGameOver();
		
	}
	
	private void checkGameOver(){
		if(questionNumber==10){
			gameOver = true;
			disableFire = true;
		}
	}
	
	private void checkExitConfirmation(){
		if(isExitConfirmation){
			if(exitConfirmation.clickNo()){
				stage.clear();
				stage.addActor(exitButtonTable);
				stage.addActor(musicToggleTable);
				exitConfirmation.reset();
				isExitConfirmation = false;
				disableFire = false;
			}
			if(exitConfirmation.clickYes()){
				stage.clear();
				//dispose();
				Gdx.input.setCursorCatched(false);
				gsm.setState(GameStateManager.MASTER_MENU);
				
			}
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
	private void checkCheckSignHit(){
		checkBoxSign.checkHit(cocoNut);
		if(checkBoxRect.contains(cocoNut.getXpos(), cocoNut.getYpos())){
			if(!checkSignAnimation.animating()){
				checkSound.play();
				if(targetMaster.getPassFail()==1){
					cocoNut.kill();
					targetMaster.holdForNextQuestion();
					passFailTop = "Well done!";
					passFailBottom = "  You hit the right targets!";
					System.out.println("PASS!");
					score +=1;
					scorePersent = ((double)score/(double)questionNumber)*100;
					passSound.play();
				}else{
					cocoNut.kill();
					targetMaster.holdForNextQuestion();
					System.out.println("FAIL!");
					passFailTop = "   Ohh no!";
					passFailBottom = "You missed the right targets!";
					scorePersent = ((double)score/(double)questionNumber)*100;
					failSound.play();
				}
				if(targetMaster.checkPassFail()){
					
				}
				/*
				 * if(targetMaster.getAllAnsweredStatus()){
					
				}
				 */
				//targetMaster.checkHitAnswers();
				checkSignAnimation.start();
			}
		}
	}
	
	public void updateAll(){
		
	}
	
	private void updateClock(){
		if(TimeUtils.nanoTime() - lastTimeCheck > checkInterval){
			checkBoxSign.setAdvanceFrame();
			checkSignAnimation.setAdvanceFrame();
			 lastTimeCheck = TimeUtils.nanoTime();
		 }
		
	}
	

	@Override
	public void render() {
		//Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		sb.draw(jungleBackground,-10,-5,855,610);
		
		//sb.draw(coconut, (float)cocoNut.getXpos(),(float)cocoNut.getYpos(),20,20);
		
		
		
		
		sb.end();
		
		drawDebug();
		
		
		stage.draw();
		
		if(gameOver){
			drawGameOver();
		}else{
			if(isExitConfirmation){
			drawExitConfirmationText();
		}
		if(gameStart&!isExitConfirmation){
			drawAnimations();
			drawTargets();
			drawQuestion();
			drawBlaster();
			
			drawScore();
			if(!cocoNut.hasHitTarget()){
				drawCoconut();
			}
		}else{
			if(!isExitConfirmation){
				drawSplashScreen();
			}
			
		}
			
		}
		
		
		drawCross();
		
	}
	
	private void drawGameOver(){
		sb.begin();
		FontManager.jungleFontMedium.draw(sb, "GAME OVER!",250,400);
		//jungleFont.setColor(1, 1, 1, 0.7f);
		FontManager.jungleFont.draw(sb, ("SCORE: "+Math.round(scorePersent)+" '/,"),305,330);
		FontManager.jungleFont.draw(sb, "Click anywhere to restart!",150,260);
		sb.end();
		
		
	}
	
	private void drawExitConfirmationText(){
		sb.begin();
		FontManager.jungleFontMedium.draw(sb, "    Are you sure \nyou want to exit?",160,450);
		sb.end();
	}
	private void drawAnimations(){
		checkSignAnimation.advanceFrameNoLoop();
		sb.begin();
		sb.draw(checkBoxSign.getTexture(),checkBoxSign.getX(),checkBoxSign.getY(),checkBoxSign.getWidth(),checkBoxSign.getHeight());
		if(checkSignAnimation.animating()){
			//System.out.println(checkSignAnimation.getFrameNumber());
			//sb.draw(checkSignAnimation.getTexture(),
			//		checkSignAnimation.getX(),checkSignAnimation.getY(),checkSignAnimation.getWidth(),checkSignAnimation.getHeight());
		}else{
			//System.out.println("AN");
			//sb.draw(checkSignAnimation.getFirstTexture(),
			//		checkSignAnimation.getX(),checkSignAnimation.getY(),checkSignAnimation.getWidth(),checkSignAnimation.getHeight());
			
		}
		sb.end();
	}
	public void drawScore(){
		
		sb.begin();
		FontManager.jungleFont.setColor(1, 1, 1, 0.7f);
		FontManager.jungleFont.draw(sb, ("SCORE: "+Math.round(scorePersent)+" '/,"),400,60);
		FontManager.jungleFont.draw(sb, "QUESTION: "+questionNumber,100,60);
		sb.end();
	}
	public void drawCross(){
		sb.begin();
		//crossHair,Gdx.input.getX()-16,Gdx.graphics.getHeight()-Gdx.input.getY()-16
		//float x = HelpMethods.getAspectMousePosX(Gdx.input.getX())+HelpMethods.applyScale(16); //( Gdx.input.getX()*0.78125f)-171+(16/0.78125f);
		//float y = HelpMethods.getAspectMousePosY(Gdx.input.getY())-HelpMethods.applyScale(16); //((Gdx.graphics.getHeight()-Gdx.input.getY())*(0.78125f))-(16/0.78125f);
		sb.draw(crossHair,Gdx.input.getX()-17,Gdx.graphics.getHeight()-Gdx.input.getY()-17,34,34);
		//System.out.println("MOUSE X: "+Gdx.input.getX());
		sb.end();
	}
	
	private void drawBlaster(){
		
		
		
		
		sb.begin();
		sb.draw(bambooBlaster,CANON_BASE_X-22,CANON_BASE_Y,
				16, 0, 30,120, 1, 1, cannon.getCannonAngle(), 0, 0, 80, 300, false, false);
		sb.end();
	}
	
	private void drawSplashScreen(){
		sb.begin();

		FontManager.jungleFont.setColor(0, 0, 0, 0.7f);
		FontManager.jungleFont.draw(sb,splashScreen.getInstructions(), splashScreen.getPos().x-10,splashScreen.getPos().y);
		FontManager.jungleFont.draw(sb, "Press SPACE BAR to start",170,200);
		sb.end();
	}
	
	public void drawQuestion(){
		
		//question.getQuestion()
		
		if(targetMaster.waitForHold()){
			if(gameStart){
				sb.begin();
				FontManager.jungleFont.setColor(0, 0, 0, 0.7f);

				FontManager.jungleFontMedium.draw(sb,passFailTop, 260,350);
				FontManager.jungleFont.draw(sb,passFailBottom, 150,300);
				FontManager.jungleFontMedium.draw(sb,"Click to continue", 205,250);
				sb.end();
			}
		}else{
			sb.begin();
			FontManager.jungleFont.setColor(0, 0, 0, 0.7f);
			FontManager.jungleFont.draw(sb,targetMaster.getQuestion(), 150,300);
			sb.end();
		}
		
	}
	public void drawCoconut(){
		sb.begin();
		sb.draw(coconut, (float)cocoNut.getXpos(),(float)cocoNut.getYpos(),20,20);
		sb.end();
	}
	
	public void drawTargets(){
		sb.begin();
		FontManager.jungleFont.setColor(0, 0, 0, 0.7f);
		ArrayList<BambooBlasterTarget> mT = targetMaster.getTargetArray();
		for(BambooBlasterTarget t:mT){
			FontManager.jungleFont.draw(sb, ""+t.getTargetNumber(), t.getX()+18,t.getY());
			if(!t.isHit()){
				sb.draw(leafTarget, t.getX(),t.getY(),t.getWidth(),t.getHeight());
				//hit = false;
			}
			else{
				sb.draw(brokenLeaf, t.getX(),t.getY(),t.getWidth(),t.getHeight());
				
			}
		}
		
		for(int i = 0;i<600;i+=100){
			//jungleFont.draw(sb, ""+targetMaster.getQuestionObj().getNumber(i/100), i+150,400);
		}
		sb.end();
		
	}
	
	private void drawDebug(){

		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setAutoShapeType(true);
		shapeRenderer.circle(CANON_BASE_X-5,  CANON_BASE_Y, 10);
	
		shapeRenderer.rect(CANON_BASE_X, CANON_BASE_Y, 30, 120);
		
		shapeRenderer.rect(checkBoxSign.getX(),checkBoxSign.getY(),checkBoxSign.getWidth(),checkBoxSign.getHeight());
		shapeRenderer.rect(soundToggleRect.x,soundToggleRect.y,soundToggleRect.width,soundToggleRect.height);
		shapeRenderer.rect(exitButtonRect.x,exitButtonRect.y,exitButtonRect.width,exitButtonRect.height);

		//shapeRenderer.rect(testTarget.x,testTarget.y,testTarget.width,testTarget.height);
		shapeRenderer.end();
		
		sb.begin();
		FontManager.fontSmall.draw(sb,"Gdx.Input X: "+Gdx.input.getX(),10,400);
		FontManager.fontSmall.draw(sb,"Gdx.Input ADJUSTED X: "+HelpMethods.getAspectMousePosX(Gdx.input.getX()),50,430);

		FontManager.fontSmall.draw(sb,"Gdx.Input Y: "+Gdx.input.getY(),10,370);
		FontManager.fontSmall.draw(sb,"Cannon angle: "+cannon.getCannonAngle(),10,340);
		sb.end();
	}
	@Override
	public void dispose() {
		System.out.println("DISPOSED");
		mainMusic.dispose();
		/*
		 * jungleBackground.dispose();
		bambooBlaster.dispose();
		crossHair.dispose();
		coconut.dispose();
		leafTarget.dispose();
		brokenLeaf.dispose();
		 */
		
	}
	
	private void debugCannonData(){
		
	}

}







/*
 * private void updateQuestion(){
		boolean allAnswerd = true;
		for(BambooBlasterTarget t: targets){
			if(!t.isHit()){
				allAnswerd = false;
			}
		}
		
		if(allAnswerd){
			question.setNew();
			for(BambooBlasterTarget t: targets){
				t.resetHit();
				cocoNut.kill();
			}
		}
	}
	
	public void checkHits(){
		for(BambooBlasterTarget t:targets){
			if(!t.isHit()){
				t.checkHit(cocoNut.getXpos(), cocoNut.getYpos());
				if (t.isHit()){
					cocoNut.setHitTrue();
				}
			}
			
			
		}
	}
	
	
	*/
