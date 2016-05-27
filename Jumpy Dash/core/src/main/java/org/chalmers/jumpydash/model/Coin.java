package main.java.org.chalmers.jumpydash.jumpydash.model;

public class Coin extends Item {

    private static int valueCoin;

    public Coin(int valueCoin){
        Coin.valueCoin = valueCoin;
    }

    public static int getValue() {
        return valueCoin;
    }
}
