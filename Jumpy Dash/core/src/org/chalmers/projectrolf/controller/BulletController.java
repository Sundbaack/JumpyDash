package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.chalmers.projectrolf.model.Bullet;
import org.chalmers.projectrolf.view.BulletView;

import java.util.ArrayList;
import java.util.List;

public class BulletController {

    private BulletView bulletView;
    private List<Bullet> bulletList;
    private long previousFireTime;
    private float tileWidthHeight;

    public BulletController(float tileWidthHeight) {

        bulletList = new ArrayList<Bullet>();
        this.tileWidthHeight = tileWidthHeight;
        bulletView = new BulletView(bulletList);
    }

    public void fireBullet() {

        // Cooldown
        long fireCooldown = 50;

        // Allow shooting if not on cooldown
        if (System.currentTimeMillis() - previousFireTime >= fireCooldown && bulletList.size() < 3) {

            // Reset cooldown
            previousFireTime = System.currentTimeMillis();

            // Bullet body Box2D
            BodyDef bulletBodyDef = new BodyDef();
            bulletBodyDef.type = BodyDef.BodyType.KinematicBody;
            bulletBodyDef.position.set(PlayerController.player.getPosition().x + (32 / GameController.PIXELS_TO_METERS), PlayerController.player.getPosition().y + ((tileWidthHeight / 2) / GameController.PIXELS_TO_METERS));
            Body bulletBody = GameController.world.createBody(bulletBodyDef);
            bulletBody.setBullet(true);

            Bullet bullet = new Bullet(bulletBody, (tileWidthHeight / 2) / GameController.PIXELS_TO_METERS);
            bulletList.add(bullet);

        }

    }

    // Remove bullets when moving out of screen
    public void updateBullets() {
        for (int i = 0; i < bulletList.size(); i++) {
            if (((bulletList.get(i).getPosition().x * GameController.PIXELS_TO_METERS) + 16) > (GameController.camera.position.x + 1280 / 2)) {
                bulletList.remove(i);
            }
        }
    }

    public BulletView getView() {
         return bulletView;
    }

    public void handleInput() {
        // Jumping
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            fireBullet();
        }
    }
}
