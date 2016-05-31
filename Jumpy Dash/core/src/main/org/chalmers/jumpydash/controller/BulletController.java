package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import org.chalmers.jumpydash.view.BulletView;
import org.chalmers.jumpydash.view.JDView;
import org.chalmers.jumpydash.model.Bullet;
import org.chalmers.jumpydash.physics.Box2D;
import org.chalmers.jumpydash.physics.IBox2D;

import javax.vecmath.Vector2f;

public class BulletController extends JDController {

    private JDView bulletView;
    private Bullet bullet;
    private IBox2D box2D;
    private Vector2f bulletDirection;

    public BulletController(IBox2D box2D, float x, float y, Vector2f bulletDirection) {
        this.box2D = box2D;
        bulletView = new BulletView();
        bullet = new Bullet();
        this.bulletDirection = bulletDirection;
        bullet.setJDBody(box2D.newBullet(x,y));
        bullet.getJDBody().setLinearVelocity(bulletDirection);
        bullet.getJDBody().setUserData(bullet);
    }

    // Remove bullets when moving out of screen
    private void updateBullets() {
        if (((bullet.getPosition().x * Box2D.PIXELS_TO_METERS) + Box2D.TILE_SIZE / 2) >= (box2D.getCamera().position.x + Box2D.SCREEN_WIDTH / 2)) {
            bullet.getJDBody().setUserData(null);
        }
    }

    @Override
    public void act(float delta) {
        updateBullets();

        if (!bullet.getJDBody().isActive()) {
            this.remove();
        }

        if(bullet.getJDBody().isAwake()) {
            bullet.getJDBody().setLinearVelocity(bulletDirection);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        bulletView.render(batch, bullet.getPosition().x * Box2D.PIXELS_TO_METERS, bullet.getPosition().y * Box2D.PIXELS_TO_METERS);
    }

    @Override
    public void dispose() {
        bulletView.dispose();
    }
}
