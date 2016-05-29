package org.chalmers.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class BossProjectileView implements JDView {

    private Texture bossProjectileTile;

    public BossProjectileView() {
        bossProjectileTile = new Texture(Gdx.files.internal("images/enemyProjectile.png"));
    }

    public void render(Batch batch, float x, float y) {
        batch.draw(bossProjectileTile, x, y);
    }

    public void dispose() {
        bossProjectileTile.dispose();
    }
}
