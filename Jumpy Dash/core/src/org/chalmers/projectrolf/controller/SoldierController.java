package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.projectrolf.model.JDBody;
import org.chalmers.projectrolf.model.Soldier;
import org.chalmers.projectrolf.view.SoldierView;

public class SoldierController extends Actor {

    private Soldier soldier;
    private SoldierView soldierView;
    private JDBody body;
    private Box2D box2D;

    public SoldierController(Box2D box2D,int x,int y, int mapHeight) {

        this.box2D = box2D;
        soldier = new Soldier();
        soldierView = new SoldierView();
        this.body = box2D.newDynamic(x,y,mapHeight);
        body.setUserData(soldier);
    }


    public void act(float Delta){

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
       soldierView.render(batch, getPosition().x*box2D.getPixelsToMeters(),getPosition().y * box2D.getPixelsToMeters());

    }

    public Vector2 getPosition(){
        return body.getPosition();
    }

    public JDBody getBody() {
        return body;
    }

    public void move() {
        // Checks in what direction the soldier should move
        if(soldier.getDirectionFlag()){
            body.applyForceToCenter(new Vector2(50f, 0), true);
        }
        else{
            body.applyForceToCenter(new Vector2(-50f, 0), true);
        }
    }


    public void dispose() {
        soldierView.dispose();
    }
}
