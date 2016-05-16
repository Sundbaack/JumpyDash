package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.physics.IJDBody;
import org.chalmers.jumpydash.physics.JDBody;

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
