package org.chalmers.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class KeyView implements JDView {

    private Texture keyTile;

    public KeyView() {
        keyTile = new Texture(Gdx.files.internal("images/key.png"));
    }

    public void render(Batch batch,float x, float y) {
        batch.draw(keyTile, x, y);
    }

    public void dispose() {
        keyTile.dispose();
    }

}
