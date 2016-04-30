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

    public AbilityController(float tileWidthHeight) {

        this.tileWidthHeight = tileWidthHeight;
        abilityList = new ArrayList<Ability>();
        abilityView = new AbilityView();
    }

    public void createObject(int x, int y, int mapHeight) {
        abilityBodyDef = new BodyDef();
        abilityBodyDef.type = BodyDef.BodyType.DynamicBody;
        abilityBodyDef.position.set(x * tileWidthHeight / JumpyDash.PIXELS_TO_METERS, (mapHeight - 1 - y) * 32 / JumpyDash.PIXELS_TO_METERS);
        abilityBody = JumpyDash.world.createBody(abilityBodyDef);

        Ability ability = new Ability(abilityBody, tileWidthHeight / JumpyDash.PIXELS_TO_METERS);
        abilityList.add(ability);
    }

    public void update(SpriteBatch batch) {
        render(batch);
    }

    public void render(SpriteBatch batch) {
        for(Ability a: abilityList) {
            abilityView.render(a.getPosition().x * JumpyDash.PIXELS_TO_METERS, a.getPosition().y * JumpyDash.PIXELS_TO_METERS, batch);
        }
    }

    public void dispose() {
        abilityView.dispose();
    }
}
