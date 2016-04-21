package com.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.controller.GameController;

public class Ability implements Items {

    private Body body;
    private FixtureDef fixtureDef;

    public Ability(Body body){

        this.body = body;

        // Create a polygon and apply it to a fixture
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(16/ GameController.Pixels_To_Meters, 16/GameController.Pixels_To_Meters);
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
