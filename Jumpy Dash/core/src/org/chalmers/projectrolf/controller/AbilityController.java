package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.projectrolf.model.Ability;
import org.chalmers.projectrolf.physics.IBox2D;
import org.chalmers.projectrolf.view.AbilityView;

public class AbilityController extends Actor{

    private AbilityView abilityView;
    private IBox2D box2D;
    private Ability ability;

    public AbilityController(IBox2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        ability = new Ability(box2D.newDynamic(x, y, mapHeight));
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
