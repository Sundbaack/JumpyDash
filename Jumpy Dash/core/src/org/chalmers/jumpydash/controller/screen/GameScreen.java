package org.chalmers.jumpydash.controller.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.chalmers.jumpydash.controller.collision.CollisionListener;
import org.chalmers.jumpydash.physics.Box2D;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.service.IReadFile;
import org.chalmers.jumpydash.service.ReadFile;
import org.chalmers.jumpydash.controller.*;
import org.chalmers.jumpydash.view.screen.GameView;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class GameScreen extends JDScreen {

    private Stage stage;
    private Stage uiStage;
    private IBox2D box2D;
    private PlayerController playerController;
    private List<SoldierController> soldierControllerList = new ArrayList<SoldierController>();
    private GameView gameView;

    public GameScreen(Stage stage, Stage uiStage) {
        this.stage = stage;
        this.uiStage = uiStage;
        this.stage.clear();
        this.uiStage.clear();
        this.gameView = new GameView(uiStage);

        box2D = new Box2D();
        this.stage.setViewport(new ScreenViewport(box2D.getCamera()));

        // Load the map
        try {
            IReadFile fileHandler = new ReadFile();
            loadMap(fileHandler.fileToArray(new File("levels/level1.txt")));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        // Set contact listener
        //box2D.getWorld().setContactListener(new CollisionListener(playerController));

    }

    private void loadMap(char[][] level) {

        int mapHeight = level.length;
        int mapWidth = level[0].length;

        // Loop from top to bottom, left to right
        // Create objects, place them in lists and set their positions
        for (int y = 0; y < mapHeight; y++) {

            for (int x = 0; x < mapWidth; x++) {

                if (level[y][x] == 'P') {
                    playerController = new PlayerController(box2D, x, y, mapHeight);
                    playerController.setName("playerController");
                    stage.addActor(playerController);
                }
                if (level[y][x] == '#') {
                    PlatformController platformController = new PlatformController(box2D, x, y, mapHeight);
                    stage.addActor(platformController);
                }
                if(level[y][x] == 'M'){
                    MovingPlatformController movingPlatformController = new MovingPlatformController(box2D,x,y,mapHeight);
                    stage.addActor(movingPlatformController);
                }
                if (level[y][x] == 'C') {
                    CoinController coinController = new CoinController(box2D, x, y, mapHeight);
                    stage.addActor(coinController);
                }
                if (level[y][x] == 'S') {
                    SoldierController soldierController = new SoldierController(box2D, x, y, mapHeight, soldierControllerList.size());
                    soldierControllerList.add(soldierController);
                    stage.addActor(soldierController);
                }
                if (level[y][x] == 'A') {
                    AbilityController abilityController = new AbilityController(box2D, x, y, mapHeight);
                    stage.addActor(abilityController);
                }
                if (level[y][x] == 'T') {
                    TrampolineController trampolineController = new TrampolineController(box2D, x, y, mapHeight);
                    stage.addActor(trampolineController);
                }
                if (level[y][x] == 'O') {
                    SpikeController spikeController = new SpikeController(box2D, x, y, mapHeight);
                    stage.addActor(spikeController);
                }
                if (level[y][x] == 'U') {
                    SpeedUpController speedUpController = new SpeedUpController(box2D, x, y, mapHeight);
                    stage.addActor(speedUpController);
                }
                if (level[y][x] == 'B') {
                    CannonController cannonController = new CannonController(box2D, x, y, mapHeight);
                    stage.addActor(cannonController);
                }
                if(level[y][x] == '|'){
                    SensorController sensorController = new SensorController(box2D, x, y, mapHeight,"soldier");
                    stage.addActor(sensorController);
                }
                if(level[y][x] == 's'){
                    SensorController sensorController = new SensorController(box2D, x, y, mapHeight,"player");
                    stage.addActor(sensorController);

                }
            }
        }
        addCollisionListener();
    }

    private void addCollisionListener(){
        box2D.getWorld().setContactListener(new CollisionListener(playerController,soldierControllerList));
    }

    private void handleInput() {
        if (!ScreenManager.getInstance().isPaused()) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
                ScreenManager.getInstance().initPause(stage, uiStage);
            }
        }
    }

    @Override
    public void render(float delta) {
        handleInput();

        if (!ScreenManager.getInstance().isPaused()) {
            // Update box2D simulations and camera

            box2D.update();

            // Update gameView
            gameView.update(Math.round(box2D.getCamera().position.x), playerController.getPlayer().getPoints(), playerController.getPlayer().getHealth());

            // Gameover
            if (playerController.getPlayer().isDead()) {
                ScreenManager.getInstance().initGameOver(stage, uiStage);
            }
        }

    }

    @Override
    public void dispose() {
        stage.dispose();
        uiStage.dispose();
    }
}