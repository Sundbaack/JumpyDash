package main.java.org.chalmers.jumpydash.jumpydash.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import main.java.org.chalmers.jumpydash.jumpydash.model.Invincible;
import main.java.org.chalmers.jumpydash.jumpydash.physics.IBox2D;
import main.java.org.chalmers.jumpydash.jumpydash.util.Options;
import main.java.org.chalmers.jumpydash.jumpydash.view.InvincibleView;
import main.java.org.chalmers.jumpydash.jumpydash.physics.BodyType;
import main.java.org.chalmers.jumpydash.jumpydash.view.JDView;

import static main.java.org.chalmers.jumpydash.jumpydash.physics.Box2D.PIXELS_TO_METERS;

public class InvincibleController extends JDController {

    private Invincible invincible;
    private JDView invincibleView;
    private Sound invincibleSound;

    public InvincibleController(IBox2D box2D, int x, int y, int mapHeight) {
        invincibleView = new InvincibleView();
        invincible = new Invincible();
        invincible.setJDBody(box2D.newBody(x, y, mapHeight, BodyType.STATIC, false,false));
        invincible.getJDBody().setUserData(invincible);
        invincibleSound = Gdx.audio.newSound(Gdx.files.internal("sounds/powerup.wav"));
    }

    @Override
    public void act(float Delta) {
        if (!invincible.getJDBody().isActive()) {
            if (Options.getInstance().getSound()) {
                invincibleSound.play(1);
            }
            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        invincibleView.render(batch, invincible.getPosition().x * PIXELS_TO_METERS, invincible.getPosition().y * PIXELS_TO_METERS);
    }

    @Override
    public void dispose() {
        invincibleView.dispose();
        invincibleSound.dispose();
    }

}
