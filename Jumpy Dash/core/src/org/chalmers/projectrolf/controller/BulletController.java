package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.chalmers.projectrolf.model.Bullet;
import org.chalmers.projectrolf.view.BulletView;

import java.util.ArrayList;
import java.util.List;

public class BulletController {

    private BulletView bulletView;
    private List<Bullet> bulletList;
    private long previousFireTime;
    private Box2D box2D;
    private final float PIXELS_TO_METERS;

    public BulletController(Box2D box2D) {
        this.box2D = box2D;
        bulletList = new ArrayList<Bullet>();
        bulletView = new BulletView();
        this.PIXELS_TO_METERS = box2D.getPixelsToMeters();
    }

    public void fireBullet() {

        // Cooldown
        long fireCooldown = 50;

        // Allow shooting if not on cooldown
        if (System.currentTimeMillis() - previousFireTime >= fireCooldown && bulletList.size() < 3) {

            // Reset cooldown
            previousFireTime = System.currentTimeMillis();
            bulletList.add(new Bullet(box2D.newBullet(PlayerController.getPosition().x, PlayerController.getPosition().y)));
        }
    }

    // Remove bullets when moving out of screen
    public void updateBullets() {
        for (int i = 0; i < bulletList.size(); i++) {
            if (((bulletList.get(i).getPosition().x * PIXELS_TO_METERS) + 16) > (Box2D.camera.position.x + 1280 / 2)) {
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
