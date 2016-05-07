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
    private List<Bullet> bullets;
    private long previousFireTime;
    private Box2D box2D;

    public BulletController(Box2D box2D) {
        this.box2D = box2D;
        bullets = new ArrayList<Bullet>();
        bulletView = new BulletView();
    }

    public void fireBullet() {

        // Cooldown
        long fireCooldown = 50;

        // Allow shooting if not on cooldown
        if (System.currentTimeMillis() - previousFireTime >= fireCooldown && bullets.size() < 3) {

            // Reset cooldown
            previousFireTime = System.currentTimeMillis();
            bullets.add(new Bullet(box2D.newBullet(PlayerController.getPosition().x, PlayerController.getPosition().y)));
        }
    }

    // Remove bullets when moving out of screen
    public void updateBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            if (((bullets.get(i).getPosition().x * box2D.getPixelsToMeters()) + 16) > (box2D.getCamera().position.x + 1280 / 2)) {
                bullets.remove(i);
            }
        }
    }

    public void update(SpriteBatch batch) {
        handleInput();
        updateBullets();
        render(batch);
    }

    public void render(SpriteBatch  batch) {
        for (Bullet b : bullets) {
            bulletView.render(b.getPosition().x * box2D.getPixelsToMeters(), b.getPosition().y * box2D.getPixelsToMeters(), batch);
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
