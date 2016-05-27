package main.java.org.chalmers.jumpydash.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class CannonView implements JDView {
    private Texture cannonTile;

    public CannonView() {
        cannonTile = new Texture(Gdx.files.internal("images/cannon.png"));
    }

    public void render(Batch batch, float x, float y){
        batch.draw(cannonTile, x, y);
    }

    public void dispose(){
        cannonTile.dispose();
    }
}
