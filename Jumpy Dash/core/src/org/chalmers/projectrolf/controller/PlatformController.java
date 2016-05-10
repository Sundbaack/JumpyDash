package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.projectrolf.physics.Box2D;
import org.chalmers.projectrolf.physics.JDBody;
import org.chalmers.projectrolf.model.Platform;
import org.chalmers.projectrolf.view.PlatformView;

public class PlatformController extends Actor {

    private PlatformView platformView;
    private Platform platform;
    private Box2D box2D;
    private JDBody body;

    public PlatformController(Box2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        platformView = new PlatformView();
        platform = new Platform();
        this.body = box2D.newStatic(x,y,mapHeight,true);
        body.setUserData(platform);
    }

    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        platformView.render(batch,getPosition().x * box2D.getPixelsToMeters(), getPosition().y * box2D.getPixelsToMeters());
    }

    public Vector2 getPosition(){
        return body.getPosition();
    }

    public JDBody getJDBody(){
        return body;
    }

    public void dispose() {
        platformView.dispose();
    }

}
