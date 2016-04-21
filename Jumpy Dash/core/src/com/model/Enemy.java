package com.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.controller.GameController;

/**
 * Created by Johannes on 2016-04-18.
 */
public class Enemy {
    private Body body;
    private FixtureDef fixtureDef;
    private Fixture fixture;


    public Enemy(Body body) {

        this.body = body;

        // Create a polygon and apply it to a fixture
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(16/ GameController.PIXELS_TO_METERS, 16/GameController.PIXELS_TO_METERS);
        fixtureDef = new FixtureDef();
        fixtureDef.shape = polygon;

        // Attach fixture to the body
        this.body.createFixture(fixtureDef);
    }

    public Body getBody() {
        return this.body;
    }

    public Vector2 getPosition() {
        return this.body.getPosition();
    }
}
