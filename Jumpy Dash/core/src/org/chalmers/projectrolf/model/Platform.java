package org.chalmers.projectrolf.model;

import com.badlogic.gdx.math.Vector2;
import org.chalmers.projectrolf.physics.IJDBody;

public class Platform  {

    private IJDBody jdBody;

    public Platform(IJDBody jdBody){
        this.jdBody =jdBody;
        jdBody.setUserData(this);
    }


    public Vector2 getPosition(){
        return jdBody.getPosition();
    }
}
