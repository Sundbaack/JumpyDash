package com.jumpydash;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by Surface pro 3 on 2016-04-18.
 */

public class Platform  {

    private Body body;
    private FixtureDef fixtureDef;

    public Platform(Body body){

        this.body = body;

        // Create a fixture definition to apply our shape to
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(16, 16);
        fixtureDef = new FixtureDef();
        fixtureDef.shape = polygon;

        // Create our fixture and attach it to the body
        this.body.createFixture(fixtureDef);
    }

    public Body getBody() {
        return this.body;
    }

    public Vector2 getPosition() {
        return this.body.getPosition();
    }

}
