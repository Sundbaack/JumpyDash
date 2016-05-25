package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import org.chalmers.jumpydash.model.Cannon;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.CannonView;
import org.chalmers.jumpydash.view.JDView;

import javax.vecmath.Vector2f;
import static org.chalmers.jumpydash.physics.Box2D.PIXELS_TO_METERS;

public class CannonController extends JDController {

    private Cannon cannon;
    private JDView cannonView;
    private IBox2D box2D;

    public CannonController(IBox2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        cannon = new Cannon();
        cannon.setJDBody(box2D.newBody(x, y, mapHeight, "dynamic", false,false));
        cannon.getJDBody().setUserData(cannon);
        cannonView = new CannonView();
    }

    @Override
    public void act(float Delta){
        if (cannon.getJDBody().isAwake()) {
            fireCannon();
        }
        if (!cannon.getJDBody().isActive()) {
            this.remove();
        }
    }

    public void fireCannon(){
        if(cannon.allowedToFire()){
            BulletController bulletController = new BulletController(box2D, cannon.getPosition().x, cannon.getPosition().y, new Vector2f(-5f, 0));
            getStage().addActor(bulletController);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        cannonView.render(batch, cannon.getPosition().x * PIXELS_TO_METERS, cannon.getPosition().y * PIXELS_TO_METERS);

    }

    @Override
    public void dispose() {
        cannonView.dispose();
    }
}
    /*BulletController bulletController = new BulletController(box2D, player.getPosition().x, player.getPosition().y);
    getStage().addActor(bulletController);*/