package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.chalmers.projectrolf.model.Coin;
import org.chalmers.projectrolf.view.CoinView;

import java.util.ArrayList;
import java.util.List;

public class CoinController extends ApplicationAdapter {

    private List<Coin> coins;
    private CoinView coinView;
    private Box2D box2D;

    public CoinController(Box2D box2D) {
        this.box2D = box2D;
        coins = new ArrayList<Coin>();
        coinView = new CoinView();
    }

    public void createObject(int x, int y, int mapHeight) {
        coins.add(new Coin(box2D.newStatic(x, y, mapHeight, false), 20));
    }

    public void update(SpriteBatch batch) {
       render(batch);
    }

    public void render(SpriteBatch batch) {
        for (Coin c : coins) {
            coinView.render(c.getPosition().x * box2D.getPixelsToMeters(), c.getPosition().y * box2D.getPixelsToMeters(), batch);
        }
    }

    public void dispose() {
        coinView.dispose();
    }
}
