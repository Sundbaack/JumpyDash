package com.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.controller.GameController;

public class Player {

    private Body body;
    private FixtureDef fixtureDef;
    private float impulse;
    private boolean jumpFlag = false;
    private float maxSpeedX;
    private int TileWidth = 32;
    private int TileHeight = 32;

    public Player(Body body) {

        this.body = body;
        maxSpeedX = 5.5f;

        // Create a polygon and apply it to a fixture
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox((TileWidth / 2) / GameController.PIXELS_TO_METERS, (TileHeight / 2) / GameController.PIXELS_TO_METERS);
        fixtureDef = new FixtureDef();
        fixtureDef.shape = polygon;

        // Attach fixture to the body
        this.body.createFixture(fixtureDef);

        this.impulse = this.body.getMass() * 6f;
    }

    public void setJumpState(){
        jumpFlag = !jumpFlag;
    }

    public boolean getJumpState(){
        return jumpFlag;
    }

    public void jump() {
        getBody().applyLinearImpulse(new Vector2(0, getImpulse()), getBody().getWorldCenter(), true);
    }

    public void move() {

        Vector2 speed = this.body.getLinearVelocity();
        float speedX = speed.x;

        if (speedX < maxSpeedX) {
            getBody().applyForceToCenter(new Vector2(6, 0), true);
        }
    }


    public float getImpulse() {
        return this.impulse;
    }

    public Body getBody() {
        return this.body;
    }

    public Vector2 getPosition() {
        return this.body.getPosition();
    }
}

