package com.jumpydash;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by Surface pro 3 on 2016-04-18.
 */

public class Platform extends Sprite {

    private Body body;
    private FixtureDef fixtureDef;
    private Fixture fixture;

    public Platform(Body body){


        this.body = body;

        // Create a fixture definition to apply our shape to
        fixtureDef = new FixtureDef();
        PolygonShape platform = new PolygonShape();
        platform.setAsBox(10,10,new Vector2(1,2),0);

        fixtureDef.shape = new PolygonShape();

        // Create our fixture and attach it to the body
        fixture = this.body.createFixture(fixtureDef);

    }

    public Body getBody() {
        return this.body;
    }

    public Vector2 getPosition() {
        return this.body.getPosition();
    }

}
