package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.model.Soldier;
import org.chalmers.jumpydash.view.IView;
import org.chalmers.jumpydash.view.SoldierView;
import static org.chalmers.jumpydash.physics.Box2D.PIXELS_TO_METERS;

public class SoldierController extends Actor {

    private Soldier soldier;
    private IView soldierView;

    public SoldierController(IBox2D box2D, int x, int y, int mapHeight) {
        soldier = new Soldier();
        soldier.setJDBody(box2D.newBody(x, y, mapHeight, "dynamic", false,false));
        soldier.getJDBody().setUserData(soldier);
        soldierView = new SoldierView();
    }

    @Override
    public void act(float Delta){
        soldier.move();
        if (!soldier.getJDBody().isActive()) {
            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
       soldierView.render(batch, soldier.getPosition().x * PIXELS_TO_METERS, soldier.getPosition().y * PIXELS_TO_METERS);

    }

    public void dispose() {
        soldierView.dispose();
    }
}
