package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import org.chalmers.jumpydash.model.Invincible;
import org.chalmers.jumpydash.physics.BodyType;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.util.Options;
import org.chalmers.jumpydash.view.JDView;
import org.chalmers.jumpydash.view.InvincibleView;
import static org.chalmers.jumpydash.physics.Box2D.PIXELS_TO_METERS;

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
