package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Box2D {

    private World world;
    private static final float PIXELS_TO_METERS = 100f;

    private Box2D() {

        world = new World(new Vector2(0, -10f), true); //Create a world object with a gravity vector
        world.setContactListener(new CollisionListener());
    }

    public Body newDynamic(float x, float y, float tileWidthHeight) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x / PIXELS_TO_METERS, y / PIXELS_TO_METERS);
        Body body = world.createBody(bodyDef);

        float hTileWidthHeight = (tileWidthHeight / 2) / PIXELS_TO_METERS;
        Vector2 vCenter = new Vector2(hTileWidthHeight, hTileWidthHeight);

        // Create a polygon and apply it to a fixture
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(hTileWidthHeight, hTileWidthHeight, vCenter, 0);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygon;
        fixtureDef.friction = 0;

        // Attach fixture to the body
        body.createFixture(fixtureDef);

        return body;
    }

    public Body newStatic(float x, float y, float tileWidthHeight, boolean ghost) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x / PIXELS_TO_METERS, y / PIXELS_TO_METERS);
        Body body = world.createBody(bodyDef);

        if (!ghost) {
            float hTileWidthHeight = (tileWidthHeight / 2) / PIXELS_TO_METERS;

            Vector2 vCenter = new Vector2(hTileWidthHeight, hTileWidthHeight);

            // Create a polygon and apply it to a fixture
            PolygonShape polygon = new PolygonShape();
            polygon.setAsBox(hTileWidthHeight, hTileWidthHeight, vCenter, 0);
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = polygon;

            // Attach fixture to the body
            body.createFixture(fixtureDef);
        } else {
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
            body.createFixture(fixtureDef);
        }
        return body;
    }

    public Body newKinematic(float x, float y, float tileWidthHeight, boolean bullet) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x / PIXELS_TO_METERS, y / PIXELS_TO_METERS);
        Body body = world.createBody(bodyDef);

        float hTileWidthHeight = (tileWidthHeight / 2) / PIXELS_TO_METERS;
        Vector2 vCenter = new Vector2(hTileWidthHeight, hTileWidthHeight);

        // Create a polygon and apply it to a fixture
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(hTileWidthHeight, hTileWidthHeight, vCenter, 0);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygon;

        // Attach fixture to the body
        body.createFixture(fixtureDef);

        if (bullet) {
            body.setBullet(true);
        }
        return body;
    }

}
