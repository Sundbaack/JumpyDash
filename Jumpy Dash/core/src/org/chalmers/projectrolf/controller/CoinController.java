package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.chalmers.projectrolf.model.Coin;
import org.chalmers.projectrolf.view.CoinView;

import java.util.ArrayList;
import java.util.List;

public class CoinController extends ApplicationAdapter {

    private BodyDef coinBodyDef;
    private Body coinBody;

    private List<Coin> coinList;
    private CoinView coinView;
    private final float tileWidthHeight;

    public CoinController(float tileWidthHeight) {

        this.tileWidthHeight = tileWidthHeight;
        coinList = new ArrayList<Coin>();
        coinView = new CoinView();
    }

    public void createObject(int x, int y, int mapHeight) {

        // Coin body for Box2D
        coinBodyDef = new BodyDef();
        coinBodyDef.type = BodyDef.BodyType.StaticBody;
        coinBodyDef.position.set(x * tileWidthHeight / JumpyDash.PIXELS_TO_METERS, (mapHeight - 1 - y) * 32 / JumpyDash.PIXELS_TO_METERS);
        coinBody = JumpyDash.world.createBody(coinBodyDef);

        Coin coin = new Coin(coinBody, 20, tileWidthHeight / JumpyDash.PIXELS_TO_METERS);
        coinList.add(coin);
    }

    public void update(SpriteBatch batch) {
       render(batch);
    }

    public void render(SpriteBatch batch) {
        for (Coin c : coinList) {
            coinView.render(c.getPosition().x * JumpyDash.PIXELS_TO_METERS, c.getPosition().y * JumpyDash.PIXELS_TO_METERS, batch);
        }
    }

    public void dispose() {
        coinView.dispose();
    }

}
