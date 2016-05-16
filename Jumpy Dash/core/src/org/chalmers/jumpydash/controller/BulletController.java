package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.jumpydash.model.Bullet;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.BulletView;

public class BulletController extends Actor {

    private BulletView bulletView;
    private long previousFireTime;
    private Bullet bullet;
    private IBox2D box2D;

    public BulletController(IBox2D box2D, float x, float y) {
        this.box2D = box2D;
        bulletView = new BulletView();
        bullet = new Bullet(box2D.newBullet(x,y));
    }

    /*public void fireBullet() {

        // Cooldown
        long fireCooldown = 50;

        // Allow shooting if not on cooldown
        if (System.currentTimeMillis() - previousFireTime >= fireCooldown && bullets.size() < 3) {
            if(count <3){
                System.out.println(count);
                new Bullet(box2D.newBullet(PlayerController.getPosition().x, PlayerController.getPosition().y));
            }
            // Reset cooldown
            previousFireTime = System.currentTimeMillis();
            // bullets.add(

        }
    }*/

    // Remove bullets when moving out of screen
     private void updateBullets() {
         if (((bullet.getPosition().x * box2D.getPixelsToMeters()) + 16) > (box2D.getCamera().position.x + 1280 / 2)) {
             this.remove();
         }
     }

    public void act(float delta) {
        updateBullets();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        bulletView.render(batch, bullet.getPosition().x * box2D.getPixelsToMeters(), bullet.getPosition().y * box2D.getPixelsToMeters());
    }

    public void dispose() {
        bulletView.dispose();
    }
}
