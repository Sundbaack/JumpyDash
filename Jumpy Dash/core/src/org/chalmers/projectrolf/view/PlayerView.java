package org.chalmers.projectrolf.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class PlayerView {

    private Texture playerTile;
    private Texture background;

    public PlayerView() {
        playerTile = new Texture(Gdx.files.internal("player.png"));

        background = new Texture(Gdx.files.internal("background_1.png"));
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }

    public void render(Batch batch, float x, float y) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.draw(background, 0, 0, 0, 0, 10000, 736);
        batch.draw(playerTile, x, y);
    }

    public void dispose() {
        playerTile.dispose();
    }
}
