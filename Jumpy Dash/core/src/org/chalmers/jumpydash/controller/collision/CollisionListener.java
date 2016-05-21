package org.chalmers.jumpydash.controller.collision;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import java.util.ArrayList;
import java.util.List;

public class CollisionListener extends Collision {

    private List<ContactListener> collisionListenerList;

    public CollisionListener() {
        collisionListenerList = new ArrayList<ContactListener>();

        // Add separate contact listeners
        collisionListenerList.add(new PlayerCollisionListener());
        collisionListenerList.add(new BulletCollisionListener());
    }

    @Override
    public void beginContact(Contact contact) {
       for(ContactListener c: collisionListenerList){
            c.beginContact(contact);
        }
    }
}