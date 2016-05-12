package org.chalmers.projectrolf.model;

import com.badlogic.gdx.math.Vector2;
import org.chalmers.projectrolf.physics.IBox2D;
import org.chalmers.projectrolf.physics.IJDBody;

public class Soldier extends Enemy {

    private IJDBody jdBody;
    private boolean directionFlag;

    public Soldier(IJDBody jdBody) {
        this.jdBody = jdBody;
        this.jdBody.setUserData(this);
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
            jdBody.applyForceToCenter(new Vector2(50f, 0), true);
        }
        else{
            jdBody.applyForceToCenter(new Vector2(-50f, 0), true);
        }
    }

    public Vector2 getPosition(){
        return jdBody.getPosition();
    }

}
