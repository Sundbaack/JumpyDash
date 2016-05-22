package org.chalmers.jumpydash.controller.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.chalmers.jumpydash.view.screen.MenuView;

public class MenuScreen extends JDScreen {

    private Stage stage;
    private Stage uiStage;
    private Music music;
    private MenuView menuView;

    public MenuScreen(Stage stage, Stage uiStage) {
        this.stage = stage;
        this.uiStage = uiStage;
        this.stage.clear();
        this.uiStage.clear();
        menuView = new MenuView(uiStage);

        Gdx.input.setInputProcessor(uiStage);

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/getlucky.mp3"));
        music.play();
        music.setVolume(0.2f);
        music.setLooping(true);

        setListeners();
    }

    private void setListeners() {
        menuView.getPlayButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // Switch to game
                ScreenManager.getInstance().initGame(stage, uiStage);
            }
        });

        menuView.getQuitButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // Exit application
                System.exit(0);
            }
        });
    }

    @Override
    public void render(float delta) {
        menuView.update();
    }

    @Override
    public void hide() {
        music.stop();
    }

    @Override
    public void dispose() {
        stage.dispose();
        uiStage.dispose();
        music.dispose();
    }
}
