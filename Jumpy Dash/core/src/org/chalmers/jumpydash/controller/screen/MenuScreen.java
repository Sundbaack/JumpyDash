package org.chalmers.jumpydash.controller.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.chalmers.jumpydash.view.screen.MenuView;

public class MenuScreen extends JDScreen {

    private Stage stage;
    private Stage uiStage;
    private MenuView menuView;

    public MenuScreen(Stage stage, Stage uiStage) {
        this.stage = stage;
        this.uiStage = uiStage;
        this.stage.clear();
        this.uiStage.clear();
        menuView = new MenuView(uiStage);

        Gdx.input.setInputProcessor(uiStage);

        setListeners();
    }

    private void setListeners() {
        menuView.getPlayButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // Switch to game
                ScreenManager.getInstance().initGame(stage, uiStage);
            }
        });

        menuView.getOptionButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // Switch to options
                ScreenManager.getInstance().initOption(stage, uiStage);
            }
        });

        menuView.getQuitButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // Exit application
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float delta) {
        menuView.update();
    }

    @Override
    public void dispose() {
        stage.dispose();
        uiStage.dispose();
        menuView.dispose();
    }
}
