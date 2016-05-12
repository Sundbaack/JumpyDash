package org.chalmers.projectrolf.model;

import org.chalmers.projectrolf.physics.IJDBody;

import javax.vecmath.Vector2f;

public class Platform  {

    private IJDBody jdBody;

    public Platform(IJDBody jdBody){
        this.jdBody =jdBody;
        jdBody.setUserData(this);
    }


    public Vector2f getPosition(){
        return jdBody.toVector2f(jdBody.getPosition());
    }
    }
