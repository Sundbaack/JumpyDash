package org.chalmers.jumpydash.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import org.chalmers.jumpydash.util.Options;

import javax.vecmath.Vector2f;

public class Player extends JDModel {

    public enum State {FALLING, STANDING, JUMPING, RUNNING}

    public State currentState;
    public State previousState;
    private float impulse;
    private boolean invincible;
    private int points;
    private int health;
    private int keys;
    private long previousFireTime;
    private long invinciblePickUpTime;
    private float playerSpeedX;
    private static final float MAX_SPEED_X = 3.5f;
    private Sound trampolineSound;

    public Player() {
        this.points = 0;
        playerSpeedX = 4;
        health = 3;
        keys = 0;
        previousFireTime = 0;
        currentState = State.RUNNING;
        previousState = State.RUNNING;
        invincible = false;
    }

    //Applies linear impulse in y-axis to make the player jump. Setting state for later use in the view class.
    public void jump() {
        getJDBody().applyLinearImpulse(new Vector2f(0, getImpulse()), getJDBody().getWorldCenter(), true);
        currentState = State.JUMPING;
        previousState = State.RUNNING;
    }

    //Check if player collides with any objects by checking the provided JDModel.
    @Override
    public void checkCollision(JDModel jDModel) {
        //Check if this JDModel is a player before proceeding.
        if (this.getClass() == Player.class) {
            //Check if JDModel is of any other class that player can collide with and do necessary calculations.
            if (jDModel.getClass() == Soldier.class) {
                if (invincible) {
                    jDModel.userDataNull();
                } else {
                    this.setHealth(-1);
                    this.applySoldierImpulse();
                }
            } else if (jDModel.getClass() == Platform.class) {
                this.currentState = State.RUNNING;
            } else if (jDModel.getClass() == Coin.class) {
                this.setPoints(Coin.getValue());
                jDModel.userDataNull();
            } else if (jDModel.getClass() == Trampoline.class) {
                if (Options.getInstance().getSound() && trampolineSound != null) {
                    trampolineSound.play(1);
                }
                this.applyTrampolineImpulse();
            } else if (jDModel.getClass() == Spike.class) {
                if (invincible) {
                    jDModel.userDataNull();
                } else {
                    this.setHealth(-(this.getHealth()));
                }
            } else if (jDModel.getClass() == Cannon.class) {
                if (invincible) {
                    jDModel.userDataNull();
                } else {
                    this.setHealth(-1);
                }
            } else if (jDModel.getClass() == SpeedUp.class) {
                this.playerSpeedUp();
                jDModel.userDataNull();
            } else if (jDModel.getClass() == EnemyProjectile.class) {
                if (!invincible) {
                    this.setHealth(-1);
                    this.applySoldierImpulse();
                }
                jDModel.userDataNull();
            } else if (jDModel.getClass() == Sensor.class) {
                Sensor sensor = ((Sensor) jDModel);
                if (sensor.getType().equalsIgnoreCase("player")) {
                    MovingPlatform.movePlatforms = !MovingPlatform.movePlatforms;
                }
            } else if (jDModel.getClass() == Heart.class) {
                if (health != 3) {
                    health += 1;
                }
                jDModel.userDataNull();
            } else if (jDModel.getClass() == Key.class) {
                this.keys += 1;
                jDModel.userDataNull();
                System.out.println(keys);
            } else if (jDModel.getClass() == LockedDoor.class) {
                if (keys != 0) {
                    jDModel.userDataNull();
                    keys = keys - 1;
                    System.out.println(keys);
                }
            } else if (jDModel.getClass() == Invincible.class) {
                jDModel.userDataNull();
                setInvincible();
                setInvinciblePickUpTime();
            }
        }
    }

    public long getInvinciblePickUpTime() {
        return invinciblePickUpTime;
    }

    public void setInvinciblePickUpTime() {
        invinciblePickUpTime = System.currentTimeMillis();
    }

    public boolean isInvincible() {
        return invincible;
    }

    public void setInvincible() {
        invincible = !invincible;
    }

    public boolean allowedToFire() {
        long fireCooldown = 250;
        if (System.currentTimeMillis() - previousFireTime >= fireCooldown) {
            setPreviousFireTime(System.currentTimeMillis());
            return true;
        }
        return false;
    }

    public void move() {
        Vector2f speed = getJDBody().getLinearVelocity();
        float speedX = speed.x;

        if (speedX < getMaxSpeedX()) {
            getJDBody().applyForceToCenter(new Vector2f(playerSpeedX, 0), true);
        }
    }

    public void applyTrampolineImpulse() {
        getJDBody().applyLinearImpulse(new Vector2f(0, getImpulse() + 2.5f), getJDBody().getWorldCenter(), true);
    }

    public void applySoldierImpulse() {
        getJDBody().applyLinearImpulse(new Vector2f(-getImpulse(), 0), getJDBody().getWorldCenter(), true);
    }

    public State getState() {
        if (this.getJDBody().getLinearVelocity().y > 0) {
            currentState = State.JUMPING;
        } else if (this.getJDBody().getLinearVelocity().y < 0) {
            currentState = State.FALLING;
        } else if (this.getJDBody().getLinearVelocity().x != 0) {
            currentState = State.RUNNING;
        } else {
            currentState = State.STANDING;
        }
        return currentState;
    }

    public boolean isDead() {
        return health == 0;
    }

    public void playerSpeedUp() {
        setSpeed(100);
    }

    private float getMaxSpeedX() {
        return MAX_SPEED_X;
    }

    private float getImpulse() {
        return this.impulse;
    }

    public void setImpulse(float impulse) {
        this.impulse = impulse;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int a) {
        points += a;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int healthChange) {
        health += healthChange;
    }

    private void setSpeed(float speedChange) {
        playerSpeedX += speedChange;
    }

    public float getSpeed() {
        return playerSpeedX;
    }

    public void setPreviousFireTime(long time) {
        previousFireTime = time;
    }

    public void setSound() {
        trampolineSound = Gdx.audio.newSound(Gdx.files.internal("sounds/trampoline.wav"));
    }

    public void dispose() {
        trampolineSound.dispose();
    }
}

