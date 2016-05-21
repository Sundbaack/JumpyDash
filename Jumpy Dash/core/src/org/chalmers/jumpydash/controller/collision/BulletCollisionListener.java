package org.chalmers.jumpydash.controller.collision;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import org.chalmers.jumpydash.model.Bullet;
import org.chalmers.jumpydash.model.Cannon;
import org.chalmers.jumpydash.model.Soldier;

public class BulletCollisionListener extends Collision {

    private boolean bulletA;
    private boolean bulletB;
    private boolean cannonA;
    private boolean cannonB;
    private boolean soldierA;
    private boolean soldierB;

    // Determine type of the two colliding bodies
    private void checkInstance(Body a, Body b) {
        bulletA = a.getUserData() instanceof Bullet;
        bulletB = b.getUserData() instanceof Bullet;
        cannonA = a.getUserData() instanceof Cannon;
        cannonB = b.getUserData() instanceof Cannon;
        soldierA = a.getUserData() instanceof Soldier;
        soldierB = b.getUserData() instanceof Soldier;
    }

    // Determine who is colliding with who
    private void checkCollision(Body a, Body b) {
        //Check collision between bullet and soldier
        if (bulletB && soldierA || bulletA && soldierB) {
            a.setUserData(null);
            b.setUserData(null);
        }

        //Check collision between bullet and cannon
        if (bulletA && cannonB) {
            b.setUserData(null);
        } else if (bulletB && cannonA) {
            a.setUserData(null);
        }
    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();
        checkInstance(a,b);
        checkCollision(a,b);
    }
}
