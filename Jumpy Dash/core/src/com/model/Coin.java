package com.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.controller.GameController;

public class Coin implements Items {

    private Body body;
    private FixtureDef fixtureDef;

    public int valueCoin;

    public Coin(Body body, int valueCoin){

        this.body = body;
        this.valueCoin = valueCoin;

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

    public int getValue() { return this.valueCoin; }

}
