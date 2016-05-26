package main.java.org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import main.java.org.chalmers.jumpydash.model.SpeedUp;
import main.java.org.chalmers.jumpydash.physics.IBox2D;
import main.java.org.chalmers.jumpydash.view.JDView;
import main.java.org.chalmers.jumpydash.view.SpeedUpView;

import static main.java.org.chalmers.jumpydash.physics.Box2D.PIXELS_TO_METERS;

public class SpeedUpController extends JDController {

    private SpeedUp speedUp;
    private JDView speedUpView;

    public SpeedUpController(IBox2D box2D, int x, int y, int mapHeight) {
        speedUpView = new SpeedUpView();
        speedUp = new SpeedUp();
        speedUp.setJDBody(box2D.newBody(x, y, mapHeight, "static", false,false));
        speedUp.getJDBody().setUserData(speedUp);
    }

    @Override
    public void act(float Delta) {
        if (!speedUp.getJDBody().isActive()) {
            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        speedUpView.render(batch, speedUp.getPosition().x * PIXELS_TO_METERS, speedUp.getPosition().y * PIXELS_TO_METERS);
    }

    @Override
    public void dispose() {
        speedUpView.dispose();
    }

}
