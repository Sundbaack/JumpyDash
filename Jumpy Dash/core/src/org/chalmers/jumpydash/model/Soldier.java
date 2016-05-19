package org.chalmers.jumpydash.model;

import javax.vecmath.Vector2f;

public class Soldier extends Enemy {

    private boolean directionRight;

    public Soldier() {
        super(1,1);
        directionRight = true;
    }

    public void setDirectionFlag(){
        directionRight = !directionRight;
    }

    public boolean getDirection(){
        return directionRight;
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
