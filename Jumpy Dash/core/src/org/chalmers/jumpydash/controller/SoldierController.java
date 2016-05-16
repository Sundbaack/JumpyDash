package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.model.Soldier;
import org.chalmers.jumpydash.view.SoldierView;

public class SoldierController extends Actor {

    private Soldier soldier;
    private SoldierView soldierView;
    private IBox2D box2D;

    public SoldierController(IBox2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        soldier = new Soldier(box2D.newDynamic(x,y,mapHeight));
        soldierView = new SoldierView();

    }

    public void act(float Delta){
        soldier.move();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
       soldierView.render(batch, soldier.getPosition().x * box2D.getPixelsToMeters(),soldier.getPosition().y * box2D.getPixelsToMeters());

    }

    public void dispose() {
        soldierView.dispose();
    }
}
