package com.demogiuaky.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.demogiuaky.flappybird.sprites.Bird;
import com.demogiuaky.flappybird.sprites.Tube;


import static com.demogiuaky.flappybird.FlappyBirdDemo.HEIGHT;
import static com.demogiuaky.flappybird.FlappyBirdDemo.WIDTH;

public class PlayState extends State{
    private static final int TUBE_SPACE = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -70;
    private Bird bird;
    private static Array<Tube> tubeArray;
    private Texture backgroundTexture;
    private Texture ground;
    private Vector2 groundPosition1,groundPosition2;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        bird = new Bird(50,200);
        backgroundTexture = new Texture("sprites/background-day.png");
        ground = new Texture("sprites/base.png");
        orthographicCamera.setToOrtho(false, WIDTH/2,HEIGHT/2);

        tubeArray = new Array<Tube>();
        for(int i = 1; i <= TUBE_COUNT; i++){
            tubeArray.add(new Tube(i * (TUBE_SPACE + Tube.TUBE_WIDTH)));
        }
        groundPosition1 = new Vector2(orthographicCamera.position.x - orthographicCamera.viewportWidth/2 , GROUND_Y_OFFSET);
        groundPosition2 = new Vector2((orthographicCamera.position.x - orthographicCamera.viewportWidth/2) + ground.getWidth(),GROUND_Y_OFFSET);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            bird.jump();
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        updateGround();
        bird.update(deltaTime);
        orthographicCamera.position.x = bird.getPosition().x + 80;
        for(Tube tube : tubeArray){
            if(orthographicCamera.position.x - orthographicCamera.viewportWidth/2 > tube.getPositionTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPositionTopTube().x + (TUBE_SPACE + Tube.TUBE_WIDTH)* TUBE_COUNT);
            }
            if(tube.collies(bird.getBounds()))
                gameStateManager.set(new GameOverState(gameStateManager));
        }
        if(bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET)
            gameStateManager.set(new GameOverState(gameStateManager));
        orthographicCamera.update();
    }
    public void updateGround(){
        if(orthographicCamera.position.x - (orthographicCamera.viewportWidth / 2) > groundPosition1.x + ground.getWidth()){
            groundPosition1.add(ground.getWidth() * 2,0);
        }
        if(orthographicCamera.position.x - (orthographicCamera.viewportWidth / 2) > groundPosition2.x + ground.getWidth()){
            groundPosition2.add(ground.getWidth() * 2,0);
        }
    }
    @Override
    public void render(SpriteBatch spriteBatch) {
        ScreenUtils.clear(1, 0, 0, 1);
        spriteBatch.setProjectionMatrix(orthographicCamera.combined);
        spriteBatch.begin();

        spriteBatch.draw(backgroundTexture, orthographicCamera.position.x - orthographicCamera.viewportWidth/2, 0, WIDTH, HEIGHT);
        spriteBatch.draw(bird.getBirdTexture(),bird.getPosition().x,bird.getPosition().y);
        for(Tube tube : tubeArray) {
            spriteBatch.draw(tube.getTopTube(), tube.getPositionTopTube().x, tube.getPositionTopTube().y);
            spriteBatch.draw(tube.getBotTube(), tube.getPositionBotTube().x, tube.getPositionBotTube().y);
        }
        spriteBatch.draw(ground,groundPosition1.x, groundPosition1.y);
        spriteBatch.draw(ground,groundPosition2.x, groundPosition2.y);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        bird.dispose();
        ground.dispose();
        for(Tube tube: tubeArray)
            tube.dispose();
    }
}
