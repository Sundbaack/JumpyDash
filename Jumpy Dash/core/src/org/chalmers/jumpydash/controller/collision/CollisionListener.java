package org.chalmers.jumpydash.controller.collision;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import org.chalmers.jumpydash.controller.PlayerController;
import org.chalmers.jumpydash.controller.SoldierController;

import java.util.ArrayList;
import java.util.List;

public class CollisionListener extends JDCollision {

    private List<ContactListener> collisionListenerList;

    public CollisionListener(PlayerController playerController, List<SoldierController> soldierControllerList) {
        collisionListenerList = new ArrayList<ContactListener>();

        // Add separate contact listeners
        collisionListenerList.add(new PlayerCollisionListener(playerController));
        collisionListenerList.add(new BulletCollisionListener(soldierControllerList));
        collisionListenerList.add(new SoldierCollisionListener(soldierControllerList));
    }

    @Override
    public void beginContact(Contact contact) {
       for(ContactListener c: collisionListenerList){
            c.beginContact(contact);
        }
    }
}