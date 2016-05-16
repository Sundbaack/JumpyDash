package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.physics.box2d.*;
<<<<<<< HEAD
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
=======
import org.chalmers.jumpydash.model.Coin;
import org.chalmers.jumpydash.model.Platform;
import org.chalmers.jumpydash.model.Player;
import org.chalmers.jumpydash.model.Trampoline;
>>>>>>> f94d1d4b5696d513f08696223791fd559929d7d7
import org.chalmers.jumpydash.model.*;
import org.chalmers.jumpydash.physics.IBox2D;
public class CollisionListener implements ContactListener {

    //PlayerCollisionListener playerCollisionListener = new PlayerCollisionListener();

    private Array<Actor> actors;

    public CollisionListener(Array<Actor> actors){
        this.actors = actors;
    }
    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        boolean playera = a.getUserData() instanceof Player;
        boolean coinb = b.getUserData() instanceof Coin;
        boolean playerb = b.getUserData() instanceof Player;
        boolean coina = a.getUserData() instanceof Coin;
        boolean platforma = a.getUserData() instanceof Platform;
        boolean platformb = b.getUserData() instanceof Platform;
        boolean soldiera = a.getUserData() instanceof Enemy;
        boolean soldierb = b.getUserData() instanceof Enemy;
        boolean bulleta = a.getUserData() instanceof Bullet;
        boolean bulletb = b.getUserData() instanceof Bullet;

        for(Actor actor : actors){

        }

        if ((playera && platformb) ||
                (platforma && playerb)) {
            if (!PlayerController.getPlayer().getJumpState()) {
                PlayerController.getPlayer().setJumpState();
            }
        }
        if ((playera && coinb) || (coina && playerb)) {
            System.out.println("funkar");
            PlayerController.getPlayer().setPoints(Coin.getValue());
        }
        if ((playera && soldierb) || soldiera && playerb) {

        }

        if (bulleta && soldiera || bulletb && soldierb) {

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