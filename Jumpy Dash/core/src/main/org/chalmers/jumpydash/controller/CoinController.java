package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import org.chalmers.jumpydash.model.Coin;
import org.chalmers.jumpydash.physics.BodyType;
import org.chalmers.jumpydash.view.CoinView;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.util.Options;
import org.chalmers.jumpydash.view.JDView;
import org.chalmers.jumpydash.physics.Box2D;

public class CoinController extends JDController {

    private JDView coinView;
    private Coin coin;
    private Sound coinSound;

    public CoinController(IBox2D box2D, int x, int y, int mapHeight) {
        coinView = new CoinView();
        coin = new Coin(1);
        coin.setJDBody(box2D.newBody(x, y, mapHeight, BodyType.STATIC, false, true));
        coin.getJDBody().setUserData(coin);
        coinSound = Gdx.audio.newSound(Gdx.files.internal("sounds/coin.wav"));
    }

    @Override
    public void act(float Delta){
        if (!coin.getJDBody().isActive()) {
            if (Options.getInstance().getSound()) {
                coinSound.play(1);
            }
            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        coinView.render(batch, coin.getPosition().x * Box2D.PIXELS_TO_METERS, coin.getPosition().y * Box2D.PIXELS_TO_METERS);
    }

    @Override
    public void dispose() {
        coinView.dispose();
        coinSound.dispose();
    }
}
