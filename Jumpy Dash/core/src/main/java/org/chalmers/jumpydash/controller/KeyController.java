package main.java.org.chalmers.jumpydash.jumpydash.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import main.java.org.chalmers.jumpydash.jumpydash.model.Key;
import main.java.org.chalmers.jumpydash.jumpydash.physics.BodyType;
import main.java.org.chalmers.jumpydash.jumpydash.physics.Box2D;
import main.java.org.chalmers.jumpydash.jumpydash.physics.IBox2D;
import main.java.org.chalmers.jumpydash.jumpydash.util.Options;
import main.java.org.chalmers.jumpydash.jumpydash.view.JDView;
import main.java.org.chalmers.jumpydash.jumpydash.view.KeyView;

public class KeyController extends JDController {

    private JDView keyView;
    private Key key;
    private Sound keySound;

    public KeyController(IBox2D box2D, int x, int y, int mapHeight) {
        keyView = new KeyView();
        key = new Key();
        key.setJDBody(box2D.newBody(x, y, mapHeight, BodyType.STATIC, false, true));
        key.getJDBody().setUserData(key);
        keySound = Gdx.audio.newSound(Gdx.files.internal("sounds/key.wav"));
    }

    @Override
    public void act(float Delta){
        if (!key.getJDBody().isActive()) {
            if (Options.getInstance().getSound()) {
                keySound.play(1);
            }
            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
         keyView.render(batch, key.getPosition().x * Box2D.PIXELS_TO_METERS, key.getPosition().y * Box2D.PIXELS_TO_METERS);
    }

    @Override
    public void dispose() {
        keyView.dispose();
        keySound.dispose();
    }
}
