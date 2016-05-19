package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.jumpydash.model.Coin;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.CoinView;
import static org.chalmers.jumpydash.physics.Box2D.PIXELS_TO_METERS;

public class CoinController extends Actor {

    private CoinView coinView;
    private IBox2D box2D;
    private Coin coin;
    private Sound sound;

    public CoinController(IBox2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        coinView = new CoinView();
        coin = new Coin(box2D.newBody(x, y, mapHeight, "static", false,true), 1);
    }

    @Override
    public void act(float Delta){
        if (!coin.getJdBody().isActive()) {
            sound = Gdx.audio.newSound(Gdx.files.internal("sounds/coin.wav"));
            sound.play(1);

            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        coinView.render(batch, coin.getPosition().x * PIXELS_TO_METERS, coin.getPosition().y * PIXELS_TO_METERS);
    }

    public void dispose() {
        coinView.dispose();
        sound.dispose();
    }
}
