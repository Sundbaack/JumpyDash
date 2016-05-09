package org.chalmers.projectrolf.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SoldierView {
    private Texture soldierTile;

    public SoldierView() {
        soldierTile = new Texture(Gdx.files.internal("soldier.png"));
    }

    public void render(Batch batch,float x, float y){
        batch.draw(soldierTile, x, y);
    }

    public void dispose(){
        soldierTile.dispose();
    }
}
