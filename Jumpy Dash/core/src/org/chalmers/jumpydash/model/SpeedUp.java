package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.controller.PlayerController;
import org.chalmers.jumpydash.physics.IJDBody;

import javax.vecmath.Vector2f;

/**
 * Created by alexsundback on 2016-05-18.
 */
public class SpeedUp extends Item {

    public SpeedUp(IJDBody jdBody) {
        super(jdBody);
        jdBody.setUserData(this);
    }

}
