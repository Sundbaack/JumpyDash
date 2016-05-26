package org.chalmers.jumpydash.model;

import javax.vecmath.Vector2f;

public class Soldier extends Enemy {

    private boolean directionRight;
    private int count;

    public Soldier(int count) {
        super(1,1);
        this.count = count;
        directionRight = true;
    }

    public void setDirectionFlag(){
        directionRight = !directionRight;
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
        if(getDirection()){
            getJDBody().applyForceToCenter(new Vector2f(2f,0), true);
        } else{
            getJDBody().applyForceToCenter(new Vector2f(-2f,0), true);
        }
    }
}
