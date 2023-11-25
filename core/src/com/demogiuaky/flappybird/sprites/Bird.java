package com.demogiuaky.flappybird.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Rectangle;


public class Bird {
    private static final int GRAVITY = -15;
    public static final int MOVEMENT = 100;

    private Vector3 position;
    private Vector3 velocity;
    private Texture[] birdTextures;
    private int currentFrame;
    private float frameTime;
    private float frameDuration = 0.2f;
    private Rectangle bounds;
    private Sound wing;

    public Bird(int x,int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        String[] birdTexturePaths = {
                "sprites/yellowbird-upflap.png",
                "sprites/yellowbird-midflap.png",
                "sprites/yellowbird-downflap.png"
        };
        birdTextures = new Texture[birdTexturePaths.length];
        for (int i = 0; i < birdTexturePaths.length; i++) {
            birdTextures[i] = new Texture(birdTexturePaths[i]);
        }
        currentFrame = 0;
        frameTime = 0;
        bounds = new Rectangle(x, y, birdTextures[0].getWidth(), birdTextures[0].getHeight());
        wing = Gdx.audio.newSound(Gdx.files.internal("audio/wing.wav"));
    }

    public void update(float deltaTime){
        //Do not use else position.y = 0 here, I tried it lmao. -ken-
        if(position.y > 0)
            velocity.add(0,GRAVITY,0);

        velocity.scl(deltaTime);
        position.add(MOVEMENT*deltaTime,velocity.y,0);

        if (position.y < 0)
            position.y = 0;

        velocity.scl(1/deltaTime);
        bounds.setPosition(position.x,position.y);

        frameTime += deltaTime;
        if (frameTime > frameDuration) {
            frameTime = 0;
            currentFrame = (currentFrame + 1) % birdTextures.length;
        }
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBirdTexture() {
        return birdTextures[currentFrame];
    }
    public void jump(){
        velocity.y = 250;
        wing.play(0.5f);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        for (Texture texture : birdTextures) {
            texture.dispose();
        }
        wing.dispose();
    }

}
