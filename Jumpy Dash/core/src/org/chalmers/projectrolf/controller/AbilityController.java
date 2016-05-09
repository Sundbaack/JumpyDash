package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.projectrolf.model.Ability;
import org.chalmers.projectrolf.view.AbilityView;

public class AbilityController extends Actor{


    private AbilityView abilityView;
    private Box2D box2D;
    private Ability ability;

    public AbilityController(Box2D box2D,int x, int y, int mapheight) {
        this.box2D = box2D;
        ability = new Ability(box2D.newStatic(x,y,mapheight,false));
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
