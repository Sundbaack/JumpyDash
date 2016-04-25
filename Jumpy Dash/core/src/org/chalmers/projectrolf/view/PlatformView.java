package org.chalmers.projectrolf.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.chalmers.projectrolf.controller.GameController;
import org.chalmers.projectrolf.model.Platform;

import java.util.List;

public class PlatformView {

    private List<Platform> platformList;
    private Texture platformTile;

    public PlatformView(List<Platform> platformList){
        this.platformList = platformList;
        platformTile = new Texture(Gdx.files.internal("platform.png"));
    }

    public void render(SpriteBatch batch){
        for(Platform p : platformList) {
            batch.draw(platformTile, p.getPosition().x * GameController.PIXELS_TO_METERS, p.getPosition().y * GameController.PIXELS_TO_METERS);
        }
    }

    public void dispose(){
        platformTile.dispose();
    }


}
