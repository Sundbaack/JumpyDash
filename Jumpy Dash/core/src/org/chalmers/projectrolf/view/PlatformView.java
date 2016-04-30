package org.chalmers.projectrolf.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlatformView {

    private Texture platformTile;

    public PlatformView(){
        platformTile = new Texture(Gdx.files.internal("platform.png"));
    }

    public void render(float x, float y, SpriteBatch batch){
        batch.draw(platformTile, x, y);
    }

    public void dispose(){
        platformTile.dispose();
    }


}
