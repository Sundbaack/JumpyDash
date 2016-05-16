package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.physics.IJDBody;

public class Cannon extends Enemy {

    public Cannon(IJDBody jdBody) {
        super(1, jdBody);
        jdBody.setUserData(this);
    }
}
