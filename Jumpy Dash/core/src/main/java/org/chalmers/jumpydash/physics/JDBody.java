package main.java.org.chalmers.jumpydash.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import javax.vecmath.Vector2f;

public class JDBody implements IJDBody {

    public Body body;

    public float getMass() {
        return body.getMass();
    }

    public void applyLinearImpulse(Vector2f impulse, Vector2f point, boolean wake) {
        body.applyLinearImpulse(toVector2(impulse), toVector2(point), wake);
    }

    public Vector2 toVector2(Vector2f vector2f){
        return new Vector2(vector2f.x,vector2f.y);
    }

    public Vector2f toVector2f(Vector2 vector2){
        return new Vector2f(vector2.x,vector2.y);
    }

    public boolean isActive() {
        return body.isActive();
    }

    public boolean isAwake() {
        return body.isAwake();
    }

    public void applyForceToCenter(Vector2f force, boolean wake) {
        body.applyForceToCenter(toVector2(force), wake);
    }

    public void setLinearVelocity(Vector2f v) {
        body.setLinearVelocity(toVector2(v));
    }

    public Vector2f getLinearVelocity() {
        return toVector2f(body.getLinearVelocity());
    }

    public Vector2f getPosition() {
        return toVector2f(body.getPosition());
    }

    public Vector2f getWorldCenter() {
        return toVector2f(body.getWorldCenter());
    }

    public void setUserData(Object userData){
        body.setUserData(userData);
    }
}
