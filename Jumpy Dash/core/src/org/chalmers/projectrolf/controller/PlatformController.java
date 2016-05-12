package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.projectrolf.physics.IBox2D;
import org.chalmers.projectrolf.model.Platform;
import org.chalmers.projectrolf.view.PlatformView;

public class PlatformController extends Actor {

    private PlatformView platformView;
    private Platform platform;
    private IBox2D box2D;

    public PlatformController(IBox2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        platformView = new PlatformView();
        platform = new Platform(box2D.newStatic(x,y,mapHeight,true));
    }

    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        platformView.render(batch, platform.getPosition().x * box2D.getPixelsToMeters(), platform.getPosition().y * box2D.getPixelsToMeters());
    }

    public void dispose() {
        platformView.dispose();
    }
}
