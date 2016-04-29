package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.chalmers.projectrolf.model.Enemy;
import org.chalmers.projectrolf.model.Soldier;
import org.chalmers.projectrolf.view.SoldierView;

import java.util.ArrayList;
import java.util.List;

import static org.chalmers.projectrolf.controller.GameController.PIXELS_TO_METERS;

/**
 * Created by alexsundback on 2016-04-28.
 */
public class SoldierController {
    private List<Soldier> soldierList;
    private float tileWidthHeight;
    private float mapHeight;
    private final SoldierView soldierView;

    public SoldierController(float tileWidthHeight, float mapHeight) {
        soldierList = new ArrayList<Soldier>();
        this.tileWidthHeight = tileWidthHeight;
        this.mapHeight = mapHeight;

        soldierView = new SoldierView(soldierList);
    }

    public void createObject(int x,int y) {
        BodyDef soldierBodyDef = new BodyDef();
        soldierBodyDef.type = BodyDef.BodyType.DynamicBody;
        soldierBodyDef.position.set(x * tileWidthHeight / PIXELS_TO_METERS, (mapHeight - 1 - y) * 32 / PIXELS_TO_METERS);
        Body soldierBody = world.createBody(soldierBodyDef);

        Soldier soldier = new Soldier(soldierBody, tileWidthHeight / PIXELS_TO_METERS);
        soldierList.add(soldier);
    }
}
