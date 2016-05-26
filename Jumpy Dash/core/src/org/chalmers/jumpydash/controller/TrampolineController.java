package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import org.chalmers.jumpydash.model.Trampoline;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.JDView;
import org.chalmers.jumpydash.view.TrampolineView;

import static org.chalmers.jumpydash.physics.Box2D.PIXELS_TO_METERS;

public class TrampolineController extends JDController {

    private JDView trampolineView;
    private Trampoline trampoline;

    public TrampolineController(IBox2D box2D, int x, int y, int mapHeight) {
        trampolineView = new TrampolineView();
        trampoline = new Trampoline();
        trampoline.setJDBody(box2D.newBody(x, y, mapHeight, "static", false,false));
        trampoline.getJDBody().setUserData(trampoline);
    }

    @Override
    public void act(float Delta) {
        if (!trampoline.getJDBody().isActive()) {
            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        trampolineView.render(batch, trampoline.getPosition().x * PIXELS_TO_METERS, trampoline.getPosition().y * PIXELS_TO_METERS);
    }

    @Override
    public void dispose() {
        trampolineView.dispose();
    }
}
