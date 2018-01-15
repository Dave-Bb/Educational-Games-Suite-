package com.games.wordgames.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.games.wordgames.handlers.GameStateManager;
import static com.games.wordgames.handlers.GameStateManager.MEMORY_GAME;
import com.games.wordgames.handlers.MyInput;
import static com.games.wordgames.main.Game.V_HEIGHT;
import static com.games.wordgames.main.Game.V_WIDTH;
import java.util.Random;

// A card matching game
public class MemoryGame extends GameState {

    // Must be even NB****
    private int finalScore;
    
    private int difficultyInput = 2;
    private int difficulty = difficultyInput * 2;
    private int moves = 0;

    private int[][] board = new int[difficulty][difficulty];
    private int[][] complete = new int[difficulty][difficulty];
    private int[][] active = new int[difficulty][difficulty];

    private int activeCard = -1;
    private int activeCardX = -1;
    private int activeCardY = -1;

    private int previousCard = -1;
    private int previousCardX = -1;
    private int previousCardY = -1;

    private int activeCount = 1;
    private int cardVariations = difficulty * difficulty / 2;

    private Texture background;   // 0 

    //Textures for cards to be matched
    private Texture[] textures = new Texture[100];
    private Texture cardBack;   // 0 
    private Texture bubble;     // 1
    private Texture fourStar;   // 2 
    private Texture fiveStar;   // 3 
    private Texture heart;      // 4 
    private Texture diamond;    // 5 
    private Texture hexagon;    // 6 
    private Texture lightning;  // 7 
    private Texture arrow;      // 8 

    private Texture one;        //
    private Texture two;        //
    private Texture three;      //
    private Texture four;       //
    private Texture five;       //
    private Texture six;        // 
    private Texture seven;      //
    private Texture eight;      //
    private Texture nine;       //
    private Texture zero;       //

    private Sound boop1;        //
    private Sound boop2;        //

    //Fields that represent the size of the cards
    private int cardWidth;
    private int cardHeight;

    private int victoryCount = 0;
    Random rand = new Random();
    private int turnsTaken = 0;

    public MemoryGame(GameStateManager gsm) {
        super(gsm);

        cardWidth = (V_WIDTH - (difficulty + 1) * 10) / (difficulty);
        cardHeight = (V_HEIGHT - 100 - (difficulty + 1) * 10) / (difficulty);

        System.out.println(cardWidth);
        System.out.println(cardHeight);

        // Assigning sounds
        boop1 = Gdx.audio.newSound(Gdx.files.internal("sounds/boop1.wav"));
        boop2 = Gdx.audio.newSound(Gdx.files.internal("sounds/boop2.wav"));

        background = new Texture(Gdx.files.internal("backgrounds/images.jpg"));

        //Assigning images to cards
        cardBack = new Texture(Gdx.files.internal("cardbacks/cardback.png"));
        bubble = new Texture(Gdx.files.internal("cardbacks/bubble.png"));
        fourStar = new Texture(Gdx.files.internal("cardbacks/4star.png"));
        fiveStar = new Texture(Gdx.files.internal("cardbacks/5star.png"));
        heart = new Texture(Gdx.files.internal("cardbacks/heart.png"));
        diamond = new Texture(Gdx.files.internal("cardbacks/diamond.png"));
        hexagon = new Texture(Gdx.files.internal("cardbacks/hexagon.png"));
        lightning = new Texture(Gdx.files.internal("cardbacks/lightning.png"));
        arrow = new Texture(Gdx.files.internal("cardbacks/arrow.png"));

        one = new Texture(Gdx.files.internal("cardbacks/numbers/1.png"));
        two = new Texture(Gdx.files.internal("cardbacks/numbers/2.png"));
        three = new Texture(Gdx.files.internal("cardbacks/numbers/3.png"));
        four = new Texture(Gdx.files.internal("cardbacks/numbers/4.png"));
        five = new Texture(Gdx.files.internal("cardbacks/numbers/5.png"));
        six = new Texture(Gdx.files.internal("cardbacks/numbers/6.png"));
        seven = new Texture(Gdx.files.internal("cardbacks/numbers/7.png"));
        eight = new Texture(Gdx.files.internal("cardbacks/numbers/8.png"));
        nine = new Texture(Gdx.files.internal("cardbacks/numbers/9.png"));
        zero = new Texture(Gdx.files.internal("cardbacks/numbers/0.png"));

        // Adding textures to array
        textures[0] = cardBack;
        textures[1] = bubble;
        textures[2] = fourStar;
        textures[3] = fiveStar;
        textures[4] = heart;
        textures[5] = diamond;
        textures[6] = hexagon;
        textures[7] = lightning;
        textures[8] = arrow;

        textures[9] = one;
        textures[10] = two;
        textures[11] = three;
        textures[12] = four;
        textures[13] = five;
        textures[14] = six;
        textures[15] = seven;
        textures[16] = eight;
        textures[17] = nine;
        textures[18] = zero;

        //Populating the memory game
        int count = 1;
        for (int i = 0; i < difficulty; i++) {
            for (int j = 0; j < difficulty; j++) {

                boolean isPlaced = false;
                while (isPlaced == false) {

                    int tmpX = rand.nextInt(difficulty);
                    int tmpY = rand.nextInt(difficulty);

                    if (board[tmpY][tmpX] == 0) {
                        board[tmpY][tmpX] = count;
                        count++;
                        //  System.out.println(count - 1 + " " + tmpX + " " + tmpY);

                        isPlaced = true;
                    }

                }

                complete[i][j] = 0;
                active[i][j] = 0;

                if (count == cardVariations + 1) {
                    count = 1;
                }
            }

            rand.nextInt(difficulty);

        }
        System.out.println("");
        System.out.println("");
        System.out.println("Populated");
    }

    @Override
    public void handleInput() {

        //Need to confirm with dave how he handles input
        if (Gdx.input.justTouched()) {

            active[getCursorHeightBlock()][getCursorWidthBlock()] = 1;

            if (activeCount == 1) {
                activeCard = board[getCursorHeightBlock()][getCursorWidthBlock()];
                activeCardX = getCursorWidthBlock();
                activeCardY = getCursorHeightBlock();
            }

            if (activeCount == 2) {

                // Set adding card stats to previous card stats for card comparison
                previousCard = activeCard;
                previousCardX = activeCardX;
                previousCardY = activeCardY;

                //Get new card
                activeCard = board[getCursorHeightBlock()][getCursorWidthBlock()];
                activeCardX = getCursorWidthBlock();
                activeCardY = getCursorHeightBlock();

                //Compare cards
                if (previousCard == activeCard) {
                    //Confirm that it is not the same board position
                    if (!(previousCardX == activeCardX && previousCardY == activeCardY)) {

                        //Confirm that the card has not been matched already
                        if (complete[activeCardY][activeCardX] == 0) {
                            System.out.println("Match");
                            boop1.play();
                            victoryCount++;
                        }

                        //Add the matched card to the complete matrix for allow the renderer to show the matched cards
                        complete[activeCardY][activeCardX] = 1;
                        complete[previousCardY][previousCardX] = 1;
                        activeCount++;
                    }
                }
            }

            // reset active cards
            if (activeCount == 3) {
                
                turnsTaken++;
                
                activeCard = -1;
                activeCardX = -1;
                activeCardY = -1;

                activeCount = 0;
                for (int i = 0; i < difficulty; i++) {
                    for (int j = 0; j < difficulty; j++) {
                        active[i][j] = 0;
                    }
                }
            }

            //Confirm an action has been taken
            activeCount++;

            // Victory condition check
            // if the amout of matches is = the amount of different cards in the set then you win
            if (victoryCount == cardVariations) {
                System.out.println("You Win!");
                boop2.play();
                finalScore = 11 - (turnsTaken/(difficulty * 4));
                GameStateManager.increaseMemoryGameScore();
                System.out.println(finalScore);
                System.out.println(finalScore);
                System.out.println(finalScore);
                System.out.println(finalScore);
                System.out.println(finalScore);
                System.out.println(finalScore);
                
                System.out.println(gsm.getMemoryGameScore());
                gsm.setState(MEMORY_GAME);
            }

        }

        // Return to main menu
        if (MyInput.isPressed(MyInput.ESCAPE)) {
            gsm.setState(GameStateManager.WORD_GAME_MENU);
        }

    }

    // Return an array index based on pointer co-ordiantes
    public int getCursorHeightBlock() {
        return ((difficulty - 1) - (Gdx.input.getY() - 110) / ((V_HEIGHT - 110) / difficulty));
    }

    // Return an array index based on pointer co-ordiantes
    public int getCursorWidthBlock() {
        return ((Gdx.input.getX() - 20) / ((V_WIDTH - 10) / difficulty));
    }

    @Override
    public void update(float delta) {
        handleInput();
    }

    // Return the required texture from the textures array based on board co-ordiantes
    public Texture getBoardTexture(int x, int y) {
        int z = board[y][x];
        return textures[z];
    }

    @Override
    public void render() {

        //Refresh to white
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        sb.draw(background, 0, 0, V_WIDTH, V_HEIGHT);
        fontMedium.setColor(Color.BLACK);
        fontMedium.draw(sb, "Score: " + gsm.memoryGameScore, 10, (Gdx.graphics.getHeight()) - 10);
        fontMedium.draw(sb, "Turns: " + turnsTaken, 10, (Gdx.graphics.getHeight()) - 45);

        // Render the board
        for (int i = 0; i < difficulty; i++) {
            for (int j = 0; j < difficulty; j++) {

                if (active[i][j] == 1 || complete[i][j] == 1) {
                    // render the cards if they are part of the active or completed board
                    sb.draw(getBoardTexture(j, i), 10 + j * cardWidth + j * 10, i * cardHeight + i * 10, cardWidth, cardHeight);
                } else {
                    // if the the block is not part of the active baord or the completed board render the card back
                    sb.draw(textures[0], 10 + j * cardWidth + j * 10, i * cardHeight + i * 10, cardWidth, cardHeight);
                }
            }
        }

        sb.end();

    }

    @Override
    public void dispose() {

    }

}
