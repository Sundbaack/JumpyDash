package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.projectrolf.physics.IBox2D;
import org.chalmers.projectrolf.model.Platform;
import org.chalmers.projectrolf.physics.IJDBody;
import org.chalmers.projectrolf.view.PlatformView;

public class PlatformController extends Actor {

    private PlatformView platformView;
    private Platform platform;
    private IBox2D box2D;
    private IJDBody jdBody;

    public PlatformController(IBox2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        platformView = new PlatformView();
        platform = new Platform();
        jdBody = box2D.newStatic(x,y,mapHeight,true);
        jdBody.setUserData(platform);
    }

    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        platformView.render(batch, getPosition().x * box2D.getPixelsToMeters(), getPosition().y * box2D.getPixelsToMeters());
    }

    public Vector2 getPosition(){
        return jdBody.getPosition();
    }

    public void dispose() {
        platformView.dispose();
    }
}
