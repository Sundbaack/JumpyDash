package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.chalmers.projectrolf.model.Soldier;
import org.chalmers.projectrolf.view.SoldierView;

import java.util.ArrayList;
import java.util.List;

import static org.chalmers.projectrolf.controller.GameController.PIXELS_TO_METERS;


public class SoldierController {
    private List<Soldier> soldierList;
    private float tileWidthHeight;
    private final SoldierView soldierView;

    public SoldierController(float tileWidthHeight) {
        soldierList = new ArrayList<Soldier>();
        this.tileWidthHeight = tileWidthHeight;

        soldierView = new SoldierView(soldierList);
    }

    public void createObject(int x,int y, int mapHeight) {
        BodyDef soldierBodyDef = new BodyDef();
        soldierBodyDef.type = BodyDef.BodyType.DynamicBody;
        soldierBodyDef.position.set(x * tileWidthHeight / PIXELS_TO_METERS, (mapHeight - 1 - y) * 32 / PIXELS_TO_METERS);
        Body soldierBody = GameController.world.createBody(soldierBodyDef);

        Soldier soldier = new Soldier(soldierBody, tileWidthHeight / PIXELS_TO_METERS);
        soldierList.add(soldier);
    }

    public SoldierView getView() {
        return this.soldierView;
    }
}
