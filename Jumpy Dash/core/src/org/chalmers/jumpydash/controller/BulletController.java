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
    private long previousFireTime;
    private Bullet bullet;
    private IBox2D box2D;

    public BulletController(IBox2D box2D, float x, float y, Vector2f bulletDirection) {
        this.box2D = box2D;
        bulletView = new BulletView();
        bullet = new Bullet(box2D.newBullet(x,y),bulletDirection);
    }
/*
    public void fireBullet() {

        // Cooldown
        long fireCooldown = 50;

        // Allow shooting if not on cooldown
        if (System.currentTimeMillis() - previousFireTime >= fireCooldown) {
                new Bullet(box2D.newBullet(bullet.getPosition().getX(), bullet.getPosition().y));
            // Reset cooldown
            previousFireTime = System.currentTimeMillis();
            // bullets.add(

        }
    }*/

    // Remove bullets when moving out of screen
     private void updateBullets() {
         if (((bullet.getPosition().x * PIXELS_TO_METERS) + H_TILE_SIZE) > (box2D.getCamera().position.x + SCREEN_WIDTH / 2)) {
             this.remove();
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
