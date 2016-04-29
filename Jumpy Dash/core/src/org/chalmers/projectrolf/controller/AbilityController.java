package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.chalmers.projectrolf.model.Ability;
import org.chalmers.projectrolf.model.Coin;
import org.chalmers.projectrolf.view.AbilityView;
import org.chalmers.projectrolf.view.CoinView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexsundback on 2016-04-28.
 */
public class AbilityController {

    private BodyDef abilityBodyDef;
    private Body abilityBody;
    private List<Ability> abilityList;
    private AbilityView abilityView;

    public AbilityController() {
        abilityList = new ArrayList<Ability>();
    }

    public void createObject(int x, int y, float tileWidthHeight, int mapHeight) {
        abilityBodyDef = new BodyDef();
        abilityBodyDef.type = BodyDef.BodyType.DynamicBody;
        abilityBodyDef.position.set(x * tileWidthHeight / GameController.PIXELS_TO_METERS, (mapHeight - 1 - y) * 32 / GameController.PIXELS_TO_METERS);
        abilityBody = GameController.world.createBody(abilityBodyDef);

        Ability ability = new Ability(abilityBody, tileWidthHeight / GameController.PIXELS_TO_METERS);
        abilityList.add(ability);
        abilityView = new AbilityView(abilityList);
    }

    public AbilityView getAbilityView() {
        return this.abilityView;
    }

}
