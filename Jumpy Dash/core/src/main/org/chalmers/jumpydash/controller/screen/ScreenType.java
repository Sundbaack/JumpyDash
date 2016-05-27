package org.chalmers.jumpydash.controller.screen;

import com.badlogic.gdx.scenes.scene2d.Stage;

public enum ScreenType {

    MENU {
        public JDScreen setScreen(Stage stage, Stage uiStage) {
            return new MenuScreen(stage, uiStage);
        }
    },
    GAME {
        public JDScreen setScreen(Stage stage, Stage uiStage) {
            return null;
        }
        @Override
        public JDScreen setScreenGame(Stage stage, Stage uiStage, int level) {
            if (newGameInstance()) {
                gameScreen = new GameScreen(stage, uiStage, level);
                resetNewGameInstance();
            }
            return gameScreen;
        }
    },
    PAUSE {
        public JDScreen setScreen(Stage stage, Stage uiStage) {
            return new PauseScreen(stage, uiStage);
        }
    },
    RESUME {
        public JDScreen setScreen(Stage stage, Stage uiStage) {
            return gameScreen;
        }
    },
    OPTION {
        public JDScreen setScreen(Stage stage, Stage uiStage) {
            uiStage.clear();
            return new OptionScreen(stage, uiStage);
        }
    },
    GAMEOVER {
        public JDScreen setScreen(Stage stage, Stage uiStage) {
            resetNewGameInstance();
            return new GameOverScreen(stage, uiStage);
        }
    },
    WIN {
        public JDScreen setScreen(Stage stage, Stage uiStage) {
            resetNewGameInstance();
            return new WinScreen(stage, uiStage);
        }
    },
    LEVEL {
        public JDScreen setScreen(Stage stage, Stage uiStage) {
            return new LevelSelectScreen(stage, uiStage);
        }
    };

    private static JDScreen gameScreen;
    private static boolean newGameInstance = true;

    private static boolean newGameInstance() {
        return newGameInstance;
    }

    private static void resetNewGameInstance() {
        newGameInstance = !newGameInstance;
    }

    public abstract JDScreen setScreen(Stage stage, Stage uiStage);

    public JDScreen setScreenGame(Stage stage, Stage uiStage, int level) {
        return null;
    }
}