package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.controller.PlayerController;
import org.chalmers.jumpydash.physics.IJDBody;

/**
 * Created by alexsundback on 2016-05-18.
 */
public class SpeedUp extends Ability {

    public SpeedUp(IJDBody jdBody) {
        super(jdBody);
        jdBody.setUserData(this);
    }

    public void playerSpeedUp() {
        PlayerController.getPlayer().setSpeed(4);
    }

}
