package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.physics.box2d.*;
import org.chalmers.projectrolf.model.Platform;

public class CollisionListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        // Check to see if the collision is between the the player and a platform
	/*	for (Platform p: PlatformController.getPlatforms()) {

            if ((fa.getBody() == PlayerController.getPlayer().getJDBody().getBody() &&
			    fb.getBody() == p.getJDBody().getBody())
				||
			    (fa.getBody() == p.getJDBody().getBody() &&
						fb.getBody() == PlayerController.getPlayer().getJDBody().getBody())) {

                if (!PlayerController.getPlayer().getJumpState()) {
                    PlayerController.getPlayer().setJumpState();
                }
			}
		}

        /*
		for (Soldier s: soldierList){
		    if((contact.getFixtureA().getJDBody() == player.getJDBody() &&
                contact.getFixtureB().getJDBody() == s.getJDBody())
                ||
                (contact.getFixtureA().getJDBody() == s.getJDBody() &&
                    contact.getFixtureB().getJDBody() == player.getJDBody())) {
                        System.out.println("Ajabaja nu dog du");
            }
		}

        for(Coin c: coinList){
            if((contact.getFixtureA().getJDBody() == player.getJDBody() &&
               contact.getFixtureB().getJDBody() == c.getJDBody())
               ||
               (contact.getFixtureA().getJDBody() == c.getJDBody() &&
                    contact.getFixtureB().getJDBody() == player.getJDBody())) {
                        player.setPoints(c.getValue());
						coinList.remove(coinList.indexOf(c));

            }
        }
        */
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