package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.physics.IJDBody;
import javax.vecmath.Vector2f;

public abstract class JDModel {

    private IJDBody jdBody;

    public IJDBody getJDBody() {
        return this.jdBody != null ? this.jdBody : null;
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

        JDModel jdModel = (JDModel) o;

        return jdBody != null ? jdBody.equals(jdModel.jdBody) : jdModel.jdBody == null;

    }
    public void checkCollision(JDModel jDModelB){
        if(this == jDModelB){
            System.out.println("colliding");
        }
    }

    @Override
    public int hashCode() {
        return jdBody != null ? (37 * jdBody.hashCode()) : 0;
    }
}
