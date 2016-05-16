package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.jumpydash.model.Trampoline;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.TrampolineView;

public class TrampolineController extends Actor {

    private TrampolineView trampolineView;
    private IBox2D box2D;
    private Trampoline trampoline;

    public TrampolineController(IBox2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        trampolineView = new TrampolineView();
        trampoline = new Trampoline(box2D.newStatic(x, y, mapHeight, false));
    }

    public void act(float Delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        trampolineView.render(batch, trampoline.getPosition().x * box2D.getPixelsToMeters(), trampoline.getPosition().y * box2D.getPixelsToMeters());
    }

    public void dispose() {
        trampolineView.dispose();
    }
}
