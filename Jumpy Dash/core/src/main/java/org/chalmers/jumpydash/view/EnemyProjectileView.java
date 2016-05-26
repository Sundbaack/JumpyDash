package main.java.org.chalmers.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class EnemyProjectileView implements JDView {

    private Texture projectileTile;

    public EnemyProjectileView() {
        projectileTile = new Texture(Gdx.files.internal("images/enemyProjectile.png"));
    }

    public void render(Batch batch,float x, float y) {
        batch.draw(projectileTile, x, y);
    }

    public void dispose() {
        projectileTile.dispose();
    }
}

