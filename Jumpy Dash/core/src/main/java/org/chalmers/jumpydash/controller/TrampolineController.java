package main.java.org.chalmers.jumpydash.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import main.java.org.chalmers.jumpydash.jumpydash.model.Trampoline;
import main.java.org.chalmers.jumpydash.jumpydash.physics.BodyType;
import main.java.org.chalmers.jumpydash.jumpydash.physics.IBox2D;
import main.java.org.chalmers.jumpydash.jumpydash.view.JDView;
import main.java.org.chalmers.jumpydash.jumpydash.view.TrampolineView;
import static main.java.org.chalmers.jumpydash.jumpydash.physics.Box2D.PIXELS_TO_METERS;

public class TrampolineController extends JDController {

    private JDView trampolineView;
    private Trampoline trampoline;

    public TrampolineController(IBox2D box2D, int x, int y, int mapHeight) {
        trampolineView = new TrampolineView();
        trampoline = new Trampoline();
        trampoline.setJDBody(box2D.newBody(x, y, mapHeight, BodyType.STATIC, false,false));
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
