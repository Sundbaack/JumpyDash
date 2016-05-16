package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.jumpydash.model.Cannon;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.CannonView;

public class CannonController extends Actor {

    private Cannon cannon;
    private CannonView cannonView;
    private IBox2D box2D;

    public CannonController(IBox2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        cannon = new Cannon(box2D.newDynamic(x,y,mapHeight));
        cannonView = new CannonView();
    }

    public void act(float Delta){

    }

    public void fireShit(){
        BulletController bulletController = new BulletController(box2D, cannon.getPosition().x, cannon.getPosition().y);
        getStage().addActor(bulletController);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        cannonView.render(batch, cannon.getPosition().x * box2D.getPixelsToMeters(),cannon.getPosition().y * box2D.getPixelsToMeters());

    }

    public void dispose() {
        cannonView.dispose();
    }
}
    /*BulletController bulletController = new BulletController(box2D, player.getPosition().x, player.getPosition().y);
    getStage().addActor(bulletController);*/