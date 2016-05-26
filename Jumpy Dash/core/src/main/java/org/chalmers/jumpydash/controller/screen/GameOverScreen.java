package main.java.org.chalmers.jumpydash.controller.screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import main.java.org.chalmers.jumpydash.controller.PlayerController;
import main.java.org.chalmers.jumpydash.view.screen.GameOverView;

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
                ScreenManager.getInstance().initGame(stage, uiStage);
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
    }
}
