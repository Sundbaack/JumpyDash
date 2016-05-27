package main.java.org.chalmers.jumpydash.jumpydash.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import main.java.org.chalmers.jumpydash.jumpydash.physics.Box2D;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GameView {

    private Stage uiStage;
    private Skin skin;
    private BitmapFont font;
    private Texture background;

    private Image health3;
    private Image health2;
    private Image health1;
    private Label scoreLabel;
    private Label timeLabel;
    private long startTime;
    private SimpleDateFormat dateFormat;
    private FreeTypeFontGenerator generator;

    public GameView(Stage uiStage) {
        this.uiStage = uiStage;

        health3 = new Image(new Texture(Gdx.files.internal("images/health3.png")));
        health3.setPosition(1125, 682);
        health2 = new Image(new Texture(Gdx.files.internal("images/health2.png")));
        health2.setPosition(1125, 682);
        health1 = new Image(new Texture(Gdx.files.internal("images/health1.png")));
        health1.setPosition(1125, 682);
        uiStage.addActor(health3);

        background = new Texture(Gdx.files.internal("images/bg.png"));
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        loadUI();
    }

    private void loadUI() {
        startTime = System.currentTimeMillis();
        dateFormat = new SimpleDateFormat("mm:ss");

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

    private void setScoreLabelText(int points) {
        scoreLabel.setText("Score: " + points);
    }

    public void update(int cameraPosX, int points, int health) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw background
        uiStage.getBatch().begin();
        uiStage.getBatch().draw(background, 0, 0, cameraPosX, 0, Box2D.SCREEN_WIDTH, Box2D.SCREEN_HEIGHT);
        uiStage.getBatch().end();

        // Update score label
        setScoreLabelText(points);
        if(health == 3){
            uiStage.addActor(health3);
        }
        // Update health bar
        if (health == 2) {
            health3.remove();
            uiStage.addActor(health2);
        } else if (health == 1) {
            health2.remove();
            uiStage.addActor(health1);
        }

        // Update time label
        long elapsedTime = System.currentTimeMillis() - startTime;
        timeLabel.setText(dateFormat.format(new Date(elapsedTime)));
    }


    public void dispose() {
        background.dispose();
        uiStage.dispose();
        skin.dispose();
        font.dispose();
        generator.dispose();
    }
}
