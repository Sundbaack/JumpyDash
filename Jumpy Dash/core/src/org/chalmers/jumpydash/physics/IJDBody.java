package org.chalmers.jumpydash.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import javax.vecmath.Vector2f;

public interface IJDBody {

    float getMass();

    void applyLinearImpulse(Vector2 impulse, Vector2 point, boolean wake);

    Vector2 toVector2(Vector2f vector2f);

    Vector2f toVector2f(Vector2 vector2);

    boolean isActive();

    Body getBody();

    void applyForceToCenter(Vector2 force, boolean wake);

    void setLinearVelocity(Vector2 v);

    Vector2 getLinearVelocity();

    Vector2 getPosition();

    Vector2 getWorldCenter();

    void setUserData(Object userData);
}
