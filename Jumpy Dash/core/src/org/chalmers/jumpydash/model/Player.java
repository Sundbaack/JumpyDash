package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.physics.IJDBody;
import javax.vecmath.Vector2f;

public class Player {

    private IJDBody jdBody;
    private float impulse;
    private boolean jumpFlag;
    private static int points;
    private static int health = 3;
    private static long previousFireTime;
    private static final float MAX_SPEED_X = 3.5f;
    private Vector2f vector2f;


    public Player(IJDBody jdBody) {
        this.jdBody = jdBody;
        jumpFlag = false;

        jdBody.setUserData(this);
        setImpulse(jdBody.getMass() * 6f);
        this.points = 0;
    }

    public void jump() {
        vector2f = new Vector2f(0,getImpulse());
        jdBody.applyLinearImpulse(jdBody.toVector2(vector2f), jdBody.getWorldCenter(), true);
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

        Vector2f speed = jdBody.toVector2f(jdBody.getLinearVelocity());
        float speedX = speed.x;

        if (speedX < getMaxSpeedX()) {
            jdBody.applyForceToCenter(jdBody.toVector2(new Vector2f(4,0)), true);
        }
    }

    public void applyTrampolineImpulse() {
        vector2f = new Vector2f(0, getImpulse() + 2.5f);
        jdBody.applyLinearImpulse(jdBody.toVector2(vector2f), jdBody.getWorldCenter(), true);
    }

    public void applySoldierImpulse(){
        vector2f = new Vector2f(-getImpulse(),0);
        jdBody.applyLinearImpulse(jdBody.toVector2(vector2f),jdBody.getWorldCenter(),true);
    }

    public Vector2f getPosition() {
        return jdBody.toVector2f(jdBody.getPosition());
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

}

