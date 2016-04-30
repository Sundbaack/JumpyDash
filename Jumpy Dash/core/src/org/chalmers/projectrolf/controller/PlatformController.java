package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.chalmers.projectrolf.model.Platform;
import org.chalmers.projectrolf.view.PlatformView;

import java.util.ArrayList;
import java.util.List;

public class PlatformController extends ApplicationAdapter {

    private BodyDef platformBodyDef;
    private Body platformBody;
    private Platform platform;
    private List<Platform> platformList;
    private PlatformView platformView;
    private final float tileWidthHeight;
    private final float PIXELS_TO_METERS;

    public PlatformController(float tileWidthHeight, float PIXELS_TO_METERS) {

        this.tileWidthHeight = tileWidthHeight;
        this.PIXELS_TO_METERS = PIXELS_TO_METERS;
        platformList = new ArrayList<Platform>();
        platformView = new PlatformView();
    }

    public void createObject(int x, int y, int mapHeight) {

        platformBodyDef = new BodyDef();
        platformBodyDef.type = BodyDef.BodyType.StaticBody;
        platformBodyDef.position.set(x * tileWidthHeight / PIXELS_TO_METERS, (mapHeight - 1 - y) * 32 / PIXELS_TO_METERS);
        platformBody = JumpyDash.world.createBody(platformBodyDef);
        platform = new Platform(platformBody, tileWidthHeight / PIXELS_TO_METERS);
        platformList.add(platform);
    }

    public void update(SpriteBatch batch) {
        render(batch);
    }

    public void render(SpriteBatch batch) {
        for (Platform p : platformList) {
            platformView.render(p.getPosition().x * PIXELS_TO_METERS, p.getPosition().y * PIXELS_TO_METERS, batch);
        }
    }

    public void dispose() {
        platformView.dispose();
    }

}
