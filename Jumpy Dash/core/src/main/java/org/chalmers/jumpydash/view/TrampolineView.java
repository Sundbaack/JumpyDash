package main.java.org.chalmers.jumpydash.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class TrampolineView implements JDView {

    private Texture trampolineTile;

    public TrampolineView(){
        trampolineTile = new Texture(Gdx.files.internal("images/trampoline.png"));
    }

    public void render(Batch batch, float x, float y) {
        batch.draw(trampolineTile, x, y);
    }

    public void dispose() {
        trampolineTile.dispose();
    }
}
