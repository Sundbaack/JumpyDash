package org.chalmers.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class CoinView {

    private Texture coinTile;

    public CoinView() {
        coinTile = new Texture(Gdx.files.internal("coin.png"));
    }

    public void render(Batch batch,float x, float y) {
        batch.draw(coinTile, x, y);
    }

    public void dispose() {
        coinTile.dispose();
    }

}
