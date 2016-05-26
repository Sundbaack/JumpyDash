package org.chalmers.jumpydash.model;

public class Bullet extends JDModel {

    public Bullet() {

    }

    public void checkCollision(JDModel jDModel) {
        if (this.getClass() == Bullet.class) {
            if (jDModel.getClass() == Cannon.class) {
                Cannon cannon = ((Cannon) jDModel);
                System.out.println("cannon");
            }
        }
    }
}
