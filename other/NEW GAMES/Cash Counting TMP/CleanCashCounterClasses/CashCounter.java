package com.games.wordgames.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.games.wordgames.gameobjects.Currency;
import com.games.wordgames.gameobjects.Letters;
import com.games.wordgames.gameobjects.PiggyBank;
import com.games.wordgames.handlers.GameStateManager;
import com.games.wordgames.handlers.MyInput;
import static com.games.wordgames.main.Game.V_HEIGHT;

import java.util.ArrayList;
import java.util.Random;

public class CashCounter extends GameState {

    private int finalScore;
    private int score;
    private int mistakes;

    private Random random;

    private Sound boop2;
    private Sound caching;
    private Sound meepmerp;

    private Texture tenCentTexture;
    private Texture fiftyCentTexture;
    private Texture oneRandTexture;
    private Texture twoRandTexture;
    private Texture fiveRandTexture;

    private Texture tenRandTexture;
    private Texture twentyRandTexture;
    private Texture fiftyRandTexture;
    private Texture hundredRandTexture;
    private Texture twoHundredRandTexture;

    private int tenRands = 0, twentyRands = 0, fiftyRands = 0, hundredRands = 0, twoHundredRands = 0;
    private int tenCents = 0, fiftyCents = 0, oneRands = 0, twoRands = 0, fiveRands = 0;

    private double total = 0;

    private double answer = 543.21;
    private double heldAmount = 0;

    boolean actionQueued = false;
    private Texture backGroundTexture;
    /////////////////////////////////
    ///NEW VARIABLES AND OBJECTS/////
    ////////////////////////////////
    
    //This is a currency object that will be set to what ever the last 
    // coint or note was clicked
    private Currency currencyInHand;
    
    //Array of all the currency objects, R0,2 0,5, 1,2,5,10,20,50,100,200
    private ArrayList<Currency> cashArray;
    
    //Could be included in another object Player or CashGame perhaps? 
    private boolean holdCash;
    
    //This object keeps track of the banks location, as well as the amount deposited 
    private PiggyBank bank;

    public CashCounter(GameStateManager gsm) {

        super(gsm);
        this.mistakes = 0;
        this.score = 0;
        stage.clear();
        random = new Random();
        reset();
        
        
        //SOUNDS NOT INCLUDED WHEN YOU UPLOADED< SO I COMMENTED THEM OUT
        //boop2 = Gdx.audio.newSound(Gdx.files.internal("assets/images/cashCounter/sounds/boop2.wav"));
        //caching = Gdx.audio.newSound(Gdx.files.internal("sounds/caching.mp3"));
        //meepmerp = Gdx.audio.newSound(Gdx.files.internal("sounds/meepmerp.mp3"));
       
        //Set textures 
        initTextures();
        
        //Create all currency objects, value, texture, and clickRectangle Location 
        createCurrencyObjects();
        
        //New bank 
        bank = new PiggyBank();
        
        //Cash in hand (could be part of the bank object possibly. 
        holdCash = false;
    }
    
    private void initTextures(){
    	
    	//May need to rename some of these file locations as I changed the main name of the foler 
    	backGroundTexture = new Texture(Gdx.files.internal("images/cashCounter/moneyCounting.png"));

        tenCentTexture = new Texture(Gdx.files.internal("images/cashCounter/10Cents.jpg"));
        fiftyCentTexture = new Texture(Gdx.files.internal("images/cashCounter/50Cents.jpg"));
        oneRandTexture = new Texture(Gdx.files.internal("images/cashCounter/1Rand.jpg"));
        twoRandTexture = new Texture(Gdx.files.internal("images/cashCounter/2Rand.jpg"));
        fiveRandTexture = new Texture(Gdx.files.internal("images/cashCounter/5Rand.png"));

        tenRandTexture = new Texture(Gdx.files.internal("images/cashCounter/ten.jpg"));
        twentyRandTexture = new Texture(Gdx.files.internal("images/cashCounter/twenty.jpg"));
        fiftyRandTexture = new Texture(Gdx.files.internal("images/cashCounter/fifty.jpg"));
        hundredRandTexture = new Texture(Gdx.files.internal("images/cashCounter/hundred.jpg"));
        twoHundredRandTexture = new Texture(Gdx.files.internal("images/cashCounter/twoHundred.jpg"));
        
    }
    private void createCurrencyObjects(){
    	//These are all the different currency objects
    	// If a click takes place in one of their click boxes, then 
    	// the cash in hand object will be set to that, this will include
    	// the value as well as the texture
    	cashArray = new ArrayList<Currency>();
    	cashArray.add(new Currency(5, fiveRandTexture,140,295,55,55 ));
    	cashArray.add(new Currency(2, twoRandTexture,140,225,55,55 ));
    	cashArray.add(new Currency(1, oneRandTexture,140,155,55,55));
    	cashArray.add(new Currency(0.5, fiftyCentTexture,140,80,55,55));
    	cashArray.add(new Currency(0.2, tenCentTexture,140,10,55,55));
    	
    	cashArray.add(new Currency(200, twoHundredRandTexture,10,295,120,55 ));
    	cashArray.add(new Currency(100, hundredRandTexture,10,225,120,55 ));
    	cashArray.add(new Currency(50, fiftyRandTexture,10,155,120,55));
    	cashArray.add(new Currency(20, twentyRandTexture,10,80,120,55));
    	cashArray.add(new Currency(10, tenRandTexture,10,10,120,55));
    	
    	currencyInHand = new Currency(0,fiveRandTexture,0,0,0,0);
    	
    	
    }

    @Override
    public void handleInput() {
    	//KEEP THE RENDER METHODS OUT OF THE HANDLE INPUT!! 
    	// This method is ONLY for handeling input and that only. 
        if (Gdx.input.justTouched()) {
        	//Will drop any money held if clicked 
        	holdCash = false;
        	//Checks to see if a click took place over the bank, and if so, will add the 
        	// value of the cash in hand object to the bank
        	checkPiggyBankDeposit();
        	
        	//Checks if a click took place over any of the currency object click boxes, 
        	// if so, will add that to the cash in hand object 
        	checkCashTouchBoxes();
        	
        }
        
    }
    
    private void checkCashTouchBoxes(){
    	//Go through each Currency click box and check if it contains the mouse X and Y
    
    	for(Currency c: cashArray){
    		if(c.clickBox(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY())){
    			//If it does, we are holding cash, and we set the currencyInHand object to
    			// the coin or note that was just clicked 
    			System.out.println(c.getValue());
    			holdCash = true;
    			currencyInHand = c;
    		}
    	}
    }
    
    private void checkPiggyBankDeposit(){
    	  //if(holdCash){
    		if(bank.clickBox(Gdx.input.getX(),Gdx.graphics.getHeight()- Gdx.input.getY())){
    			//If we click over the bank, we deposit the value from the currency in hand 
    			// then set that currency in hand value to 0 to avoid re adding 
    			//This is a very hacked solution and is no ideal what so ever 
    		
    			bank.deposit(currencyInHand.getValue());
    			System.out.println("BANK BANK");
    			currencyInHand = new Currency();
    	//	}
    	}
    }

    public void reset() {
    	//Not used in this version, missing implementation 
        this.total = 0;
        this.answer = random.nextInt(500) + random.nextInt(9) * 0.1;
    }

    @Override
    public void update(float delta) {
    	//This method will update any game objects 
    	// and call the handleInput
    	// this method is called every "game loop"
        handleInput();
       
        
    }

    @Override
    public void render() {
    	//KEEP THE RENDER METHOD JUST FOCUES ON RENDERING 
    	//MAKE A RENDER METHOD WITH IN THIS METHOD TO BREAK IT UP 
    	//DO NOT PUT INPUT LISTENERES AND OTHER RANDOM GAME OBJECT UPDATE TRIGGERS IN 
    	// THE RENDER METHOD! 
    	// (yes, technically you could, but its bad form and confusing as fuck when you come back 
    	// and read the code at a later stage) 

        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 1, 1, 1);

        sb.begin();
        sb.draw(backGroundTexture, 0, 0,800,600);
        sb.end();
        
        //Commented out, as it is not in use 
        //THE METHOD drawDebugBoxes() just shows you where the click boxes are possitoned in the space
        //drawDebugBoxes();
        
        drawTextHud();
        drawCurrencyInHand();
      
 
    }
    
    private void drawTextHud(){
    	//Draws all the text and "hud" elements 
    	sb.begin();
    	fontMedium.setColor(Color.BLACK);
        fontMedium.draw(sb, "Total: R" + bank.getBalance(), 450, 500);
        fontMedium.draw(sb, "Total Required: R" + answer + "0", 400, 150);

        fontMedium.draw(sb, "Score: " + score, 10, 500);
        fontMedium.draw(sb, "Mistakes: " + mistakes, 10, 450);
        sb.end();
    }
    
    private void drawCurrencyInHand(){
    	//If the player is holding any cash, it will be drawn
    	// currencyInHand is the last coin/note clicked, drawn at the mouse x and y 
    	//If the mouse is clicked, the holdCash will be false, and thus not drawn
    	
    	//NOTE 
    	// If you want the picked up coin or note to be centered, then subtract the
    	// currencyInHands rectangle Width and Height from the render width and height
    	// or maybe you add them? I dunno. But that will draw the coin or note down and to the left
    	// this will make it look like it appears directly ontop and not off to the sided
    	
    	if(holdCash){
    		sb.begin();
    		sb.draw(currencyInHand.getTexture(), Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY(), 
    				currencyInHand.getRect().width, currencyInHand.getRect().height);
    		sb.end();
    		
    	}
    }
    private void drawDebugBoxes(){
    	//This method just draws the boxes for the "toch box" for each coin or note
    	// So, each currency object has a Rectangle, with these peramiters 
    	shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(0, 0, 0, 1);
		shapeRenderer.setAutoShapeType(true);
		//Coin Boxes
		shapeRenderer.rect(140,295,55,55); //R5 coin
		shapeRenderer.rect(140,225,55,55); //R2 coin
		shapeRenderer.rect(140,155,55,55); //R1 coin
		shapeRenderer.rect(140,80,55,55); //R0.5 coin
		shapeRenderer.rect(140,10,55,55); //R0.1 coin
		
		//Note Boxes
		shapeRenderer.rect(10,295,120,55); //R200 note
		shapeRenderer.rect(10,225,120,55); //R100 note
		shapeRenderer.rect(10,155,120,55); //R50 note
		shapeRenderer.rect(10,80,120,55); //R20 note
		shapeRenderer.rect(10,10,120,55); //R10 note
		
		//PiggyBank Rect
		shapeRenderer.rect(400,160,260,260);
		shapeRenderer.end();
    }
    
    public double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @Override
    public void dispose() {
    }
}
