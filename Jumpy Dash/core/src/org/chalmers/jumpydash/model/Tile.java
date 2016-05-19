package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.physics.IJDBody;

import javax.vecmath.Vector2f;

public abstract class Tile {

    private IJDBody jdBody;

    public IJDBody getJDBody() {
        return this.jdBody;
    }

    public void setJDBody(IJDBody jdBody) {
        this.jdBody = jdBody;
    }

    public Vector2f getPosition() {
        return jdBody.getPosition();
    }
}
