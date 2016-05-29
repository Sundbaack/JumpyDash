package org.chalmers.jumpydash.model;

import javax.vecmath.Vector2f;

public class Soldier extends Enemy {

    public enum State {RIGHT, LEFT}

    private State currentState;

    private int count;

    public Soldier(int count) {
        super(1, 1);
        this.count = count;
        currentState = State.RIGHT;

    }

    @Override
    public void checkCollision(JDModel jDModel) {
        if (this.getClass() == Soldier.class) {
            if (jDModel.getClass() == Sensor.class) {
                setCurrentState();
            }
            if (jDModel.getClass() == Bullet.class) {
                this.takeDamage(1);
                jDModel.userDataNull();
                if(isDead()){
                    this.userDataNull();
                }
            }
        }
    }

    public void jump() {
        getJDBody().applyLinearImpulse(new Vector2f(0, 3f), getJDBody().getWorldCenter(), true);
    }

    public void move() {
        // Checks in what direction the soldier should move
        if (currentState == State.RIGHT) {
            getJDBody().applyForceToCenter(new Vector2f(2f, 0), true);
        } else {
            getJDBody().applyForceToCenter(new Vector2f(-2f, 0), true);
        }
    }

    public void setCurrentState(){
        if (currentState == State.RIGHT) {
            currentState = State.LEFT;

        } else if (currentState == State.LEFT) {
            currentState = State.RIGHT;
        }
    }
}
