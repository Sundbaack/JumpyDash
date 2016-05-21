package org.chalmers.jumpydash.model;

import javax.vecmath.Vector2f;

public class Player extends JDModel {

    private float impulse;
    private boolean jumpFlag;
    private static int points;
    private static int health;
    private static long previousFireTime;
    private static final float MAX_SPEED_X = 3.5f;
    private float playerSpeedX;

    public Player() {
        this.points = 0;

        playerSpeedX = 4;

        jumpFlag = false;
        health = 3;
        previousFireTime = 0;
        points = 0;
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

    public static float getMaxSpeedX() {
        return MAX_SPEED_X;
    }

    public float getImpulse() {
        return this.impulse;
    }

    public void setImpulse(float impulse) {
        this.impulse = impulse;
    }

    public static int getPoints() {
        return points;
    }

    public static void setPoints(int a) {
        points += a;
    }

    public static int getHealth() { return health; }

    public static void setDamage(int damage) { health = health - damage; }

    public void setSpeed(float speedIncrease) {
        playerSpeedX += speedIncrease;
    }

    public float getSpeed(){
        return playerSpeedX;
    }

}

