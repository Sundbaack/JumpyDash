package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.projectrolf.model.Coin;
import org.chalmers.projectrolf.model.Platform;
import org.chalmers.projectrolf.model.Player;

public class CollisionListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();
        if ((a.getUserData() instanceof Player && b.getUserData() instanceof Platform)
                || (a.getUserData() instanceof Platform && b.getUserData() instanceof Player)) {
            if (!PlayerController.getPlayer().getJumpState()) {
                PlayerController.getPlayer().setJumpState();
            }
        }
        if(a.getUserData() instanceof Player && b.getUserData() instanceof Coin || b.getUserData() instanceof Coin && a.getUserData() instanceof Player){
            Player.setPoints(Coin.getValue());
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