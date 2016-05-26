package org.chalmers.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by Oscar on 2016-05-26.
 */
public class HeartView implements JDView {



    private Texture heartTile;

    public HeartView() {
        heartTile = new Texture(Gdx.files.internal("images/heart.png"));
    }

    public void render(Batch batch,float x, float y) {
        batch.draw(heartTile, x, y);
    }

    public void dispose() {
        heartTile.dispose();
    }
}
