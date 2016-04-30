package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class JDBody {
    Body body;

    public float getMass() {
        return body.getMass();
    }

    public void applyLinearImpulse(Vector2 impulse, Vector2 point, boolean wake) {
        body.applyLinearImpulse(impulse, point, wake);
    }

    public void applyForceToCenter(Vector2 force, boolean wake) {
        body.applyForceToCenter(force, wake);
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
}
