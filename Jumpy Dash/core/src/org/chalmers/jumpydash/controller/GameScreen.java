package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.chalmers.jumpydash.physics.Box2D;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.service.ReadFile;
import java.io.File;
import java.io.FileNotFoundException;

public class GameScreen implements Screen {

    private Stage stage;
    private IBox2D box2D;
    private final float tileWidthHeight = 32;
    //private Box2DDebugRenderer debugRenderer;
    //private Matrix4 debugMatrix;

    public GameScreen(Stage stage) {
        box2D = new Box2D(tileWidthHeight);
        box2D.getWorld().setContactListener(new CollisionListener(box2D));

        this.stage = stage;
        this.stage.setViewport(new ScreenViewport(box2D.getCamera()));

        //debugRenderer = new Box2DDebugRenderer();

        // Load the map
        try {
            loadMap(ReadFile.read(new File("level1.txt")));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }

    public void loadMap(char[][] level) {

        int mapHeight = level.length;
        int mapWidth = level[0].length;

        // Loop from top to bottom, left to right
        // Create objects, place them in lists and set their positions
        for (int y = 0; y < mapHeight; y++) {

            for (int x = 0; x < mapWidth; x++) {

                if (level[y][x] == 'P') {
                    stage.addActor(new PlayerController(box2D, x, y, mapHeight));

                } else if (level[y][x] == '#') {
                    stage.addActor(new PlatformController(box2D, x, y, mapHeight));

                } else if (level[y][x] == 'C') {
                    stage.addActor(new CoinController(box2D, x, y, mapHeight));

                } else if (level[y][x] == 'S') {
                    stage.addActor(new SoldierController(box2D, x, y, mapHeight));

                } else if (level[y][x] == 'A') {
                    stage.addActor(new AbilityController(box2D, x, y, mapHeight));

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
