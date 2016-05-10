package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.projectrolf.model.Coin;
import org.chalmers.projectrolf.physics.IBox2D;
import org.chalmers.projectrolf.physics.IJDBody;
import org.chalmers.projectrolf.view.CoinView;

public class CoinController extends Actor {

    private CoinView coinView;
    private IBox2D box2D;
    private IJDBody jdBody;
    private Coin coin;

    public CoinController(IBox2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        coinView = new CoinView();
        coin = new Coin(1);
        jdBody = box2D.newStatic(x, y, mapHeight, false);
        jdBody.setUserData(coin);
    }

    public void act(float Delta){

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        coinView.render(batch, getPosition().x * box2D.getPixelsToMeters(), getPosition().y * box2D.getPixelsToMeters());

    }

    public Vector2 getPosition() {
        return jdBody.getPosition();
    }

    public void dispose() {
        coinView.dispose();
    }
}
