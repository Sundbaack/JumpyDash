package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.model.Soldier;
import org.chalmers.jumpydash.view.JDView;
import org.chalmers.jumpydash.view.SoldierView;
import org.chalmers.jumpydash.physics.Box2D;

public class SoldierController extends JDController {

    private Soldier soldier;
    private JDView soldierView;
    private int count;

    public SoldierController(IBox2D box2D, int x, int y, int mapHeight, int count) {
        soldier = new Soldier(count);
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
       soldierView.render(batch, soldier.getPosition().x * Box2D.PIXELS_TO_METERS, soldier.getPosition().y * Box2D.PIXELS_TO_METERS);

    }

    public Soldier getSoldier(){
        return soldier;
    }

    public void setSoldierCount(int count){
        System.out.println(count);
        this.count = count;
    }

    @Override
    public void dispose() {
        soldierView.dispose();
    }
}
