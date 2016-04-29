package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.chalmers.projectrolf.model.Coin;
import org.chalmers.projectrolf.view.CoinView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexsundback on 2016-04-28.
 */
public class CoinController extends ApplicationAdapter {

    private BodyDef coinBodyDef;
    private Body coinBody;
    private List<Coin> coinList;
    private CoinView coinView;

    public CoinController() {
        coinList = new ArrayList<Coin>();
    }

    public void createObject(int x, int y, float tileWidthHeight, int mapHeight) {
        // Coin body for Box2D
        coinBodyDef = new BodyDef();
        coinBodyDef.type = BodyDef.BodyType.StaticBody;
        coinBodyDef.position.set(x * tileWidthHeight / GameController.PIXELS_TO_METERS, (mapHeight - 1 - y) * 32 / GameController.PIXELS_TO_METERS);
        coinBody = GameController.world.createBody(coinBodyDef);

        Coin coin = new Coin(coinBody, 20, tileWidthHeight / GameController.PIXELS_TO_METERS);
        coinList.add(coin);
        coinView = new CoinView(coinList);
    }

    public CoinView getCoinView() {
        return this.coinView;
    }

}
