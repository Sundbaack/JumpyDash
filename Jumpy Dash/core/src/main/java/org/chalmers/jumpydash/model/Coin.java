package main.java.org.chalmers.jumpydash.model;

public class Coin extends Item {

    private static int valueCoin;

    public Coin(int valueCoin){
        Coin.valueCoin = valueCoin;
    }

    public static int getValue() {
        return valueCoin;
    }
}
