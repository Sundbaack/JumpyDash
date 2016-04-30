package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.chalmers.projectrolf.model.Platform;
import org.chalmers.projectrolf.view.PlatformView;

import java.util.ArrayList;
import java.util.List;

public class PlatformController extends ApplicationAdapter {

    private List<Platform> platformList;
    private PlatformView platformView;
    private Box2D box2D;
    private final float PIXELS_TO_METERS;

    public PlatformController(Box2D box2D) {
        this.box2D = box2D;
        this.PIXELS_TO_METERS = box2D.getPixelsToMeters();
        platformList = new ArrayList<Platform>();
        platformView = new PlatformView();
    }

    public void createObject(int x, int y, int mapHeight) {
        platformList.add(new Platform(box2D.newStatic(x, y, mapHeight, false)));
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
