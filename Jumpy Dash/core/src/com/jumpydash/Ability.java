package com.jumpydash;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by alexsundback on 2016-04-19.
 */
public class Ability implements Items {

    private Body body;
    private FixtureDef fixtureDef;

    public Ability(Body body){

        this.body = body;

        // Create a fixture definition to apply our shape to
        fixtureDef = new FixtureDef();
        fixtureDef.shape = new PolygonShape();

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
