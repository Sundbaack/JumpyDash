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
    private boolean isMovingUp;
    private IBox2D box2D;
    private static final float BOSS_MAX_LIMIT = 635.33307f;
    private static final float BOSS_MIN_LIMIT = 48.666862f;

    public BossController(IBox2D box2D, int x, int y, int mapHeight) {
        bossView = new BossView();
        boss = new Boss();
        boss.setJDBody(box2D.newBody(x, y, mapHeight, BodyType.KINEMATIC, false, false));
        boss.getJDBody().setUserData(boss);
        this.box2D = box2D;
        isMovingUp = true;
    }

    @Override
    public void act(float Delta) {

        if (!boss.getJDBody().isAwake()) {
            boss.getJDBody().setLinearVelocity(new Vector2f(0, 2f));
        }

        if (boss.getJDBody().isAwake()) {
            fireProjectile();
        }

        if (((boss.getPosition().y * Box2D.PIXELS_TO_METERS) == BOSS_MAX_LIMIT) && isMovingUp) {
            boss.getJDBody().setLinearVelocity(new Vector2f(0, -2f));
            isMovingUp = !isMovingUp;
        }

        if (((boss.getPosition().y * Box2D.PIXELS_TO_METERS) == BOSS_MIN_LIMIT) && !isMovingUp) {
            boss.getJDBody().setLinearVelocity(new Vector2f(0, 2f));
            isMovingUp = !isMovingUp;
        }

        if (!boss.getJDBody().isActive()) {
            this.remove();
        }

    }

    public void fireProjectile(){
        if(boss.allowedToFire()){
            EnemyProjectileController enemyProjectileController = new EnemyProjectileController(box2D, boss.getPosition().x, boss.getPosition().y, new Vector2f(-2f, 0),1);
            getStage().addActor(enemyProjectileController);
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
