package org.chalmers.projectrolf.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Enemy {

    private Body body;

    public Enemy(Body body, float tileWidthHeight) {

        this.body = body;
        float hTileWidthHeight = tileWidthHeight / 2;
        Vector2 vCenter = new Vector2(hTileWidthHeight, hTileWidthHeight);

        // Create a polygon and apply it to a fixture
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(hTileWidthHeight, hTileWidthHeight, vCenter, 0);
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
