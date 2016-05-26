package org.chalmers.jumpydash.model;

public class Cannon extends Enemy {

    private long previousFireTime = 0;

    public Cannon() {
        super(1,1);
    }

    public boolean allowedToFire(){
        long fireCooldown = 2500;
        if (System.currentTimeMillis() - previousFireTime >= fireCooldown) {
            setPreviousFireTime(System.currentTimeMillis());
            return true;
        }
        return false;
    }

    public void setPreviousFireTime(long time){
        previousFireTime = time;
    }
}
