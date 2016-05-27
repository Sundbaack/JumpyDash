package org.chalmers.jumpydash.controller.screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.chalmers.jumpydash.controller.PlayerController;
import org.chalmers.jumpydash.view.screen.WinView;

public class WinScreen extends JDScreen {

    private Stage stage;
    private Stage uiStage;
    private WinView winView;
    private PlayerController playerController;

    public WinScreen(Stage stage, Stage uiStage) {
        this.stage = stage;
        this.uiStage = uiStage;

        getPoints();
        this.stage.clear();
        this.uiStage.clear();
        winView = new WinView(this.uiStage, playerController.getPlayer().getPoints());

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
        winView.getPlayButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // Switch to game
                ScreenManager.getInstance().initGame(stage, uiStage, GameScreen.getLevel());
            }
        });

        winView.getMenuButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // Switch to menu
                ScreenManager.getInstance().initMenu(stage, uiStage);
            }
        });
    }

    @Override
    public void render(float delta) {
        winView.update();
    }

    @Override
    public void dispose() {
        stage.dispose();
        uiStage.dispose();
        winView.dispose();
    }

}
