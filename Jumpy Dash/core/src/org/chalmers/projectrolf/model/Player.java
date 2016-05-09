package org.chalmers.projectrolf.model;

import com.badlogic.gdx.math.Vector2;

public class Player {

    private JDBody body;
    private float impulse;
    private boolean jumpFlag;
    private int points;
    private static final float MAX_SPEED_X = 5.5f;

    public Player(JDBody body) {
        this.body = body;
        this.body.setUserData(this);
        jumpFlag = false;
        this.points = 0;

        this.impulse = this.body.getMass() * 6f;
    }

    public void setJumpState(){
        this.jumpFlag = !jumpFlag;
    }

    public boolean getJumpState() {
        return this.jumpFlag;
    }

    public void jump() {
        getJDBody().applyLinearImpulse(new Vector2(0, getImpulse()), getJDBody().getWorldCenter(), true);
    }

    public void move() {

        Vector2 speed = getJDBody().getLinearVelocity();
        float speedX = speed.x;

        if (speedX < MAX_SPEED_X) {
            getJDBody().applyForceToCenter(new Vector2(6, 0), true);
        }
    }

    public float getImpulse() {
        return this.impulse;
    }

    public JDBody getJDBody() {
        return this.body;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int a) {
        this.points += a;
    }

    public Vector2 getPosition() {
        return this.body.getPosition();
    }
}

