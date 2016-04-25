package org.chalmers.projectrolf.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.chalmers.projectrolf.controller.GameController;
import org.chalmers.projectrolf.model.Coin;

import java.util.List;

/**
 * Created by Johannes on 2016-04-25.
 */
public class CoinView {
    private List<Coin> coinList;
    private Texture coinTile;

    public CoinView(List<Coin> coinList) {
        this.coinList = coinList;
        coinTile = new Texture(Gdx.files.internal("coin.png"));
    }

    public void render(SpriteBatch batch) {
        for(Coin c: coinList){
            batch.draw(coinTile, c.getPosition().x * GameController.PIXELS_TO_METERS, c.getPosition().y * GameController.PIXELS_TO_METERS);
        }

    }

    public void dispose() {
        coinTile.dispose();
    }
}

