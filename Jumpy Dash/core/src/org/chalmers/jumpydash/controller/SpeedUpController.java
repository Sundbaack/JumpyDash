package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import org.chalmers.jumpydash.model.SpeedUp;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.util.Options;
import org.chalmers.jumpydash.view.JDView;
import org.chalmers.jumpydash.view.SpeedUpView;
import static org.chalmers.jumpydash.physics.Box2D.PIXELS_TO_METERS;

public class SpeedUpController extends JDController {

    private SpeedUp speedUp;
    private JDView speedUpView;
    private Sound speedUpSound;

    public SpeedUpController(IBox2D box2D, int x, int y, int mapHeight) {
        speedUpView = new SpeedUpView();
        speedUp = new SpeedUp();
        speedUp.setJDBody(box2D.newBody(x, y, mapHeight, "static", false,false));
        speedUp.getJDBody().setUserData(speedUp);
        speedUpSound = Gdx.audio.newSound(Gdx.files.internal("sounds/powerup.wav"));
    }

    @Override
    public void act(float Delta) {
        if (!speedUp.getJDBody().isActive()) {
            if (Options.getInstance().getSound()) {
                speedUpSound.play(1);
            }
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
        speedUpSound.dispose();
    }

}
