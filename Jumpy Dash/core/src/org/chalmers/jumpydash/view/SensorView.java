package org.chalmers.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by Surface pro 3 on 2016-05-18.
 */
public class SensorView {

    private Texture abilityTile;

    public SensorView() {
        abilityTile = new Texture(Gdx.files.internal("ability.png"));
    }

    public void render(Batch batch,float x, float y) {
        batch.draw(abilityTile, x, y);
    }

    public void dispose() {
        abilityTile.dispose();
    }

}
