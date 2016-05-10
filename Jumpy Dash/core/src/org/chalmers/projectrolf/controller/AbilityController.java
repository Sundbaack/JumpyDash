package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.projectrolf.model.Ability;
import org.chalmers.projectrolf.physics.IBox2D;
import org.chalmers.projectrolf.physics.IJDBody;
import org.chalmers.projectrolf.view.AbilityView;

public class AbilityController extends Actor{

    private AbilityView abilityView;
    private IBox2D box2D;
    private IJDBody jdBody;
    private Ability ability;

    public AbilityController(IBox2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        ability = new Ability();
        abilityView = new AbilityView();
        jdBody = box2D.newDynamic(x, y, mapHeight);
        jdBody.setUserData(ability);
    }

    public void act(float Delta){

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        abilityView.render(batch, getPosition().x * box2D.getPixelsToMeters(), getPosition().y * box2D.getPixelsToMeters());

    }

    public Vector2 getPosition() {
        return jdBody.getPosition();
    }

    public void dispose() {
        abilityView.dispose();
    }
}
