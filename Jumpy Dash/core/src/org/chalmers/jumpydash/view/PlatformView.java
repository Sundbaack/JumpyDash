package org.chalmers.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class PlatformView {

    private Texture platformTile;

    public PlatformView(){
        platformTile = new Texture(Gdx.files.internal("platform.png"));
    }

    public void render(Batch batch,float x, float y){
        batch.draw(platformTile, x, y);
    }

    public void dispose(){
        platformTile.dispose();
    }
}
