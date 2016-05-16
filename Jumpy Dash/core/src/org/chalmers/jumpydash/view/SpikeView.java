package org.chalmers.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by alexsundback on 2016-05-16.
 */
public class SpikeView {

    private Texture spikeTile;

    public SpikeView() {
        spikeTile = new Texture(Gdx.files.internal("spike.png"));
    }

    public void render(Batch batch, float x, float y) {
        batch.draw(spikeTile, x, y);
    }

    public void dispose() {
        spikeTile.dispose();
    }

}
