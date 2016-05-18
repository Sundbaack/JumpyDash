package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.jumpydash.model.Trampoline;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.TrampolineView;
import static org.chalmers.jumpydash.util.Constants.*;

public class TrampolineController extends Actor {

    private TrampolineView trampolineView;
    private IBox2D box2D;
    private Trampoline trampoline;

    public TrampolineController(IBox2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        trampolineView = new TrampolineView();
        trampoline = new Trampoline(box2D.newBody(x, y, mapHeight, "static", false));
    }

    @Override
    public void act(float Delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        trampolineView.render(batch, trampoline.getPosition().x * PIXELS_TO_METERS, trampoline.getPosition().y * PIXELS_TO_METERS);
    }

    public void dispose() {
        trampolineView.dispose();
    }
}
