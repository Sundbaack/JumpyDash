package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.chalmers.projectrolf.model.Platform;
import org.chalmers.projectrolf.view.PlatformView;
import java.util.ArrayList;
import java.util.List;

public class PlatformController extends ApplicationAdapter {

    private static List<Platform> platforms;
    private PlatformView platformView;
    private Box2D box2D;

    public PlatformController(Box2D box2D) {
        this.box2D = box2D;
        platforms = new ArrayList<Platform>();
        platformView = new PlatformView();
    }

    public void createObject(int x, int y, int mapHeight) {
        platforms.add(new Platform(box2D.newStatic(x, y, mapHeight, false)));
    }

    public void update(SpriteBatch batch) {
        render(batch);
    }

    public void render(SpriteBatch batch) {
        for (Platform p : platforms) {
            platformView.render(p.getPosition().x * box2D.getPixelsToMeters(), p.getPosition().y * box2D.getPixelsToMeters(), batch);
        }
    }

    public static List<Platform> getPlatforms() {
        return platforms;
    }

    public void dispose() {
        platformView.dispose();
    }

}
