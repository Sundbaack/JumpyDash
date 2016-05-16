package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.chalmers.jumpydash.physics.Box2D;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.service.ReadFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {

    private Stage stage;
    private IBox2D box2D;
    private final float tileWidthHeight = 32;

    //private Box2DDebugRenderer debugRenderer;
    //private Matrix4 debugMatrix;

    public GameScreen(Stage stage) {
        box2D = new Box2D(tileWidthHeight);


        this.stage = stage;
        this.stage.setViewport(new ScreenViewport(box2D.getCamera()));

        //debugRenderer = new Box2DDebugRenderer();

        // Load the map
        try {
            loadMap(ReadFile.read(new File("level1.txt")));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        box2D.getWorld().setContactListener(new CollisionListener(stage.getActors()));

    }

    public void loadMap(char[][] level) {

        int mapHeight = level.length;
        int mapWidth = level[0].length;

        // Loop from top to bottom, left to right
        // Create objects, place them in lists and set their positions
        for (int y = 0; y < mapHeight; y++) {

            for (int x = 0; x < mapWidth; x++) {

                if (level[y][x] == 'P') {
                    PlayerController playerController = new PlayerController(box2D,x,y,mapHeight);
                    playerController.setName("player");
                    stage.addActor(playerController);
                } else if (level[y][x] == '#') {
                    PlatformController platformController = new PlatformController(box2D, x, y, mapHeight);
                    platformController.setName("platform");
                    stage.addActor(platformController);
                } else if (level[y][x] == 'C') {
                    CoinController coinController = new CoinController(box2D, x, y, mapHeight);
                    coinController.setName("coin");
                    stage.addActor(coinController);
                } else if (level[y][x] == 'S') {
                    SoldierController soldierController = new SoldierController(box2D, x, y, mapHeight);
                    soldierController.setName("soldier");
                    stage.addActor(soldierController);
                } else if (level[y][x] == 'A') {
                    AbilityController abilityController =new AbilityController(box2D, x, y, mapHeight);
                    abilityController.setName("ability");
                    stage.addActor(abilityController);
                }
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Update box2D simulations and camera
        box2D.update();

		/*
		// Debugging
		debugMatrix = batch.getProjectionMatrix().cpy().scale(box2D.getPixelsToMeters(), box2D.getPixelsToMeters(), 0);
		debugRenderer.render(box2D.world, debugMatrix);
		*/

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
