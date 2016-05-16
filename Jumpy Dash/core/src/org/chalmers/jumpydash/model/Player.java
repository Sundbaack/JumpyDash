package org.chalmers.jumpydash.model;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import org.chalmers.jumpydash.physics.IJDBody;
import javax.vecmath.Vector2f;

public class Player {

    private IJDBody jdBody;
    private float impulse;
    private boolean jumpFlag;
    private static int points;
    private static final float MAX_SPEED_X = 3.5f;


    public Player(IJDBody jdBody) {
        this.jdBody = jdBody;
        jumpFlag = false;



        jdBody.setUserData(this);
        setImpulse(jdBody.getMass() * 6f);
        this.points = 0;
    }

    public void jump() {
        Vector2f vector2f = new Vector2f(0,getImpulse());
        jdBody.applyLinearImpulse(jdBody.toVector2(vector2f), jdBody.getWorldCenter(), true);
    }

    public void move() {

        Vector2f speed = jdBody.toVector2f(jdBody.getLinearVelocity());
        float speedX = speed.x;

        if (speedX < getMaxSpeedX()) {
            jdBody.applyForceToCenter(jdBody.toVector2(new Vector2f(4,0)), true);
        }
    }



    public Vector2f getPosition() {
        return jdBody.toVector2f(jdBody.getPosition());
    }

    public void setJumpState(){
        this.jumpFlag = !jumpFlag;
    }

    public boolean getJumpState() {
        return this.jumpFlag;
    }

    public static float getMaxSpeedX() {
        return MAX_SPEED_X;
    }

    public float getImpulse() {
        return this.impulse;
    }

    public void setImpulse(float impulse) {
        this.impulse = impulse;
    }

    public static int getPoints() {
        return points;
    }

    public static void setPoints(int a) {
        points += a;
    }
}

