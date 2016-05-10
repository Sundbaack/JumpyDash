package org.chalmers.projectrolf.physics;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;

public interface IBox2D {

    float getPixelsToMeters();

    World getWorld();

    OrthographicCamera getCamera();

    JDBody newDynamic(float x, float y, int mapHeight);

    JDBody newStatic(float x, float y, int mapHeight, boolean ghost);

    JDBody newBullet(float x, float y);

    void step();

    void update();
}
