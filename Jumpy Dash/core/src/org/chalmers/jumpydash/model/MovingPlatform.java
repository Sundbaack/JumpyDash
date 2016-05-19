package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.physics.IJDBody;
import javax.vecmath.Vector2f;

public class MovingPlatform {

    private IJDBody jdBody;
    public static boolean movePlatforms;

    public MovingPlatform(IJDBody jdBody){
        this.jdBody = jdBody;
        jdBody.setUserData(this);
        movePlatforms = false;
    }

    public void moveUp(){
        if(movePlatforms == true){
            jdBody.setLinearVelocity(jdBody.toVector2(new Vector2f(0,1)));
        }
        else {
            jdBody.setLinearVelocity(jdBody.toVector2(new Vector2f(0,0)));
        }

    }

    public Vector2f getPosition(){
        return jdBody.toVector2f(jdBody.getPosition());
    }
}
