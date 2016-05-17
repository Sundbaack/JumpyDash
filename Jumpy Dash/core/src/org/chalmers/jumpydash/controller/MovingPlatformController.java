package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.jumpydash.model.MovingPlatform;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.MovingPlatformView;

/**
 * Created by Surface pro 3 on 2016-05-17.
 */
public class MovingPlatformController extends Actor {

    private MovingPlatformView movingPlatformView;
    private MovingPlatform movingPlatform;
    private IBox2D box2D;

    public MovingPlatformController(IBox2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        movingPlatformView = new MovingPlatformView();
        movingPlatform = new MovingPlatform(box2D.newDynKin(x,y,mapHeight,true));
    }

    public void act(float delta) {
        if(MovingPlatform.movePlatforms == true){
            movingPlatform.moveUp();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        movingPlatformView.render(batch, movingPlatform.getPosition().x * box2D.getPixelsToMeters(), movingPlatform.getPosition().y * box2D.getPixelsToMeters());
    }

    public void dispose() {
        movingPlatformView.dispose();
    }
}
