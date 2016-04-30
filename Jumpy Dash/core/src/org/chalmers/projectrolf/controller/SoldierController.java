package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.chalmers.projectrolf.model.Soldier;
import org.chalmers.projectrolf.view.SoldierView;

import java.util.ArrayList;
import java.util.List;

public class SoldierController {

    private List<Soldier> soldierList;
    private SoldierView soldierView;
    private final float tileWidthHeight;
    private final float PIXELS_TO_METERS;

    public SoldierController(float tileWidthHeight, float PIXELS_TO_METERS) {
        soldierList = new ArrayList<Soldier>();
        this.tileWidthHeight = tileWidthHeight;
        this.PIXELS_TO_METERS = PIXELS_TO_METERS;

        soldierView = new SoldierView();
    }

    public void createObject(int x,int y, int mapHeight) {
        BodyDef soldierBodyDef = new BodyDef();
        soldierBodyDef.type = BodyDef.BodyType.DynamicBody;
        soldierBodyDef.position.set(x * tileWidthHeight / PIXELS_TO_METERS, (mapHeight - 1 - y) * 32 / PIXELS_TO_METERS);
        Body soldierBody = JumpyDash.world.createBody(soldierBodyDef);

        Soldier soldier = new Soldier(soldierBody, tileWidthHeight / PIXELS_TO_METERS);
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
