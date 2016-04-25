package org.chalmers.projectrolf.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.chalmers.projectrolf.controller.GameController;
import org.chalmers.projectrolf.model.Enemy;

/**
 * Created by Marcus on 2016-04-21.
 */
public class EnemyView {
    private Enemy enemy;
    private Texture soldierTile;

    public EnemyView(Enemy enemy){
        this.enemy = enemy;
        soldierTile = new Texture(Gdx.files.internal("soldier.png"));
    }

    public void render(SpriteBatch batch){
        batch.draw(soldierTile,enemy.getPosition().x * GameController.PIXELS_TO_METERS,enemy.getPosition().y * GameController.PIXELS_TO_METERS);
    }
    public void dispose(){
        soldierTile.dispose();
    }



}
