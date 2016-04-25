package org.chalmers.projectrolf.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.chalmers.projectrolf.controller.GameController;
import org.chalmers.projectrolf.model.Platform;

public class PlatformView {

    private Platform platform;
    private Texture platformTile;

    public PlatformView(Platform platform){
        this.platform = platform;
        platformTile = new Texture(Gdx.files.internal("platform.png"));
    }

    public void render(SpriteBatch batch){
        batch.draw(platformTile,platform.getPosition().x * GameController.PIXELS_TO_METERS,platform.getPosition().y*GameController.PIXELS_TO_METERS);
    }

    public void dispose(){
        platformTile.dispose();
    }


}
