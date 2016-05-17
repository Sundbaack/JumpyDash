package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.physics.IJDBody;

public class Cannon extends Enemy {

    private long previousFireTime =0;

    public Cannon(IJDBody jdBody) {
        super(jdBody,1,1);
        jdBody.setUserData(this);
    }

    public boolean allowedToFire(){
        long fireCooldown = 5000;
        if (System.currentTimeMillis() - previousFireTime >= fireCooldown) {
            previousFireTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }
}
