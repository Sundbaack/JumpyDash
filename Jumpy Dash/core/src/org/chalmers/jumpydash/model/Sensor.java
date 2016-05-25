package org.chalmers.jumpydash.model;

public class Sensor extends JDModel {

    private String type;

    public Sensor(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }
}

