package org.chalmers.jumpydash.model;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import org.chalmers.jumpydash.physics.IJDBody;

import javax.vecmath.Vector2f;

/**
 * Created by Surface pro 3 on 2016-05-18.
 */
public class Sensor {

    private IJDBody jdBody;

    public Sensor(IJDBody jdBody) {
        this.jdBody = jdBody;


    }

    public Vector2f getPosition() {
        return jdBody.toVector2f(jdBody.getPosition());
    }

    public IJDBody getJdBody(){
        return jdBody;
    }
}

