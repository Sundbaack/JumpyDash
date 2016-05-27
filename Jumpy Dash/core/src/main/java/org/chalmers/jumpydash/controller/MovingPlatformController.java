package main.java.org.chalmers.jumpydash.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import main.java.org.chalmers.jumpydash.jumpydash.physics.IBox2D;
import main.java.org.chalmers.jumpydash.jumpydash.model.MovingPlatform;
import main.java.org.chalmers.jumpydash.jumpydash.physics.BodyType;
import main.java.org.chalmers.jumpydash.jumpydash.view.JDView;
import main.java.org.chalmers.jumpydash.jumpydash.view.MovingPlatformView;
import static main.java.org.chalmers.jumpydash.jumpydash.physics.Box2D.PIXELS_TO_METERS;

public class MovingPlatformController extends JDController {

    private JDView movingPlatformView;
    private MovingPlatform movingPlatform;

    public MovingPlatformController(IBox2D box2D, int x, int y, int mapHeight) {
        movingPlatformView = new MovingPlatformView();
        movingPlatform = new MovingPlatform();

        movingPlatform.setJDBody(box2D.newBody(x, y, mapHeight, BodyType.KINEMATIC, false,false));
        movingPlatform.getJDBody().setUserData(movingPlatform);
    }

    @Override
    public void act(float delta) {
        if (MovingPlatform.movePlatforms) {
            movingPlatform.moveUp();
        }
        if (!movingPlatform.getJDBody().isActive()) {
            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        movingPlatformView.render(batch, movingPlatform.getPosition().x * PIXELS_TO_METERS, movingPlatform.getPosition().y * PIXELS_TO_METERS);
    }

    @Override
    public void dispose() {
        movingPlatformView.dispose();
    }
}
