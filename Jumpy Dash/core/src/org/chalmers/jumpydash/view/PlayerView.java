package org.chalmers.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class PlayerView {

    private Texture playerTile;
    private Texture background;

    public PlayerView() {
        playerTile = new Texture(Gdx.files.internal("player.png"));
        background = new Texture(Gdx.files.internal("bg.png"));
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }

    public void render(Batch batch, float x, float y) {
        batch.draw(background, 0, 0, 0, 0, 9984, 736);
        batch.draw(playerTile, x, y);
    }

    public void dispose() {
        background.dispose();
        playerTile.dispose();
    }
}
