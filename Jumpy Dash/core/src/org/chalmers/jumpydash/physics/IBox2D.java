package org.chalmers.jumpydash.physics;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;

public interface IBox2D {

    World getWorld();

    void setPause();

    void setResume();

    OrthographicCamera getCamera();

    JDBody newBody(float x, float y, int mapHeight, BodyType bodyType , boolean ghost, boolean sensor);

    void setGhost(JDBody jdBody);

    void setSensor(JDBody jdBody);

    JDBody newBullet(float x, float y);

    void update();
}
