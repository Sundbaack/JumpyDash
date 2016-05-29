package org.chalmers.jumpydash.model;

public class Cannon extends Enemy {

    private long previousFireTime = 0;

    public Cannon() {
        super(1,1);
    }

    public void checkCollision(JDModel jDModelB) {
        if (this.getClass() == Cannon.class && jDModelB.getClass() == Bullet.class && this.isDead()) {
            this.getJDBody().setUserData(null);
            jDModelB.getJDBody().setUserData(null);
            this.userDataNull();
            this.getJDBody().setUserData(null);
            this.takeDamage(1);
        }
    }

    public boolean allowedToFire(){
        long fireCooldown = 2500;
        if (System.currentTimeMillis() - previousFireTime >= fireCooldown) {
            setPreviousFireTime(System.currentTimeMillis());
            return true;
        }
        return false;
    }

    public void setPreviousFireTime(long time){
        previousFireTime = time;
    }
}
