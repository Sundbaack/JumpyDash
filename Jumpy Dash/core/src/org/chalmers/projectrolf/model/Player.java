package org.chalmers.projectrolf.model;

public class Player {

    private float impulse;
    private boolean jumpFlag;
    private static int points;
    private static final float MAX_SPEED_X = 3.5f;

    public Player() {
        jumpFlag = false;
        this.points = 0;
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

