package org.chalmers.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class PlayerView implements JDView {

    private Texture playerTile;

    public PlayerView() {
        playerTile = new Texture(Gdx.files.internal("images/player.png"));
    }

    public void render(Batch batch, float x, float y) {
        batch.draw(playerTile, x, y);
    }

    public void dispose() {
        playerTile.dispose();
    }
}
