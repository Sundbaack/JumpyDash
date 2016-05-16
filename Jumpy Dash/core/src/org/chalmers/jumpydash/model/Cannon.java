package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.physics.IJDBody;

/**
 * Created by Johannes on 2016-05-16.
 */
public class Cannon extends Enemy {

    public Cannon(IJDBody jdBody) {
        super(1, jdBody);
        jdBody.setUserData(this);
    }

}
