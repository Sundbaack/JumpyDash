package org.chalmers.projectrolf.model;

public class Coin extends Item {

    private static int valueCoin;

    public Coin(JDBody body, int valueCoin){
        super(body);
        this.valueCoin = valueCoin;
    }

    public static int getValue() { return valueCoin; }

}
