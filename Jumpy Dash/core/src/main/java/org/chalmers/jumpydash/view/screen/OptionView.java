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

/**
 * Created by alexsundback on 2016-05-25.
 */

public class OptionView {

    private Texture menuBg;
    private Stage uiStage;
    private Skin skin;
    private Pixmap pixmap;
    private BitmapFont font;
    private TextButton menuButton;
    private TextButton muteSoundButton;
    private TextButton muteMusicButton;

    public OptionView(Stage uiStage) {
        this.uiStage = uiStage;
        menuBg = new Texture(Gdx.files.internal("images/menuBg.png"));

        loadUI();
    }

    public void loadUI() {
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

        // Menu button
        menuButton = new TextButton("Back to menu", textButtonStyle);
        menuButton.setPosition(515, 350);
        menuButton.setName("menuButton");
        uiStage.addActor(menuButton);

        // Mute sound button
        muteSoundButton = new TextButton("Mute sound", textButtonStyle);
        muteSoundButton.setPosition(515, 250);
        muteSoundButton.setName("muteSoundButton");
        uiStage.addActor(muteSoundButton);

        // Mute music button
        muteMusicButton = new TextButton("Mute music", textButtonStyle);
        muteMusicButton.setPosition(515, 150);
        muteMusicButton.setName("muteMusicButton");
        uiStage.addActor(muteMusicButton);
    }

    public TextButton getMenuButton() {
        return this.menuButton;
    }

    public TextButton getMuteSoundButton() {
        return this.muteSoundButton;
    }

    public TextButton getMuteMusicButton() {
        return this.muteMusicButton;
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
    }
}

