package org.chalmers.projectrolf.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class PlayerView {

    private Texture playerTile;

    public PlayerView() {
        playerTile = new Texture(Gdx.files.internal("player.png"));
    }

    public void render(Batch batch, float x, float y) {
        batch.draw(playerTile, x, y);
    }

    public void dispose() {
        playerTile.dispose();
    }
}
