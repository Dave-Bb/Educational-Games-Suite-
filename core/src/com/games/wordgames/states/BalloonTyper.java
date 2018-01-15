package com.games.wordgames.states;




import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.games.wordgames.gameobjects.Animation;
import com.games.wordgames.gameobjects.Letters;
import com.games.wordgames.handlers.*;


public class BalloonTyper extends GameState{
	
	private FontManager fontManager;
	private Letters letter;
	private Array<Letters> letters;
	private long lastDropTime;
	private Timer timer;
	private Long frameAdvanceTime;
	private Long frameRate;
	private Task task;
	private String nextLetter =" ";
	
	private Color blockColor;
	
	private Vector2 blockPosition;
	private int lettersMissed;
	private double score;
	private long dropInterval;
	private double incorrectLetters;
	private double accuracy = 0;
	private int level;
	private int lives;
	
	private Texture carnivalBackgroundImage;
	private Texture carnivalGameOverImage;
	private Texture carnivalBackgroundSplash;
	
	private Sprite carnivalBackgroundSprite;
	
	private Texture baloonImage;
	private Texture gameOverCloud;
	private Texture livesLeftBaloon;
	
	private Sound popSound;
	private Sound gameOverSound;
	
	private boolean gameOver;
	
	private boolean popAnimating;
	private boolean advanceFrame;
	private Texture[] popTexture;
	
	private Animation popAnimation;
	private Animation plusOneAnimation;
	
	private double keyStrokes;
	
	private boolean gameStart;
	
	private Texture keyboardMapTx;
	
	private BackGroundMusic gameMusic;
	
	public BalloonTyper(GameStateManager gsm) {
		super(gsm);
		fontManager = FontManager.getInstance();

		gameStart = false;
		
		advanceFrame = false;
		frameRate = (long) 70000000;
		frameAdvanceTime =(long) 0;
		System.out.println("PLAY STATE CALLED");
		//letters = new Letters();
		gameMusic = new BackGroundMusic("assets/sounds/baloon/circusMusicQuick.mp3");
		//gameMusic.play();
		initGame();
		timer = new Timer();
		popAnimation = new Animation(5,"assets/images/fallingLetters/baloonPop/",180,180);
		plusOneAnimation = new Animation(9,"assets/images/fallingLetters/plus1/",60,60);
		
		popAnimating = false;
		initTextures();
		dropInterval = 2000000000;
		
		lives = 5;
		
		popSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/shortPop.mp3"));
		gameOverSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/gameOver.mp3"));
		
		
		gameOver = false;
		
		initSpashScreen();
	}
	
	private void initSpashScreen(){
		
	}
	
	
	
	private void initTextures(){
		keyboardMapTx = Assets.manager.get("assets/images/fallingLetters/Keyboard.png",Texture.class);
		baloonImage = Assets.manager.get("assets/images/fallingLetters/b3.png",Texture.class);
		carnivalBackgroundImage= Assets.manager.get("assets/images/fallingLetters/carnivalBackgroundHR.png",Texture.class);
		carnivalBackgroundSprite = new Sprite(carnivalBackgroundImage);
		gameOverCloud = Assets.manager.get("assets/images/fallingLetters/gameOverCloud.png",Texture.class);
		livesLeftBaloon = Assets.manager.get("assets/images/fallingLetters/baloon.png",Texture.class);
		carnivalGameOverImage = Assets.manager.get("assets/images/fallingLetters/carnivalBackgroundGameOverHR.png",Texture.class);
		carnivalBackgroundSplash= Assets.manager.get("assets/images/fallingLetters/carnivalBackgroundSplash.png",Texture.class);
		
		popTexture = new Texture[5];
		for(int i = 0; i<5;i++){
			popTexture[i] = Assets.manager.get("assets/images/fallingLetters/baloonPop/"+i+".png",Texture.class);
		}
	}
	
	private void initGame(){
		blockPosition = new Vector2(40,40);
		letters = new Array<Letters>();
		lettersMissed = 0;
		score = 0;
		incorrectLetters = 0;
		gameOver = false;
		keyStrokes = 0;
		accuracy = 0;
		level = 1;
		lives = 3;
		//gameMusic.play();
	}

	@Override
	public void handleInput() {
		if(Gdx.input.justTouched()){
			Vector3 mp = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0));
			gameMusic.checkClickBox((int)mp.x+153,(int)mp.y);
		}
		
		if(!gameStart){
			if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
				gameStart = true;
				initGame();
			}
		}
		if(gameOver){
			if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
				initGame();
			}
			
		}
		if(Gdx.input.isKeyJustPressed(Keys.ANY_KEY)){
			int i = 0;
			keyStrokes +=1;
			int activeLetters = 0;
			
			
			for(Letters l:letters){
				if(Gdx.input.isKeyPressed(Keys.valueOf(l.getString()))){
					//System.out.println(l.getString());
					popSound.play();
					startPopAnimation(l);
					
					letters.removeValue(l, false);
					score+= 1;
					if(score%9 == 0){
						increaseSpawnSpeed();
					}
					
					
					

				}else {
					activeLetters +=1;
					i ++;
					incorrectLetters+=i/activeLetters;	
					
				}
				i=0;
				
					
				
			}
			incorrectLetters = keyStrokes - score;
			
		}
		if(MyInput.isPressed(MyInput.ESCAPE)){
			gsm.setState(GameStateManager.MASTER_MENU);
			dispose();
		}
		
		
	}
	
	private void startPopAnimation(Letters l){
		
		if(!popAnimation.animating()){
			popAnimation.setPos((int)l.getPosition().x, (int)l.getPosition().y);
			popAnimation.setColor(l.getColor());
			popAnimation.start();
			
		}
		
		if(!plusOneAnimation.animating()){
			plusOneAnimation.setPos((int)l.getPosition().x, (int)l.getPosition().y);
			plusOneAnimation.start();
		}
		
	}
	
	private void updateTimer(){
		if(TimeUtils.nanoTime() - lastDropTime > dropInterval){
			 addLetter();
			 
			
			 lastDropTime = TimeUtils.nanoTime();
		 }
		if(TimeUtils.nanoTime() - frameAdvanceTime >frameRate){
			frameAdvanceTime= TimeUtils.nanoTime();
			advanceFrame = true;
			popAnimation.setAdvanceFrame();
			plusOneAnimation.setAdvanceFrame();
		}
		 
		
	}
	
	private void increaseSpawnSpeed(){
			if(dropInterval>400000000){
			  dropInterval -=50000000;
			}
			level +=1;
			System.out.println(dropInterval);
		
	}

	@Override
	public void update(float delta) {
		updateTimer();
		handleInput();
		checkGameOver();
		itterateBaloons();
	}
	
	private void checkGameOver(){
		if(!gameOver){
			if(gameStart){
			if(lives<1){
				gameOver = true;
				gameOverSound.play();
				gameMusic.mute();

			}
			}
		}
		
	}
	
	private void itterateBaloons(){
		Iterator<Letters> iter = letters.iterator();
		 while(iter.hasNext()){
			 Letters l = iter.next();
			 l.updatePosition();
			 if(l.offScreen()){
				 iter.remove();
				 lettersMissed+=1;
				 lives -=1;
				 
			 }
		 }
	}
	@Override
	public void render() {
		//Gdx.gl.glClearColor(1, 1, 1, 1);
		//Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
		
		
		drawBackground();
		
		if(!gameStart){
			drawInstructions();
		}else{
			if(!gameOver){
				
				drawBaloons();
				
				drawPlayText();
				
			}else{
				drawGameOverScreenText();
				}
			
		}
		
		drawPopAnimations();

		drawMusicToggle();
		// drawDebug();
		
	}
	
	private void drawBackground(){
		sb.begin();
		if(!gameStart){
			sb.draw(carnivalBackgroundSplash,-25,0,Gdx.graphics.getWidth()+40,600);
		}
		else if(gameOver){
			sb.draw(carnivalGameOverImage,-25,0,Gdx.graphics.getWidth()+40,600);
		}else{
			sb.draw(carnivalBackgroundImage,-25,0,Gdx.graphics.getWidth()+40,600);
		}
		
		sb.end();
		
		
	}
	private void drawInstructions(){
		sb.begin();
		FontManager.circusFontLarge.draw(sb, "Carnival Baloons", 70, 550);
		FontManager.fontMedium.setColor(0,0,0,1);
		FontManager.fontMedium.draw(sb, "Pop the balloons before they fly away!",160, 180);
		FontManager.fontMedium.draw(sb, "Use the keyboard and type the letter on each balloon",75, 140);
		FontManager.circusFontMedium.draw(sb, "Press the SPACE bar to start ",180, 90);
		sb.draw(keyboardMapTx, 70,205,669,227 );
		sb.end();
	}
	
	private void drawBaloons(){
		sb.begin();
		for(Letters letter: letters){
			sb.setColor(letter.getColor().r, letter.getColor().g, letter.getColor().b, 1);
			sb.draw(baloonImage, letter.getPosition().x-75f, letter.getPosition().y-150, 180, 180);
			FontManager.fontLarge.setColor(1,1,1,1);
			FontManager.fontLarge.draw(sb, letter.getString(), letter.getLetterXPosiition(), letter.getPosition().y);
		}
		sb.end();
		
		
		 
	}
	
	private void drawPlayText(){
		sb.begin();
		sb.setColor(1, 1, 1, 1);

		FontManager.circusFontMedium.draw(sb, "Level: "+(int)level, 40, 580);
		FontManager.circusFontMedium.draw(sb, "Score: "+(int)score, 40, 530);
		
		//fontMedium.draw(sb, "Key Strokes: "+keyStrokes, 550, 560);
		
		if(score>0){
			accuracy = score/(keyStrokes-1)*100;
		}
		FontManager.circusFontMedium.draw(sb, "Accuracy  :  "+(Math.round(accuracy))+"%", 515, 585);
		FontManager.circusFontMedium.draw(sb, "Lives: ", 516, 540);
		
		sb.end();
		
		drawLivesLeftBaloons();
	}
	
	private void drawLivesLeftBaloons(){
		sb.begin();
		for(int i = 0; i<lives;i++){
			sb.draw(livesLeftBaloon,615+(i*30),490,20,42);
		}
		sb.end();
	}
	
	private void drawGameOverScreenText(){
		sb.begin();
		//sb.setColor(255,1,1,1);
		FontManager.fontLarge.setColor(0, 0, 0, 1);
		FontManager.fontMedium.setColor(0, 0, 0, 1);
		FontManager.circusFontLarge.draw(sb, "GAME OVER", 155, 550);
		FontManager.circusFontMedium.draw(sb, "You let too many balloons get away!", 145, 450);
		//WINDOWd//fontLarge.draw(sb, "Score: "+score, (Gdx.graphics.getWidth()/2)-130, (Gdx.graphics.getHeight()/2)+20);
		FontManager.circusFontLarge.draw(sb, "Score: "+(int)score, 240, 380);
		//double accuracy = score/keyStrokes*100;
		//Math.round(accuracy);
		FontManager.circusFontLarge.draw(sb, "Accuracy: "+Math.round(accuracy)+"%", 140, 280);

		FontManager.curcusFontSmall.setColor(255, 0, 0, 1);
		FontManager.curcusFontSmall.draw(sb, "Press SPACE BAR to restart",
				245, 160);
		FontManager.curcusFontSmall.draw(sb, "Press ESCAPE to exit",
				285, 120);
		sb.end();
	}
	
	private void drawPopAnimations(){
		popAnimation.advanceFrameNoLoop();
		if(popAnimation.animating()){
			sb.begin();
			animationSB.begin();
			System.out.println("AN");
			animationSB.setColor(popAnimation.getColor());
			
			animationSB.draw(popAnimation.getTexture(),
					popAnimation.getX()-75f,popAnimation.getY()-150,popAnimation.getWidth(),popAnimation.getHeight());
			FontManager.circusFontMedium.draw(sb, "+1", 200,200);
			animationSB.end();
			
			sb.end();
		}
		
		plusOneAnimation.advanceFrameNoLoop();
		if(plusOneAnimation.animating()){
			sb.begin();
			
			sb.draw(plusOneAnimation.getTexture(),
					plusOneAnimation.getX()-20,plusOneAnimation.getY()+25,plusOneAnimation.getWidth(),plusOneAnimation.getHeight());


			sb.end();
		}
		
	}
	
	public void drawMusicToggle(){
		sb.begin();
		sb.draw(gameMusic.getTexture(),gameMusic.getXPos(),gameMusic.getYPos(),gameMusic.getW(),gameMusic.getH());
		sb.end();
	}
	
	
	private void drawDebug(){
		String m = "ON";
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(0, 0, 0, 1);
		shapeRenderer.setAutoShapeType(true);
		shapeRenderer.rect(gameMusic.getXPos(),gameMusic.getYPos(),gameMusic.getW(),gameMusic.getH());
		shapeRenderer.end();
		sb.begin();
		Vector3 mp = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0));
		if(gameMusic.isMute()){
			m = "OFF";
		}

		FontManager.fontMedium.draw(sb,m+" X: "+mp.x+" RAW X"+Gdx.input.getX()+" Y:"+mp.y+" RAWY:"+Gdx.input.getY(),100,550);
		FontManager.fontMedium.draw(sb," RX: "+gameMusic.getXPos()+" Y:"+gameMusic.getYPos(),100,590);
		sb.end();
		

	}
	
	public void addLetter(){
		letters.add(new Letters((float)random.nextInt(150),(float)random.nextInt(150)));
		

	}
	
	private void removeLetter(String removeLetter){
		
		Iterator<Letters> iter = letters.iterator();
		 while(iter.hasNext()){
			 Letters l = iter.next();
			 l.updatePosition();
			 if(l.getString()==removeLetter){
				 iter.remove();
				 lettersMissed+=1;
			 }
		 }
	}
	
	
	@Override
	public void dispose() {
		gameMusic.dispose();
		
		//System.out.println("DISPOSE WAS CALLED");
	}

}
