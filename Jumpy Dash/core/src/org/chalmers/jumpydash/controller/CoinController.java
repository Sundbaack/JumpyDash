package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.jumpydash.model.Coin;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.CoinView;

public class CoinController extends Actor {

    private CoinView coinView;
    private IBox2D box2D;
    private Coin coin;

    public CoinController(IBox2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        coinView = new CoinView();
        coin = new Coin(1, box2D.newStatic(x, y, mapHeight, false));
    }

    public void act(float Delta){

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        coinView.render(batch, coin.getPosition().x * box2D.getPixelsToMeters(), coin.getPosition().y * box2D.getPixelsToMeters());

    }



    public void dispose() {
        coinView.dispose();
    }
}
