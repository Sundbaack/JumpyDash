package org.chalmers.jumpydash.controller.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class ScreenManager {

    // Singleton
    private static ScreenManager instance = new ScreenManager();

    // Game object reference
    private Game game;

    // Current screen
    private ScreenType currentScreen;

    private ScreenManager() {
    }

    public void initialize(Game game) {
        this.game = game;
    }

    public static ScreenManager getInstance() {
        return instance;
    }

    // MenuScreen
    public void initMenu(Stage stage, Stage uiStage) {
        setScreen(ScreenType.MENU, stage, uiStage);
    }

    // GameScreen
    public void initGame(Stage stage, Stage uiStage, int level) {
        setScreenGame(ScreenType.GAME, stage, uiStage, level);
    }

    // GameOverScreen
    public void initGameOver(Stage stage, Stage uiStage) {
        setScreen(ScreenType.GAMEOVER, stage, uiStage);
    }

    // PauseScreen
    public void initPause(Stage stage, Stage uiStage) {
        setScreen(ScreenType.PAUSE, stage, uiStage);
    }

    // Resume
    public void initResume(Stage stage, Stage uiStage) {
        setScreen(ScreenType.RESUME, stage, uiStage);
    }

    // WinScreen
    public void initWin(Stage stage, Stage uiStage) {
        setScreen(ScreenType.WIN, stage, uiStage);
    }

    // Level select screen
    public  void initLevelSelect(Stage stage, Stage uiStage) {
        setScreen(ScreenType.LEVEL, stage, uiStage);
    }

    // Options
    public void initOption(Stage stage, Stage uiStage) {
        setScreen(ScreenType.OPTION, stage, uiStage);
    }

    // Set new screen
    private void setScreen(ScreenType type, Stage stage, Stage uiStage) {
        game.setScreen(type.setScreen(stage, uiStage));
        currentScreen = type;
    }

    private void setScreenGame(ScreenType type, Stage stage, Stage uiStage, int level) {
        game.setScreen(type.setScreenGame(stage, uiStage, level));
        currentScreen = type;
    }

    public ScreenType getScreen() {
        return currentScreen;
    }

    public boolean isPaused() {
        return currentScreen.equals(ScreenType.PAUSE);
    }
}