package org.chalmers.jumpydash.controller.collision;

import com.badlogic.gdx.physics.box2d.*;
import org.chalmers.jumpydash.model.Bullet;
import org.chalmers.jumpydash.model.Cannon;
import org.chalmers.jumpydash.model.Soldier;
import org.chalmers.jumpydash.physics.IBox2D;

public class BulletCollisionListener implements ContactListener {

    private boolean bulletA;
    private boolean bulletB;
    private boolean cannonA;
    private boolean cannonB;
    private boolean soldierA;
    private boolean soldierB;

    private IBox2D box2D;

    public BulletCollisionListener(IBox2D box2D){
        this.box2D = box2D;
    }

    private void checkInstance(Body a, Body b){
        bulletA = a.getUserData() instanceof Bullet;
        bulletB = b.getUserData() instanceof Bullet;
        cannonA = a.getUserData() instanceof Cannon;
        cannonB = b.getUserData() instanceof Cannon;
        soldierA = a.getUserData() instanceof Soldier;
        soldierB = b.getUserData() instanceof Soldier;
    }

    private void checkCollision(Body a, Body b){
        if (bulletB && soldierA || bulletA && soldierB) {
            box2D.getBodiesToBeDestroyed().add(a);
            box2D.getBodiesToBeDestroyed().add(b);
        }

        //Check collision between bullet and cannon
        if (bulletA && cannonB) {
            box2D.getBodiesToBeDestroyed().add(b);
        } else if(bulletB && cannonA){
            box2D.getBodiesToBeDestroyed().add(a);
        }
    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();
        checkInstance(a,b);
        checkCollision(a,b);

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
