package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.jumpydash.model.Spike;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.SpikeView;
import static org.chalmers.jumpydash.physics.Box2D.PIXELS_TO_METERS;

public class SpikeController extends Actor {

    private SpikeView spikeView;
    private IBox2D box2D;
    private Spike spike;

    public SpikeController(IBox2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        spikeView = new SpikeView();
        spike = new Spike(box2D.newBody(x, y, mapHeight, "static", false,false));
    }

    @Override
    public void act(float Delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        spikeView.render(batch, spike.getPosition().x * PIXELS_TO_METERS, spike.getPosition().y * PIXELS_TO_METERS);
    }

    public void dispose() {
        spikeView.dispose();
    }

}
