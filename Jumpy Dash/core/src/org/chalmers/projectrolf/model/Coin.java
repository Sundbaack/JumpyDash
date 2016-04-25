package org.chalmers.projectrolf.model;

import com.badlogic.gdx.physics.box2d.Body;

public class Coin extends Item {

    public int valueCoin;

    public Coin(Body body, int valueCoin, int tileWidthHeight){

        super(body, tileWidthHeight);
        this.valueCoin = valueCoin;
    }

    public int getValue() { return this.valueCoin; }

}
