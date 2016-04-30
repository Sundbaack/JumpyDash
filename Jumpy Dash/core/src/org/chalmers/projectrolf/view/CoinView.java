package org.chalmers.projectrolf.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CoinView {

    private Texture coinTile;

    public CoinView() {
        coinTile = new Texture(Gdx.files.internal("coin.png"));
    }

    public void render(float x, float y, SpriteBatch batch) {
        batch.draw(coinTile, x, y);
    }

    public void dispose() {
        coinTile.dispose();
    }

}
