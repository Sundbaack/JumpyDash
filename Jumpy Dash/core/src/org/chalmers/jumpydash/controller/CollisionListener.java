package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.physics.box2d.*;
import org.chalmers.jumpydash.model.Coin;
import org.chalmers.jumpydash.model.Platform;
import org.chalmers.jumpydash.model.Player;
import org.chalmers.jumpydash.model.Trampoline;
import org.chalmers.jumpydash.model.*;
import org.chalmers.jumpydash.physics.IBox2D;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollisionListener implements ContactListener {

    private PlayerCollisionListener playerCollisionListener;
    private BulletCollisionListener soldierCollisionListener;
    private List<ContactListener> collisionListenerList;

    private IBox2D box2D;

    public CollisionListener(IBox2D box2D) {
        collisionListenerList = new ArrayList<ContactListener>();
        this.box2D = box2D;
        playerCollisionListener = new PlayerCollisionListener(box2D);
        soldierCollisionListener = new BulletCollisionListener(box2D);

        collisionListenerList.add(playerCollisionListener);
        collisionListenerList.add(soldierCollisionListener);
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