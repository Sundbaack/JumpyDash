package org.chalmers.projectrolf.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import org.chalmers.projectrolf.controller.GameController;

public class Platform  {

    private Body body;
    private FixtureDef fixtureDef;
    private int TileWidth = 32;
    private int TileHeight = 32;

    public Platform(Body body){

        this.body = body;

        // Create a polygon and apply it to a fixture
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox((TileWidth / 2) / GameController.PIXELS_TO_METERS, (TileHeight / 2) / GameController.PIXELS_TO_METERS);
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
