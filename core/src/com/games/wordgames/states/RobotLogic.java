/*
Help the robot reach the finish line, and pick up coins along the way

This game is based of another game called Robot Island
Robot Island can be found here https://www.mathplayground.com/logic_robot_islands.html

The sprites and map textures have been recreated in the same image as Robot Island

This code is meant for personal use and as a project, it is not intended for commercial use.
 */
package com.games.wordgames.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.games.wordgames.gameobjects.Animation;
import com.games.wordgames.gameobjects.Robot;
import com.games.wordgames.gameobjects.RobotLevels;
import com.games.wordgames.gameobjects.RobotLogicGroundBloc;
import com.games.wordgames.handlers.*;


public class RobotLogic extends GameState {
	
	private long lastTimeCheck;
	private long frameRate;
	private Timer timer;
	
	private Texture blueBackgroundTx;
	private Texture groundTexture;
	private Texture groundHoleTexture;
	private Texture finishLineTexture;
	private Texture startBlockTexture;
	
	private RobotLogicGroundBloc[][] mapArray;
	
	private Texture coinTexture;
	
	private RobotLevels levels;
	
	private Texture[] arrowTx;
	private Texture rightArrow;
	private Texture leftArrow;
	private Texture upArrow;
	private Texture downArrow;
	private Texture turnAroundArrow;

	private int currentSelectorPos;
	

	
	private Animation robotDownAnimation;
	private Animation robotUpAnimation;
	private Animation robotLeftAnimation;
	private Animation robotRightAnimation;
	private Animation robotDrownAnimation;
	private Array<Animation> robotAnimations;
	
	
	private boolean advanceFrame;
	
	private Robot robot;
	
	private boolean levelEditor;
	private boolean freeMovement;
	private boolean debug;
	
	private int[][] instructionSet= {{Robot.RIGHT,185,0},{Robot.UP,0,295},{Robot.LEFT,-185,0}};
	
	private int setIndex;
	
	private int level;
	
	private boolean hasGameStarted;
	public static final int ROW = 5;
	public static final int COL = 8;
	
	private Table musicToggleTable;
	private ImageButton toggleMusicImgButton;
	private BackGroundMusic mainMusic;
	
	private Table exitButtonTable;
	private ImageButton exitButton;
	private ExitConfirmation exitConfirmation;
	
	private SplashScreen splashScreen;

	private boolean playing;

	private Table arrowButtonTable;
	private ImageButton leftArrowImageButton;
	private ImageButton rightArrowImageButton;
	private ImageButton upArrowImageButton;
	private ImageButton downArrowImageButton;

	private Table controlButtonTable;
	private ImageButton startImageButton;
	private ImageButton resetImageButton;
	private ImageButton stopImageButton;

	private Table nextLevelButtonTable;
	private ImageButton nextLevelImageButton;

	
	public RobotLogic(GameStateManager gsm) {
		super(gsm);
		
		timer = new Timer();
		
		advanceFrame = false;
		
		frameRate = 200000000;
		
		robotAnimations = new Array<Animation>();
		
		initTextures();
		
		initUIButtons();
		

		initUITables();
		
		mapArray = new RobotLogicGroundBloc[ROW][COL];
		
		generateBlankMap();
		
		initSplashScreen();
		
		level = 0;
		
		levels = new RobotLevels();
		robot = new Robot(250,250);
		mapArray = levels.getMap();
		
		setRobotStartPos();

		levelEditor = false;
		
		freeMovement = true;
		
		debug = false;
		hasGameStarted = false;

		playing = false; //set to true after splash screen
		
	}

	private void initTextures(){
		initMapTextures();
		initRobotTexture();
        initArrowTextures();
	}

	private void initMapTextures(){
		blueBackgroundTx =Assets.manager.get("assets/images/RobotLogic/BlueBackgorund.png", Texture.class);
		groundTexture =Assets.manager.get("assets/images/RobotLogic/path.png", Texture.class);
		groundHoleTexture =Assets.manager.get("assets/images/RobotLogic/pathHole.png", Texture.class);
		finishLineTexture = Assets.manager.get("assets/images/RobotLogic/finishLine.png", Texture.class);
		coinTexture=Assets.manager.get("assets/images/RobotLogic/coin.png", Texture.class);
		startBlockTexture=Assets.manager.get("assets/images/RobotLogic/startBlock.png", Texture.class);

	}

	private void initRobotTexture(){

		robotUpAnimation = new Animation(3,"assets/images/RobotLogic/robotAnimation/up/",159/3,246/3);
		robotDownAnimation = new Animation(3,"assets/images/RobotLogic/robotAnimation/down/",159/3,246/3);
		robotLeftAnimation = new Animation(3,"assets/images/RobotLogic/robotAnimation/left/",159/3,246/3);
		robotRightAnimation = new Animation(3,"assets/images/RobotLogic/robotAnimation/right/",159/3,246/3);
		robotDrownAnimation = new Animation(6,"assets/images/RobotLogic/robotAnimation/drown/",88,88);


		robotAnimations.add(robotDownAnimation);
		robotAnimations.add(robotUpAnimation);
		robotAnimations.add(robotLeftAnimation);
		robotAnimations.add(robotRightAnimation);
	}

	private void initUIButtons(){
		initMusicToggle();
		initExitObjects();

	}

	private void initMusicToggle(){
		mainMusic = new BackGroundMusic("assets/sounds/spaceBlasterTheme.mp3");
		//soundToggleRect = new Rectangle(720,515,75,75);
		musicToggleTable = new Table();
		musicToggleTable.setPosition(750, 550);
		
		toggleMusicImgButton = HelpMethods.createImageButtonToggleSwitch("assets/images/global/musicIcon/soundOn.png", "assets/images/global/musicIcon/soundOff.png");
		
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
		
		exitButton =HelpMethods.createImageButton("assets/imageButton/exitButtonIcon.png","assets/imageButton/exitButtonIcon.png");
		exitButtonTable.add(exitButton).width(50).height(50);; 
		
		exitButton.addListener(new InputListener(){
			 public  void  enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
					}
				public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
					}
				public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
					setExitConfirm();
					
					return false;
				}
			});

		
		stage.addActor(exitButtonTable);
		
	}
	
	private void initSplashScreen(){
		String instructions = "Help the robot make \n      	it to the finish \ncollect coins along the way";
		splashScreen = new SplashScreen(blueBackgroundTx, instructions, 130,350);		
		
	}
	
	private void setExitConfirm(){
		playing = false;
		exitConfirmation.setExitOn();
		stage.clear();
		stage.addActor(exitConfirmation.getTable());
		
	}
	
	private void initUITables(){
		arrowButtonTable = new Table();
		arrowButtonTable.setPosition(Gdx.graphics.getWidth()/2,80);

        controlButtonTable = new Table();
        controlButtonTable.setPosition(Gdx.graphics.getWidth()/2 ,175);

        nextLevelButtonTable = new Table();
        nextLevelButtonTable.setPosition(Gdx.graphics.getWidth()/2,100);

		upArrowImageButton = HelpMethods.createImageButtonToggleSwitchHightlight(
		        "assets/images/RobotLogic/imageButtons/upUp.png",
                "assets/images/RobotLogic/imageButtons/upDown.png",
                "assets/images/RobotLogic/imageButtons/upSelected.png");
        downArrowImageButton = HelpMethods.createImageButtonToggleSwitchHightlight(
                "assets/images/RobotLogic/imageButtons/downUp.png",
                "assets/images/RobotLogic/imageButtons/downDown.png",
                "assets/images/RobotLogic/imageButtons/downSelected.png");
		leftArrowImageButton = HelpMethods.createImageButtonToggleSwitchHightlight(
		        "assets/images/RobotLogic/imageButtons/leftUp.png",
                "assets/images/RobotLogic/imageButtons/leftDown.png",
                "assets/images/RobotLogic/imageButtons/leftSelected.png");
		rightArrowImageButton = HelpMethods.createImageButtonToggleSwitchHightlight(
		        "assets/images/RobotLogic/imageButtons/rightUp.png",
                "assets/images/RobotLogic/imageButtons/rightDown.png",
                "assets/images/RobotLogic/imageButtons/rightSelected.png");

		startImageButton  = HelpMethods.createImageButtonToggleSwitchHightlight(
                "assets/images/RobotLogic/imageButtons/playDown.png",
                "assets/images/RobotLogic/imageButtons/playUp.png",
                "assets/images/RobotLogic/imageButtons/playSelected.png");

		stopImageButton  = HelpMethods.createImageButtonToggleSwitchHightlight(
                "assets/images/RobotLogic/imageButtons/stopDown.png",
                "assets/images/RobotLogic/imageButtons/stopUp.png",
                "assets/images/RobotLogic/imageButtons/stopSelected.png");

		resetImageButton  = HelpMethods.createImageButtonToggleSwitchHightlight(
                "assets/images/RobotLogic/imageButtons/resetDown.png",
                "assets/images/RobotLogic/imageButtons/resetUp.png",
                "assets/images/RobotLogic/imageButtons/resetSelected.png");

		nextLevelImageButton = HelpMethods.createImageButton(
                "assets/images/RobotLogic/imageButtons/nextLevelUp.png",
                "assets/images/RobotLogic/imageButtons/nextLevelDown.png");

        rightArrowImageButton.addListener(new InputListener(){
            public  void  enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                // showExitButtonToolTip();
            }
            public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                //hideExitButtonToolTip();
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                currentSelectorPos = 3;
                if(leftArrowImageButton.isChecked()){
                    leftArrowImageButton.toggle();
                }if(upArrowImageButton.isChecked()){
                    upArrowImageButton.toggle();
                }if(downArrowImageButton.isChecked()){
                    downArrowImageButton.toggle();
                }
                return false;
            }
        });

        leftArrowImageButton.addListener(new InputListener(){
            public  void  enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                // showExitButtonToolTip();
            }
            public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                //hideExitButtonToolTip();
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                currentSelectorPos = 2;
                if(rightArrowImageButton.isChecked()){
                    rightArrowImageButton.toggle();
                }if(upArrowImageButton.isChecked()){
                    upArrowImageButton.toggle();
                }if(downArrowImageButton.isChecked()){
                    downArrowImageButton.toggle();
                }
                return false;
            }
        });

        downArrowImageButton.addListener(new InputListener(){
            public  void  enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                // showExitButtonToolTip();
            }
            public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                //hideExitButtonToolTip();
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                currentSelectorPos = 0;
                if(rightArrowImageButton.isChecked()){
                    rightArrowImageButton.toggle();
                }if(upArrowImageButton.isChecked()){
                    upArrowImageButton.toggle();
                }if(leftArrowImageButton.isChecked()){
                    leftArrowImageButton.toggle();
                }
                return false;
            }
        });

        upArrowImageButton.addListener(new InputListener(){
            public  void  enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                // showExitButtonToolTip();
            }
            public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                //hideExitButtonToolTip();
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                currentSelectorPos = 1;
                if(rightArrowImageButton.isChecked()){
                    rightArrowImageButton.toggle();
                }if(downArrowImageButton.isChecked()){
                    downArrowImageButton.toggle();
                }if(leftArrowImageButton.isChecked()){
                    leftArrowImageButton.toggle();
                }
                return false;
            }
        });

        startImageButton.addListener(new InputListener(){
            public  void  enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                // showExitButtonToolTip();
            }
            public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                //hideExitButtonToolTip();
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                play();
                if(stopImageButton.isChecked()){
                    stopImageButton.toggle();
                }if(resetImageButton.isChecked()){
                    resetImageButton.toggle();
                }
                return false;
            }
        });

        stopImageButton.addListener(new InputListener(){
            public  void  enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                // showExitButtonToolTip();
            }
            public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                //hideExitButtonToolTip();
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                stop();
                if(startImageButton.isChecked()){
                    startImageButton.toggle();
                }if(resetImageButton.isChecked()){
                    resetImageButton.toggle();
                }
                return false;
            }
        });

        resetImageButton.addListener(new InputListener(){
            public  void  enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                // showExitButtonToolTip();
            }
            public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                //hideExitButtonToolTip();
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                clearAllArrows();
                if(startImageButton.isChecked()){
                    startImageButton.toggle();
                }if(stopImageButton.isChecked()){
                    stopImageButton.toggle();
                }
                return false;
            }
        });

        nextLevelImageButton.addListener(new InputListener(){
            public  void  enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                // showExitButtonToolTip();
            }
            public  void  exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                //hideExitButtonToolTip();
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                levels.nextLevel();
                mapArray = levels.getMap();
                setRobotStartPos();
                robot.reset();
                playing = true;
                setLevelStart();
                return false;
            }
        });

        controlButtonTable.add(startImageButton).width(75).height(75).pad(15);
        controlButtonTable.add(stopImageButton).width(75).height(75).pad(15);
        controlButtonTable.add(resetImageButton).width(75).height(75).pad(15);

        arrowButtonTable.add(downArrowImageButton).width(75).height(75).pad(15);
		arrowButtonTable.add(upArrowImageButton).width(75).height(75).pad(15);
        arrowButtonTable.add(leftArrowImageButton).width(75).height(75).pad(15);
        arrowButtonTable.add(rightArrowImageButton).width(75).height(75).pad(15);

        nextLevelButtonTable.add(nextLevelImageButton);

	}



	private void initArrowTextures(){

		arrowTx = new Texture[5];
		rightArrow=Assets.manager.get("assets/images/RobotLogic/arrows/rightArrow.png", Texture.class);
		leftArrow=Assets.manager.get("assets/images/RobotLogic/arrows/leftArrow.png", Texture.class);
		upArrow=Assets.manager.get("assets/images/RobotLogic/arrows/upArrow.png", Texture.class);
		downArrow=Assets.manager.get("assets/images/RobotLogic/arrows/downArrow.png", Texture.class);
		turnAroundArrow=Assets.manager.get("assets/images/RobotLogic/arrows/turnAroundArrow.png", Texture.class);

		arrowTx[0] = downArrow;
		arrowTx[1] = upArrow;
		arrowTx[2]= leftArrow;
		arrowTx[3] = rightArrow;
		arrowTx[4] = turnAroundArrow;
		

		currentSelectorPos = 0;
	}

	private void generateBlankMap(){
		for(int row =0;row<ROW;row++){
			for(int col  = 0;col<COL;col++){
				mapArray[row][col] = 
						new RobotLogicGroundBloc(135+(64*col),480-(row*64));
				//sb.draw(groundTexture,200+(64*i),380-(j*64),64,72);
			}
			
		}
		
	}
	

	@Override
	public void handleInput() {
		
		if(Gdx.input.justTouched()){
			if(!levelEditor){

				checkBoxClicked();
			}
			
			if(levelEditor){
				blockOnOff();
				
			}
		}
		
			if(Gdx.input.isKeyJustPressed(Keys.H)){
				if(levelEditor){
					System.out.println("HACHE");
					for(int row =0;row<ROW;row++){
						for(int col  = 0;col<COL;col++){
							if(mapArray[row][col].boxClicked(Gdx.input.getX(),Gdx.graphics.getHeight()- Gdx.input.getY())){
								if(!mapArray[row][col].hasHole()){
									mapArray[row][col].makeHole();
									}
								else{
									mapArray[row][col].fullHole();
								}
								}
							}
						}
					
				}
				}
		if(Gdx.input.isKeyJustPressed(Keys.F)){
			if(levelEditor){
		
				for(int row =0;row<ROW;row++){
					for(int col  = 0;col<COL;col++){
						if(mapArray[row][col].boxClicked(Gdx.input.getX(),Gdx.graphics.getHeight()- Gdx.input.getY())){
							if(!mapArray[row][col].isFinish()){
								mapArray[row][col].setFinish();
								}
							else{
								mapArray[row][col].removeFinish();
							}
							}
						}
					}
				
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			if(!splashScreen.isStart()){
			    setLevelStart();
				splashScreen.startGame();

			}
		}
		if(Gdx.input.isKeyJustPressed(Keys.C)){
			if(levelEditor){
				System.out.println("HACHE");
				for(int row =0;row<ROW;row++){
					for(int col  = 0;col<COL;col++){
						if(mapArray[row][col].boxClicked(Gdx.input.getX(),Gdx.graphics.getHeight()- Gdx.input.getY())){
							if(!mapArray[row][col].hasCoin()){
								mapArray[row][col].addCoin();
								}
							else{
								mapArray[row][col].pickUpCoin();
							}
							}
						}
					}
				
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.G)){
			if(levelEditor){
				System.out.println("HACHE");
				for(int row =0;row<ROW;row++){
					for(int col  = 0;col<COL;col++){
						if(mapArray[row][col].boxClicked(Gdx.input.getX(),Gdx.graphics.getHeight()- Gdx.input.getY())){
							if(!mapArray[row][col].isStart()){
								mapArray[row][col].setStart();;
								}
							else{
								mapArray[row][col].removeStart();
							}
							}
						}
					}
				
			}
		}

		if(freeMovement){
			if(Gdx.input.isKeyJustPressed(Keys.LEFT)){
				robot.changeDirection(Robot.LEFT);
			}
			if(Gdx.input.isKeyJustPressed(Keys.RIGHT)){
				robot.changeDirection(Robot.RIGHT);
			}
			if(Gdx.input.isKeyJustPressed(Keys.UP)){
				robot.changeDirection(Robot.UP);
			}
			if(Gdx.input.isKeyJustPressed(Keys.DOWN)){
				robot.changeDirection(Robot.DOWN);
			}
			if(Gdx.input.isKeyPressed(Keys.LEFT)){
				robot.moveLeft();
				
				
			}if(Gdx.input.isKeyPressed(Keys.RIGHT)){
				
				robot.moveRight();
				
			}if(Gdx.input.isKeyPressed(Keys.UP)){
				robot.moveUp();
			}if(Gdx.input.isKeyPressed(Keys.DOWN)){
				robot.moveDown();
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			System.out.println("RUN");
			//robot.run();
		}
		if(Gdx.input.isKeyJustPressed(Keys.R)){
            clearAllArrows();
		}
		if(Gdx.input.isKeyJustPressed(Keys.S)){
			if(levelEditor){
				saveLevelDesign();
			}else{
				robot.stopRun();
			}
			
		}
		if(Gdx.input.isKeyJustPressed(Keys.N)){
			if(!levelEditor){
				levels.nextLevel();
				mapArray = levels.getMap();
				setRobotStartPos();
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			setExitConfirm();

		}

	}

	private void setLevelStart(){
	    stage.clear();
	    stage.addActor(exitButtonTable);
	    stage.addActor(musicToggleTable);

        stage.addActor(controlButtonTable);
        stage.addActor(arrowButtonTable);

        playing = true;
    }

    private void play(){
        robot.run();
        hasGameStarted = true;
        for(Animation a:robotAnimations){
            a.setLoop();
        }
    }

    private void stop(){
        robot.reset();
        for(Animation a:robotAnimations){
            a.stopLoop();
        }
        System.out.println("RESET");
    }

	private void clearAllArrows(){
		for(int row =0;row<ROW;row++){
			for(int col  = 0;col<COL;col++){
				mapArray[row][col].removeArrow();
			}
		}
	}
	
	private void setRobotStartPos(){
	    //Set the start pos depending on the map
		for(int row =0;row<ROW;row++){
			for(int col  = 0;col<COL;col++){
				if(mapArray[row][col].isStart()){
					robot.setStart(mapArray[row][col].getxPos()+10,mapArray[row][col].getyPos()+20);
					
				}
			}
		}
		
	}
	
	private void checkOffMap(){
	    //Checks to see if the robot has walked off the map or not
		boolean offMap = true;
		//System.out.println("OFF MAP");
		if(hasGameStarted){
			for(int row =0;row<ROW;row++){
				for(int col  = 0;col<COL;col++){
					if(mapArray[row][col].isDraw()){
						if(mapArray[row][col].boxClicked(robot.getxPos(), robot.getyPos())){
							offMap = false;
						}
					}
				}
			}
			
			if(offMap){
				System.out.println("OFF MAP");
				drownAnimation(robot.getxPos(),robot.getyPos());
				//robot.reset();
			}
		}
		
	}

	private void saveLevelDesign(){
		System.out.println("mapArray = generateMap();");
		for(int row =0;row<ROW;row++){
			for(int col  = 0;col<COL;col++){
				if(mapArray[row][col].isDraw()){
					System.out.println("mapArray["+row+"]["+col+"].setDraw(true);");
				}if(mapArray[row][col].hasHole()){
					System.out.println("mapArray["+row+"]["+col+"].makeHole();");
				}
				if(mapArray[row][col].isFinish()){
					System.out.println("mapArray["+row+"]["+col+"].setFinish();");
				}
				
				if(mapArray[row][col].hasCoin()){
					System.out.println("mapArray["+row+"]["+col+"].addCoin();");
				}
				if(mapArray[row][col].isStart()){
					System.out.println("mapArray["+row+"]["+col+"].setStart();");
				}
			}
		}
		System.out.println("levelArray.add(mapArray);");
	}
	
	private void checkBoxClicked(){
		for(int row =0;row<ROW;row++){
			for(int col  = 0;col<COL;col++){
				if(mapArray[row][col].boxClicked(Gdx.input.getX(), 600-Gdx.input.getY())){
					System.out.print(row+":"+col+",");
					if(mapArray[row][col].isDraw()){
						if(mapArray[row][col].hasArrow()){
							mapArray[row][col].removeArrow();
						}else{
							mapArray[row][col].setArrow(currentSelectorPos);
						}
					}
				}
				
			}
			
		}
	}
	
	private void blockOnOff(){
		
		for(int row =0;row<ROW;row++){
			for(int col  = 0;col<COL;col++){
				if(mapArray[row][col].boxClicked(Gdx.input.getX(), 
						600-Gdx.input.getY())){
					if(mapArray[row][col].isDraw()){
						mapArray[row][col].setDraw(false);
					}else{
						mapArray[row][col].setDraw(true);
					}
				}
			}
		}
		
	}

	@Override
	public void update(float delta) {
		handleInput();
		updateClock();
		robot.update();
		checkGround();
		checkOffMap();
		checkExitConfirmation();

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

			stage.clear();
			stage.addActor(exitButtonTable);
			stage.addActor(musicToggleTable);
			stage.addActor(arrowButtonTable);
			stage.addActor(controlButtonTable);
			if(!splashScreen.isSplashScreen()){
				playing = true;
			}

		}
	}
	
	private void updateClock(){
		if(TimeUtils.nanoTime() - lastTimeCheck > frameRate){
			
			//System.out.println("TICK");
			advanceFrame = true;
			robotDrownAnimation.setAdvanceFrame();
			for(Animation a:robotAnimations){
				a.setAdvanceFrame();
			}
			 lastTimeCheck = TimeUtils.nanoTime();
		 }
	}

	private void checkGround(){
	    //Checks each map block object, if the robot is
        //on the map, finish, hole, or coin
		for(int row =0;row<ROW;row++){
			for(int col  = 0;col<COL;col++){
				if(mapArray[row][col].isDraw()){
					if(mapArray[row][col].hasHole()){
						if(checkPathObject(row,col)){
							drownAnimation(robot.getxPos(),robot.getyPos());
							robot.reset();
							resetMapArrows();
						}
					}

					if(mapArray[row][col].hasCoin()){
						if(checkPathObject(row,col)){
							robot.addCoin();
							mapArray[row][col].pickUpCoin();
						}
					}
					if(mapArray[row][col].isFinish()){
						if(checkPathObject(row,col)){
						    setLevelFinishedScreen();
							robot.setLevelFinished();
							playing = false;
							for(Animation a:robotAnimations){
								a.stopLoop();
							}
						}
					}
					
					if(mapArray[row][col].hasArrow()){
							if(checkPathObject(row,col)){
								if(!mapArray[row][col].arrowUsed()){
									if(mapArray[row][col].robotRectContact(
											new Rectangle(robot.getxPos(), robot.getyPos(), robot.getWidth(), robot.getHeight()-35))){
										System.out.println("WE HAVE A MATCHG!");
									}
									robot.changeDirection(mapArray[row][col].getCurrentArrow());
									mapArray[row][col].useArrow();
								}
						}
					}
				}
			}
		}
		
	}

	private void setLevelFinishedScreen(){
	    stage.clear();
	    stage.addActor(exitButtonTable);
	    stage.addActor(musicToggleTable);
	    stage.addActor(nextLevelButtonTable);
    }

	private void drownAnimation(int x, int y){
		if(!robot.isDrowning()){
			robotDrownAnimation.setPos(x, y);
			robotDrownAnimation.start();
			robot.drown();
		}else{
			robot.stopRun();
			robot.reset();
			resetMapArrows();
			for(Animation a:robotAnimations){
				a.stopLoop();
			}
			
		}
	}
	
	private void resetMapArrows(){
		for(int row =0;row<ROW;row++){
			for(int col = 0;col<COL;col++){
				if(mapArray[row][col].hasArrow()){
					mapArray[row][col].resetArrow();
				}
			}
		}
	}
	
	private boolean checkPathObject(int row, int col){	
		for(int i = 0; i<4;i++){
			if(robot.getDirection()==i){
				if(mapArray[row][col].boxClicked(robot.getxPos()+(int)robot.getOffSet(i).x, 
						robot.getyPos()+(int)robot.getOffSet(i).y)){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void render() {
		//Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);		

		drawBackground();

		stage.draw();
		if(exitConfirmation.displayExitConfirmation()){
			drawExitConfirmation();
		}

		if(splashScreen.isSplashScreen()){
			if(!exitConfirmation.displayExitConfirmation())
				drawSplashScreen();
		}

		if(playing){
			drawFullLevelPath();
			drawRobot();
			drawHud();
		}

		if(robot.levelCompleate()){
			drawLevelCompleate();
		}

		if(debug){
			robotDebug();
			mapDebug();
		}

	}

	private void drawBackground(){
		sb.begin();
		sb.draw(blueBackgroundTx,0,0,850,650);
		sb.end();
	}

	private void drawExitConfirmation(){
		sb.begin();
		FontManager.spaceFontLarge.draw(sb,"Exit, Are you sure?",50,450);
		sb.end();
	}

	private void drawRobot(){
		if(!robot.isDrowning()){
			drawRobotAlive();
		}

		if(robot.isDrowning()){
			robotDrownAnimation.advanceFrameNoLoop();
			if(robotDrownAnimation.animating()){
				sb.begin();
				System.out.println("AN");
				sb.draw(robotDrownAnimation.getTexture(),
						robotDrownAnimation.getX(),robotDrownAnimation.getY(),88,88);
				sb.end();
			}
		}

		if(!robotDrownAnimation.animating()){
			robot.stopDrowning();
		}

		if(advanceFrame){
			advanceFrame = false;
		}
	}

	private void drawHud(){
		sb.begin();
		FontManager.hidiSpeedMedium.setColor(1,1,1,1);
		FontManager.hidiSpeedMedium.draw(sb,"Level: "+(levels.getLevel()+1),295,586);
		FontManager.hidiSpeedMedium.draw(sb,""+robot.getCoinCount(),730,375);
		sb.draw(coinTexture,670,326,36,49);
		//fontMedium.draw(sb,"COINS: "+robot.getCoinCount(),350,40);
		
		sb.end();
	}
	
	private void drawLevelCompleate(){
		sb.begin();
		FontManager.spaceFontLarge.draw(sb,"LEVEL COMPLETE",50,400);
		sb.end();
	}

	private void drawLevelEditorPath(){
		sb.begin();
		for(int row =0;row<ROW;row++){
			for(int col = 0;col<COL;col++){
				int x = mapArray[row][col].getxPos();
				int y = mapArray[row][col].getyPos();
				if(mapArray[row][col].isDraw()){
					sb.draw(groundTexture,x,y,64,72);
					if(mapArray[row][col].hasArrow()){
						sb.draw(arrowTx[mapArray[row][col].getCurrentArrow()],
								mapArray[row][col].getxPos(),mapArray[row][col].getyPos(),
								64,64);
					}
				}
				
			}
			
		}
		
		sb.end();
		
	}

	private void drawRobotAlive(){
		int direction = robot.getDirection();
		for(Animation a:robotAnimations){
			a.advanceFrame();
		}

		sb.begin();
		switch(direction){
		case Robot.UP:
			sb.draw(robotUpAnimation.getTextureLoop(),robot.getxPos(),robot.getyPos(),159/3,246/3);
			break;
		case Robot.DOWN:
			sb.draw(robotDownAnimation.getTextureLoop(),robot.getxPos(),robot.getyPos(),159/3,246/3);
			break;
		case Robot.LEFT:
			Sprite sprite = new Sprite(robotRightAnimation.getTextureLoop());
			sprite.flip(true, false);
			sb.draw(sprite,robot.getxPos()+10,robot.getyPos(),80/3,246/3);
			break;
		case Robot.RIGHT:
			Sprite right_sprite = new Sprite(robotRightAnimation.getTextureLoop());
			right_sprite.flip(false, false);
			//sb.draw(robotRightTx,robot.getxPos()+10,robot.getyPos(),80/3,246/3);
			sb.draw(right_sprite,robot.getxPos()+10,robot.getyPos(),80/3,246/3);
			break;
		}
		sb.end();
	}
	
	private void drawSplashScreen(){
	
		sb.begin();

		FontManager.hidiSpeedMedium.setColor(1,1,1,1);
		FontManager.hidiSpeedMedium.draw(sb,"Help the robot make", 185,380);
		FontManager.hidiSpeedMedium.draw(sb,"it to the finish", 265,330);
		FontManager.hidiSpeedMedium.draw(sb,"collect coins along the way", 130,280);

		FontManager.hidiSpeedMedium.draw(sb, "Press SPACE to start",185,180);
		sb.end();
		
	}

	private void drawFullLevelPath(){
		if(!levelEditor){
			mapArray = levels.getMap();
		}
		
		sb.begin();
		for(int row =0;row<ROW;row++){
			for(int col = 0;col<COL;col++){
				int x = mapArray[row][col].getxPos();
				int y = mapArray[row][col].getyPos();
				if(mapArray[row][col].isDraw()){
					sb.draw(groundTexture,x,y,64,72);
					if(mapArray[row][col].hasHole()){
						sb.draw(groundHoleTexture,x,y,64,72);
					}
					if(mapArray[row][col].isFinish()){
						sb.draw(finishLineTexture,mapArray[row][col].getxPos()+10,
								mapArray[row][col].getyPos()+15,
								45,45);
					}
					if(mapArray[row][col].hasArrow()){
						if(!mapArray[row][col].hasHole()){
							if(!mapArray[row][col].arrowUsed()){
								sb.draw(arrowTx[mapArray[row][col].getCurrentArrow()],
										mapArray[row][col].getxPos(),mapArray[row][col].getyPos()+7,
										64,64);
							}
						}
					}
					if(mapArray[row][col].hasCoin()){
						sb.draw(coinTexture,x+20,y+30,25,36);
					}
					if(mapArray[row][col].isStart()){
						sb.draw(startBlockTexture,x,y,64,72);
					}
				}
			}
		}
		
		sb.end();
	}
	
	private void robotDebug(){
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(0, 0, 0, 1);
		shapeRenderer.setAutoShapeType(true);
		shapeRenderer.rect(robot.getxPos(), robot.getyPos(), robot.getWidth(), robot.getHeight()-35);
		
		shapeRenderer.end();
	}
	
	private void mapDebug(){
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(1, 1, 1, 1);
		shapeRenderer.setAutoShapeType(true);
		for(int row =0;row<ROW;row++){
			for(int col = 0;col<COL;col++){
				int x = mapArray[row][col].getxPos();
				int y = mapArray[row][col].getyPos();
				if(mapArray[row][col].isDraw()){
					shapeRenderer.rect(x,y,64,72);
					if(mapArray[row][col].hasArrow()){
						shapeRenderer.rect(mapArray[row][col].getxPos(),mapArray[row][col].getyPos(),
								64,64);
					}
				}
				
			}
		}
		shapeRenderer.end();
	}
	

	@Override
	public void dispose() {
		mainMusic.dispose();
		
	}

}
