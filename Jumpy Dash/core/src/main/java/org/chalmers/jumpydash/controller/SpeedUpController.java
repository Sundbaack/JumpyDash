package main.java.org.chalmers.jumpydash.jumpydash.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import main.java.org.chalmers.jumpydash.jumpydash.model.SpeedUp;
import main.java.org.chalmers.jumpydash.jumpydash.physics.BodyType;
import main.java.org.chalmers.jumpydash.jumpydash.physics.IBox2D;
import main.java.org.chalmers.jumpydash.jumpydash.util.Options;
import main.java.org.chalmers.jumpydash.jumpydash.view.JDView;
import main.java.org.chalmers.jumpydash.jumpydash.view.SpeedUpView;
import static main.java.org.chalmers.jumpydash.jumpydash.physics.Box2D.PIXELS_TO_METERS;

public class SpeedUpController extends JDController {

    private SpeedUp speedUp;
    private JDView speedUpView;
    private Sound speedUpSound;

    public SpeedUpController(IBox2D box2D, int x, int y, int mapHeight) {
        speedUpView = new SpeedUpView();
        speedUp = new SpeedUp();
        speedUp.setJDBody(box2D.newBody(x, y, mapHeight, BodyType.STATIC, false,false));
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
