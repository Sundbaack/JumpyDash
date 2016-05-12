package org.chalmers.projectrolf.model;

import org.chalmers.projectrolf.physics.IJDBody;

import javax.vecmath.Vector2f;

public class Ability extends Item {

    private IJDBody jdBody;

    public Ability(IJDBody jdBody){
        this.jdBody = jdBody;
        this.jdBody.setUserData(this);
    }

    public Vector2f getPosition() {
        return jdBody.toVector2f(jdBody.getPosition());
    }

}
