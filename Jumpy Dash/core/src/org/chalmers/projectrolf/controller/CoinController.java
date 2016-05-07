package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.chalmers.projectrolf.model.Coin;
import org.chalmers.projectrolf.view.CoinView;

import java.util.ArrayList;
import java.util.List;

public class CoinController extends ApplicationAdapter {

    private List<Coin> coinList;
    private CoinView coinView;
    private final float PIXELS_TO_METERS;
    private Box2D box2D;

    public CoinController(Box2D box2D) {
        this.box2D = box2D;
        this.PIXELS_TO_METERS = box2D.getPixelsToMeters();
        coinList = new ArrayList<Coin>();
        coinView = new CoinView();
    }

    public void createObject(int x, int y, int mapHeight) {
        coinList.add(new Coin(box2D.newStatic(x, y, mapHeight, false), 20));
    }

    public void update(SpriteBatch batch) {
       render(batch);
    }

    public void render(SpriteBatch batch) {
        for (Coin c : coinList) {
            coinView.render(c.getPosition().x * PIXELS_TO_METERS, c.getPosition().y * PIXELS_TO_METERS, batch);
        }
    }

    public void dispose() {
        coinView.dispose();
    }
}
