package com.demogiuaky.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


public class FlappyBirdDemo extends ApplicationAdapter {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	SpriteBatch batch;
	Texture backgroundTexture;
	Texture messageTexture;


	@Override
	public void create() {
		batch = new SpriteBatch();
		backgroundTexture = new Texture("sprites/background-day.png");
		messageTexture = new Texture("sprites/message.png");
		ScreenUtils.clear(1, 0, 0, 1);
	}

	@Override
	public void render() {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();

		// Draw background with scaling
		batch.draw(backgroundTexture, 0, 0, WIDTH, HEIGHT);

		// Calculate scaling factors for background
		float backgroundScaleX = (float) Gdx.graphics.getWidth() / backgroundTexture.getWidth();

        // Calculate position for message (centered)
		float messageX = (WIDTH - messageTexture.getWidth() * backgroundScaleX) / 2;
		float messageY = (HEIGHT - messageTexture.getHeight() * backgroundScaleX) / 2;

		// Draw message on top of the background with scaling
		batch.draw(messageTexture, messageX, messageY, messageTexture.getWidth() * backgroundScaleX, messageTexture.getHeight() * backgroundScaleX);

		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		backgroundTexture.dispose();
		messageTexture.dispose();
	}
}
