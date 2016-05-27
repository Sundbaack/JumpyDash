package main.java.org.chalmers.jumpydash.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class SpikeView implements JDView {

    private Texture spikeTile;

    public SpikeView() {
        spikeTile = new Texture(Gdx.files.internal("images/spike.png"));
    }

    public void render(Batch batch, float x, float y) {
        batch.draw(spikeTile, x, y);
    }

    public void dispose() {
        spikeTile.dispose();
    }
}
