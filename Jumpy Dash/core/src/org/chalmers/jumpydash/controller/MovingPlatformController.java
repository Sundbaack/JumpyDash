package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.jumpydash.model.MovingPlatform;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.IView;
import org.chalmers.jumpydash.view.MovingPlatformView;
import static org.chalmers.jumpydash.physics.Box2D.PIXELS_TO_METERS;

public class MovingPlatformController extends Actor {

    private IView movingPlatformView;
    private MovingPlatform movingPlatform;

    public MovingPlatformController(IBox2D box2D, int x, int y, int mapHeight) {
        movingPlatformView = new MovingPlatformView();
        movingPlatform = new MovingPlatform();
        movingPlatform.setJDBody(box2D.newBody(x, y, mapHeight, "kinematic", false,false));
        movingPlatform.getJDBody().setUserData(movingPlatform);
    }

    @Override
    public void act(float delta) {
        if (MovingPlatform.movePlatforms) {
            movingPlatform.moveUp();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        movingPlatformView.render(batch, movingPlatform.getPosition().x * PIXELS_TO_METERS, movingPlatform.getPosition().y * PIXELS_TO_METERS);
    }

    public void dispose() {
        movingPlatformView.dispose();
    }
}
