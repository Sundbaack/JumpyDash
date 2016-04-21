package com.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public interface Items {

    Body getBody();
    Vector2 getPosition();

}
