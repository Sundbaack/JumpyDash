package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.physics.IJDBody;

public class Cannon extends Enemy {

    private long previousFireTime =0;

    public Cannon(IJDBody jdBody) {
        super(1, jdBody);
        jdBody.setUserData(this);
    }

    public boolean allowedToFire(){
        long fireCooldown = 250;
        if (System.currentTimeMillis() - previousFireTime >= fireCooldown) {
            previousFireTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }
}
