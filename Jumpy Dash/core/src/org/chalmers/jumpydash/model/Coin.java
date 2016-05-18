package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.physics.IJDBody;

public class Coin extends Item {

    private static int valueCoin;

    public Coin(IJDBody jdBody, int valueCoin){
        super(jdBody);
        jdBody.setUserData(this);
        this.valueCoin = valueCoin;
    }

    public static int getValue() {
        return valueCoin;
    }
}
