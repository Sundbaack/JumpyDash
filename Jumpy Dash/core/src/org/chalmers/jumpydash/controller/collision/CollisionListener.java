package org.chalmers.jumpydash.controller.collision;

import com.badlogic.gdx.physics.box2d.*;
import org.chalmers.jumpydash.physics.IBox2D;
import java.util.ArrayList;
import java.util.List;

public class CollisionListener implements ContactListener {

    private List<ContactListener> collisionListenerList;

    public CollisionListener(IBox2D box2D) {
        collisionListenerList = new ArrayList<ContactListener>();

        // Add separate contact listeners
        collisionListenerList.add(new PlayerCollisionListener(box2D));
        collisionListenerList.add(new BulletCollisionListener(box2D));
    }

    @Override
    public void beginContact(Contact contact) {
       for(ContactListener c: collisionListenerList){
            c.beginContact(contact);
        }
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