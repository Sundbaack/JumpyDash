package org.chalmers.jumpydash.controller.screen;

import com.badlogic.gdx.scenes.scene2d.Stage;

public enum ScreenType {

    MENU {
        public JDScreen getScreen(Stage stage, Stage uiStage) {
            return new MenuScreen(stage, uiStage);
        }
    },
    GAME {
        public JDScreen getScreen(Stage stage, Stage uiStage) {
            return new GameScreen(stage, uiStage);
        }
    },
    GAMEOVER {
        public JDScreen getScreen(Stage stage, Stage uiStage) {
            return new GameOverScreen(stage, uiStage);
        }
    };

    public abstract JDScreen getScreen(Stage stage, Stage uiStage);
}
