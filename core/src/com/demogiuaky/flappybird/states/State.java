package com.demogiuaky.flappybird.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.Vector;

public abstract class State {
    protected OrthographicCamera orthographicCamera;
    protected Vector3 mouse;
    protected GameStateManager gameStateManager;

    protected State(GameStateManager gameStateManager) {
        this.orthographicCamera = new OrthographicCamera();
        this.mouse = new Vector3();
        this.gameStateManager = gameStateManager;
    }


    protected abstract void handleInput();
    public abstract void update(float deltaTime);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void dispose();
}
