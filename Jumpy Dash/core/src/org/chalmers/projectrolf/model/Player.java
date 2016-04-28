package org.chalmers.projectrolf.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Player {

    private Body body;
    private float impulse;
    private boolean jumpFlag;
    private int points;
    private static final float MAX_SPEED_X = 5.5f;

    public Player(Body body, float tileWidthHeight) {

        this.body = body;
        jumpFlag = false;
        this.points = 0;

        float hTileWidthHeight = tileWidthHeight / 2;
        Vector2 vCenter = new Vector2(hTileWidthHeight, hTileWidthHeight);

        // Create a polygon and apply it to a fixture
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(hTileWidthHeight, hTileWidthHeight, vCenter, 0);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygon;
        fixtureDef.friction = 0;

        // Attach fixture to the body
        this.body.createFixture(fixtureDef);

        this.impulse = this.body.getMass() * 6f;
    }

    public void setJumpState(){
        this.jumpFlag = !jumpFlag;
    }

    public boolean getJumpState(){
        return this.jumpFlag;
    }

    public void jump() {
        getBody().applyLinearImpulse(new Vector2(0, getImpulse()), getBody().getWorldCenter(), true);
    }

    public void move() {

        Vector2 speed = getBody().getLinearVelocity();
        float speedX = speed.x;

        if (speedX < MAX_SPEED_X) {
            getBody().applyForceToCenter(new Vector2(6, 0), true);
        }
    }

    public float getImpulse() {
        return this.impulse;
    }

    public Body getBody() {
        return this.body;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int a) {
        this.points += a;
    }

    public Vector2 getPosition() {
        return this.body.getPosition();
    }
}

