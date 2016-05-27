package main.java.org.chalmers.jumpydash.jumpydash.controller.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import main.java.org.chalmers.jumpydash.jumpydash.view.screen.PauseView;

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
            if (ScreenManager.getInstance().isPaused()) {
                ScreenManager.getInstance().initResume(stage, uiStage);
            }
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
