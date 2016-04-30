package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private final float tileWidthHeight;
    private final float PIXELS_TO_METERS;

    public BulletController(float tileWidthHeight, float PIXELS_TO_METERS) {

        bulletList = new ArrayList<Bullet>();
        this.tileWidthHeight = tileWidthHeight;
        this.PIXELS_TO_METERS = PIXELS_TO_METERS;
        bulletView = new BulletView();
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
            bulletBodyDef.position.set(PlayerController.player.getPosition().x + (32 / PIXELS_TO_METERS), PlayerController.player.getPosition().y + ((tileWidthHeight / 2) / PIXELS_TO_METERS));
            Body bulletBody = JumpyDash.world.createBody(bulletBodyDef);
            bulletBody.setBullet(true);

            Bullet bullet = new Bullet(bulletBody, (tileWidthHeight / 2) / PIXELS_TO_METERS);
            bulletList.add(bullet);
        }
    }

    // Remove bullets when moving out of screen
    public void updateBullets() {
        for (int i = 0; i < bulletList.size(); i++) {
            if (((bulletList.get(i).getPosition().x * PIXELS_TO_METERS) + 16) > (JumpyDash.camera.position.x + 1280 / 2)) {
                bulletList.remove(i);
            }
        }
    }

    public void update(SpriteBatch batch) {
        handleInput();
        updateBullets();
        render(batch);
    }

    public void render(SpriteBatch  batch) {
        for (Bullet b : bulletList) {
            bulletView.render(b.getPosition().x * PIXELS_TO_METERS, b.getPosition().y * PIXELS_TO_METERS, batch);
        }
    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            fireBullet();
        }
    }

    public void dispose() {
        bulletView.dispose();
    }
}
