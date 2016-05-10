package org.chalmers.projectrolf.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class AbilityView {

    private Texture abilityTile;

    public AbilityView() {
        abilityTile = new Texture(Gdx.files.internal("ability.png"));
    }

    public void render(Batch batch,float x, float y) {
        batch.draw(abilityTile, x, y);
    }

    public void dispose() {
        abilityTile.dispose();
    }

}
