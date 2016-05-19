package org.chalmers.jumpydash.controller.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class ScreenManager {

    // Singleton
    private static ScreenManager instance = new ScreenManager();

    // Game object reference
    private Game game;

    private ScreenManager() {
    }

    public void initialize(Game game) {
        this.game = game;
    }

    public static ScreenManager getInstance() {
        return instance;
    }

    // MenuScreem
    public void initMenu(Stage stage, Stage uiStage) {
        setScreen(ScreenType.MENU, stage, uiStage);
    }

    // GameScreem
    public void initGame(Stage stage, Stage uiStage) {
        setScreen(ScreenType.GAME, stage, uiStage);
    }

    // GameOverScreem
    public void initGameOver(Stage stage, Stage uiStage) {
        setScreen(ScreenType.GAMEOVER, stage, uiStage);
    }

    // Set new screen
    private void setScreen(ScreenType type, Stage stage, Stage uiStage) {
        game.setScreen(type.getScreen(stage, uiStage));
    }
}
