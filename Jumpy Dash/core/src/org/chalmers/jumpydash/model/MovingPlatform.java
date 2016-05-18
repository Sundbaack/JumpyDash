package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.physics.IJDBody;
import javax.vecmath.Vector2f;

public class MovingPlatform {

    private IJDBody jdBody;
    public static boolean movePlatforms = false;

    public MovingPlatform(IJDBody jdBody){
        this.jdBody = jdBody;
        jdBody.setUserData(this);
    }

    public void moveUp(){
        //System.out.println("moving");
        jdBody.applyForceToCenter(jdBody.toVector2(new Vector2f(10,100)),true);
    }

    public Vector2f getPosition(){
        return jdBody.toVector2f(jdBody.getPosition());
    }
}
