package org.chalmers.jumpydash.physics;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Box2D implements IBox2D {

    private OrthographicCamera camera;
    private World world;
    private final float PIXELS_TO_METERS = 100f;
    private final float tileWidthHeight;

    public Box2D(float tileWidthHeight) {
        this.tileWidthHeight = tileWidthHeight;
        world = new World(new Vector2(0, -10f), true); //Create a world object with a gravity vector

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 736);
    }

    public World getWorld() {
        return this.world;
    }

    public float getPixelsToMeters() {
        return this.PIXELS_TO_METERS;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public JDBody newBody(float x, float y, int mapHeight, String type, boolean ghost) {
        BodyDef bodyDef = new BodyDef();

        if (type.equalsIgnoreCase("kinematic")) {
            bodyDef.type = BodyDef.BodyType.KinematicBody;
        } else if (type.equalsIgnoreCase("dynamic")) {
            bodyDef.type = BodyDef.BodyType.DynamicBody;
        } else if (type.equalsIgnoreCase("static")) {
            bodyDef.type = BodyDef.BodyType.StaticBody;
        }

        bodyDef.position.set((x * tileWidthHeight) / PIXELS_TO_METERS, ((mapHeight - 1 - y) * tileWidthHeight) / PIXELS_TO_METERS);
        JDBody jdBody = new JDBody();
        jdBody.body = world.createBody(bodyDef);

        if (!ghost) {
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
        } else {
            //Ghost vertices
            Vector2 v1 = new Vector2(0, tileWidthHeight / PIXELS_TO_METERS);
            Vector2 v2 = new Vector2(tileWidthHeight / PIXELS_TO_METERS, tileWidthHeight / PIXELS_TO_METERS);
            Vector2 v0 = new Vector2(0, 0);
            Vector2 v3 = new Vector2(tileWidthHeight / PIXELS_TO_METERS, 0);

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

    public void update() {
        world.step(1/60f, 8, 3);
        world.clearForces();
        camera.update();
    }
}
