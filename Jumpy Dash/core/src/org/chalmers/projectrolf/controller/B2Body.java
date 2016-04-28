package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class B2Body extends Body {

    protected B2Body(World world, long addr) {
        super(world, addr);
    }

    public void applyForceToCenter(Vector2 force, boolean wake) {
       super.applyForceToCenter(force, wake);
    }

    public void applyLinearImpulse(Vector2 impulse, Vector2 point, boolean wake) {
        super.applyLinearImpulse(impulse, point, wake);
    }

    public Fixture createFixture(FixtureDef fixtureDef) {
        return super.createFixture(fixtureDef);
    }

    public float getMass() {
        return super.getMass();
    }

    public Vector2 getWorldCenter() {
        return super.getWorldCenter();
    }

    public Vector2 getLinearVelocity() {
        return super.getLinearVelocity();
    }

    public Vector2 getPosition() {
        return super.getPosition();
    }
}
