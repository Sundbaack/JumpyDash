package org.chalmers.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by Johannes on 2016-05-16.
 */
public class CannonView {
        private Texture cannonTile;

        public CannonView() {
            cannonTile = new Texture(Gdx.files.internal("soldier.png"));
        }

        public void render(Batch batch, float x, float y){
            batch.draw(cannonTile, x, y);
        }

        public void dispose(){
            cannonTile.dispose();
        }


}
