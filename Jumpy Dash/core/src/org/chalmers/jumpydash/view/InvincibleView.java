package org.chalmers.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class InvincibleView implements JDView {

    private Texture invincibleTile;

    public InvincibleView() {
        invincibleTile = new Texture(Gdx.files.internal("images/playerInvincible.png"));
    }

    public void render(Batch batch, float x, float y) {
        batch.draw(invincibleTile, x, y);
    }

    public void dispose() {
        invincibleTile.dispose();
    }
}
