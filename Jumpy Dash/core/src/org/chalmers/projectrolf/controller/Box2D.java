package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import org.chalmers.projectrolf.model.JDBody;

public class Box2D {

    public World world;
    private static final float PIXELS_TO_METERS = 100f;
    private final float tileWidthHeight;

    public Box2D(float tileWidthHeight) {
        this.tileWidthHeight = tileWidthHeight;
        world = new World(new Vector2(0, -10f), true); //Create a world object with a gravity vector
        world.setContactListener(new CollisionListener());
    }

    public float getPixelsToMeters() {
        return this.PIXELS_TO_METERS;
    }

    public JDBody newDynamic(float x, float y, int mapHeight) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((x * tileWidthHeight) / PIXELS_TO_METERS, (mapHeight - 1 - y) * tileWidthHeight / PIXELS_TO_METERS);
        JDBody jdBody = new JDBody();
        jdBody.body = world.createBody(bodyDef);

        float hTileWidthHeight = (tileWidthHeight / 2) / PIXELS_TO_METERS;
        Vector2 vCenter = new Vector2(hTileWidthHeight, hTileWidthHeight);

        // Create a polygon and apply it to a fixture
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(hTileWidthHeight, hTileWidthHeight, vCenter, 0);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygon;
        fixtureDef.friction = 0;

        // Attach fixture to the body
        jdBody.body.createFixture(fixtureDef);

        return jdBody;
    }

    public JDBody newStatic(float x, float y, int mapHeight, boolean ghost) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((x * tileWidthHeight) / PIXELS_TO_METERS, (mapHeight - 1 - y) * tileWidthHeight / PIXELS_TO_METERS);
        JDBody jdBody = new JDBody();
        jdBody.body = world.createBody(bodyDef);

        if (ghost == false) {
            float hTileWidthHeight = (tileWidthHeight / 2) / PIXELS_TO_METERS;

            Vector2 vCenter = new Vector2(hTileWidthHeight, hTileWidthHeight);

            // Create a polygon and apply it to a fixture
            PolygonShape polygon = new PolygonShape();
            polygon.setAsBox(hTileWidthHeight, hTileWidthHeight, vCenter, 0);
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = polygon;

            // Attach fixture to the body
            jdBody.body.createFixture(fixtureDef);
        } else {

            //Not working yet for some reason

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
            jdBody.body.createFixture(fixtureDef);
        }
        return jdBody;
    }

    public JDBody newBullet(float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x + (40 / PIXELS_TO_METERS),  y + ((tileWidthHeight / 2 / 2) / PIXELS_TO_METERS));
        JDBody jdBody = new JDBody();
        jdBody.body = world.createBody(bodyDef);

        float hTileWidthHeight = (tileWidthHeight / 2) / PIXELS_TO_METERS;
        Vector2 vCenter = new Vector2(hTileWidthHeight, hTileWidthHeight);

        // Create a polygon and apply it to a fixture
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(hTileWidthHeight, hTileWidthHeight, vCenter, 0);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygon;
        jdBody.body.setBullet(true);

        // Attach fixture to the body
        jdBody.body.createFixture(fixtureDef);

        return jdBody;
    }

    public void step() {
        world.step(1/60f, 6, 3);
    }

}
