package org.chalmers.projectrolf.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.chalmers.projectrolf.controller.GameController;
import org.chalmers.projectrolf.model.Ability;

import java.util.List;

/**
 * Created by alexsundback on 2016-04-29.
 */
public class AbilityView {

    private List<Ability> abilityList;
    private Texture abilityTile;

    public AbilityView(List<Ability> abilityList) {
        this.abilityList = abilityList;
        abilityTile = new Texture(Gdx.files.internal("ability.png"));
    }

    public void render(SpriteBatch batch) {
        for(Ability a: abilityList){
            batch.draw(abilityTile, a.getPosition().x * GameController.PIXELS_TO_METERS, a.getPosition().y * GameController.PIXELS_TO_METERS);
        }
    }

    public void dispose() {
        abilityTile.dispose();
    }

}
