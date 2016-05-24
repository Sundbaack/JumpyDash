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
            gameScreen = new GameScreen(stage, uiStage);
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
    GAMEOVER {
        public JDScreen setScreen(Stage stage, Stage uiStage) {
            return new GameOverScreen(stage, uiStage);
        }
    };

    private static JDScreen gameScreen;

    public abstract JDScreen setScreen(Stage stage, Stage uiStage);
}