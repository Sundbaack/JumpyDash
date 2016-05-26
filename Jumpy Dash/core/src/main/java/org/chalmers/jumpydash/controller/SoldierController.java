package main.java.org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import main.java.org.chalmers.jumpydash.view.JDView;
import main.java.org.chalmers.jumpydash.view.SoldierView;

import main.java.org.chalmers.jumpydash.physics.IBox2D;
import main.java.org.chalmers.jumpydash.model.Soldier;
import main.java.org.chalmers.jumpydash.physics.Box2D;
import java.util.Timer;
import java.util.TimerTask;

public class SoldierController extends JDController {

    private Soldier soldier;
    private JDView soldierView;
    private Timer time;

    public SoldierController(IBox2D box2D, int x, int y, int mapHeight, int count) {
        soldier = new Soldier(count);
        soldier.setJDBody(box2D.newBody(x, y, mapHeight, "dynamic", false,false));
        soldier.getJDBody().setUserData(soldier);
        soldierView = new SoldierView();
        time = new Timer();
        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (soldier.getJDBody().isAwake()) {
                    soldier.jump();
                }
            }
        },0,3000);
    }

    @Override
    public void act(float Delta){
        if (soldier.getJDBody().isAwake()) {
            soldier.move();
        }
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

    @Override
    public void dispose() {
        soldierView.dispose();
    }
}
