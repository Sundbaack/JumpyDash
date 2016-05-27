package main.java.org.chalmers.jumpydash.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import main.java.org.chalmers.jumpydash.jumpydash.model.Heart;
import main.java.org.chalmers.jumpydash.jumpydash.physics.BodyType;
import main.java.org.chalmers.jumpydash.jumpydash.physics.Box2D;
import main.java.org.chalmers.jumpydash.jumpydash.physics.IBox2D;
import main.java.org.chalmers.jumpydash.jumpydash.view.HeartView;
import main.java.org.chalmers.jumpydash.jumpydash.view.JDView;

public class HeartController extends JDController {

    private JDView heartView;
    private Heart heart;

    public HeartController(IBox2D box2D, int x, int y, int mapHeight) {
        heartView = new HeartView();
        heart = new Heart();
        heart.setJDBody(box2D.newBody(x, y, mapHeight, BodyType.STATIC, false, true));
        heart.getJDBody().setUserData(heart);
    }

    @Override
    public void act(float Delta){
        if (!heart.getJDBody().isActive()) {
            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        heartView.render(batch, heart.getPosition().x * Box2D.PIXELS_TO_METERS, heart.getPosition().y * Box2D.PIXELS_TO_METERS);
    }

    @Override
    public void dispose() {
        heartView.dispose();
    }
    
}
