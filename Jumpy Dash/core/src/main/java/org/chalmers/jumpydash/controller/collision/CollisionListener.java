package main.java.org.chalmers.jumpydash.jumpydash.controller.collision;

import com.badlogic.gdx.physics.box2d.Contact;
import main.java.org.chalmers.jumpydash.jumpydash.model.JDModel;

public class CollisionListener extends JDCollision {

    private JDModel jDModelA;
    private JDModel jdModelB;

    @Override
    public void beginContact(Contact contact) {
        jDModelA = ((JDModel) contact.getFixtureA().getBody().getUserData());
        jdModelB = ((JDModel) contact.getFixtureB().getBody().getUserData());
        if(jdModelB != null && jDModelA != null){
                       jDModelA.checkCollision(jdModelB);
                       jdModelB.checkCollision(jDModelA);
                  }
    }
}