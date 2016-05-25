package org.chalmers.jumpydash.controller.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.chalmers.jumpydash.view.screen.PauseView;

public class PauseScreen extends JDScreen {

    private PauseView pauseView;
    private Stage uiStage;
    private Stage stage;

    public PauseScreen(Stage stage, Stage uiStage) {
        this.stage = stage;
        this.uiStage = uiStage;
        this.pauseView = new PauseView(uiStage);
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            if (getPaused()) {
                ScreenManager.getInstance().initGame(stage, uiStage);
            }
        }
    }

    private boolean getPaused() {
        return ScreenManager.getInstance().getScreen().equals(ScreenType.PAUSE);
    }

    @Override
    public void render(float delta) {
        handleInput();
        pauseView.update();
    }

    @Override
    public void dispose() {
        uiStage.dispose();
    }
}
