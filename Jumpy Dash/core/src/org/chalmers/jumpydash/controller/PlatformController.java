package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.model.Platform;
import org.chalmers.jumpydash.view.IView;
import org.chalmers.jumpydash.view.PlatformView;
import static org.chalmers.jumpydash.physics.Box2D.PIXELS_TO_METERS;

public class PlatformController extends Actor {

    private IView platformView;
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

    public void dispose() {
        platformView.dispose();
    }
}
