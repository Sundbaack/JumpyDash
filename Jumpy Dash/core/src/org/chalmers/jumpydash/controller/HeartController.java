package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import org.chalmers.jumpydash.model.Heart;
import org.chalmers.jumpydash.physics.Box2D;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.HeartView;
import org.chalmers.jumpydash.view.JDView;

/**
 * Created by Oscar on 2016-05-26.
 */
public class HeartController extends JDController {


    private JDView heartView;
    private Heart heart;

    public HeartController(IBox2D box2D, int x, int y, int mapHeight) {
        heartView = new HeartView();
        heart = new Heart();
        heart.setJDBody(box2D.newBody(x, y, mapHeight, "static", false, true));
        heart.getJDBody().setUserData(heart);
    }

    @Override
    public void act(float Delta){
        if (!heart.getJDBody().isActive()) {
            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        heartView.render(batch, heart.getPosition().x * Box2D.PIXELS_TO_METERS, heart.getPosition().y * Box2D.PIXELS_TO_METERS);
    }

    @Override
    public void dispose() {
        heartView.dispose();
    }
    
}
