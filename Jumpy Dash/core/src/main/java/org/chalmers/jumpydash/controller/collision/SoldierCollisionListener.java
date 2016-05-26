package main.java.org.chalmers.jumpydash.controller.collision;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import main.java.org.chalmers.jumpydash.model.Sensor;
import main.java.org.chalmers.jumpydash.controller.SoldierController;
import main.java.org.chalmers.jumpydash.model.Soldier;

import java.util.List;

public class SoldierCollisionListener extends JDCollision {


    private List<SoldierController> soldierControllerList;
    private boolean soldierA;
    private boolean soldierB;
    private boolean sensorA;
    private boolean sensorB;
    private Soldier soldier;

    public SoldierCollisionListener(List<SoldierController> soldierControllerList) {
        this.soldierControllerList = soldierControllerList;
    }


    private void checkInstance(Body a, Body b) {
        soldierA = a.getUserData() instanceof Soldier;
        soldierB = b.getUserData() instanceof Soldier;
        sensorA = a.getUserData() instanceof Sensor;
        sensorB = b.getUserData() instanceof Sensor;
    }

    private void checkCollision(Body a, Body b) {
        if(soldierA && sensorB || soldierB && sensorA) {
            if(soldierA){
                soldier = ((Soldier) a.getUserData());
                System.out.println("Body a Soldier count: " +soldier.getCount());
                soldierControllerList.get(soldier.getCount()).getSoldier().setDirectionFlag();
            }
            else if(soldierB){
                soldier = ((Soldier) b.getUserData());
                System.out.println("Body b Soldier count: " + soldier.getCount());
                soldierControllerList.get(soldier.getCount()).getSoldier().setDirectionFlag();

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
