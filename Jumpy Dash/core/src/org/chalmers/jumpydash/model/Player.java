package org.chalmers.jumpydash.model;

import javax.vecmath.Vector2f;

public class Player extends JDModel {

    private float impulse;
    private boolean jumpFlag;
    private int points;
    private int health;
    private long previousFireTime;
    private float playerSpeedX;
    private final float MAX_SPEED_X = 3.5f;

    public Player() {
        this.points = 0;
        playerSpeedX = 4;
        jumpFlag = false;
        health = 3;
        previousFireTime = 0;
    }

    public void jump() {
        getJDBody().applyLinearImpulse(new Vector2f(0,getImpulse()), getJDBody().getWorldCenter(), true);
    }

    public boolean allowedToFire(){
        long fireCooldown = 250;
        if (System.currentTimeMillis() - previousFireTime >= fireCooldown) {
            previousFireTime = System.currentTimeMillis();
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

    public void applySoldierImpulse(){
        getJDBody().applyLinearImpulse(new Vector2f(-getImpulse(),0), getJDBody().getWorldCenter(), true);
    }

    public void playerSpeedUp() {
        setSpeed(100);
    }

    public void setJumpState(){
        this.jumpFlag = !jumpFlag;
    }

    public boolean getJumpState() {
        return this.jumpFlag;
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

    public int getHealth() { return health; }

    public void setDamage(int damage) { health = health - damage; }

    private void setSpeed(float speedIncrease) {
        playerSpeedX += speedIncrease;
    }

    public float getSpeed(){
        return playerSpeedX;
    }

}

