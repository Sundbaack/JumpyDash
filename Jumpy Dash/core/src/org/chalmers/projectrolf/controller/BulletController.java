package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.projectrolf.model.Bullet;
import org.chalmers.projectrolf.view.BulletView;


public class BulletController extends Actor {

    private BulletView bulletView;
    private long previousFireTime;
    private Bullet bullet;
    private Box2D box2D;

    public BulletController(Box2D box2D) {
        this.box2D = box2D;
        bulletView = new BulletView();
        bullet = new Bullet(box2D.newBullet(PlayerController.getPosition().x,PlayerController.getPosition().y));

    }

  /*  public void fireBullet() {

        // Cooldown
        long fireCooldown = 50;

        // Allow shooting if not on cooldown
       // if (System.currentTimeMillis() - previousFireTime >= fireCooldown && bullets.size() < 3) {
            if(count <3){
                System.out.println(count);
                new Bullet(box2D.newBullet(PlayerController.getPosition().x, PlayerController.getPosition().y));
            }
            // Reset cooldown
            previousFireTime = System.currentTimeMillis();
           // bullets.add(

    }
*/
    // Remove bullets when moving out of screen
     public void updateBullets() {
         if (((bullet.getPosition().x * box2D.getPixelsToMeters()) + 16) > (box2D.getCamera().position.x + 1280 / 2)) {
             this.remove();
         }
     }

    public Vector2 getPosition(){
        return bullet.getPosition();
    }

    public void act(float delta){
        updateBullets();
    }

    public void draw(Batch batch, float parentAlpha){
        bulletView.render(batch,bullet.getPosition().x * box2D.getPixelsToMeters(), bullet.getPosition().y * box2D.getPixelsToMeters());
    }

    public void dispose() {
        bulletView.dispose();
    }
}
