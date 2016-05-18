package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.physics.box2d.*;
import org.chalmers.jumpydash.model.Coin;
import org.chalmers.jumpydash.model.Platform;
import org.chalmers.jumpydash.model.Player;
import org.chalmers.jumpydash.model.Trampoline;
import org.chalmers.jumpydash.model.*;
import org.chalmers.jumpydash.physics.IBox2D;

import java.util.ArrayList;
import java.util.List;

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
    private boolean trampolineA;
    private boolean trampolineB;
    private boolean spikeA;
    private boolean spikeB;

    private boolean speedUpA;
    private boolean speedUpB;

    private IBox2D box2D;
    private List<Body> bodiesToBeDestroyed;

    public CollisionListener(IBox2D box2D) {
        this.box2D = box2D;
        bodiesToBeDestroyed = new ArrayList<Body>();
        box2D.setBodiesToBeDestroyed(bodiesToBeDestroyed);
    }

    @Override
    public void beginContact(Contact contact) {
        boolean sensorAA = contact.getFixtureA().isSensor();
        boolean sensorBB = contact.getFixtureB().isSensor();

        //Bodies to be used for contact
        a = contact.getFixtureA().getBody();
        b = contact.getFixtureB().getBody();

        //Check what instance the userdata is
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
        trampolineA = a.getUserData() instanceof Trampoline;
        trampolineB = b.getUserData() instanceof Trampoline;
        spikeA = a.getUserData() instanceof Spike;
        spikeB = b.getUserData() instanceof Spike;
        speedUpA = a.getUserData() instanceof SpeedUp;
        speedUpB = b.getUserData() instanceof SpeedUp;



        //Check collision between player and platform
        if ((playerA && platformB) ||
                (platformA && playerB)) {
            if (!PlayerController.getPlayer().getJumpState()) {
                PlayerController.getPlayer().setJumpState();
            }
        }
        //Check collision between player and coin
        if ((playerA && coinB)) {
            PlayerController.getPlayer().setPoints(Coin.getValue());
            bodiesToBeDestroyed.add(b);
        } else if ((coinA && playerB)) {
            PlayerController.getPlayer().setPoints(Coin.getValue());
            bodiesToBeDestroyed.add(a);
        }

        //Check collision between player and soldier
        if ((playerA && soldierB) || (soldierA && playerB)) {
            PlayerController.getPlayer().setDamage(1);
            PlayerController.getPlayer().applySoldierImpulse();
        }
        //Check collision between bullet and soldier
        if (bulletA && soldierA || bulletB && soldierB) {

        }
        //Check collision between player and trampoline
        if (playerA && trampolineB || trampolineA && playerB) {
            PlayerController.getPlayer().setJumpState();
            PlayerController.getPlayer().applyTrampolineImpulse();
        }
        //Check collision between player and spike
        if (playerA && spikeB || spikeA && playerB) {
            PlayerController.getPlayer().setDamage(PlayerController.getPlayer().getHealth());
        }
        //Check collision between player and SpeedUp
        if ((playerA && speedUpB)) {
            PlayerController.getPlayer().playerSpeedUp();
            bodiesToBeDestroyed.add(b);f
        } else if ((speedUpA && playerB)) {
            PlayerController.getPlayer().playerSpeedUp();
            bodiesToBeDestroyed.add(a);
        }

        if(sensorAA) {
            System.out.println("sensora");
            if (playerB) {
                System.out.println("ContactB");
            }
        }
        if(sensorBB){
            System.out.println("sensorb");
               if(playerA){
                   System.out.println("ContactA");
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