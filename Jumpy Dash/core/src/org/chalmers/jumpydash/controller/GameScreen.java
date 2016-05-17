package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.chalmers.jumpydash.physics.Box2D;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.service.ReadFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameScreen implements Screen {

    private Game game;
    private Stage stage;
    private Stage uiStage;
    private IBox2D box2D;
    private Skin skin;
    private BitmapFont font;
    private final float tileWidthHeight = 32;

    private Label scoreLabel;
    private Label timeLabel;
    private long startTime;
    private SimpleDateFormat dateFormat;

    private PlayerController playerController;
    //private Box2DDebugRenderer debugRenderer;
    //private Matrix4 debugMatrix;

    public GameScreen(Game game, Stage stage, Stage uiStage) {
        this.game = game;
        this.stage = stage;
        this.uiStage = uiStage;
        startTime = System.currentTimeMillis();
        dateFormat = new SimpleDateFormat("mm:ss");

        box2D = new Box2D(tileWidthHeight);
        this.stage.setViewport(new ScreenViewport(box2D.getCamera()));

        //debugRenderer = new Box2DDebugRenderer();

        // Load the map
        try {
            loadMap(ReadFile.fileToArray(new File("level1.txt")));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        box2D.getWorld().setContactListener(new CollisionListener());
        createUI();
    }

    private void createUI() {
        skin = new Skin();

        // Use custom font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("OpenSans.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 22;
        font = generator.generateFont(parameter);
        generator.dispose();

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
                    playerController = new PlayerController(box2D, x, y, mapHeight);
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

        // Update scoreLabel
        scoreLabel.setText("Score: " + playerController.getPlayer().getPoints());

        // Update timeLabel
        long elapsedTime = System.currentTimeMillis() - startTime;
        timeLabel.setText(dateFormat.format(new Date(elapsedTime)));

        // Gameover
        //game.setScreen(new GameOverScreen(game, stage, uiStage));

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
        skin.dispose();
        font.dispose();
    }
}
