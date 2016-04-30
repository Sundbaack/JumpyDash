package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class CollisionListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        /*
        // Check to see if the collision is between the the player and a platform
		for (Platform p: platformList) {
		    if ((contact.getFixtureA().getBody() == player.getBody() &&
			    contact.getFixtureB().getBody() == p.getBody())
				||
			    (contact.getFixtureA().getBody() == p.getBody() &&
						contact.getFixtureB().getBody() == player.getBody())) {
				player.setJumpState();

			}
		}

		for (Soldier s: soldierList){
		    if((contact.getFixtureA().getBody() == player.getBody() &&
                contact.getFixtureB().getBody() == s.getBody())
                ||
                (contact.getFixtureA().getBody() == s.getBody() &&
                    contact.getFixtureB().getBody() == player.getBody())) {
                        System.out.println("Ajabaja nu dog du");
            }
		}

        for(Coin c: coinList){
            if((contact.getFixtureA().getBody() == player.getBody() &&
               contact.getFixtureB().getBody() == c.getBody())
               ||
               (contact.getFixtureA().getBody() == c.getBody() &&
                    contact.getFixtureB().getBody() == player.getBody())) {
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