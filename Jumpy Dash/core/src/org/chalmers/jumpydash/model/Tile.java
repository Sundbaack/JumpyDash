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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        return jdBody != null ? jdBody.equals(tile.jdBody) : tile.jdBody == null;

    }

    @Override
    public int hashCode() {
        return jdBody != null ? (37 * jdBody.hashCode()) : 0;
    }
}
