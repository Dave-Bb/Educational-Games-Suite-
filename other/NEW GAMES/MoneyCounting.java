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
import com.games.wordgames.gameobjects.Letters;
import com.games.wordgames.handlers.GameStateManager;
import com.games.wordgames.handlers.MyInput;
import static com.games.wordgames.main.Game.V_HEIGHT;
import java.util.Random;

public class MoneyCounting extends GameState {

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
    private final Texture backGroundTexture;

    public MoneyCounting(GameStateManager gsm) {

        super(gsm);
        this.mistakes = 0;
        this.score = 0;
        stage.clear();
        random = new Random();
        reset();

        boop2 = Gdx.audio.newSound(Gdx.files.internal("sounds/boop2.wav"));
        caching = Gdx.audio.newSound(Gdx.files.internal("sounds/caching.mp3"));
        meepmerp = Gdx.audio.newSound(Gdx.files.internal("sounds/meepmerp.mp3"));

        backGroundTexture = new Texture(Gdx.files.internal("backgrounds/moneyCounting.png"));

        tenCentTexture = new Texture(Gdx.files.internal("currency/10Cents.jpg"));
        fiftyCentTexture = new Texture(Gdx.files.internal("currency/50Cents.jpg"));
        oneRandTexture = new Texture(Gdx.files.internal("currency/1Rand.jpg"));
        twoRandTexture = new Texture(Gdx.files.internal("currency/2Rand.jpg"));
        fiveRandTexture = new Texture(Gdx.files.internal("currency/5Rand.jpg"));

        tenRandTexture = new Texture(Gdx.files.internal("currency/ten.jpg"));
        twentyRandTexture = new Texture(Gdx.files.internal("currency/twenty.jpg"));
        fiftyRandTexture = new Texture(Gdx.files.internal("currency/fifty.jpg"));
        hundredRandTexture = new Texture(Gdx.files.internal("currency/hundred.jpg"));
        twoHundredRandTexture = new Texture(Gdx.files.internal("currency/twoHundred.jpg"));

    }

    @Override
    public void handleInput() {
    }

    public void reset() {
        this.total = 0;
        this.answer = random.nextInt(500) + random.nextInt(9) * 0.1;
    }

    @Override
    public void update(float delta) {
        handleInput();
    }

    @Override
    public void render() {

        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 1, 1, 1);

        sb.begin();
        sb.draw(backGroundTexture, 0, 0);

        int x = (Gdx.input.getX());
        int y = (V_HEIGHT - Gdx.input.getY());

        if (Gdx.input.justTouched()) {

            System.out.println(x);
            System.out.println(y);

            // 10 Rand
            if (x < 190 && x > 10 && y > 10 && y < 100) {
                //sb.draw(tenRandTexture, 10, 10, 180, 90);
                heldAmount = 10;
                System.out.println(heldAmount);
                actionQueued = true;
            }

            // 20 Rand
            if (x < 190 && x > 10 && y > 110 && y < 200) {
                //sb.draw(twentyRandTexture, 10, 110, 180, 90);
                heldAmount = 20;
                System.out.println(heldAmount);
                actionQueued = true;
            }

            // 50 Rand
            if (x < 190 && x > 10 && y > 210 && y < 300) {
                //sb.draw(fiftyRandTexture, 10, 210, 180, 90);
                heldAmount = 50;
                System.out.println(heldAmount);
                actionQueued = true;
            }

            // 100 Rand
            if (x < 190 && x > 10 && y > 310 && y < 400) {
                //sb.draw(hundredRandTexture, 10, 310, 180, 90);
                heldAmount = 100;
                System.out.println(heldAmount);
                actionQueued = true;
            }

            // 200 Rand
            if (x < 190 && x > 10 && y > 410 && y < 500) {
                //sb.draw(twoHundredRandTexture, 10, 410, 180, 90);
                heldAmount = 200;
                System.out.println(heldAmount);
                actionQueued = true;
            }

            // 10 Cents
            if (x < 290 && x > 200 && y > 10 && y < 100) {
                //sb.draw(tenCentTexture, 190, 10, 90, 90);
                heldAmount = 0.1;
                System.out.println(heldAmount);
                actionQueued = true;
            }

            // 50 Cents
            if (x < 290 && x > 200 && y > 110 && y < 200) {
                //sb.draw(fiftyCentTexture, 190, 110, 90, 90);
                heldAmount = 0.5;
                System.out.println(heldAmount);
                actionQueued = true;
            }

            // 1 Rand
            if (x < 290 && x > 200 && y > 210 && y < 300) {
                //sb.draw(oneRandTexture, 190, 210, 90, 90);
                heldAmount = 1;
                System.out.println(heldAmount);
                actionQueued = true;
            }

            // 2 Rand
            if (x < 290 && x > 200 && y > 310 && y < 400) {
                //sb.draw(twoRandTexture, 190, 310, 90, 90);
                heldAmount = 2;
                System.out.println(heldAmount);
                actionQueued = true;
            }

            // 5 Rand
            if (x < 290 && x > 200 && y > 410 && y < 500) {
                //sb.draw(fiveRandTexture, 190, 410, 90, 90);
                heldAmount = 5;
                System.out.println(heldAmount);
                actionQueued = true;
            }

            if (Gdx.input.justTouched()) {

                System.out.println("Just touched");

                // Wallet
                if (x < 500 && x > 300 && y > 10 && y < 100) {
                    //sb.draw(tenRandTexture, 10, 10, 180, 90);
                    total = total + heldAmount;
                }

            }

        }

        if (Gdx.input.isTouched() && heldAmount != 0) {
            // 10 Rand
            if (heldAmount == 10) {
                sb.draw(tenRandTexture, x, y, 180, 90);
            }

            // 20 Rand
            if (heldAmount == 20) {
                sb.draw(twentyRandTexture, x, y, 180, 90);
            }

            // 50 Rand
            if (heldAmount == 50) {
                sb.draw(fiftyRandTexture, x, y, 180, 90);
            }

            // 100 Rand
            if (heldAmount == 100) {
                sb.draw(hundredRandTexture, x, y, 180, 90);
            }

            // 200 Rand
            if (heldAmount == 200) {
                sb.draw(twoHundredRandTexture, x, y, 180, 90);
            }

            // 10 Cents
            if (heldAmount == 0.1) {
                sb.draw(tenCentTexture, x, y, 90, 90);

            }

            // 50 Cents
            if (heldAmount == 0.5) {
                sb.draw(fiftyCentTexture, x, y, 90, 90);
            }

            // 1 Rand
            if (heldAmount == 1) {
                sb.draw(oneRandTexture, x, y, 90, 90);
            }

            // 2 Rand
            if (heldAmount == 2) {
                sb.draw(twoRandTexture, x, y, 90, 90);
            }

            // 5 Rand
            if (heldAmount == 5) {
                sb.draw(fiveRandTexture, x, y, 90, 90);

            }
        }

        if (actionQueued && !Gdx.input.isTouched()) {

            if (x > 400) {
                total = total + heldAmount;
                caching.play();
            }

            heldAmount = 0;
            actionQueued = false;
        }

        total = round(total, 2);
        if (total == answer) {
            System.out.println("Yes");
            score++;
            reset();
            boop2.play();
        } else {

            if (total > answer) {
                System.out.println("Oops, you've made a mistake");
                mistakes++;
                reset();
                meepmerp.play();
            }
        }
        
        if (score + mistakes == 10) {
            finalScore = score;
        }

        fontMedium.setColor(Color.BLACK);
        fontMedium.draw(sb, "Total: R" + total + "0", 570, 610);
        fontMedium.draw(sb, "Total Required: R" + answer + "0", 570, 220);

        fontMedium.draw(sb, "Score: " + score, 10, (Gdx.graphics.getHeight()) - 5 - (11 * 25));
        fontMedium.draw(sb, "Mistakes: " + mistakes, 10, (Gdx.graphics.getHeight()) - 5 - (12 * 25));

        sb.draw(tenRandTexture, 10, 10, 180, 90);
        sb.draw(tenCentTexture, 190, 10, 90, 90);

        sb.draw(twentyRandTexture, 10, 110, 180, 90);
        sb.draw(fiftyCentTexture, 190, 110, 90, 90);

        sb.draw(fiftyRandTexture, 10, 210, 180, 90);
        sb.draw(oneRandTexture, 190, 210, 90, 90);

        sb.draw(hundredRandTexture, 10, 310, 180, 90);
        sb.draw(twoRandTexture, 190, 310, 90, 90);

        sb.draw(twoHundredRandTexture, 10, 410, 180, 90);
        sb.draw(fiveRandTexture, 190, 410, 90, 90);

        sb.end();
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
