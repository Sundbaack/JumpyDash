package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen implements Screen {

    private Game game;
    private Stage stage;
    private Texture menuBackground;
    private Skin skin;
    private Pixmap pixmap;
    private BitmapFont font;
    private TextButton playButton;
    private TextButton quitButton;

    public MenuScreen(Game game, Stage stage) {
        this.game = game;
        this.stage = stage;
        menuBackground = new Texture(Gdx.files.internal("menuBackground.png"));
        createUI();
    }

    public void createUI() {

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
        stage.addActor(playButton);

        playButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // Switch to game
                game.setScreen(new GameScreen(stage));
            }
        });

        // Quit button
        quitButton = new TextButton("Quit", textButtonStyle);
        quitButton.setPosition(515, 250);
        stage.addActor(quitButton);

        quitButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.getBatch().begin();
        stage.getBatch().draw(menuBackground,0,0);
        stage.getBatch().end();
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
