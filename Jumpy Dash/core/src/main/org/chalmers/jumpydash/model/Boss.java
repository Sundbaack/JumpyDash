package org.chalmers.jumpydash.model;

public class Boss extends Enemy {

    private long previousFireTime = 0;

    public Boss() {
        super(10,2);
    }

    public boolean allowedToFire(){
        long fireCooldown = 1000;
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
