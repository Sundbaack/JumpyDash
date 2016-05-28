package physics;

import com.badlogic.gdx.math.Vector2;
import org.chalmers.jumpydash.physics.IJDBody;

import javax.vecmath.Vector2f;

public class MockJDBody implements IJDBody {
    private Vector2f position;

    public void setPosition(Vector2f position){
        this.position=position;
    }

    @Override
    public float getMass() {
        return 0;
    }

    @Override
    public void applyLinearImpulse(Vector2f impulse, Vector2f point, boolean wake) {

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

    }

    @Override
    public void setLinearVelocity(Vector2f v) {

    }

    @Override
    public Vector2f getLinearVelocity() {
        return null;
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

    }

    @Override
    public Object getUserData() {
        return null;
    }


}
