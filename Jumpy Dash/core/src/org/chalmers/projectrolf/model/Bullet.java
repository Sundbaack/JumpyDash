package org.chalmers.projectrolf.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import org.chalmers.projectrolf.controller.GameController;

public class Bullet {

    private Body body;

    public Bullet(Body body, int tileWidthHeight) {

        this.body = body;
        this.body.setLinearVelocity(10,0);

        // Create a polygon and apply it to a fixture
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox((tileWidthHeight / 2) / GameController.PIXELS_TO_METERS, (tileWidthHeight / 2) / GameController.PIXELS_TO_METERS);
        FixtureDef fixtureDef = new FixtureDef();
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
