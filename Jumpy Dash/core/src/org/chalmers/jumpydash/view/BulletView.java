package org.chalmers.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class BulletView implements JDView {

    private Texture bulletTile;

    public BulletView() {
        bulletTile = new Texture(Gdx.files.internal("images/bullet.png"));
    }

    public void render(Batch batch,float x, float y) {
        batch.draw(bulletTile, x, y);
    }

    public void dispose() {
        bulletTile.dispose();
    }
}

