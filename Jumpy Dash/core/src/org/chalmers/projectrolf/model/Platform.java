package org.chalmers.projectrolf.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class Platform  {

    private Body body;

    public Platform(Body body, float tileWidthHeight){

        this.body = body;

        //Ghost vertices
        Vector2 v1 = new Vector2(0, tileWidthHeight);
        Vector2 v2 = new Vector2(tileWidthHeight, tileWidthHeight);
        Vector2 v0 = new Vector2(0,-tileWidthHeight);
        Vector2 v3 = new Vector2(tileWidthHeight,-tileWidthHeight);

        // Create a EdgeShape and apply it to a fixture
        EdgeShape edgeShape = new EdgeShape();
        edgeShape.set(v1, v2);
        edgeShape.setVertex0(v0);
        edgeShape.setVertex3(v3);
        edgeShape.setHasVertex0(true);
        edgeShape.setHasVertex3(true);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = edgeShape;

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
