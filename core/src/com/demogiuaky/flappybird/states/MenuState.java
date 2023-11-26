package com.demogiuaky.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.demogiuaky.flappybird.FlappyBirdDemo.HEIGHT;
import static com.demogiuaky.flappybird.FlappyBirdDemo.WIDTH;

public class MenuState extends State{
    private Texture backgroundTexture;
    private Texture messageTexture;
    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        backgroundTexture = new Texture("assets/sprites/background-day.png");
        messageTexture = new Texture("assets/sprites/message.png");
        orthographicCamera.setToOrtho(false, WIDTH/2,HEIGHT/2);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)){
            gameStateManager.set(new PlayState(gameStateManager));
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
        spriteBatch.begin();

        // Draw background with scaling
        spriteBatch.draw(backgroundTexture, 0, 0, WIDTH, HEIGHT);

        // Calculate scaling factors for background
        float backgroundScaleX = (float) Gdx.graphics.getWidth() / backgroundTexture.getWidth();

        // Calculate position for message (centered)
        float messageX = (WIDTH - messageTexture.getWidth() * backgroundScaleX) / 2;
        float messageY = (HEIGHT - messageTexture.getHeight() * backgroundScaleX) / 2;

        // Draw message on top of the background with scaling
        spriteBatch.draw(messageTexture, messageX, messageY, messageTexture.getWidth() * backgroundScaleX, messageTexture.getHeight() * backgroundScaleX);

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        messageTexture.dispose();
    }
}
