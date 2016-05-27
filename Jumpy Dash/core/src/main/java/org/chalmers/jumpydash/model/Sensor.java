package main.java.org.chalmers.jumpydash.jumpydash.model;

public class Sensor extends JDModel {

    private String type;

    public Sensor(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }
}

