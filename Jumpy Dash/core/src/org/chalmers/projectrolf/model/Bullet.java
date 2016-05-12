package org.chalmers.projectrolf.model;

import org.chalmers.projectrolf.physics.IJDBody;
import org.chalmers.projectrolf.physics.JDBody;

import javax.vecmath.Vector2f;

public class Bullet {

    private IJDBody jdBody;

    public Bullet(JDBody jdBody) {
        this.jdBody=jdBody;
        this.jdBody.setLinearVelocity(jdBody.toVector2(new Vector2f(15f, 0)));
        this.jdBody.setUserData(this);
    }
    public Vector2f getPosition() {
        return jdBody.toVector2f(jdBody.getPosition());
    }


}
