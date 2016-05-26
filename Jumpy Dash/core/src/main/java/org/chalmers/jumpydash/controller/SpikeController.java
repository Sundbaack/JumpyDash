package main.java.org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import main.java.org.chalmers.jumpydash.view.JDView;
import main.java.org.chalmers.jumpydash.model.Spike;
import main.java.org.chalmers.jumpydash.physics.IBox2D;
import main.java.org.chalmers.jumpydash.view.SpikeView;
import static main.java.org.chalmers.jumpydash.physics.Box2D.PIXELS_TO_METERS;

public class SpikeController extends JDController {

    private JDView spikeView;
    private Spike spike;

    public SpikeController(IBox2D box2D, int x, int y, int mapHeight) {
        spikeView = new SpikeView();
        spike = new Spike();
        spike.setJDBody(box2D.newBody(x, y, mapHeight, "static", false,false));
        spike.getJDBody().setUserData(spike);
    }

    @Override
    public void act(float Delta) {
        if (!spike.getJDBody().isActive()) {
            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        spikeView.render(batch, spike.getPosition().x * PIXELS_TO_METERS, spike.getPosition().y * PIXELS_TO_METERS);
    }

    @Override
    public void dispose() {
        spikeView.dispose();
    }

}
