package org.chalmers.projectrolf.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import org.chalmers.projectrolf.controller.GameController;

public class Player {

    private Body body;
    private float impulse;
    private boolean jumpFlag;
    private float maxSpeedX;
    private int points;

    public Player(Body body, int tileWidthHeight) {

        this.body = body;
        maxSpeedX = 5.5f;
        jumpFlag = false;
        this.points=0;

        // Create a polygon and apply it to a fixture
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox((tileWidthHeight / 2) / GameController.PIXELS_TO_METERS, (tileWidthHeight / 2) / GameController.PIXELS_TO_METERS);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygon;

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

        //getBody().setSleepingAllowed(false);
        Vector2 speed = getBody().getLinearVelocity();
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

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int a) {
        this.points=this.getPoints()+a;
    }

    public Vector2 getPosition() {
        return this.body.getPosition();
    }
}

