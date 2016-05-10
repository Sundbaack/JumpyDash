package org.chalmers.projectrolf.model;

public class Coin extends Item {

    private static int valueCoin;

    public Coin(int valueCoin){
        super();
        this.valueCoin = valueCoin;
    }

    public static int getValue() { return valueCoin; }

}
