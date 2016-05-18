package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.physics.IJDBody;

import javax.vecmath.Vector2f;

public class Item {

    private IJDBody jdBody;

    public Item(IJDBody jdBody) {
        this.jdBody = jdBody;
    }

    public Vector2f getPosition() {
        return jdBody.toVector2f(jdBody.getPosition());
    }

    public IJDBody getJdBody(){
        return jdBody;
    }
}
