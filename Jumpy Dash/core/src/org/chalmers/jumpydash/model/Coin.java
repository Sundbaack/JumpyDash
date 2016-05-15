package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.physics.IJDBody;

import javax.vecmath.Vector2f;

public class Coin extends Item {

    private static int valueCoin;
    private IJDBody jdBody;

    public Coin(int valueCoin, IJDBody jdBody){
        super();
        this.valueCoin = valueCoin;
        this.jdBody = jdBody;
        this.jdBody.setUserData(this);
    }

    public static int getValue() { return valueCoin; }

    public Vector2f getPosition() { return jdBody.toVector2f(jdBody.getPosition()); }

}
