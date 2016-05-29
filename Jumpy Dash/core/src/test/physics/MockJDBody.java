package physics;

import com.badlogic.gdx.math.Vector2;
import org.chalmers.jumpydash.physics.IJDBody;

import javax.vecmath.Vector2f;

public class MockJDBody implements IJDBody {
    private Vector2f position;
    private Vector2f forceToCenter;
    private Vector2f velocity;
    private Vector2f impulse;
    private Object userData;


    public void setPosition(Vector2f position){
        this.position=position;
    }

    @Override
    public float getMass() {
        return 0;
    }

    @Override
    public void applyLinearImpulse(Vector2f impulse, Vector2f point, boolean wake) {
        this.impulse=impulse;
    }

    public Vector2f getImpulse(){
        return impulse;
    }

    @Override
    public Vector2 toVector2(Vector2f vector2f) {
        return null;
    }

    @Override
    public Vector2f toVector2f(Vector2 vector2) {
        return null;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public boolean isAwake() {
        return false;
    }

    @Override
    public void applyForceToCenter(Vector2f force, boolean wake) {
        forceToCenter=force;
    }

    public Vector2f getForceToCenter() {
        return forceToCenter;
    }

    @Override
    public void setLinearVelocity(Vector2f v) {
        velocity = v;
    }

    @Override
    public Vector2f getLinearVelocity() {
        return velocity;
    }

    public Vector2f getPosition(){
        return position;
    }

    @Override
    public Vector2f getWorldCenter() {
        return null;
    }

    @Override
    public void setUserData(Object userData) {
        this.userData=userData;
    }

    @Override
    public Object getUserData() {
        return userData;
    }


}
