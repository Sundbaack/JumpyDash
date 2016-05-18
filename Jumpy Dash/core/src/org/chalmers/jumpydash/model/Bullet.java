package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.physics.IJDBody;
import org.chalmers.jumpydash.physics.JDBody;

import javax.vecmath.Vector2f;

public class Bullet {

    private IJDBody jdBody;

    public Bullet(JDBody jdBody, Vector2f bulletDirection) {
        this.jdBody=jdBody;
        this.jdBody.setLinearVelocity(jdBody.toVector2(bulletDirection));
        this.jdBody.setUserData(this);
    }

    public Vector2f getPosition() {
        return jdBody.toVector2f(jdBody.getPosition());
    }

    public IJDBody getJdBody(){
        return jdBody;
    }
}
