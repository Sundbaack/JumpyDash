package org.chalmers.jumpydash.controller.screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.chalmers.jumpydash.view.screen.GameOverView;
import org.chalmers.jumpydash.controller.PlayerController;

public class GameOverScreen extends JDScreen {

    private Stage stage;
    private Stage uiStage;
    private GameOverView gameOverView;
    private PlayerController playerController;

    public GameOverScreen(Stage stage, Stage uiStage) {
        this.stage = stage;
        this.uiStage = uiStage;

        getPoints();
        this.stage.clear();
        this.uiStage.clear();
        gameOverView = new GameOverView(this.uiStage, playerController.getPlayer().getPoints());

        setListeners();
    }

    private void getPoints() {
        for (Actor a: stage.getActors()) {
            if (a.getName() != null && a.getName().equalsIgnoreCase("playerController")) {
                playerController = (PlayerController) a;
            }
        }
    }
    private void setListeners() {
        gameOverView.getPlayButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // Switch to game
                ScreenManager.getInstance().initGame(stage, uiStage, GameScreen.getLevel());
            }
        });

        gameOverView.getMenuButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // Switch to menu
                ScreenManager.getInstance().initMenu(stage, uiStage);
            }
        });
    }

    @Override
    public void render(float delta) {
        gameOverView.update();
    }

    @Override
    public void dispose() {
        stage.dispose();
        uiStage.dispose();
        gameOverView.dispose();
    }
}
