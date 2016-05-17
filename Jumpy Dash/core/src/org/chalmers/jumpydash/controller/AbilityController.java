package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.jumpydash.model.Ability;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.AbilityView;

public class AbilityController extends Actor{

    private AbilityView abilityView;
    private IBox2D box2D;
    private Ability ability;

    public AbilityController(IBox2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        ability = new Ability(box2D.newBody(x, y, mapHeight, "static", false));
        abilityView = new AbilityView();
    }

    public void act(float Delta){

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        abilityView.render(batch, ability.getPosition().x * box2D.getPixelsToMeters(), ability.getPosition().y * box2D.getPixelsToMeters());

    }

    public void dispose() {
        abilityView.dispose();
    }
}
