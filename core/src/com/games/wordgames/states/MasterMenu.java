//This state is the main menu portion
//Start screen, and game selection

package com.games.wordgames.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.games.wordgames.gameobjects.Animation;
import com.games.wordgames.gameobjects.MenuAnimation;
import com.games.wordgames.handlers.*;
import com.games.wordgames.main.Game;

public class MasterMenu extends GameState {
	
	private Table mainButtonTable;
	//private Table titleTable;
	private Table navigationTable;
	private Table musicToggleTable;
    private Table playButtonTable;
    private Table exitTable;

	
	private String menuTitle;

	private static final int START_SCREEN = 0;
	private static final int MAIN_MENU_STATE = 1;
	private static final int WORD_GAME_STATE =2;
	private static final int NUMBER_GAME_STATE = 3;
	private static final int LOGIC_GAME_STATE = 4;
	
	private int menuState;

	private Texture mainMenuBackgroundTx;
	private Texture menuBackgroundTexture;
	
	private Texture wordGamesBackground;
	private Texture numberGamesBackground;
	private Texture logicGamesBackground;
	private Texture confirmExitBackground;
    private Texture startScreenBackground;
    private Texture leftPointingArrowTexture;
	
	private ImageButton numberGamesImgButton;
	private ImageButton wordGamesImgButton;
	private ImageButton logicGamesImgButton;
	private ImageButton bambooBlasterImgButton;
	private ImageButton speedSumsImgButton;
	private ImageButton balloonTyperImgButton;
	private ImageButton spaceBlasterImgButton;
	private ImageButton robotRulesImgButton;
	private ImageButton backImgButton;
	private ImageButton logOutImgButton;
	private ImageButton toggleMusicImgButton;
    private ImageButton playButton;
    private ImageButton exitButton;
	
	private BackGroundMusic mainMenuMusic;
	
	private MenuAnimation gameTypeBaloonAnimation;
    private MenuAnimation robotMenuAnimation;
    private MenuAnimation menuAnimation;

    private ExitConfirmation exitConfirmObj;

    //Start screen stuff



    private String freePlayToolTipText;
    private boolean showFreePlayToolTip;
    private boolean showExitButtonToolTip;


    FontManager fontManager;

	public MasterMenu(GameStateManager gsm) {
		super(gsm);
        System.out.println("Main Menu Screen");
		initTextures();

        initImageButtons();

        initMenuAnimations();

        initTables();




        freePlayToolTipText = "";

		mainMenuMusic = new BackGroundMusic("assets/sounds/mainMenuMusic.mp3",true);
		mainMenuMusic.play();

        exitConfirmObj = new ExitConfirmation();

        if(game.isShowStartScreen()){
            setStartScreen();
            game.stopStartScreen();
            System.out.println("FIRST START ");
        }else{
            setToMainMenu();
            System.out.println("NOT FIRST START ");
        }


	}

	private void initTextures(){
        initBackgroundTextures();
        initOtherTextures();
    }

	private void initBackgroundTextures(){
        startScreenBackground = Assets.manager.get("assets/images/global/logInBackground.png", Texture.class);
        confirmExitBackground = Assets.manager.get(Assets.confirmExitBackgroundMM, Texture.class);
        mainMenuBackgroundTx= Assets.manager.get("assets/images/mainMenu/MainMenuGreen.png", Texture.class);
        menuBackgroundTexture = Assets.manager.get(Assets.mainMenuBackgroundTxMM, Texture.class);
        wordGamesBackground = Assets.manager.get(Assets.wordGamesBackgroundMM, Texture.class);
        numberGamesBackground = Assets.manager.get(Assets.numberGamesBackgroundMM, Texture.class);
        logicGamesBackground = Assets.manager.get(Assets.logicGamesBackgroundMM, Texture.class);
    }

    private void initOtherTextures(){
        leftPointingArrowTexture =Assets.manager.get("assets/images/global/logIn/leftPointingArrow.png",Texture.class);
    }

    private void initTables(){

	    mainButtonTable = new Table();

        navigationTable = new Table();
        navigationTable.setPosition(100, 550);

        musicToggleTable = new Table();
        musicToggleTable.setPosition(750, 50);
        musicToggleTable.add(toggleMusicImgButton).size(50,50);

        playButtonTable = new Table();
        playButtonTable.setPosition(Game.V_WIDTH/2,Game.V_HEIGHT/2);
        playButtonTable.add(playButton);

        playButtonTable = new Table();
        playButtonTable.setPosition(Game.V_WIDTH/2,Game.V_HEIGHT/2);
        playButtonTable.add(playButton);

        exitTable = new Table();
        exitTable.setPosition(Gdx.graphics.getWidth()*0.9f, Gdx.graphics.getHeight()*0.9f);
        exitTable.setSize(50, 50);
        exitTable.add(exitButton).size(50,50);

    }


    private void setStartScreen(){
        menuAnimation = robotMenuAnimation;
        exitConfirmObj.reset();
        menuTitle = "";
	    menuState = START_SCREEN;
        menuBackgroundTexture = startScreenBackground;
        stage.clear();
        stage.addActor(musicToggleTable);
        stage.addActor(playButtonTable);
        stage.addActor(exitTable);
    }
	
	private void initMenuAnimations(){

		gameTypeBaloonAnimation = new MenuAnimation((Assets.manager.get(Assets.menuBaloonTextureMM, Texture.class)),
                50, 150, 190, 250, 0, 0.2f, 350);
		gameTypeBaloonAnimation.setMoveUpDown();
        gameTypeBaloonAnimation.setName("Baloons");
        Animation robotAnimation = new Animation(3,"assets/images/RobotLogic/robotAnimation/right/",159/3,246/3);
        robotAnimation.start();
        robotMenuAnimation = new MenuAnimation(10,0, robotAnimation);
        robotMenuAnimation.setMoveRightLeft();
        robotMenuAnimation.setName("Robot");
        robotMenuAnimation.setSize(159/3,246/3);
        menuAnimation = robotMenuAnimation;
		
	}
	

	private void initImageButtons(){
		initMenuButtonIcons();
		initButtonListeners();
	}

	private void initButtonListeners(){

        toggleMusicImgButton.addListener(new InputListener(){
            public  void  checked(InputEvent event, float x, float y, int pointer, Actor fromActor){

            }
            public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                mainMenuMusic.toggleSound();

                toggleMusicImgButton.getImage().setDrawable(toggleMusicImgButton.getStyle().imageChecked);
                return false;
            }

        });

        playButton.addListener(new InputListener(){
            public  void  enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                mouseOverPlayButton();
            }
            public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                mouseOffPlayButton();
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                setToMainMenu();

                return false;
            }
        });

        exitButton.addListener(new InputListener(){
            public  void  enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                showExitButtonToolTip();
            }
            public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                hideExitButtonToolTip();
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                // confirmExit();
                confirmExit();
                return false;
            }
        });

        backImgButton.addListener(new InputListener(){
            public  void  enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
            }
            public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                switch(menuState){
                    case MAIN_MENU_STATE:
                        confirmExit();
                        break;
                    case NUMBER_GAME_STATE:
                        setToMainMenu();
                        break;
                    case WORD_GAME_STATE:
                        setToMainMenu();
                    case LOGIC_GAME_STATE:
                        setToMainMenu();
                }
                return false;
            }
        });

        logOutImgButton.addListener(new InputListener(){
            public  void  enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
            }
            public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                setStartScreen();
                return false;
            }
        });

        robotRulesImgButton.addListener(new InputListener(){
            public  void  enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
            }
            public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                startRobotRules();
                return false;
            }
        });

        bambooBlasterImgButton.addListener(new InputListener(){
            public  void  enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                if(bambooBlasterImgButton.isPressed()){
                    startBambooBlaster();
                }
            }
            public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
            }
            public void touchUp (float x, float y, int pointer) {
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return false;
            }
        });

        spaceBlasterImgButton.addListener(new InputListener(){
            public  void  enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
            }
            public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                startSpaceBlasterGame();
                return false;
            }
        });

        balloonTyperImgButton.addListener(new InputListener(){
            public  void  enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
            }
            public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                startFallingLettersGame();
                return false;
            }
        });

        numberGamesImgButton.addListener(new InputListener(){
            public  void  enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
            }
            public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                setNumberGamesMenu();
                return false;
            }

        });

        wordGamesImgButton.addListener(new InputListener(){
            public  void  enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
            }
            public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                setWordGamesMenu();
                return false;
            }

        });

        logicGamesImgButton.addListener(new InputListener(){
            public  void  enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
            }
            public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                setLogicGamesMenu();
                return false;
            }
        });

        speedSumsImgButton.addListener(new InputListener(){
            public  void  enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
            }
            public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                startSpeedSumsGame();
                return false;
            }

        });
    }

	@Override
	public void handleInput() {
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			confirmExit();
		}
	}

	@Override
	public void update(float delta) {
		handleInput();
		updateMenuAnimations();
        checkExitConfirm();
		stage.act(Gdx.graphics.getDeltaTime());

	}

    private void checkExitConfirm() {

        if (exitConfirmObj.clickYes()){
            Gdx.app.exit();

    }
        if(exitConfirmObj.clickNo()){
            exitConfirmObj.reset();
            setStartScreen();
        }
    }


    private void updateMenuAnimations(){
	    menuAnimation.update();
	}
	
	private void setToMainMenu(){
        menuAnimation = gameTypeBaloonAnimation;
		menuBackgroundTexture = mainMenuBackgroundTx;
		menuTitle = "Home";
		menuState = MAIN_MENU_STATE;

		mainButtonTable.clear();
		mainButtonTable.setPosition(400, 250);

		mainButtonTable.add(numberGamesImgButton).size(206, 126).padBottom(10);
		mainButtonTable.row();
		mainButtonTable.add(wordGamesImgButton).size(206, 126).padBottom(10);
		mainButtonTable.row();
		mainButtonTable.add(logicGamesImgButton).size(206, 126).padBottom(10);
		
		navigationTable.clear();
		navigationTable.add(logOutImgButton).size(113, 43);

		stage.clear();
        stage.addActor(mainButtonTable);
        stage.addActor(navigationTable);
        stage.addActor(musicToggleTable);

	}

	
	private void confirmExit(){
		menuBackgroundTexture =confirmExitBackground;
		stage.clear();
        stage.addActor(exitConfirmObj.getTable());
        exitConfirmObj.setExitOn();
        menuState = -1;
	}

	private void setNumberGamesMenu(){
		menuBackgroundTexture = numberGamesBackground;
		menuState = NUMBER_GAME_STATE;
		menuTitle = "Numbers";
		mainButtonTable.clear();
		mainButtonTable.setPosition(400, 250);
		
		mainButtonTable.add(speedSumsImgButton).size(206, 126).padRight(60).padBottom(20);
		mainButtonTable.add(bambooBlasterImgButton).size(206, 126).padBottom(20);
		mainButtonTable.row();

		navigationTable.clear();
		navigationTable.add(backImgButton).size(113, 43);

	}
	
	private void setWordGamesMenu(){
		menuBackgroundTexture = wordGamesBackground;
		menuState = WORD_GAME_STATE;
		menuTitle = "Words";

		mainButtonTable.clear();
		mainButtonTable.setPosition(400, 300);
		
		mainButtonTable.add(spaceBlasterImgButton).size(206, 126).padRight(60);
		mainButtonTable.add(balloonTyperImgButton).size(206, 126);
		
		navigationTable.clear();
		navigationTable.add(backImgButton).size(113, 43);

	}
	
	private void setLogicGamesMenu(){
		menuBackgroundTexture = logicGamesBackground;
		menuState = LOGIC_GAME_STATE;
		menuTitle = " Puzzle";
		mainButtonTable.clear();
		mainButtonTable.setPosition(400, 300);
		
		mainButtonTable.add(robotRulesImgButton).size(206, 126);

		navigationTable.clear();
		navigationTable.add(backImgButton).size(113, 43);
		
	}

	
	private void initMenuButtonIcons(){

		bambooBlasterImgButton = HelpMethods.createImageButton(
		        "assets/images/mainMenu/ButtonIcons/bambooBlasterUP.png",
                "assets/images/mainMenu/ButtonIcons/bambooBlasterDOWN.png");

		speedSumsImgButton = HelpMethods.createImageButton(
		        "assets/images/mainMenu/ButtonIcons/speedSumsUP.png",
                "assets/images/mainMenu/ButtonIcons/speedSumsDOWN.png");

		spaceBlasterImgButton  = HelpMethods.createImageButton(
		        "assets/images/mainMenu/ButtonIcons/spaceBlasterUP.png",
                "assets/images/mainMenu/ButtonIcons/spaceBlasterDown.png");
		

		balloonTyperImgButton  = HelpMethods.createImageButton(
		        "assets/images/mainMenu/ButtonIcons/balloonGameIconUP.png",
                "assets/images/mainMenu/ButtonIcons/balloonGameIconDOWN.png");

		numberGamesImgButton = HelpMethods.createImageButton(
		        "assets/images/mainMenu/ButtonIcons/numberGamesUP.png",
                "assets/images/mainMenu/ButtonIcons/numberGamesDOWN.png");

		wordGamesImgButton = HelpMethods.createImageButton(
		        "assets/images/mainMenu/ButtonIcons/wordGamesUP.png",
                "assets/images/mainMenu/ButtonIcons/wordGamesDOWN.png");

		logicGamesImgButton= HelpMethods.createImageButton(
		        "assets/images/mainMenu/ButtonIcons/logicGamesUP.png",
                "assets/images/mainMenu/ButtonIcons/logicGamesDOWN.png");

		robotRulesImgButton= HelpMethods.createImageButton(
		        "assets/images/mainMenu/ButtonIcons/robotRulesUP.png",
                "assets/images/mainMenu/ButtonIcons/robotRulesDOWN.png");

        String backUp = "assets/images/mainMenu/ButtonIcons/backUp.png";
        String backDown= "assets/images/mainMenu/ButtonIcons/backDOWN.png";


        backImgButton= HelpMethods.createImageButton(backUp,backDown);

        logOutImgButton= HelpMethods.createImageButton(backUp,backDown);

        exitButton = HelpMethods.createImageButton("assets/imageButton/exitButtonIcon.png",
                "assets/imageButton/exitButtonIcon.png");

        playButton = HelpMethods.createImageButton("assets/images/mainMenu/ButtonIcons/playUp.png",
                "assets/images/mainMenu/ButtonIcons/playDown.png");

        toggleMusicImgButton = HelpMethods.createImageButtonToggleSwitch("assets/images/global/musicIcon/soundOn.png", "assets/images/global/musicIcon/soundOff.png");


	}

	@Override
	public void render() {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		sb.draw(menuBackgroundTexture, -40, 0, 1024, 768);

        FontManager.slackeyFontMedium.setColor(1,1,1,1);
        int titleX = 0;
        int titleY = 0;
		switch(menuState){
            case START_SCREEN:
                drawAnimations();

            case MAIN_MENU_STATE:
                titleX = 255;
                titleY =550;
                drawAnimations();
                break;
            case NUMBER_GAME_STATE:
                titleX = 180;
                titleY =520;
                break;
            case WORD_GAME_STATE:
                titleX = 240;
                titleY =520;
                break;
            case LOGIC_GAME_STATE:
                titleX = 220;
                titleY =520;
                break;

        }
        FontManager.slackeyFontMedium.draw(sb, menuTitle,titleX, titleY);

        if (exitConfirmObj.displayExitConfirmation()) {
            FontManager.slackeyFontMedium.draw(sb, "Exit?", 280, 500);
        }

        drawToolTips();

		sb.end();

		stage.draw();
	}

    private void drawToolTips(){
        if(showFreePlayToolTip){
            sb.draw(leftPointingArrowTexture ,600,320,90,70);
            FontManager.toolTipFont.draw(sb,freePlayToolTipText,600,460);
        }
        if(showExitButtonToolTip){
            FontManager.toolTipFont.draw(sb,"EXIT",680,580);
        }
    }
	
	private void drawAnimations(){
        if(menuAnimation.getTexture()!=null){
            sb.draw(menuAnimation.getTexture(),menuAnimation.getXPos(),menuAnimation.getYPosf(),menuAnimation.getSize().x,menuAnimation.getSize().y);
        }else if (menuAnimation.getAnimationSprite()!=null){
            sb.draw(menuAnimation.getAnimationSprite(),menuAnimation.getXPos(),menuAnimation.getYPos(),menuAnimation.getSize().x,menuAnimation.getSize().y);
        }
	}

    private void mouseOverPlayButton(){
        showFreePlayToolTip = true;
        freePlayToolTipText = "Click to start in\nfree play mode!";
    }

    private void mouseOffPlayButton(){
        showFreePlayToolTip = false;
        freePlayToolTipText = "";
    }

    private void showExitButtonToolTip(){
        showExitButtonToolTip = true;

    }

    private void hideExitButtonToolTip(){
        showExitButtonToolTip = false;

    }

	private void startRobotRules(){
		stage.clear();
		gsm.setState(GameStateManager.ROBOT_LOGIC);
	}

	private void startFallingLettersGame(){
		stage.clear();
		gsm.setState(GameStateManager.FALLING_LETTERS);
	}

	private void startBambooBlaster(){
		stage.clear();
		gsm.setState(GameStateManager.BAMBOO_BLASTER);
	}

	private void startSpeedSumsGame(){
		stage.clear();
		gsm.setState(GameStateManager.SPEED_SUMS);
		
	}
	
	private void startSpaceBlasterGame(){
		stage.clear();
		gsm.setState(GameStateManager.SPACE_BLASTER);
	}

	@Override
	public void dispose() {
		mainMenuMusic.dispose();
	}

}


