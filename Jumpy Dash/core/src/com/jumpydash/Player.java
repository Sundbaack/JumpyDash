package com.jumpydash;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Player {

    private Body body;
    private FixtureDef fixtureDef;
    private float impulse;
    private boolean jumpFlag = false;

    public Player(Body body) {

        this.body = body;


        // Create a polygon and apply it to a fixture
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(16/GameController.Pixels_To_Meters, 16/GameController.Pixels_To_Meters);
        fixtureDef = new FixtureDef();
        fixtureDef.shape = polygon;

        // Attach fixture to the body
        this.body.createFixture(fixtureDef);

        this.impulse = this.body.getMass() * 7f;
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
        getBody().setLinearVelocity(new Vector2(5, 0));
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

