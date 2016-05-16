package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.physics.IJDBody;

import javax.vecmath.Vector2f;

/**
 * Created by alexsundback on 2016-05-16.
 */
public class Trampoline {

    private IJDBody jdBody;

    public Trampoline(IJDBody jdBody){
        this.jdBody = jdBody;
        this.jdBody.setUserData(this);
    }

    public Vector2f getPosition() { return jdBody.toVector2f(jdBody.getPosition()); }

}
