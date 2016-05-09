package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.physics.box2d.*;
import org.chalmers.projectrolf.model.Platform;
import org.chalmers.projectrolf.model.Player;

public class CollisionListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();
        if (a.getUserData() instanceof Player && b.getUserData() instanceof Platform || b.getUserData() instanceof Platform && a.getUserData() instanceof Player){
            System.out.println("Working");
            if (!PlayerController.getPlayer().getJumpState()) {
                PlayerController.getPlayer().setJumpState();
            }

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