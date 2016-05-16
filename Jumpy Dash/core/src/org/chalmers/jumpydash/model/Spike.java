package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.physics.IJDBody;
import javax.vecmath.Vector2f;

public class Spike {

    private IJDBody jdBody;

    public Spike(IJDBody jdBody) {
        this.jdBody = jdBody;
        this.jdBody.setUserData(this);
    }

    public Vector2f getPosition() {
        return jdBody.toVector2f(jdBody.getPosition());
    }
}
