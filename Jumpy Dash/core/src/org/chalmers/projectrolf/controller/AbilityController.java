package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.projectrolf.model.Ability;
import org.chalmers.projectrolf.physics.Box2D;
import org.chalmers.projectrolf.physics.JDBody;
import org.chalmers.projectrolf.view.AbilityView;

public class AbilityController extends Actor{


    private AbilityView abilityView;
    private static JDBody body;
    private Box2D box2D;
    private Ability ability;

    public AbilityController(Box2D box2D,int x, int y, int mapHeight) {
        this.box2D = box2D;
        ability = new Ability();
        abilityView = new AbilityView();
        body = box2D.newDynamic(x, y, mapHeight);
        body.setUserData(ability);
    }
    public void act(float Delta){

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        abilityView.render(batch, getPosition().x * box2D.getPixelsToMeters(), getPosition().y * box2D.getPixelsToMeters());

    }

    public JDBody getJDBody() {
        return this.body;
    }

    public static Vector2 getPosition() {
        return body.getPosition();
    }



    public void dispose() {
        abilityView.dispose();
    }
}
