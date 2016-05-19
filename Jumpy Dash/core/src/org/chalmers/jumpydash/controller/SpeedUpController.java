package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.jumpydash.model.SpeedUp;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.SpeedUpView;
import static org.chalmers.jumpydash.util.Constants.*;

public class SpeedUpController extends Actor {

    private SpeedUp speedUp;
    private SpeedUpView speedUpView;
    private IBox2D box2D;

    public SpeedUpController(IBox2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        speedUpView = new SpeedUpView();
        speedUp = new SpeedUp(box2D.newBody(x, y, mapHeight, "static", false,false));
    }

    public void act(float Delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        speedUpView.render(batch, speedUp.getPosition().x * PIXELS_TO_METERS, speedUp.getPosition().y * PIXELS_TO_METERS);
    }

    public void dispose() {
        speedUpView.dispose();
    }

}
