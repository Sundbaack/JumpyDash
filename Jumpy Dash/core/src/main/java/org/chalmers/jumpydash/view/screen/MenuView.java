package main.java.org.chalmers.jumpydash.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuView {

    private Stage uiStage;
    private Texture menuBg;
    private Skin skin;
    private Pixmap pixmap;
    private BitmapFont font;
    private TextButton playButton;
    private TextButton optionButton;
    private TextButton quitButton;

    public MenuView(Stage uiStage) {
        this.uiStage = uiStage;
        menuBg = new Texture(Gdx.files.internal("images/menuBg.png"));

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
        playButton = new TextButton("Play", textButtonStyle);
        playButton.setPosition(515, 350);
        playButton.setName("playButton");
        uiStage.addActor(playButton);

        // Option button
        optionButton = new TextButton("Options", textButtonStyle);
        optionButton.setPosition(515, 250);
        optionButton.setName("optionButton");
        uiStage.addActor(optionButton);

        // Quit button
        quitButton = new TextButton("Quit", textButtonStyle);
        quitButton.setPosition(515, 150);
        playButton.setName("Button");
        uiStage.addActor(quitButton);

    }

    public TextButton getPlayButton() {
        return this.playButton;
    }

    public TextButton getQuitButton() {
        return this.quitButton;
    }

    public TextButton getOptionButton() {
        return this.optionButton;
    }

    public void update() {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        uiStage.getBatch().begin();
        uiStage.getBatch().draw(menuBg, 0, 0);
        uiStage.getBatch().end();
    }

    public void dispose() {
        uiStage.dispose();
        skin.dispose();
        pixmap.dispose();
        font.dispose();
    }
}
