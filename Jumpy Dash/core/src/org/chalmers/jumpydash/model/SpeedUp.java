package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.physics.IJDBody;

public class SpeedUp extends Item {

    public SpeedUp(IJDBody jdBody) {
        super(jdBody);
        jdBody.setUserData(this);
    }
}
