package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.chalmers.projectrolf.model.Ability;
import org.chalmers.projectrolf.view.AbilityView;

import java.util.ArrayList;
import java.util.List;

public class AbilityController {

    private List<Ability> abilities;
    private AbilityView abilityView;
    private Box2D box2D;

    public AbilityController(Box2D box2D) {
        this.box2D = box2D;
        abilities = new ArrayList<Ability>();
        abilityView = new AbilityView();
    }

    public void createObject(int x, int y, int mapHeight) {
        abilities.add(new Ability(box2D.newDynamic(x, y, mapHeight)));
    }

    public void update(SpriteBatch batch) {
        render(batch);
    }

    public void render(SpriteBatch batch) {
        for(Ability a: abilities) {
            abilityView.render(a.getPosition().x * box2D.getPixelsToMeters(), a.getPosition().y *  box2D.getPixelsToMeters(), batch);
        }
    }

    public void dispose() {
        abilityView.dispose();
    }
}
