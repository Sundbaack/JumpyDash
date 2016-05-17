package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import org.chalmers.jumpydash.model.Coin;
import org.chalmers.jumpydash.model.Platform;
import org.chalmers.jumpydash.model.Player;
import org.chalmers.jumpydash.model.Trampoline;
import org.chalmers.jumpydash.model.*;
public class CollisionListener implements ContactListener {

    private Body a;
    private Body b;

    private boolean playerA;
    private boolean coinB;
    private boolean playerB;
    private boolean coinA;
    private boolean platformA;
    private boolean platformB;
    private boolean soldierA;
    private boolean soldierB;
    private boolean bulletA;
    private boolean bulletB;

    @Override
    public void beginContact(Contact contact) {

        //Bodies to be used for contact
        a = contact.getFixtureA().getBody();
        b = contact.getFixtureB().getBody();

        //Checks what instance the userdata is
        playerA = a.getUserData() instanceof Player;
        coinB = b.getUserData() instanceof Coin;
        playerB = b.getUserData() instanceof Player;
        coinA = a.getUserData() instanceof Coin;
        platformA = a.getUserData() instanceof Platform;
        platformB = b.getUserData() instanceof Platform;
        soldierA = a.getUserData() instanceof Enemy;
        soldierB = b.getUserData() instanceof Enemy;
        bulletA = a.getUserData() instanceof Bullet;
        bulletB = b.getUserData() instanceof Bullet;
        

        //Checks collision between player and platform
        if ((playerA && platformB) ||
                (platformA && playerB)) {
            if (!PlayerController.getPlayer().getJumpState()) {
                PlayerController.getPlayer().setJumpState();
            }
        }
        //Checks collision between player and coin
        if ((playerA && coinB) || (coinA && playerB)) {
            PlayerController.getPlayer().setPoints(Coin.getValue());
        }

        //Checks collision between player and soldier
        if ((playerA && soldierB) || (soldierA && playerB)) {
            if(PlayerController.getPlayer().getHealth() > 1) {
                PlayerController.getPlayer().setHealth(1);
                PlayerController.getPlayer().applySoldierImpulse();
            }else {
                System.out.println("you are dead");
            }
        }
        //Checks collision between bullet and soldier
        if (bulletA && soldierA || bulletB && soldierB) {

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