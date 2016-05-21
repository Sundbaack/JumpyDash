package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import org.chalmers.jumpydash.model.Coin;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.CoinView;
import org.chalmers.jumpydash.view.JDView;

import static org.chalmers.jumpydash.physics.Box2D.PIXELS_TO_METERS;

public class CoinController extends JDController {

    private JDView coinView;
    private Coin coin;
    private Sound sound;

    public CoinController(IBox2D box2D, int x, int y, int mapHeight) {
        coinView = new CoinView();
        coin = new Coin(1);
        coin.setJDBody(box2D.newBody(x, y, mapHeight, "static", false, true));
        coin.getJDBody().setUserData(coin);
    }

    @Override
    public void act(float Delta){
        if (!coin.getJDBody().isActive()) {
            sound = Gdx.audio.newSound(Gdx.files.internal("sounds/coin.wav"));
            sound.play(1);

            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        coinView.render(batch, coin.getPosition().x * PIXELS_TO_METERS, coin.getPosition().y * PIXELS_TO_METERS);
    }

    @Override
    public void dispose() {
        coinView.dispose();
        sound.dispose();
    }
}
