package org.chalmers.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class SoldierView implements JDView {

    private TextureAtlas soldierAtlas;
    private Animation animation;
    private float elapsedTime = 0f;

    public SoldierView() {
        soldierAtlas = new TextureAtlas(Gdx.files.internal("images/spriteSheets/soldierAnimation.atlas"));
        animation = new Animation(1f / 20f, soldierAtlas.getRegions());
    }

    public void render(Batch batch,float x, float y){
        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(elapsedTime,true), x, y);
    }

    public void dispose(){
        soldierAtlas.dispose();
    }
}
