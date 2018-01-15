package com.games.wordgames.states;
/*
 * Speed Sums is a time based game where the user is presented with varying degrees of difficulty
 * Level 1, easy addition and subtraction
 * Level 2, complex addition and subtraction
 * Level 3 , complex division, subtraction, multiplication and addition
 * 
 * The score is based off of how many correct Vs incorrect answers are given in the time 
 * 
 * 
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.games.wordgames.gameobjects.SpeedSumGenerator;
import com.games.wordgames.handlers.Assets;
import com.games.wordgames.handlers.FontManager;
import com.games.wordgames.handlers.GameStateManager;
import com.games.wordgames.handlers.HelpMethods;

public class SpeedSums extends GameState{
	
	private int currentScreen;
	private final int LEVEL_SELECT = 0;
	private final int PLAYING = 1;
	private final int GAME_OVER_SCREEN = 2;
	private final int GAME_TIME_SECONDS = 59; //Game play time
	private final int GAME_TIME_MINUTES = 0;

	//SpeedSums generator used to generate the questions
	private SpeedSumGenerator sumGenerator;

	//Textures
	private Texture speedSumsBackground;
	private Texture correctTx;
	private Texture incorrectTx;
	private Texture tryAgainTx;
	private Texture resultTx;

	//Image Buttons
	private ImageButton easyButton;
	private ImageButton mediumButton;
	private ImageButton hardButton;
	private ImageButton yesImgButton;
	private ImageButton noImgButton;
	private ImageButton restartButton;
	private ImageButton checkButton;
	private ImageButton exitButton;
	private ImageButton returnToLevelSelectButton;

	//Sounds
	private Sound threeSecondTimer;
	private Sound fiveSecondTimer;

	//Scene2D objects
	private Table returnToLevelSelectTable;
	private Table exitButtonTable;
	private Table confirmExitTable;
	private Table checkAnsTable;
	private Table mainButtonTable;
	private Table levelSelectTable;
	private TextArea tableHeading;
	private Label tableLabel;
	private TextButton restart;
	private Label questionText;
	private TextField answerTextField;
	private TextButton checkAnswerCheckButton;
	private String questionString;

	//Timer variables
	private long lastTimeCheck;
	private long checkInterval;
	private Timer timer;
	private int countDownStart;
	private int seconds;
	private int minutes;
	private String counterString;
	private long lastResultTime;
	private long resultTick;
	
	//Game play booleans
	private boolean timeUp;
	private boolean countDown;
	private boolean playing;
	private boolean gameOver;
	private boolean resultOn;
	private boolean paused;
	private boolean hideGameOverScreen;
	private boolean mainMenu;
	private boolean levelSelectScreen;
	private boolean exitConfirm;
	private int levelSelect;
	private String result;
	private int resultTime;
	private int questionsAnswerd;
	private int score;
	private double scorePersent;

	public SpeedSums(GameStateManager gsm) {


		super(gsm);

		initGameVariables();
		
		resetAllBooleanValues();
		
		initTimer();

		initResources();

		initTables();

		setLevelSelectScreen();

        System.out.println("Playing: SpeedSums");
	}

    private void initGameVariables(){
	    //Resets the variables used in game play
        sumGenerator = new SpeedSumGenerator();
        levelSelect = 0;
        seconds = 0;
        minutes = 0;
        countDownStart = 0;
        resultTime = 1;
        result = "";
        score = 0;
        questionsAnswerd =0;
        questionString = "";
        counterString = ""+seconds;
    }

	private void resetAllBooleanValues(){
		timeUp = false;
		countDown= false;
		playing= false;
		gameOver= false;
		resultOn= false;
		paused= false;
		mainMenu = false;
		levelSelectScreen= false;
		exitConfirm= false;
		hideGameOverScreen = false;
	}
	
	private void initTimer(){
	    //Create the Timer object used to time the count down and game time
		timer = new Timer();
		checkInterval = 1000000000;
		lastResultTime = 500000000;
		resultTick = 0;
		
	}

	private void initResources(){
	    //Initialises all images,buttons,sounds and other game play assets
        initSounds();
        initTextures();
        initImageButtons();
    }

	private void initSounds(){
		threeSecondTimer = Assets.manager.get("assets/sounds/3SecondCountdown.mp3", Sound.class);
		fiveSecondTimer = Assets.manager.get("assets/sounds/5SecondCountdown.mp3", Sound.class);
	}

    private void initTextures(){
        speedSumsBackground = Assets.manager.get("assets/images/speedSums/speedSumsBackground.png",Texture.class);
        correctTx = Assets.manager.get("assets/images/speedSums/imageButtons/correctTx.png",Texture.class);
        incorrectTx = Assets.manager.get("assets/images/speedSums/imageButtons/inCorrectTx.png",Texture.class);
        tryAgainTx = Assets.manager.get("assets/images/speedSums/imageButtons/tryAgainTx.png",Texture.class);
        resultTx = correctTx;
    }

    private void initImageButtons(){
        //Sets all image button up/down images
        //Creates all image button listeners

        easyButton = HelpMethods.createImageButton(
                "assets/images/speedSums/imageButtons/easyUp.png",
                "assets/images/speedSums/imageButtons/easyDown.png");


        mediumButton = HelpMethods.createImageButton(
                "assets/images/speedSums/imageButtons/mediumUp.png",
                "assets/images/speedSums/imageButtons/mediumDown.png");

        hardButton = HelpMethods.createImageButton(
                "assets/images/speedSums/imageButtons/hardUp.png",
                "assets/images/speedSums/imageButtons/hardDown.png");

        yesImgButton = HelpMethods.createImageButton(
                "assets/images/speedSums/imageButtons/yesUp.png",
                "assets/images/speedSums/imageButtons/yesDown.png");

        noImgButton = HelpMethods.createImageButton(
                "assets/images/speedSums/imageButtons/noUp.png",
                "assets/images/speedSums/imageButtons/noDown.png");

        restartButton = HelpMethods.createImageButton(
                "assets/images/speedSums/imageButtons/restartUp.png",
                "assets/images/speedSums/imageButtons/restartDown.png");

        checkButton = HelpMethods.createImageButton(
                "assets/images/speedSums/imageButtons/checkUp.png",
                "assets/images/speedSums/imageButtons/checkDown.png");

        returnToLevelSelectButton = HelpMethods.createImageButton(
                "assets/images/speedSums/imageButtons/levelSelectUp.png",
                "assets/images/speedSums/imageButtons/levelSelectDown.png");

        exitButton = HelpMethods.createImageButton(
                "assets/images/speedSums/imageButtons/exitUp.png",
                "assets/images/speedSums/imageButtons/exitDown.png");





        answerTextField = new TextField("",cleanCrispySkin);

        answerTextField.getStyle().background = HelpMethods.getTextureRegionDrawable("assets/imageButton/textField1.png");
		FontManager.toolTipFont.getData().setScale(1.2f);
        answerTextField.getStyle().font = FontManager.toolTipFont;
        answerTextField.getStyle().focusedFontColor = new Color(0,0,0,1);
        answerTextField.setAlignment(1);
		FontManager.toolTipFont.getData().setScale(1);


        checkButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                checkAns();
                answerTextField.setText("");
                return false;
            }
        });

        easyButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                levelSelect = 1;
                clearTable();
                startCountDown();
                mainMenu = false;
                return false;
            }
        });

        mediumButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                levelSelect = 2;
                clearTable();
                startCountDown();
                mainMenu = false;
                return false;
            }
        });

        hardButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                levelSelect = 3;
                clearTable();
                startCountDown();
                mainMenu = false;
                return false;
            }
        });

        restartButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gameOver = false;
                score = 0;
                questionsAnswerd =0;
                clearTable();
                startCountDown();
                return false;
            }
        });

        returnToLevelSelectButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                setLevelSelectScreen();
                return false;
            }
        });

        exitButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                exitConfirm();
                return false;
            }
        });

        yesImgButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                switch(currentScreen){
                    case LEVEL_SELECT:
                        exitGame();
                        break;
                    case PLAYING:
                        setLevelSelectScreen();
                        break;
                    //pause();
                    case GAME_OVER_SCREEN:
                        setLevelSelectScreen();
                        break;
                }

                return false;
            }
        });

        noImgButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                stage.clear();
                exitConfirm = false;
                switch(currentScreen){
                    case LEVEL_SELECT:
                        setLevelSelectScreen();
                        break;
                    case PLAYING:
                        System.out.println("LEVEL");
                        pause();
                        playing = true;


                        stage.addActor(exitButtonTable);
                        stage.addActor(checkAnsTable);
                        stage.setKeyboardFocus(answerTextField);
                        break;
                    //pause();
                    case GAME_OVER_SCREEN:
                        showEndOfGame();
                        break;
                }

                return false;
            }
        });


    }

    private void initTables(){
		//Scene2D tables

		//Level Select Button Table 
		levelSelectTable = new Table();
		levelSelectTable.setPosition(400,200);
		levelSelectTable.add(easyButton).width(200).height(50).pad(20);
		levelSelectTable.row();
		levelSelectTable.add(mediumButton).width(200).height(50).pad(20);
		levelSelectTable.row();
		levelSelectTable.add(hardButton).width(200).height(50).pad(20);
		
		//Exit Button Table
		exitButtonTable = new Table();
		exitButtonTable.setPosition(740,535);
		exitButtonTable.add(exitButton).width(80).height(65);
		
		//Return to LevelSelect Screen 
		returnToLevelSelectTable = new Table();
		returnToLevelSelectTable.setPosition(140, 435);
		returnToLevelSelectTable.add(returnToLevelSelectButton).width(250).height(62);
		
		//Confirm Exit yes No table 
		confirmExitTable = new Table();
		confirmExitTable.setPosition(400,200);
		confirmExitTable.add(yesImgButton).width(200).height(60).pad(30);
		confirmExitTable.add(noImgButton).width(200).height(60).pad(30);
		
		//Check Answer Butto and TextField table 
		checkAnsTable = new Table();
		checkAnsTable.setPosition(400, 160);
		checkAnsTable.add(answerTextField).height(75).width(75).padBottom(30);
		checkAnsTable.row();
		checkAnsTable.add(checkButton).width(250).height(90);
		
		
		mainButtonTable = new Table();
		mainButtonTable.setTransform(true);
	}

	private void setLevelSelectScreen(){
	    //Sets up the main game difficulty and start screen
		resetAllBooleanValues();
		initGameVariables();
		levelSelectScreen = true;
		playing = false;
		currentScreen = LEVEL_SELECT;
		stage.clear();
		stage.addActor(exitButtonTable);
		stage.addActor(levelSelectTable);
	}

	private void clearTable(){
	    //Clears the scene2D table
		mainButtonTable.clear();
		
	}
	
	private void setQuestion(){
	    //Sets a random question and counts the number of questions answered
		sumGenerator.generateSum(levelSelect);
		questionString = sumGenerator.getSumString();
		questionsAnswerd+=1;
	}
	
	private void displayResult(String status){
		result = status;
		resultTime = 1;
		resultOn = true;
	}
	
	private void initGame(){
		currentScreen = PLAYING;
		levelSelectScreen = false;
		playing = true;
		stage.clear();
		stage.addActor(exitButtonTable);
		stage.addActor(checkAnsTable);
		
		stage.setKeyboardFocus(answerTextField);

		setQuestion();
		questionText = new Label("1 + 2 = ",levelPane);

	}
	
	private void checkAns(){
	    //Checks the input answer
		try{
			
			if(sumGenerator.checkAnswer(Integer.parseInt(answerTextField.getText()))){
				score +=1;
				displayResult("CORRECT");
				
				resultTx = correctTx;
				answerTextField.setText("");
				setQuestion();
			}else{
				displayResult("INCORRECT!");
				
				resultTx = incorrectTx;
				answerTextField.setText("");
				setQuestion();
			}
			
		}catch(NumberFormatException e){
			resultTx = tryAgainTx;
			displayResult("Try again!");
		}
		
	}
	
	private void showEndOfGame(){
		currentScreen = GAME_OVER_SCREEN;
		hideGameOverScreen = false;
		clearTable();
		stage.clear();
		playing = false;
		gameOver = true;
		restart = new TextButton("Restart?",levelPane);
		
		restart.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				gameOver = false;
				score = 0;
				questionsAnswerd =0;
				clearTable();
				startCountDown();
				return false;
			}
		});
		mainButtonTable.add(restartButton).width(200).height(60);
		mainButtonTable.setPosition(400, 75);
		
		stage.addActor(mainButtonTable);
		stage.addActor(exitButtonTable);
		stage.addActor(returnToLevelSelectTable);
		
	}
	
	private void startCountDown(){
		levelSelectScreen = false;
		stage.clear();
		countDownStart = 4;
		countDown = true;
	}
	
	private void startClock(){
		seconds = GAME_TIME_SECONDS; //SET THE GAME TIME HERE
		minutes = GAME_TIME_MINUTES;
	}
	
	private void pause(){
		if(paused){
			paused = false;
			playing = true;
		}else{
			paused = true;
			playing = false;
			fiveSecondTimer.stop();
		}
	}
	
	private void exitConfirm(){

		stage.clear();
		stage.addActor(confirmExitTable);
		
		exitConfirm = true;

		switch(currentScreen){

		case PLAYING:
			pause();
			break;
		case GAME_OVER_SCREEN:
			hideGameOverScreen = true;
			break;
		}
	}
	
	private void exitGame(){
		stage.clear();
		gsm.setState(GameStateManager.MASTER_MENU);
        System.out.println("Exit: SpeedSums");
	}
	
	@Override
	public void handleInput() {
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			exitConfirm();
		}
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
			if(playing){
				checkAns();
			}
		}
		
	}

	@Override
	public void update(float delta) {
		updateClock();
		handleInput();
		updateStage();
		
	}
	
	private void updateStage(){
		stage.act(Gdx.graphics.getDeltaTime());
		
	}
	
	private void updateClock(){
		
		if(TimeUtils.nanoTime() - resultTick>lastResultTime  ){
			if(!paused){
				if(resultTime!=0){
					resultTime -=1;
				}else{
					resultOn = false;
				}
			}
			resultTick = TimeUtils.nanoTime();
		}
		
		if(TimeUtils.nanoTime() - lastTimeCheck > checkInterval){
			if(!paused){
				if(countDown){
					if(countDownStart!=0){
						countDownStart -=1;
						if(countDownStart == 3){
							threeSecondTimer.play();
						}
					}else{
						countDown = false;
						startClock();
						initGame();//with level
					}
				}
				
				if(minutes<=0){
					if(seconds==6){
						fiveSecondTimer.play();
					}
				}
				if(seconds<=0){
					if(minutes <=0){
						if(playing){
							System.out.println("GAME OVER");
						    scorePersent = Math.round((score/questionsAnswerd)*100);
							showEndOfGame();
						}
					}
				}
				 if(seconds>0){
					 seconds-=1;
					 
				 }if(seconds==0){
					 if(minutes <=0){
							//System.out.println("GAME OVER");
						}
					 else
						 seconds = 59;
					 if(minutes>0){
						 minutes -=1;
					 }
				 }
			}
			 lastTimeCheck = TimeUtils.nanoTime();
		 }
	}
	

	@Override
	public void render() {
		
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sb.begin();
		
		drawBackground();
		
		drawHudText();
		
		drawCenterScreenItems();
		
		sb.end();

		if(!resultOn){
			stage.draw();
		}
	}
	
	private void drawBackground(){
		sb.draw(speedSumsBackground,0,0,800,600);
		
	}
	
	private void drawHudText(){
		if(!gameOver &playing){
			FontManager.hidiSpeedSmall.setColor(1,1,1,1);
			FontManager.hidiSpeedSmall.draw(sb,"Time: ", 520, 450);
			FontManager.digitalTimerFont.draw(sb,minutes+":"+getSecondTimeString(), 627, 452);
			FontManager.hidiSpeedSmall.draw(sb,"Question : ", 50,450);
			FontManager.digitalTimerFont.draw(sb,questionsAnswerd+"", 250,452);
			FontManager.hidiSpeedSmall.draw(sb,"Score : ", 300, 450);
			FontManager.digitalTimerFont.draw(sb,score+"", 450,452);
		}
	}
	
	private void drawCenterScreenItems(){
		if(exitConfirm){
			FontManager.hidiSpeedMedium.draw(sb,"Exit this game? ",240,300);
		}else{
			if(levelSelectScreen){
				FontManager.hidiSpeedMedium.draw(sb,"Level Select",270,380);
			}
		}
		
		if(!exitConfirm){
			if(countDown){
				if(countDownStart!=0){
					if(countDownStart<=3)
						FontManager.digitalTimerFontLarge.draw(sb," "+countDownStart,362,300);
				}else{
					FontManager.hidiSpeedLarge.draw(sb,"GO!",335,300);
				}
			}

			if(playing){
				FontManager.fontLarge.setColor(0, 0, 0, 1);
				FontManager.digitalTimerFontLarge.setColor(0, 35/255f, 0, 1);
				//digitalTimerFontLarge.draw(sb,minutes+":"+getSecondTimeString(), 340, 360);
				if(!resultOn){
					FontManager.firaSansMedium.draw(sb,questionString+" ?",200,350);
				}
			}
			
			if(resultOn){
				sb.draw(resultTx,330,160);
			}
			
			if(gameOver){
				drawResultScreen();
			}
		}
	}
	
	private void drawResultScreen(){
		if(!hideGameOverScreen){
			FontManager.hidiSpeedLarge.draw(sb,"Time Up!",200,375);
			FontManager.hidiSpeedMedium.draw(sb,"Result: "+score+"/"+(questionsAnswerd-1),292,265);
			scorePersent = ((double)score/(double)(questionsAnswerd-1))*100;
			FontManager.hidiSpeedLarge.draw(sb,Math.round(scorePersent)+"%",301,205);
		}
	}
	
	private String getSecondTimeString(){
		String secondString = "";
		if(seconds<10){
			secondString = "0"+seconds;
		}else
			secondString = ""+seconds;
		
		return secondString;
	}
	
	@Override
	public void dispose() {
	    //Dispose assets

	}

}
