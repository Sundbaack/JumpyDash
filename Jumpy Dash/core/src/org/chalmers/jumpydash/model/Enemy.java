package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.physics.IJDBody;

import javax.vecmath.Vector2f;

public class Enemy {

    private int healthPoints;
    private IJDBody jdBody;

    public Enemy(int hp, IJDBody jdBody) {
        healthPoints=hp;
        this.jdBody=jdBody;
    }

    public void damageTaken(){
        healthPoints--;
        isDead();
    }

    public boolean isDead(){
        return healthPoints<=0;
    }

    public int getHealthPoints(){
        return healthPoints;
    }

    public Vector2f getPosition(){
        return jdBody.toVector2f(jdBody.getPosition());
    }

    public IJDBody getJdBody(){
        return jdBody;
    }

}
