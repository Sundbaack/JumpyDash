package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import org.chalmers.jumpydash.model.Boss;
import org.chalmers.jumpydash.physics.BodyType;
import org.chalmers.jumpydash.physics.Box2D;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.BossView;
import org.chalmers.jumpydash.view.JDView;

import javax.vecmath.Vector2f;

import static org.chalmers.jumpydash.physics.Box2D.PIXELS_TO_METERS;

public class BossController extends JDController {

    private JDView bossView;
    private Boss boss;

    public BossController(IBox2D box2D, int x, int y, int mapHeight) {
        bossView = new BossView();
        boss = new Boss();
        boss.setJDBody(box2D.newBody(x, y, mapHeight, BodyType.KINEMATIC, false, false));
        boss.getJDBody().setUserData(boss);

    }

    @Override
    public void act(float Delta) {
        if (!boss.getJDBody().isAwake()) {
            if ((boss.getPosition().y * Box2D.PIXELS_TO_METERS) < Box2D.SCREEN_HEIGHT) {
                boss.getJDBody().setLinearVelocity(new Vector2f(0, 0.7f));
            } else {
                System.exit(0);
                boss.getJDBody().setLinearVelocity(new Vector2f(0, -0.7f));
            }

        }

        if (!boss.getJDBody().isActive()) {
            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        bossView.render(batch, boss.getPosition().x * PIXELS_TO_METERS, boss.getPosition().y * PIXELS_TO_METERS);
    }

    @Override
    public void dispose() {
        bossView.dispose();
    }

}
