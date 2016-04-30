package org.chalmers.projectrolf.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AbilityView {

    private Texture abilityTile;

    public AbilityView() {
        abilityTile = new Texture(Gdx.files.internal("ability.png"));
    }

    public void render(float x, float y, SpriteBatch batch) {
        batch.draw(abilityTile, x, y);
    }

    public void dispose() {
        abilityTile.dispose();
    }

}
