package com.jumpydash;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Johannes on 2016-04-18.
 */
public class Soldier extends Enemy{
    private float impulse;

    public Soldier(Body body) {
        super(body);

    }

    public void move() {
        this.getBody().applyForceToCenter(1.0f, 0.0f, true);

    }

}
