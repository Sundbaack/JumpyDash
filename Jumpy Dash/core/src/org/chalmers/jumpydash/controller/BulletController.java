package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.jumpydash.model.Bullet;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.BulletView;
import javax.vecmath.Vector2f;
import static org.chalmers.jumpydash.util.Constants.*;

public class BulletController extends Actor {

    private BulletView bulletView;
    private Bullet bullet;
    private IBox2D box2D;

    public BulletController(IBox2D box2D, float x, float y, Vector2f bulletDirection) {
        this.box2D = box2D;
        bulletView = new BulletView();
        bullet = new Bullet(box2D.newBullet(x,y),bulletDirection);
    }

    // Remove bullets when moving out of screen
    private void updateBullets() {
        if (((bullet.getPosition().x * PIXELS_TO_METERS) + H_TILE_SIZE) >= (box2D.getCamera().position.x + SCREEN_WIDTH / 2)) {
            box2D.getBodiesToBeDestroyed().add(bullet.getJdBody().getBody());
        }
    }

    @Override
    public void act(float delta) {
        updateBullets();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        bulletView.render(batch, bullet.getPosition().x * PIXELS_TO_METERS, bullet.getPosition().y * PIXELS_TO_METERS);
    }

    public void dispose() {
        bulletView.dispose();
    }
}
