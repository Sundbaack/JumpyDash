package org.chalmers.projectrolf.model;

import com.badlogic.gdx.math.Vector2;
import org.chalmers.projectrolf.physics.IJDBody;

public class Player {

    private IJDBody jdBody;
    private float impulse;
    private boolean jumpFlag;
    private static int points;
    private static final float MAX_SPEED_X = 3.5f;

    public Player(IJDBody jdBody) {
        this.jdBody = jdBody;
        jumpFlag = false;

        jdBody.setUserData(this);
        setImpulse(jdBody.getMass() * 6f);
        this.points = 0;
    }


    public void jump() {
        jdBody.applyLinearImpulse(new Vector2(0, getImpulse()), jdBody.getWorldCenter(), true);
    }

    public void move() {

        Vector2 speed = jdBody.getLinearVelocity();
        float speedX = speed.x;

        if (speedX < getMaxSpeedX()) {
            jdBody.applyForceToCenter(new Vector2(4, 0), true);
        }
    }

    public Vector2 getPosition() {
        return jdBody.getPosition();
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
}

