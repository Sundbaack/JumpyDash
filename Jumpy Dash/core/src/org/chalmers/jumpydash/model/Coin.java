package org.chalmers.jumpydash.model;

public class Coin extends Item {

    private static int valueCoin;

    public Coin(int valueCoin){
        this.valueCoin = valueCoin;
    }

    public static int getValue() {
        return valueCoin;
    }
}
