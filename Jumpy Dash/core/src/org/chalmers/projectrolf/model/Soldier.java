package org.chalmers.projectrolf.model;

public class Soldier extends Enemy {

    private boolean directionFlag = true;

    public Soldier() {}

    public void setDirectionFlag(){
        directionFlag = !directionFlag;
    }

    public boolean getDirectionFlag(){
        return directionFlag;
    }

}
