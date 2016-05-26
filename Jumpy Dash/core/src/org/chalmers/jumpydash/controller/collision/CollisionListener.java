package org.chalmers.jumpydash.controller.collision;

import com.badlogic.gdx.physics.box2d.Contact;
import org.chalmers.jumpydash.model.JDModel;

public class CollisionListener extends JDCollision {

    private JDModel jDModelA;
    private JDModel jdModelB;

    @Override
    public void beginContact(Contact contact) {
        jDModelA = ((JDModel) contact.getFixtureA().getBody().getUserData());
        jdModelB = ((JDModel) contact.getFixtureB().getBody().getUserData());
        jDModelA.checkCollision(jdModelB);
    }
}