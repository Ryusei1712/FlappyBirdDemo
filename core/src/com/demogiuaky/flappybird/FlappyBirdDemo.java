package com.demogiuaky.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.demogiuaky.flappybird.states.GameStateManager;
import com.demogiuaky.flappybird.states.MenuState;


public class FlappyBirdDemo extends ApplicationAdapter {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	private GameStateManager gameStateManager;

	private SpriteBatch batch;

	public Music music;

	@Override
	public void create() {
		batch = new SpriteBatch();
		gameStateManager = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("assets/audio/backgroundMusic.mp3"));
		music.setLooping(true);
		music.setVolume(0.2f);
		music.play();
		ScreenUtils.clear(1, 0, 0, 1);
		gameStateManager.push(new MenuState(gameStateManager));
	}

	@Override
	public void render() {
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.render(batch);
	}

	@Override
	public void dispose() {
		batch.dispose();
		music.dispose();
	}
}
