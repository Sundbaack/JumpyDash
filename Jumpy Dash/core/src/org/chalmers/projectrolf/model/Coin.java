package org.chalmers.projectrolf.model;

public class Coin extends Item {

    private int valueCoin;

    public Coin(JDBody body, int valueCoin){

        super(body);
        this.valueCoin = valueCoin;
    }

    public int getValue() { return this.valueCoin; }

}
