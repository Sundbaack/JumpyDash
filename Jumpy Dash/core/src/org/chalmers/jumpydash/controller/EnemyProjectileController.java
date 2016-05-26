package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import org.chalmers.jumpydash.model.Bullet;
import org.chalmers.jumpydash.model.EnemyProjectile;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.EnemyProjectileView;
import org.chalmers.jumpydash.view.JDView;
import javax.vecmath.Vector2f;
import static org.chalmers.jumpydash.physics.Box2D.PIXELS_TO_METERS;
import static org.chalmers.jumpydash.physics.Box2D.SCREEN_WIDTH;
import static org.chalmers.jumpydash.physics.Box2D.TILE_SIZE;

public class EnemyProjectileController extends JDController {

    private JDView enemyProjectileView;
    private EnemyProjectile enemyProjectile;
    private IBox2D box2D;
    private Vector2f bulletDirection;

    public EnemyProjectileController(IBox2D box2D, float x, float y, Vector2f bulletDirection, int damage) {
        this.box2D = box2D;
        this.bulletDirection = bulletDirection;
        enemyProjectileView = new EnemyProjectileView();
        enemyProjectile = new EnemyProjectile(damage);
        enemyProjectile.setJDBody(box2D.newBullet(x-(0.5f),y));
        enemyProjectile.getJDBody().setLinearVelocity(bulletDirection);
        enemyProjectile.getJDBody().setUserData(enemyProjectile);
    }

    // Remove projectiles when moving out of screen
    private void updateProjectiles() {
        if (((enemyProjectile.getPosition().x * PIXELS_TO_METERS) + TILE_SIZE / 2) >= (box2D.getCamera().position.x + SCREEN_WIDTH / 2)) {
            enemyProjectile.getJDBody().setUserData(null);
        }
    }

    @Override
    public void act(float delta) {
        updateProjectiles();
        // Enable bullets to move when unpausing
        enemyProjectile.getJDBody().setLinearVelocity(bulletDirection);
        if (!enemyProjectile.getJDBody().isActive()) {
            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        enemyProjectileView.render(batch, (enemyProjectile.getPosition().x * PIXELS_TO_METERS), enemyProjectile.getPosition().y * PIXELS_TO_METERS);
    }

    @Override
    public void dispose() {
        enemyProjectileView.dispose();
    }
}
