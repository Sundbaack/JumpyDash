package main.java.org.chalmers.jumpydash.jumpydash.model;

import javax.vecmath.Vector2f;

public class Soldier extends Enemy {

    public enum State {RIGHT, LEFT}

    private State currentState;
    private boolean directionRight;
    private int count;

    public Soldier(int count) {
        super(1, 1);
        this.count = count;
        currentState = State.RIGHT;
        directionRight = true;
    }

    public void setDirectionFlag() {
        directionRight = !directionRight;
    }

    @Override
    public void checkCollision(JDModel jDModel) {
        if (this.getClass() == Soldier.class) {
            if (jDModel.getClass() == Sensor.class) {
                if (currentState == State.RIGHT) {
                    currentState = State.LEFT;
                    setDirectionFlag();
                } else if (currentState == State.LEFT && getDirection()) {
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

    public boolean getDirection() {
        return directionRight;
    }

    public int getCount() {
        return count;
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
}
