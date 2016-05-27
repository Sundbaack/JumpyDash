package main.java.org.chalmers.jumpydash.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class HeartView implements JDView {

    private Texture heartTile;

    public HeartView() {
        heartTile = new Texture(Gdx.files.internal("images/heart.png"));
    }

    public void render(Batch batch,float x, float y) {
        batch.draw(heartTile, x, y);
    }

    public void dispose() {
        heartTile.dispose();
    }
}
