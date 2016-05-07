package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.chalmers.projectrolf.model.Soldier;
import org.chalmers.projectrolf.view.SoldierView;

import java.util.ArrayList;
import java.util.List;

public class SoldierController {

    private List<Soldier> soldiers;
    private SoldierView soldierView;
    private Box2D box2D;

    public SoldierController(Box2D box2D) {
        soldiers = new ArrayList<Soldier>();
        this.box2D = box2D;

        soldierView = new SoldierView();
    }

    public void createObject(int x,int y, int mapHeight) {
        Soldier soldier = new Soldier(box2D.newDynamic(x, y, mapHeight));
        soldiers.add(soldier);
    }

    public void update(SpriteBatch batch) {
        render(batch);
    }

    public void render(SpriteBatch batch) {
        for(Soldier p : soldiers) {
           soldierView.render(p.getPosition().x * box2D.getPixelsToMeters(), p.getPosition().y * box2D.getPixelsToMeters(), batch);
        }
    }

    public void dispose() {
        soldierView.dispose();
    }
}
