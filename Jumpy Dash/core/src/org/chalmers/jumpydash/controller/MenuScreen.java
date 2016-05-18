package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen implements Screen {

    private Game game;
    private Stage stage;
    private Stage uiStage;
    private Texture menuBackground;
    private Skin skin;
    private Pixmap pixmap;
    private BitmapFont font;
    private TextButton playButton;
    private TextButton quitButton;

    public MenuScreen(Game game, Stage stage, Stage uiStage) {
        this.game = game;
        this.stage = stage;

        this.uiStage = uiStage;
        Gdx.input.setInputProcessor(uiStage);

        menuBackground = new Texture(Gdx.files.internal("menuBackground.png"));
        createUI();
    }

    private void createUI() {
        skin = new Skin();
        pixmap = new Pixmap(250, 75, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(54,52,52,1));
        pixmap.fill();
        skin.add("grey", new Texture(pixmap));

        // Use custom font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("OpenSans.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 18;
        font = generator.generateFont(parameter);
        generator.dispose();

        skin.add("font",font);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("grey", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("grey", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("font");

        skin.add("style", textButtonStyle);

        // Play button
        playButton = new TextButton("Play", textButtonStyle);
        playButton.setPosition(515, 350);
        playButton.setName("playButton");
        uiStage.addActor(playButton);

        playButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // Switch to game
                game.setScreen(new GameScreen(game, stage, uiStage));
            }
        });

        // Quit button
        quitButton = new TextButton("Quit", textButtonStyle);
        quitButton.setPosition(515, 250);
        playButton.setName("quitButton");
        uiStage.addActor(quitButton);

        quitButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // Exit application
                System.exit(0);
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        uiStage.getBatch().begin();
        uiStage.getBatch().draw(menuBackground,0,0);
        uiStage.getBatch().end();

        uiStage.act(delta);
        uiStage.draw();
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
        playButton.remove();
        quitButton.remove();
    }

    @Override
    public void dispose() {
        skin.dispose();
        pixmap.dispose();
        font.dispose();
    }
}