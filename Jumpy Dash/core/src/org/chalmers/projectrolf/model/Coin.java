package org.chalmers.projectrolf.model;

import org.chalmers.projectrolf.controller.JDBody;

public class Coin extends Item {

    private int valueCoin;

    public Coin(JDBody body, int valueCoin){

        super(body);
        this.valueCoin = valueCoin;
    }

    public int getValue() { return this.valueCoin; }

}
