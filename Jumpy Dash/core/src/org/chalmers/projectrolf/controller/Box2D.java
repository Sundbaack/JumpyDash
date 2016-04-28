package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public interface Box2D {

    public void applyForceToCenter(Vector2 force, boolean wake);

    public void applyLinearImpulse(Vector2 impulse, Vector2 point, boolean wake);


}
