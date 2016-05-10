package org.chalmers.projectrolf.controller;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.projectrolf.model.Coin;
import org.chalmers.projectrolf.physics.Box2D;
import org.chalmers.projectrolf.physics.JDBody;
import org.chalmers.projectrolf.view.CoinView;

public class CoinController extends Actor {

    private CoinView coinView;
    private static JDBody body;
    private Box2D box2D;
    private Coin coin;


    public CoinController(Box2D box2D,int x, int y, int mapHeight) {
        this.box2D = box2D;
        coinView = new CoinView();
        coin = new Coin(1);
        body = box2D.newStatic(x, y, mapHeight, false);
        body.setUserData(coin);
    }

    public void act(float Delta){

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        coinView.render(batch, getPosition().x*box2D.getPixelsToMeters(), getPosition().y * box2D.getPixelsToMeters());

    }

    public JDBody getJDBody() {
        return this.body;
    }

    public static Vector2 getPosition() {
        return body.getPosition();
    }




    public void dispose() {
        coinView.dispose();
    }
}
