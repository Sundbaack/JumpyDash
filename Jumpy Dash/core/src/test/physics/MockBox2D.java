package physics;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import org.chalmers.jumpydash.physics.BodyType;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.physics.JDBody;

import javax.vecmath.Vector2f;

public class MockBox2D implements IBox2D {
    @Override
    public World getWorld() {
        return null;
    }

    @Override
    public void setPause() {

    }

    @Override
    public void setResume() {

    }

    @Override
    public OrthographicCamera getCamera() {
        return null;
    }

    @Override
    public MockJDBody newBody(float x, float y, int mapHeight, BodyType bodyType, boolean ghost, boolean sensor) {
        MockJDBody mockJDBody = new MockJDBody();

        mockJDBody.setPosition(new Vector2f(0,0));
        return mockJDBody;
    }

    @Override
    public void setGhost(JDBody jdBody) {

    }

    @Override
    public void setSensor(JDBody jdBody) {

    }

    @Override
    public JDBody newBullet(float x, float y) {
        return null;
    }

    @Override
    public void update() {

    }
}
