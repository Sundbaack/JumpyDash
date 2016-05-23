package org.chalmers.jumpydash.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class GameOverView {

    private Stage uiStage;
    private Texture gameOverBg;
    private Skin skin;
    private Pixmap pixmap;
    private Label scoreLabel;
    private BitmapFont font;
    private TextButton playButton;
    private TextButton menuButton;
    private int points;

    public GameOverView(Stage uiStage, int points) {
        this.uiStage = uiStage;
        this.points = points;
        gameOverBg = new Texture(Gdx.files.internal("images/gameOverBg.png"));

        loadUI();
    }

    private void loadUI() {
        skin = new Skin();
        pixmap = new Pixmap(250, 75, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(54,52,52,1));
        pixmap.fill();
        skin.add("grey", new Texture(pixmap));

        // Use custom font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 22;
        font = generator.generateFont(parameter);
        generator.dispose();

        skin.add("font",font);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("grey", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("grey", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("font");

        skin.add("style", textButtonStyle);

        // Play button
        playButton = new TextButton("Play again", textButtonStyle);
        playButton.setPosition(515, 350);
        playButton.setName("playButton");
        uiStage.addActor(playButton);

        // Menu button
        menuButton = new TextButton("Menu", textButtonStyle);
        menuButton.setPosition(515, 250);
        menuButton.setName("menuButton");
        uiStage.addActor(menuButton);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = Color.DARK_GRAY;
        style.font = skin.getFont("font");

        scoreLabel = new Label("Your score: " + points, style);
        scoreLabel.setPosition(576, 450);
        scoreLabel.setName("score");
        uiStage.addActor(scoreLabel);
    }

    public TextButton getPlayButton() {
        return this.playButton;
    }

    public TextButton getMenuButton() {
        return this.menuButton;
    }

    public void update() {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        uiStage.getBatch().begin();
        uiStage.getBatch().draw(gameOverBg, 0, 0);
        uiStage.getBatch().end();
    }

    public void dispose() {
        skin.dispose();
        pixmap.dispose();
        font.dispose();
        uiStage.dispose();
    }
}
