package org.chalmers.projectrolf.physics;

import com.badlogic.gdx.math.Vector2;

public interface IJDBody {

    float getMass();

    void applyLinearImpulse(Vector2 impulse, Vector2 point, boolean wake);

    void applyForceToCenter(Vector2 force, boolean wake);

    void setLinearVelocity(Vector2 v);

    Vector2 getLinearVelocity();

    Vector2 getPosition();

    Vector2 getWorldCenter();

    void setUserData(Object userData);
}
