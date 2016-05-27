package org.chalmers.jumpydash.model;

import javax.vecmath.Vector2f;

public class MovingPlatform extends JDModel {

    public static boolean movePlatforms = false;

    public MovingPlatform() {

    }

    public void moveUp(){
        if(movePlatforms) {
            getJDBody().setLinearVelocity(new Vector2f(0, 1));
        } else {
            getJDBody().setLinearVelocity(new Vector2f(0, 0));
        }
    }
}
