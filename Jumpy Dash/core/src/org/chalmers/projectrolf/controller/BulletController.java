package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.projectrolf.model.Bullet;
import org.chalmers.projectrolf.physics.IBox2D;
import org.chalmers.projectrolf.physics.IJDBody;
import org.chalmers.projectrolf.view.BulletView;


public class BulletController extends Actor {

    private BulletView bulletView;
    private long previousFireTime;
    private Bullet bullet;
    private IBox2D box2D;
    private IJDBody jdBody;

    public BulletController(IBox2D box2D, float x, float y) {
        this.box2D = box2D;
        bulletView = new BulletView();
        bullet = new Bullet();
        jdBody = box2D.newBullet(x, y);
        jdBody.setLinearVelocity(new Vector2(15f,0));
        jdBody.setUserData(bullet);
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
         if (((getPosition().x * box2D.getPixelsToMeters()) + 16) > (box2D.getCamera().position.x + 1280 / 2)) {
             this.remove();
         }
     }

    public Vector2 getPosition() {
        return jdBody.getPosition();
    }

    public void act(float delta) {
        updateBullets();
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        bulletView.render(batch, getPosition().x * box2D.getPixelsToMeters(), getPosition().y * box2D.getPixelsToMeters());
    }

    public void dispose() {
        bulletView.dispose();
    }
}
