package org.chalmers.jumpydash.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class PauseView {

    private Stage uiStage;
    private Texture pauseBb;

    public PauseView(Stage uiStage) {
        this.uiStage = uiStage;
        pauseBb = new Texture(Gdx.files.internal("images/pauseBg.png"));
    }

    public void update() {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        uiStage.getBatch().begin();
        uiStage.getBatch().draw(pauseBb, 0, 0);
        uiStage.getBatch().end();

    }

    public void dispose() {
        uiStage.dispose();
        pauseBb.dispose();
    }
}
