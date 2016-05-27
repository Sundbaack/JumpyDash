package main.java.org.chalmers.jumpydash.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class MovingPlatformView implements JDView {

    private Texture platformTile;

    public MovingPlatformView(){
        platformTile = new Texture(Gdx.files.internal("images/movingPlatform.png"));
    }

    public void render(Batch batch,float x, float y){
        batch.draw(platformTile, x, y);
    }

    public void dispose(){
        platformTile.dispose();
    }
}
