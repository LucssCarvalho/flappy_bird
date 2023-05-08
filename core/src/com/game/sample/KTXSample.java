package com.game.sample;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

public class KTXSample extends ApplicationAdapter {
    SpriteBatch batch;
    Texture[] birds;

    private Texture background;

    private Texture pipeTop;
    private Texture pipeBottom;

    private Texture gameOver;
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
    private BitmapFont message;
    private int points = 0;

    private boolean setPoints = false;
    private int gameState = 0; // 0 - game pause | 1 - game running | 2 - game over

    //camera
    private OrthographicCamera camera;
    private Viewport viewport;
    private final float VIRTUAL_WIDTH = 768;
    private final float VIRTUAL_HEIGHT = 1024;


    @Override
    public void create() {
        batch = new SpriteBatch();
        birds = new Texture[3];
        birds[0] = new Texture("passaro1.png");
        birds[1] = new Texture("passaro2.png");
        birds[2] = new Texture("passaro3.png");
        background = new Texture("fundo.png");
        gameOver = new Texture("game_over.png");
        pipeTop = new Texture("cano_topo.png");
        pipeBottom = new Texture("cano_baixo.png");


        message = new BitmapFont();
        message.setColor(Color.WHITE);
        message.getData().setScale(3);

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(6);

        randomValue = new Random();
        birdBody = new Circle();

        maxSizeWidth = VIRTUAL_WIDTH;
        maxSizeHeight = VIRTUAL_HEIGHT;

        startPositionVerticalBird = maxSizeWidth / 2;
        startPositionHorizontalBird = 100;
        positionMovePipe = maxSizeWidth - 100;

        spaceBetweenPipes = 300;

        // CAMERA
        camera = new OrthographicCamera();
        camera.position.set(VIRTUAL_WIDTH / 2, VIRTUAL_HEIGHT / 2, 0);
        viewport = new StretchViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);
    }

    @Override
    public void render() {

        camera.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        deltaTime = Gdx.graphics.getDeltaTime();
        variation += deltaTime * 10;
        if (variation > 2) variation = 0;

        if (gameState == 0) {
            if (Gdx.input.justTouched()) {
                gameState = 1;
            }
        } else {
            speedDrop++;

            if (startPositionVerticalBird > 0 || speedDrop < 0)
                startPositionVerticalBird = startPositionVerticalBird - speedDrop;

            if (gameState == 1) {
                ScreenUtils.clear(1, 0, 0, 1);

                positionMovePipe -= deltaTime * 200;

                birdFly();

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
            } else {
                if (Gdx.input.justTouched()) {
                    gameState = 0;
                    points = 0;
                    speedDrop = 0;
                    startPositionVerticalBird = maxSizeWidth / 2;
                    positionMovePipe = maxSizeWidth;
                }
            }

        }

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, 0, 0, maxSizeWidth, maxSizeHeight);
        batch.draw(pipeTop, positionMovePipe, maxSizeHeight / 2 + spaceBetweenPipes / 2 + randomSpaceBetweenPipes);
        batch.draw(pipeBottom, positionMovePipe, maxSizeHeight / 2 - pipeBottom.getHeight() - spaceBetweenPipes / 2 + randomSpaceBetweenPipes);
        batch.draw(birds[(int) variation], startPositionHorizontalBird, startPositionVerticalBird);
        font.draw(batch, String.valueOf(points), maxSizeWidth / 2, maxSizeHeight - 100);

        if (gameState == 2) {
            batch.draw(gameOver, maxSizeWidth / 2 - gameOver.getWidth() / 2, maxSizeHeight / 2);
            message.draw(batch, "Toque para Reiniciar!", maxSizeWidth / 2 - 200, maxSizeHeight / 2 - gameOver.getHeight() / 2);
        }
        batch.end();

        birdBody.set(startPositionHorizontalBird + birds[0].getWidth() / 2, startPositionVerticalBird + birds[0].getHeight() / 2, 30);
        pipeTopBody = new Rectangle(positionMovePipe, maxSizeHeight / 2 + spaceBetweenPipes / 2 + randomSpaceBetweenPipes, pipeTop.getWidth(), pipeTop.getHeight());
        pipeBottomBody = new Rectangle(positionMovePipe, maxSizeHeight / 2 - pipeBottom.getHeight() - spaceBetweenPipes / 2 + randomSpaceBetweenPipes, pipeBottom.getWidth(), pipeBottom.getHeight());

        if (Intersector.overlaps(birdBody, pipeBottomBody) || Intersector.overlaps(birdBody, pipeTopBody) || startPositionVerticalBird <= 0 || startPositionVerticalBird >= maxSizeHeight) {
            gameState = 2;
        }
    }

    public void birdFly() {
        if (Gdx.input.justTouched()) {
            speedDrop = -15;
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
