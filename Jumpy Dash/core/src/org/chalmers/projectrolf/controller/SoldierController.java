package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.chalmers.projectrolf.model.Soldier;
import org.chalmers.projectrolf.view.SoldierView;

import java.util.ArrayList;
import java.util.List;

public class SoldierController {

    private List<Soldier> soldierList;
    private SoldierView soldierView;
    private Box2D box2D;
    private final float PIXELS_TO_METERS;

    public SoldierController(Box2D box2D) {
        soldierList = new ArrayList<Soldier>();
        this.box2D = box2D;
        this.PIXELS_TO_METERS = box2D.getPixelsToMeters();

        soldierView = new SoldierView();
    }

    public void createObject(int x,int y, int mapHeight) {
        Soldier soldier = new Soldier(box2D.newDynamic(x, y, mapHeight));
        soldierList.add(soldier);
    }

    public void update(SpriteBatch batch) {
        render(batch);
    }

    public void render(SpriteBatch batch) {
        for(Soldier p : soldierList) {
           soldierView.render(p.getPosition().x * PIXELS_TO_METERS, p.getPosition().y * PIXELS_TO_METERS, batch);
        }
    }

    public void dispose() {
        soldierView.dispose();
    }
}
