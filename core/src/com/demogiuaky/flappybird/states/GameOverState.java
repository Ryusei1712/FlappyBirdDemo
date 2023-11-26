package com.demogiuaky.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;



import static com.demogiuaky.flappybird.FlappyBirdDemo.HEIGHT;
import static com.demogiuaky.flappybird.FlappyBirdDemo.WIDTH;

public class GameOverState extends State{
    private Texture backgroundTexture;
    private Texture gameOverTexture;


    public GameOverState(GameStateManager gameStateManager) {
        super(gameStateManager);
        backgroundTexture = new Texture("assets/sprites/background-day.png");
        gameOverTexture = new Texture("assets/sprites/gameover.png");
        orthographicCamera.setToOrtho(false, WIDTH,HEIGHT);

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)){
            gameStateManager.set(new MenuState(gameStateManager));
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();

        orthographicCamera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        ScreenUtils.clear(1, 0, 0, 1);
        spriteBatch.setProjectionMatrix(orthographicCamera.combined);
        spriteBatch.begin();

        spriteBatch.draw(backgroundTexture, orthographicCamera.position.x - orthographicCamera.viewportWidth/2, 0, WIDTH, HEIGHT);
        // Calculate scaling factors for background
        float backgroundScaleX = (float) Gdx.graphics.getWidth() / backgroundTexture.getWidth();

        // Calculate position for message (centered)
        float messageX = (WIDTH - gameOverTexture.getWidth() * backgroundScaleX) / 2;
        float messageY = (HEIGHT - gameOverTexture.getHeight() * backgroundScaleX) / 2;

        // Draw message on top of the background with scaling
        spriteBatch.draw(gameOverTexture, messageX, messageY, gameOverTexture.getWidth() * backgroundScaleX, gameOverTexture.getHeight() * backgroundScaleX);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        gameOverTexture.dispose();
    }
}
