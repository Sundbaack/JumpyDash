package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.physics.IJDBody;

import javax.vecmath.Vector2f;

public class Ability extends Item {

    private IJDBody jdBody;

    public Ability(IJDBody jdBody){
        this.jdBody = jdBody;
    }

    public Vector2f getPosition() {
        return jdBody.toVector2f(jdBody.getPosition());
    }
}
