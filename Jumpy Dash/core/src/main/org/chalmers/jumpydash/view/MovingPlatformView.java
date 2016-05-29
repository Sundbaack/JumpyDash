package org.chalmers.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class MovingPlatformView implements JDView {

    private Texture movingPlatformTile;

    public MovingPlatformView(){
        movingPlatformTile = new Texture(Gdx.files.internal("images/movingPlatform.png"));
    }

    public void render(Batch batch,float x, float y){
        batch.draw(movingPlatformTile, x, y);
    }

    public void dispose(){
        movingPlatformTile.dispose();
    }
}
