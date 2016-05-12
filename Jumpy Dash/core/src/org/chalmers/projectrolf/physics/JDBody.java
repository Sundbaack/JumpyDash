package org.chalmers.projectrolf.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import javax.vecmath.Vector2f;

public class JDBody implements IJDBody {

    public Body body;

    public float getMass() {
        return body.getMass();
    }

    public void applyLinearImpulse(Vector2 impulse, Vector2 point, boolean wake) {
        body.applyLinearImpulse(impulse, point, wake);
    }

    public Vector2 toVector2(Vector2f vector2f){
        Vector2 vector2 = new Vector2(vector2f.getX(),vector2f.getY());
        return vector2;
    }

    public Vector2f toVector2f(Vector2 vector2){
        Vector2f vector2f = new Vector2f(vector2.x,vector2.y);
        return vector2f;
    }

    public Body getBody() {
        return this.body;
    }

    public void applyForceToCenter(Vector2 force, boolean wake) {
        body.applyForceToCenter(force, wake);
    }

    public void setLinearVelocity(Vector2 v) {
        body.setLinearVelocity(v);
    }

    public Vector2 getLinearVelocity() {
        return body.getLinearVelocity();
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public Vector2 getWorldCenter() {
        return body.getWorldCenter();
    }

    public void setUserData(Object userData){
        body.setUserData(userData);
    }
}
