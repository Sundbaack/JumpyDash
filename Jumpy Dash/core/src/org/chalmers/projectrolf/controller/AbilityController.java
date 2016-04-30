package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.chalmers.projectrolf.model.Ability;
import org.chalmers.projectrolf.view.AbilityView;

import java.util.ArrayList;
import java.util.List;

public class AbilityController {

    private List<Ability> abilityList;
    private AbilityView abilityView;
    private Box2D box2D;
    private final float PIXELS_TO_METERS;

    public AbilityController(Box2D box2D) {
        this.box2D = box2D;
        this.PIXELS_TO_METERS = box2D.getPixelsToMeters();
        abilityList = new ArrayList<Ability>();
        abilityView = new AbilityView();
    }

    public void createObject(int x, int y, int mapHeight) {
        abilityList.add(new Ability(box2D.newDynamic(x, y, mapHeight)));
    }

    public void update(SpriteBatch batch) {
        render(batch);
    }

    public void render(SpriteBatch batch) {
        for(Ability a: abilityList) {
            abilityView.render(a.getPosition().x * PIXELS_TO_METERS, a.getPosition().y * PIXELS_TO_METERS, batch);
        }
    }

    public void dispose() {
        abilityView.dispose();
    }
}
