package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.physics.box2d.*;
import org.chalmers.jumpydash.model.*;
import org.chalmers.jumpydash.physics.IBox2D;

import javax.swing.*;

/**
 * Created by Surface pro 3 on 2016-05-19.
 */
public class PlayerCollisionListener implements ContactListener{

    private boolean playerA;
    private boolean coinB;
    private boolean playerB;
    private boolean coinA;
    private boolean platformA;
    private boolean platformB;
    private boolean soldierA;
    private boolean soldierB;
    private boolean trampolineA;
    private boolean trampolineB;
    private boolean spikeA;
    private boolean spikeB;
    private boolean speedUpA;
    private boolean speedUpB;

    private IBox2D box2D;

    public PlayerCollisionListener(IBox2D box2D){
        this.box2D = box2D;
    }

    private void checkInstance(Body a, Body b){
        playerA = a.getUserData() instanceof Player;
        coinB = b.getUserData() instanceof Coin;
        playerB = b.getUserData() instanceof Player;
        coinA = a.getUserData() instanceof Coin;
        platformA = a.getUserData() instanceof Platform;
        platformB = b.getUserData() instanceof Platform;
        soldierA = a.getUserData() instanceof Soldier;
        soldierB = b.getUserData() instanceof Soldier;
        trampolineA = a.getUserData() instanceof Trampoline;
        trampolineB = b.getUserData() instanceof Trampoline;
        spikeA = a.getUserData() instanceof Spike;
        spikeB = b.getUserData() instanceof Spike;
        speedUpA = a.getUserData() instanceof SpeedUp;
        speedUpB = b.getUserData() instanceof SpeedUp;
    }

    private void checkCollision(Body a, Body b){
        if ((playerA && platformB) ||
                (platformA && playerB)) {
            if (!PlayerController.getPlayer().getJumpState()) {
                PlayerController.getPlayer().setJumpState();
            }
        }
        //Check collision between player and coin
        if ((playerA && coinB)) {
            PlayerController.getPlayer().setPoints(Coin.getValue());
            box2D.getBodiesToBeDestroyed().add(b);
        } else if ((coinA && playerB)) {
            PlayerController.getPlayer().setPoints(Coin.getValue());
            box2D.getBodiesToBeDestroyed().add(a);
        }

        //Check collision between player and soldier
        if ((playerA && soldierB) || (soldierA && playerB)) {
            PlayerController.getPlayer().setDamage(1);
            PlayerController.getPlayer().applySoldierImpulse();
        }
        //Check collision between player and trampoline
        if (playerA && trampolineB || trampolineA && playerB) {
            if (!PlayerController.getPlayer().getJumpState()) {
                PlayerController.getPlayer().setJumpState();
            }
            PlayerController.getPlayer().applyTrampolineImpulse();
        }

        //Check collision between player and spike
        if (playerA && spikeB || spikeA && playerB) {
            PlayerController.getPlayer().setDamage(PlayerController.getPlayer().getHealth());
        }

        //Check collision between player and SpeedUp
        if ((playerA && speedUpB)) {
            PlayerController.getPlayer().playerSpeedUp();
            box2D.getBodiesToBeDestroyed().add(b);
        } else if ((speedUpA && playerB)) {
            PlayerController.getPlayer().playerSpeedUp();
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
