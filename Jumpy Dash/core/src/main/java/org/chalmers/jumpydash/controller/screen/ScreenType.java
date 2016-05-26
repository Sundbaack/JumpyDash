package main.java.org.chalmers.jumpydash.controller.screen;

import com.badlogic.gdx.scenes.scene2d.Stage;

public enum ScreenType {

    MENU {
        public JDScreen setScreen(Stage stage, Stage uiStage) {
            return new MenuScreen(stage, uiStage);
        }
    },
    GAME {
        public JDScreen setScreen(Stage stage, Stage uiStage) {
            if (newGameInstance()) {
                gameScreen = new GameScreen(stage, uiStage);
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
}