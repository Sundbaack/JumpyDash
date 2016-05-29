package org.chalmers.jumpydash.model;

import javax.vecmath.Vector2f;

public class Soldier extends Enemy {

    public enum State {RIGHT, LEFT}

    private State currentState;
    private static final float MAX_SPEED_X = 3.5f;
    private float soldierSpeedX;


    public Soldier() {
        super(1, 1);
        soldierSpeedX = 4;
        currentState = State.RIGHT;

    }

    @Override
    public void checkCollision(JDModel jDModel) {
        if (this.getClass() == Soldier.class) {
            if (jDModel.getClass() == Sensor.class) {
                if (currentState == State.RIGHT) {
                    currentState = State.LEFT;

                } else if (currentState == State.LEFT) {
                    currentState = State.RIGHT;
                }
            }
            if (jDModel.getClass() == Bullet.class) {
                this.userDataNull();
                jDModel.userDataNull();
                System.out.println("bullet hit soldier");
            }
        }
    }

    public void jump() {
        getJDBody().applyLinearImpulse(new Vector2f(0, 3f), getJDBody().getWorldCenter(), true);
    }

    public void move() {
        // Checks in what direction the soldier should move
        Vector2f speed = getJDBody().getLinearVelocity();
        float speedX = speed.x;

        if (currentState == State.RIGHT) {
            if (speedX < getMaxSpeedX()) {
                getJDBody().applyForceToCenter(new Vector2f(soldierSpeedX, 0), true);
            }
        } else {
            if (speedX < getMaxSpeedX()) {
                getJDBody().applyForceToCenter(new Vector2f(-soldierSpeedX, 0), true);
            }
        }

    }
    private float getMaxSpeedX() {
        return MAX_SPEED_X;
    }
}
