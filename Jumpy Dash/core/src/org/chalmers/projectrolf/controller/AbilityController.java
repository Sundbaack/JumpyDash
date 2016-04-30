package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.chalmers.projectrolf.model.Ability;
import org.chalmers.projectrolf.view.AbilityView;

import java.util.ArrayList;
import java.util.List;

public class AbilityController {

    private BodyDef abilityBodyDef;
    private Body abilityBody;
    private List<Ability> abilityList;
    private AbilityView abilityView;
    private final float tileWidthHeight;
    private final float PIXELS_TO_METERS;

    public AbilityController(float tileWidthHeight, float PIXELS_TO_METERS) {

        this.tileWidthHeight = tileWidthHeight;
        this.PIXELS_TO_METERS = PIXELS_TO_METERS;
        abilityList = new ArrayList<Ability>();
        abilityView = new AbilityView();
    }

    public void createObject(int x, int y, int mapHeight) {
        abilityBodyDef = new BodyDef();
        abilityBodyDef.type = BodyDef.BodyType.DynamicBody;
        abilityBodyDef.position.set(x * tileWidthHeight / PIXELS_TO_METERS, (mapHeight - 1 - y) * 32 / PIXELS_TO_METERS);
        abilityBody = JumpyDash.world.createBody(abilityBodyDef);

        Ability ability = new Ability(abilityBody, tileWidthHeight / PIXELS_TO_METERS);
        abilityList.add(ability);
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
