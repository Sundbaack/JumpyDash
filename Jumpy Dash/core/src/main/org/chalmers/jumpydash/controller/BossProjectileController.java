package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import org.chalmers.jumpydash.model.BossProjectile;
import org.chalmers.jumpydash.physics.Box2D;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.BossProjectileView;
import org.chalmers.jumpydash.view.JDView;

import javax.vecmath.Vector2f;

public class BossProjectileController extends JDController {

    private JDView bossProjectileView;
    private BossProjectile bossProjectile;
    private IBox2D box2D;

    public BossProjectileController(IBox2D box2D, float x, float y, Vector2f bulletDirection) {
        this.box2D = box2D;
        bossProjectileView = new BossProjectileView();
        bossProjectile = new BossProjectile();
        bossProjectile.setJDBody(box2D.newBullet(x-(0.5f),y));
        bossProjectile.getJDBody().setLinearVelocity(bulletDirection);
        bossProjectile.getJDBody().setUserData(bossProjectile);
    }

    // Remove projectiles when moving out of screen
    private void updateProjectiles() {
        if (((bossProjectile.getPosition().x * Box2D.PIXELS_TO_METERS) + Box2D.TILE_SIZE / 2) >= (box2D.getCamera().position.x + Box2D.SCREEN_WIDTH / 2)) {
            bossProjectile.getJDBody().setUserData(null);
        }
    }

    @Override
    public void act(float delta) {
        updateProjectiles();

        if (!bossProjectile.getJDBody().isActive()) {
            this.remove();
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        bossProjectileView.render(batch, (bossProjectile.getPosition().x * Box2D.PIXELS_TO_METERS), bossProjectile.getPosition().y * Box2D.PIXELS_TO_METERS);
    }

    @Override
    public void dispose() {
        bossProjectileView.dispose();
    }

}
