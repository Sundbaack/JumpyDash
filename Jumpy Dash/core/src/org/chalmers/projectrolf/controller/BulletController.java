package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.chalmers.projectrolf.model.Bullet;
import org.chalmers.projectrolf.view.BulletView;

import java.util.ArrayList;
import java.util.List;

public class BulletController {

    private final BulletView bulletView;
    private List<Bullet> bulletList;
    private long previousFireTime;
    private float tileWidthHeight;

    public BulletController(float tileWidthHeight) {

        bulletList = new ArrayList<Bullet>();
        this.tileWidthHeight = tileWidthHeight;

        bulletView = new BulletView(bulletList);
    }

    private void fireBullet() {

        // Cooldown
        long fireCooldown = 50;

        // Allow shooting if not on cooldown
        if (System.currentTimeMillis() - previousFireTime >= fireCooldown && bulletList.size() < 3) {
        /*
            // Reset cooldown
            previousFireTime = System.currentTimeMillis();

            // Bullet body Box2D
            BodyDef bulletBodyDef = new BodyDef();
            bulletBodyDef.type = BodyDef.BodyType.KinematicBody;
            bulletBodyDef.position.set(player.getPosition().x + (32 / GameController.PIXELS_TO_METERS), player.getPosition().y + ((tileWidthHeight / 2) / GameController.PIXELS_TO_METERS));
            Body bulletBody = world.createBody(bulletBodyDef);
            bulletBody.setBullet(true);

            Bullet bullet = new Bullet(bulletBody, (tileWidthHeight / 2) / GameController.PIXELS_TO_METERS;
            bulletList.add(bullet);

        */
        }

    }

    // Remove bullets when moving out of screen
    private void updateBullets() {
        for (int i = 0; i < bulletList.size(); i++) {
            if (((bulletList.get(i).getPosition().x * GameController.PIXELS_TO_METERS) + 16) > (camera.position.x + 1280 / 2)) {
                bulletList.remove(i);
            }
        }
    }
}
