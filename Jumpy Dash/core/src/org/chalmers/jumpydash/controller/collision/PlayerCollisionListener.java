package org.chalmers.jumpydash.controller.collision;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import org.chalmers.jumpydash.controller.Options;
import org.chalmers.jumpydash.controller.PlayerController;
import org.chalmers.jumpydash.model.*;

public class PlayerCollisionListener extends JDCollision {

    private PlayerController playerController;
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
    private boolean sensorA;
    private boolean sensorB;
    private boolean enemyProjectileA;
    private boolean enemyProjectileB;
    private boolean cannonA;
    private boolean cannonB;
    private Sound powerUpSound;
    private Sound trampolineSound;
    private Sound sound;
    private Sensor sensor;

    public PlayerCollisionListener(PlayerController playerController) {
        this.playerController = playerController;
        trampolineSound = Gdx.audio.newSound(Gdx.files.internal("sounds/trampoline.wav"));
        powerUpSound = Gdx.audio.newSound(Gdx.files.internal("sounds/powerup.wav"));
        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/coin.wav"));
    }

    // Determine type of the two colliding bodies
    private void checkInstance(Body a, Body b) {
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
        sensorA = a.getUserData() instanceof Sensor;
        sensorB = b.getUserData() instanceof  Sensor;
        enemyProjectileA = a.getUserData() instanceof EnemyProjectile;
        enemyProjectileB = b.getUserData() instanceof EnemyProjectile;
        cannonA = b.getUserData() instanceof EnemyProjectile;
        cannonB = b.getUserData() instanceof EnemyProjectile;
    }

    // Determine who is colliding with who
    private void checkCollision(Body a, Body b) {
        if ((playerA && platformB) ||
                (platformA && playerB)) {
                playerController.getPlayer().currentState = Player.State.RUNNING;
        }
        //Check collision between player and coin
        if ((playerA && coinB)) {
            playerController.getPlayer().setPoints(Coin.getValue());
            b.setUserData(null);
            if (Options.getInstance().getSound()) {
                sound.play(1);
            }
        } else if ((coinA && playerB)) {
            playerController.getPlayer().setPoints(Coin.getValue());
            a.setUserData(null);
            if (Options.getInstance().getSound()) {
                sound.play(1);
            }
        }

        //Check collision between player and soldier
        if ((playerA && soldierB) || (soldierA && playerB)) {
            playerController.getPlayer().setDamage(1);
            playerController.getPlayer().applySoldierImpulse();
        }

        //damage is not working properly here
        //Check collision between player and cannon
        if ((playerA && cannonB) || (cannonA && playerB)) {
            playerController.getPlayer().setDamage(1);
        }

        //damage is not working properly here, projectile collision is triggering cannon collision
        //Check collision between enemyprojectile and player
        if ((playerA && enemyProjectileB) || (enemyProjectileA && playerB)) {
            playerController.getPlayer().setDamage(1);
            playerController.getPlayer().applySoldierImpulse();
            if(enemyProjectileA){
                b.setUserData(null);
            }
            else if(enemyProjectileB){
                b.setUserData(null);
            }
        }
        //Check collision between player and trampoline
        if (playerA && trampolineB || trampolineA && playerB) {
            if (!playerController.getPlayer().getJumpState()) {
                playerController.getPlayer().setJumpState();
            }
            playerController.getPlayer().applyTrampolineImpulse();
            if (Options.getInstance().getSound()) {
                trampolineSound.play(1);
            }
        }

        //Check collision between player and spike
        if (playerA && spikeB || spikeA && playerB) {
            playerController.getPlayer().setDamage(playerController.getPlayer().getHealth());
        }


        if((playerA && sensorB) || (sensorA && playerB)){
            if(sensorA){
                sensor = ((Sensor) a.getUserData());
                if(sensor.getType().equalsIgnoreCase("player")){
                    MovingPlatform.movePlatforms = !MovingPlatform.movePlatforms;
                }
            }
            else if(sensorB){
                sensor = ((Sensor) b.getUserData());
                if(sensor.getType().equalsIgnoreCase("player")){
                    MovingPlatform.movePlatforms = !MovingPlatform.movePlatforms;
                }
            }

        }
        //Check collision between player and SpeedUp
        if ((playerA && speedUpB)) {
            playerController.getPlayer().playerSpeedUp();
            b.setUserData(null);
            if (Options.getInstance().getSound()) {
                powerUpSound.play(1);
            }
        } else if ((speedUpA && playerB)) {
            playerController.getPlayer().playerSpeedUp();
            a.setUserData(null);
            if (Options.getInstance().getSound()) {
                powerUpSound.play(1);
            }
        }
    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();
        checkInstance(a,b);
        checkCollision(a,b);
    }
}
