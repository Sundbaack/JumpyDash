package main.java.org.chalmers.jumpydash.controller.collision;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import main.java.org.chalmers.jumpydash.controller.SoldierController;
import main.java.org.chalmers.jumpydash.model.Bullet;
import main.java.org.chalmers.jumpydash.model.Cannon;
import main.java.org.chalmers.jumpydash.model.Soldier;

import java.util.List;

public class BulletCollisionListener extends JDCollision {

    private boolean bulletA;
    private boolean bulletB;
    private boolean cannonA;
    private boolean cannonB;
    private boolean soldierA;
    private boolean soldierB;
    private Soldier soldier;
    private Cannon cannon;
    private List<SoldierController> soldierControllerList;


    public BulletCollisionListener(List<SoldierController> soldierControllerList){
        this.soldierControllerList = soldierControllerList;

    }

    // Determine type of the two colliding bodies
    private void checkInstance(Body a, Body b) {
        bulletA = a.getUserData() instanceof Bullet;
        bulletB = b.getUserData() instanceof Bullet;
        cannonA = a.getUserData() instanceof Cannon;
        cannonB = b.getUserData() instanceof Cannon;
        soldierA = a.getUserData() instanceof Soldier;
        soldierB = b.getUserData() instanceof Soldier;
    }

    // Determine who is colliding with who
    private void checkCollision(Body a, Body b) {
        //Check collision between bullet and soldier
        if ((bulletB && soldierA) || (bulletA && soldierB)) {
            if(soldierA){
                System.out.println("soldiera remove");
                soldier = ((Soldier) a.getUserData());
            }
            else if(soldierB){
                System.out.println("soldierb remove");
                soldier = ((Soldier) b.getUserData());
            }
            a.setUserData(null);
            b.setUserData(null);

        }
        //not working cause cannon is static and bullet is kinematic
        //Check collision between bullet and cannon
        if ((bulletB && cannonA) || (bulletA && cannonB)) {
            if(cannonA){
                cannon = ((Cannon) a.getUserData());
                cannon.damageTaken();
                if(cannon.isDead()){
                    a.setUserData(null);
                }
                b.setUserData(null);
            }
            else if(cannonB){
                cannon = ((Cannon) b.getUserData());
                cannon.damageTaken();
                if (cannon.isDead()){
                    b.setUserData(null);
                }
                a.setUserData(null);
            }
        }
    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();
        checkInstance(a,b);
        checkCollision(a,b);
    }
}
