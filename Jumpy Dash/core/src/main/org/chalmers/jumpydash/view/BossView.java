package org.chalmers.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class BossView implements JDView {

    private Texture bossTile;

    public BossView(){
        bossTile = new Texture(Gdx.files.internal("images/boss.png"));
    }

    public void render(Batch batch, float x, float y) {
        batch.draw(bossTile, x, y);
    }

    public void dispose() {
        bossTile.dispose();
    }
}
