package org.chalmers.jumpydash.controller.screen;

import com.badlogic.gdx.scenes.scene2d.Stage;

public enum ScreenType {

    MENU {
        public BaseScreen getScreen(Stage stage, Stage uiStage) {
            return new MenuScreen(stage, uiStage);
        }
    },
    GAME {
        public BaseScreen getScreen(Stage stage, Stage uiStage) {
            return new GameScreen(stage, uiStage);
        }
    },
    GAMEOVER {
        public BaseScreen getScreen(Stage stage, Stage uiStage) {
            return new GameOverScreen(stage, uiStage);
        }
    };

    public abstract BaseScreen getScreen(Stage stage, Stage uiStage);
}
