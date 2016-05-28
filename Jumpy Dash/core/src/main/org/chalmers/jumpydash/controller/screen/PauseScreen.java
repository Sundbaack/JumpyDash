package org.chalmers.jumpydash.controller.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.chalmers.jumpydash.view.screen.PauseView;

public class PauseScreen extends JDScreen {

    private PauseView pauseView;
    private Stage uiStage;
    private Stage stage;

    public PauseScreen(Stage stage, Stage uiStage) {
        this.stage = stage;
        this.uiStage = uiStage;
        this.pauseView = new PauseView(uiStage);

        setListeners();
    }

    private void setListeners() {
        pauseView.getMenuButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // Switch to menu
                ScreenManager.getInstance().initMenu(stage, uiStage);
            }
        });
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.P) && ScreenManager.getInstance().isPaused()) {
            ScreenManager.getInstance().initResume(stage, uiStage);
        }
    }

    @Override
    public void render(float delta) {
        handleInput();
        pauseView.update();
    }

    @Override
    public void dispose() {
        stage.dispose();
        uiStage.dispose();
        pauseView.dispose();
    }
}
