package org.chalmers.projectrolf.model;

import com.badlogic.gdx.math.Vector2;

public class Soldier extends Enemy {

    private boolean directionFlag = true;

    public Soldier(JDBody body) {
        super(body);
    }

    public void move() {
        // Checks in what direction the soldier should move
        if(directionFlag){
            this.getBody().applyForceToCenter(new Vector2(50f, 0), true);
        }
        else{
            this.getBody().applyForceToCenter(new Vector2(-50f, 0), true);
        }
    }

    public void setDirectionFlag(){
        directionFlag = !directionFlag;
    }

}
