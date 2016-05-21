package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import org.chalmers.jumpydash.model.Ability;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.AbilityView;
import org.chalmers.jumpydash.view.JDView;
import static org.chalmers.jumpydash.physics.Box2D.PIXELS_TO_METERS;

public class AbilityController extends JDController {

    private JDView abilityView;
    private Ability ability;

    public AbilityController(IBox2D box2D, int x, int y, int mapHeight) {
        ability = new Ability();
        ability.setJDBody(box2D.newBody(x, y, mapHeight, "static", false,true));
        ability.getJDBody().setUserData(ability);
        abilityView = new AbilityView();
    }

    @Override
    public void act(float Delta) {
        if (!ability.getJDBody().isActive()) {
            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        abilityView.render(batch, ability.getPosition().x * PIXELS_TO_METERS, ability.getPosition().y * PIXELS_TO_METERS);

    }

    @Override
    public void dispose() {
        abilityView.dispose();
    }
}
