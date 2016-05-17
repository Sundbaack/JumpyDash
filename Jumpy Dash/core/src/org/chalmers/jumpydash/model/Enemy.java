package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.physics.IJDBody;

import javax.vecmath.Vector2f;

public class Enemy {

    private int healthPoints;
    private IJDBody jdBody;
    private int damage;

    public Enemy(IJDBody jdBody,int hp,int damage) {
        healthPoints=hp;
        this.damage=damage;
        this.jdBody=jdBody;
    }

    public void damageTaken(){
        healthPoints--;
        isDead();
    }

    public int getDamage(){
        return damage;
    }

    public boolean isDead(){
        return healthPoints<=0;
    }

    public int getHealthPoints(){
        return healthPoints;
    }

    public void dealDamage(int damage){
        healthPoints=healthPoints-damage;
    }

    public Vector2f getPosition(){
        return jdBody.toVector2f(jdBody.getPosition());
    }

    public IJDBody getJdBody(){
        return jdBody;
    }
}
