package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.model.Platform;
import org.chalmers.jumpydash.view.PlatformView;

import static org.chalmers.jumpydash.util.Constants.*;

public class PlatformController extends Actor {

    private PlatformView platformView;
    private Platform platform;
    private IBox2D box2D;

    public PlatformController(IBox2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        platformView = new PlatformView();
        platform = new Platform(box2D.newBody(x, y, mapHeight, "static", true,false));
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        platformView.render(batch, platform.getPosition().x * PIXELS_TO_METERS, platform.getPosition().y * PIXELS_TO_METERS);
    }

    public void dispose() {
        platformView.dispose();
    }
}
