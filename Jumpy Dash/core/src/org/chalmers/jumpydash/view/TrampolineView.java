package org.chalmers.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by alexsundback on 2016-05-16.
 */
public class TrampolineView {

    private Texture trampolineTile;

    public TrampolineView(){
        trampolineTile = new Texture(Gdx.files.internal("player(1).png"));
    }

    public void render(Batch batch, float x, float y) {
        batch.draw(trampolineTile, x, y);
    }

    public void dispose() {
        trampolineTile.dispose();
    }

}
