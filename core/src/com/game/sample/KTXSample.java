package com.game.sample;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class KTXSample extends ApplicationAdapter {
    SpriteBatch batch;
    Texture[] birds;

    private int move = 0;

    private OrthographicCamera cam;
    private Texture background;

    private Texture pipeTop;
    private Texture pipeBottom;
    private float maxSizeWidth;
    private float maxSizeHeight;

    private float variation = 0;
    private float speedDrop = 0;

    private Circle birdBody;
    private Rectangle pipeTopBody;
    private Rectangle pipeBottomBody;

    private float startPositionVerticalBird;
    private float startPositionHorizontalBird;

    private float positionMovePipe;
    private float deltaTime;

    private float spaceBetweenPipes;
    private Random randomValue;
    private int randomSpaceBetweenPipes;

    private BitmapFont font;
    private int points = 0;

    private boolean setPoints = false;
    private int gameState = 0; // 0 - game pause | 1 - game running


    @Override
    public void create() {
        batch = new SpriteBatch();
        birds = new Texture[3];
        birds[0] = new Texture("passaro1.png");
        birds[1] = new Texture("passaro2.png");
        birds[2] = new Texture("passaro3.png");
        background = new Texture("fundo.png");

        randomValue = new Random();

        birdBody = new Circle();

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(6);

        pipeTop = new Texture("cano_topo.png");
        pipeBottom = new Texture("cano_baixo.png");

        maxSizeWidth = Gdx.graphics.getWidth();
        maxSizeHeight = Gdx.graphics.getHeight();

        startPositionVerticalBird = maxSizeWidth / 2;
        startPositionHorizontalBird = 100;
        positionMovePipe = maxSizeWidth - 100;

        spaceBetweenPipes = 300;
    }

    @Override
    public void render() {

        deltaTime = Gdx.graphics.getDeltaTime();
        variation += deltaTime * 10;
        if (variation > 2) variation = 0;

        if (gameState == 0) {
            if (Gdx.input.justTouched()) {
                gameState = 1;
            }
        } else {

            ScreenUtils.clear(1, 0, 0, 1);

            positionMovePipe -= deltaTime * 200;

            speedDrop++;

            birdFly();

            if (startPositionVerticalBird > 0 || speedDrop < 0)
                startPositionVerticalBird = startPositionVerticalBird - speedDrop;

            if (positionMovePipe < -pipeTop.getWidth()) {
                positionMovePipe = maxSizeWidth;
                randomSpaceBetweenPipes = randomValue.nextInt(400) - 200;
                setPoints = false;
            }

            if (positionMovePipe < 100) {
                if (!setPoints) {
                    points++;
                    setPoints = true;
                }
            }
        }

        batch.begin();
        batch.draw(background, 0, 0, maxSizeWidth, maxSizeHeight);
        batch.draw(pipeTop, positionMovePipe, maxSizeHeight / 2 + spaceBetweenPipes / 2 + randomSpaceBetweenPipes);
        batch.draw(pipeBottom, positionMovePipe, maxSizeHeight / 2 - pipeBottom.getHeight() - spaceBetweenPipes / 2 + randomSpaceBetweenPipes);
        batch.draw(birds[(int) variation], startPositionHorizontalBird, startPositionVerticalBird);
        font.draw(batch, String.valueOf(points), maxSizeWidth / 2, maxSizeHeight - 100);
        batch.end();

        birdBody.set(startPositionHorizontalBird + birds[0].getWidth() / 2, startPositionVerticalBird + birds[0].getHeight() / 2, 30);
        pipeTopBody = new Rectangle(positionMovePipe, maxSizeHeight / 2 + spaceBetweenPipes / 2 + randomSpaceBetweenPipes, pipeTop.getWidth(), pipeTop.getHeight());
        pipeBottomBody = new Rectangle(positionMovePipe, maxSizeHeight / 2 - pipeBottom.getHeight() - spaceBetweenPipes / 2 + randomSpaceBetweenPipes, pipeBottom.getWidth(), pipeBottom.getHeight());

        if (Intersector.overlaps(birdBody, pipeBottomBody) || Intersector.overlaps(birdBody, pipeTopBody)) {

        }
    }

    public void birdFly() {
        if (Gdx.input.justTouched()) {
            speedDrop = -15;
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
