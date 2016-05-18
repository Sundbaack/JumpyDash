package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.physics.IJDBody;
import javax.vecmath.Vector2f;

public class Soldier extends Enemy {

    private boolean directionFlag;

    public Soldier(IJDBody jdBody) {
        super(jdBody,1,1);
        jdBody.setUserData(this);
        directionFlag=true;
    }

    public void setDirectionFlag(){
        directionFlag = !directionFlag;
    }

    public boolean getDirectionFlag(){
        return directionFlag;
    }

    public void move() {
        // Checks in what direction the soldier should move
        if(getDirectionFlag()){
            getJdBody().applyForceToCenter(getJdBody().toVector2(new Vector2f(2f,0)), true);
        }
        else{
            getJdBody().applyForceToCenter(getJdBody().toVector2(new Vector2f(-2f,0)), true);
        }
    }
}