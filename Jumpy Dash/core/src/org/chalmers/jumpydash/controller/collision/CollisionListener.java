package org.chalmers.jumpydash.controller.collision;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import org.chalmers.jumpydash.controller.PlayerController;

import java.util.ArrayList;
import java.util.List;

public class CollisionListener extends JDCollision {

    private List<ContactListener> collisionListenerList;

    public CollisionListener(PlayerController playerController) {
        collisionListenerList = new ArrayList<ContactListener>();

        // Add separate contact listeners
        collisionListenerList.add(new PlayerCollisionListener(playerController));
        collisionListenerList.add(new BulletCollisionListener());
    }

    @Override
    public void beginContact(Contact contact) {
       for(ContactListener c: collisionListenerList){
            c.beginContact(contact);
        }
    }
}