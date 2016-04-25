package org.chalmers.projectrolf.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.chalmers.projectrolf.controller.GameController;
import org.chalmers.projectrolf.model.Soldier;

import java.util.List;

public class EnemyView {

    private Texture soldierTile;
    private List<Soldier> soldierList;

    public EnemyView(List<Soldier> soldierList){
        this.soldierList = soldierList;
        soldierTile = new Texture(Gdx.files.internal("soldier.png"));
    }

    public void render(SpriteBatch batch) {
        for (Soldier s : soldierList) {
            batch.draw(soldierTile, s.getPosition().x * GameController.PIXELS_TO_METERS, s.getPosition().y * GameController.PIXELS_TO_METERS);
        }
    }
    public void dispose(){
        soldierTile.dispose();
    }



}
