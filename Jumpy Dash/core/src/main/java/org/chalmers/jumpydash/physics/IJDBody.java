package main.java.org.chalmers.jumpydash.physics;

import com.badlogic.gdx.math.Vector2;
import javax.vecmath.Vector2f;

public interface IJDBody {

    float getMass();

    void applyLinearImpulse(Vector2f impulse, Vector2f point, boolean wake);

    Vector2 toVector2(Vector2f vector2f);

    Vector2f toVector2f(Vector2 vector2);

    boolean isActive();

    boolean isAwake();

    void applyForceToCenter(Vector2f force, boolean wake);

    void setLinearVelocity(Vector2f v);

    Vector2f getLinearVelocity();

    Vector2f getPosition();

    Vector2f getWorldCenter();

    void setUserData(Object userData);
}
