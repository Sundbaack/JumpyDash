package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.projectrolf.physics.IBox2D;
import org.chalmers.projectrolf.model.Soldier;
import org.chalmers.projectrolf.physics.IJDBody;
import org.chalmers.projectrolf.view.SoldierView;

public class SoldierController extends Actor {

    private Soldier soldier;
    private SoldierView soldierView;
    private IBox2D box2D;
    private IJDBody jdBody;

    public SoldierController(IBox2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        soldier = new Soldier();
        soldierView = new SoldierView();
        jdBody = box2D.newDynamic(x,y,mapHeight);
        jdBody.setUserData(soldier);
    }

    public void act(float Delta){

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
       soldierView.render(batch, getPosition().x * box2D.getPixelsToMeters(),getPosition().y * box2D.getPixelsToMeters());

    }

    public Vector2 getPosition(){
        return jdBody.getPosition();
    }

    public void move() {
        // Checks in what direction the soldier should move
        if(soldier.getDirectionFlag()){
            jdBody.applyForceToCenter(new Vector2(50f, 0), true);
        }
        else{
            jdBody.applyForceToCenter(new Vector2(-50f, 0), true);
        }
    }

    public void dispose() {
        soldierView.dispose();
    }
}
