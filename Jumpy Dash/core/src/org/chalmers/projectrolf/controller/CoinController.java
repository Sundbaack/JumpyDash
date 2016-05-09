package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.projectrolf.model.Coin;
import org.chalmers.projectrolf.view.CoinView;

import java.util.ArrayList;
import java.util.List;

public class CoinController extends Actor {

    private CoinView coinView;
    private Box2D box2D;
    private Coin coin;


    public CoinController(Box2D box2D,int x, int y, int mapHeight) {
        this.box2D = box2D;
        coinView = new CoinView();
        coin = new Coin(box2D.newStatic(x,y,mapHeight,false),1);
    }

    public void act(float Delta){

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        coinView.render(batch, coin.getPosition().x*box2D.getPixelsToMeters(),coin.getPosition().y * box2D.getPixelsToMeters());

    }




    public void dispose() {
        coinView.dispose();
    }
}
