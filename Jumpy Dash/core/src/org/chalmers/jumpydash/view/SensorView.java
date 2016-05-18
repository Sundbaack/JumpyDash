package org.chalmers.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class SensorView {

    private Texture abilityTile;

    public SensorView() {
        abilityTile = new Texture(Gdx.files.internal("images/ability.png"));
    }

    public void render(Batch batch,float x, float y) {
        batch.draw(abilityTile, x, y);
    }

    public void dispose() {
        abilityTile.dispose();
    }

}
