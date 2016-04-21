package com.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Johannes on 2016-04-18.
 */
public class Soldier extends Enemy {
    private float impulse;
    private boolean directionFlag = true;

    public Soldier(Body body) {
        super(body);

    }

    public void move() {
        if(directionFlag){//checks in what direction the soldier should move
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
