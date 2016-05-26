package main.java.org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import main.java.org.chalmers.jumpydash.view.JDView;
import main.java.org.chalmers.jumpydash.physics.IBox2D;
import main.java.org.chalmers.jumpydash.model.Platform;
import main.java.org.chalmers.jumpydash.view.PlatformView;

import static main.java.org.chalmers.jumpydash.physics.Box2D.PIXELS_TO_METERS;

public class PlatformController extends JDController {

    private JDView platformView;
    private Platform platform;

    public PlatformController(IBox2D box2D, int x, int y, int mapHeight) {
        platformView = new PlatformView();
        platform = new Platform();
        platform.setJDBody(box2D.newBody(x, y, mapHeight, "static", true,false));
        platform.getJDBody().setUserData(platform);
    }

    @Override
    public void act(float delta) {
        if (!platform.getJDBody().isActive()) {
            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        platformView.render(batch, platform.getPosition().x * PIXELS_TO_METERS, platform.getPosition().y * PIXELS_TO_METERS);
    }

    @Override
    public void dispose() {
        platformView.dispose();
    }
}
