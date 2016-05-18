package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.jumpydash.model.SpeedUp;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.SpeedUpView;

/**
 * Created by alexsundback on 2016-05-18.
 */
public class SpeedUpController extends Actor {

    private SpeedUp speedUp;
    private SpeedUpView speedUpView;
    private IBox2D box2D;

    public SpeedUpController(IBox2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        speedUp = new SpeedUp(box2D.newStatic(x, y, mapHeight, false));
        speedUpView = new SpeedUpView();
    }

    public void act(float Delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        speedUpView.render(batch, speedUp.getPosition().x * box2D.getPixelsToMeters(), speedUp.getPosition().y * box2D.getPixelsToMeters());
    }

    public void dispose() {
        speedUpView.dispose();
    }

}
