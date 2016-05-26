package org.chalmers.jumpydash.model;

import javax.vecmath.Vector2f;

public class Soldier extends Enemy {
    public enum State {RIGHT,LEFT}
    private State currentState;
    private boolean directionRight;
    private int count;

    public Soldier(int count) {
        super(1,1);
        this.count = count;
        currentState = State.RIGHT;
        directionRight = true;
    }

    public void setDirectionFlag(){
        directionRight = !directionRight;
    }

    public void checkCollision(JDModel jDModelB){
        if(this.getClass() == Soldier.class) {
            if (jDModelB.getClass() == Sensor.class) {
                if (currentState == State.RIGHT) {
                    currentState = State.LEFT;
                    setDirectionFlag();
                } else if (currentState == State.LEFT && getDirection()) {
                    currentState = State.RIGHT;
                }
            }
            else if(jDModelB.getClass() == Bullet.class){
                this.userDataNull();
                jDModelB.userDataNull();
            }
        }
    }

    public boolean getDirection(){
        return directionRight;
    }

    public int getCount(){
        return count;
    }

    public void jump(){
        getJDBody().applyLinearImpulse(new Vector2f(0, 3f), getJDBody().getWorldCenter(), true);
    }

    public void move() {
        // Checks in what direction the soldier should move
        if(currentState == State.RIGHT){
            getJDBody().applyForceToCenter(new Vector2f(2f,0), true);
        } else{
            getJDBody().applyForceToCenter(new Vector2f(-2f,0), true);
        }
    }
}
