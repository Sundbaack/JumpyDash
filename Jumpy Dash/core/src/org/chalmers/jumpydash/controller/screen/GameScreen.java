package org.chalmers.jumpydash.controller.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.chalmers.jumpydash.controller.*;
import org.chalmers.jumpydash.controller.collision.CollisionListener;
import org.chalmers.jumpydash.physics.Box2D;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.service.IReadFile;
import org.chalmers.jumpydash.service.ReadFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameScreen extends BaseScreen {

    private Stage stage;
    private Stage uiStage;
    private IBox2D box2D;
    private Skin skin;
    private BitmapFont font;

    private Image health3;
    private Image health2;
    private Image health1;
    private Label scoreLabel;
    private Label timeLabel;
    private long startTime;
    private SimpleDateFormat dateFormat;
    private FreeTypeFontGenerator generator;

    //private Box2DDebugRenderer debugRenderer;
    //private Matrix4 debugMatrix;

    public GameScreen(Stage stage, Stage uiStage) {
        this.stage = stage;
        this.uiStage = uiStage;
        this.stage.clear();
        this.uiStage.clear();

        startTime = System.currentTimeMillis();
        dateFormat = new SimpleDateFormat("mm:ss");

        box2D = new Box2D();
        this.stage.setViewport(new ScreenViewport(box2D.getCamera()));

        health3 = new Image(new Texture(Gdx.files.internal("images/health3.png")));
        health3.setPosition(1125, 682);
        health2 = new Image(new Texture(Gdx.files.internal("images/health2.png")));
        health2.setPosition(1125, 682);
        health1 = new Image(new Texture(Gdx.files.internal("images/health1.png")));
        health1.setPosition(1125, 682);
        uiStage.addActor(health3);

        //debugRenderer = new Box2DDebugRenderer();

        // Load the map
        try {
            IReadFile fileHandler = new ReadFile();
            loadMap(fileHandler.fileToArray(new File("levels/level1.txt")));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        box2D.getWorld().setContactListener(new CollisionListener());
        createUI();
    }

    private void createUI() {
        skin = new Skin();

        // Use custom font
         generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 22;
        font = generator.generateFont(parameter);

        skin.add("font", font);

        Label.LabelStyle style = new Label.LabelStyle();
        style.font = skin.getFont("font");

        scoreLabel = new Label("Score", style);
        scoreLabel.setPosition(10, 700);
        scoreLabel.setName("score");
        uiStage.addActor(scoreLabel);

        timeLabel = new Label("Time", style);
        timeLabel.setPosition(612, 700);
        timeLabel.setName("time");
        uiStage.addActor(timeLabel);
    }

    private void loadMap(char[][] level) {

        int mapHeight = level.length;
        int mapWidth = level[0].length;

        // Loop from top to bottom, left to right
        // Create objects, place them in lists and set their positions
        for (int y = 0; y < mapHeight; y++) {

            for (int x = 0; x < mapWidth; x++) {

                if (level[y][x] == 'P') {
                    PlayerController playerController = new PlayerController(box2D, x, y, mapHeight);
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
                    SoldierController soldierController = new SoldierController(box2D, x, y, mapHeight);
                    stage.addActor(soldierController);
                }
                if (level[y][x] == 'A') {
                    AbilityController abilityController =new AbilityController(box2D, x, y, mapHeight);
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
                }/*
                if (level[y][x] == 'B') {
                    CannonController cannonController = new CannonController(box2D, x, y, mapHeight);
                    stage.addActor(cannonController);
                }*/
                if(level[y][x] == '|'){
                    SensorController sensorController = new SensorController(box2D, x, y, mapHeight);
                    stage.addActor(sensorController);
                }
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update box2D simulations and camera
        box2D.update();

        // Update scoreLabel
        scoreLabel.setText("Score: " + PlayerController.getPlayer().getPoints());

        // Update timeLabel
        long elapsedTime = System.currentTimeMillis() - startTime;
        timeLabel.setText(dateFormat.format(new Date(elapsedTime)));

        // Draw health
        if (PlayerController.getPlayer().getHealth() == 2) {
            health3.remove();
            uiStage.addActor(health2);
        } else if (PlayerController.getPlayer().getHealth() == 1) {
            health2.remove();
            uiStage.addActor(health1);
        }

        if (PlayerController.getPlayer().getHealth() == 0) {
            ScreenManager.getInstance().initGameOver(stage, uiStage);
        }

        /*
		// Debugging
		debugMatrix = batch.getProjectionMatrix().cpy().scale(box2D.getPixelsToMeters(), box2D.getPixelsToMeters(), 0);
		debugRenderer.render(box2D.world, debugMatrix);
		*/
    }

    @Override
    public void dispose() {
        skin.dispose();
        font.dispose();
        generator.dispose();
    }
}
