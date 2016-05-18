package org.chalmers.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by alexsundback on 2016-05-18.
 */
public class SpeedUpView {

    private Texture speedUpTile;

    public SpeedUpView() {
        speedUpTile = new Texture(Gdx.files.internal("ability.png"));
    }

    public void render(Batch batch, float x, float y) {
        batch.draw(speedUpTile, x, y);
    }

    public void dispose() {
        speedUpTile.dispose();
    }
}
