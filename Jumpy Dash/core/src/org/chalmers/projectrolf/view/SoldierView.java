package org.chalmers.projectrolf.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.chalmers.projectrolf.controller.GameController;
import org.chalmers.projectrolf.model.Platform;
import org.chalmers.projectrolf.model.Soldier;

import java.util.List;

/**
 * Created by Johannes on 2016-04-29.
 */
public class SoldierView {
    private List<Soldier> soldierList;
    private Texture soldierTile;

    public SoldierView(List<Soldier> soldierList){
        this.soldierList = soldierList;
        soldierTile = new Texture(Gdx.files.internal("soldier.png"));
    }

    public void render(SpriteBatch batch){
        for(Soldier p : soldierList) {
            batch.draw(soldierTile, p.getPosition().x * GameController.PIXELS_TO_METERS, p.getPosition().y * GameController.PIXELS_TO_METERS);
        }
    }

    public void dispose(){
        soldierTile.dispose();
    }
}
