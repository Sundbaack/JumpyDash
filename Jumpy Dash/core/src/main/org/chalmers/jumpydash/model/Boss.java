package org.chalmers.jumpydash.model;

public class Boss extends Enemy {

    private long previousFireTime = 0;

    public Boss() {
        super(3,2);
    }

    public boolean allowedToFire(){
        long fireCooldown = 1000;
        if (System.currentTimeMillis() - previousFireTime >= fireCooldown) {
            setPreviousFireTime(System.currentTimeMillis());
            return true;
        }
        return false;
    }

    public void setPreviousFireTime(long time){
        previousFireTime = time;
    }

    @Override
    public void checkCollision(JDModel jDModel) {
        if (this.getClass() == Boss.class) {
            if (jDModel.getClass() == Bullet.class) {
                this.takeDamage(1);
                jDModel.userDataNull();
                if(isDead()){
                    this.userDataNull();
                }
            }
        }
    }

}
