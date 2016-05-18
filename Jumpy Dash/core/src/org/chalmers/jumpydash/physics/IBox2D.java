package org.chalmers.jumpydash.physics;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import java.util.List;

public interface IBox2D {


    void setBodiesToBeDestroyed(List<Body> bodiesToBeDestroyed);

    World getWorld();

    OrthographicCamera getCamera();

    JDBody newBody(float x, float y, int mapHeight, String type, boolean ghost);

    JDBody newBullet(float x, float y);

    void update();
}
