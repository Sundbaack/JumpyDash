package org.chalmers.jumpydash.controller.screen;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.chalmers.jumpydash.view.screen.LevelSelectView;

public class LevelSelectScreen extends JDScreen {

    private Stage stage;
    private Stage uiStage;
    private LevelSelectView levelSelectView;


    public LevelSelectScreen(Stage stage, Stage uiStage) {
        this.stage = stage;
        this.uiStage = uiStage;
        this.stage.clear();
        this.uiStage.clear();
        levelSelectView = new LevelSelectView(uiStage);

        setListeners();
    }

    private void setListeners() {
        levelSelectView.getLevel1Button().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // Switch to level 1
                ScreenManager.getInstance().initGame(stage, uiStage, 1);
            }
        });

        levelSelectView.getLevel2Button().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // Switch to level 2
                ScreenManager.getInstance().initGame(stage, uiStage, 2);
            }
        });
    }

    @Override
    public void render(float delta) {
        levelSelectView.update();
    }

    @Override
    public void dispose() {
        stage.dispose();
        uiStage.dispose();
        levelSelectView.dispose();
    }

}
