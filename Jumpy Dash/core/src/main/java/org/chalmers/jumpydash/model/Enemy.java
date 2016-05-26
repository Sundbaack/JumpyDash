package main.java.org.chalmers.jumpydash.model;

public abstract class Enemy extends JDModel {

    private int healthPoints;
    private int damage;

    public Enemy(int hp,int damage) {
        healthPoints = hp;
        this.damage = damage;
    }

    public void damageTaken(){
        healthPoints--;
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
