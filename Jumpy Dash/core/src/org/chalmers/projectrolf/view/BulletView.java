package org.chalmers.projectrolf.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BulletView {

    private Texture bulletTile;

    public BulletView() {
        bulletTile = new Texture(Gdx.files.internal("bullet.png"));
    }

    public void render(float x, float y, SpriteBatch batch) {
        batch.draw(bulletTile, x, y);
    }

    public void dispose() {
        bulletTile.dispose();
    }
}
