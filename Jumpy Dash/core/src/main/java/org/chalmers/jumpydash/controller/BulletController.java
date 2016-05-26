package main.java.org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import main.java.org.chalmers.jumpydash.view.BulletView;
import main.java.org.chalmers.jumpydash.view.JDView;
import main.java.org.chalmers.jumpydash.model.Bullet;
import main.java.org.chalmers.jumpydash.physics.IBox2D;

import javax.vecmath.Vector2f;
import static main.java.org.chalmers.jumpydash.physics.Box2D.PIXELS_TO_METERS;
import static main.java.org.chalmers.jumpydash.physics.Box2D.SCREEN_WIDTH;
import static main.java.org.chalmers.jumpydash.physics.Box2D.TILE_SIZE;

public class BulletController extends JDController {

    private JDView bulletView;
    private Bullet bullet;
    private IBox2D box2D;

    public BulletController(IBox2D box2D, float x, float y, Vector2f bulletDirection) {
        this.box2D = box2D;
        bulletView = new BulletView();
        bullet = new Bullet();
        bullet.setJDBody(box2D.newBullet(x,y));
        bullet.getJDBody().setLinearVelocity(bulletDirection);
        bullet.getJDBody().setUserData(bullet);
    }

    // Remove bullets when moving out of screen
    private void updateBullets() {
        if (((bullet.getPosition().x * PIXELS_TO_METERS) + TILE_SIZE / 2) >= (box2D.getCamera().position.x + SCREEN_WIDTH / 2)) {
            bullet.getJDBody().setUserData(null);
        }
    }

    @Override
    public void act(float delta) {
        updateBullets();
        if (!bullet.getJDBody().isActive()) {
            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        bulletView.render(batch, bullet.getPosition().x * PIXELS_TO_METERS, bullet.getPosition().y * PIXELS_TO_METERS);
    }

    @Override
    public void dispose() {
        bulletView.dispose();
    }
}
