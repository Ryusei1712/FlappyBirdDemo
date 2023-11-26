package com.demogiuaky.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;
import com.badlogic.gdx.math.Rectangle;
public class Tube {
    public static final int FLUCTUATION = 130;
    public static final int TUBE_GAP = 100;
    public static final int TUBE_WIDTH = 52;
    public static final int LOWEST_OPENING = 120;

    private Texture topTube, botTube;
    private Vector2 positionTopTube, positionBotTube;
    private Random random;
    private Rectangle boundsTop, boundsBot;
    public Tube(float x){
        topTube = new Texture("assets/sprites/topTube.png");
        botTube = new Texture("assets/sprites/botTube.png");
        random = new Random();

        positionTopTube = new Vector2(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionBotTube = new Vector2(x, positionTopTube.y - TUBE_GAP - botTube.getHeight());

        boundsTop = new Rectangle((int) positionTopTube.x, (int) positionTopTube.y, topTube.getWidth(),topTube.getHeight());
        boundsBot = new Rectangle((int) positionBotTube.x, (int) positionBotTube.y, botTube.getWidth(),botTube.getHeight());

    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBotTube() {
        return botTube;
    }

    public Vector2 getPositionTopTube() {
        return positionTopTube;
    }

    public Vector2 getPositionBotTube() {
        return positionBotTube;
    }

    public void reposition(float x){
        positionTopTube.set(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionBotTube.set(x, positionTopTube.y - TUBE_GAP - botTube.getHeight());
        boundsTop.setPosition(positionTopTube.x,positionTopTube.y);
        boundsBot.setPosition(positionBotTube.x, positionBotTube.y);
    }

    public boolean collies(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public void dispose() { topTube.dispose();
        botTube.dispose();
    }
}
