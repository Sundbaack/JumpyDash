package org.chalmers.jumpydash.model;

public class Enemy {

    private int healthPoints;

    public Enemy(int hp) {
        healthPoints=hp;
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

}
