package org.chalmers.jumpydash.model;

public abstract class Enemy extends Tile {

    private int healthPoints;
    private int damage;

    public Enemy(int hp,int damage) {
        healthPoints = hp;
        this.damage = damage;
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
}
