package com.jumpydash;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by alexsundback on 2016-04-19.
 */
public interface Items {

    Body getBody();
    Vector2 getPosition();

}
