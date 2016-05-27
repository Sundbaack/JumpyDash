package main.java.org.chalmers.jumpydash.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import main.java.org.chalmers.jumpydash.jumpydash.model.Cannon;
import main.java.org.chalmers.jumpydash.jumpydash.physics.BodyType;
import main.java.org.chalmers.jumpydash.jumpydash.physics.IBox2D;
import main.java.org.chalmers.jumpydash.jumpydash.view.CannonView;
import main.java.org.chalmers.jumpydash.jumpydash.view.JDView;
import javax.vecmath.Vector2f;
import static main.java.org.chalmers.jumpydash.jumpydash.physics.Box2D.PIXELS_TO_METERS;

public class CannonController extends JDController {

    private Cannon cannon;
    private JDView cannonView;
    private IBox2D box2D;

    public CannonController(IBox2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        cannon = new Cannon();
        cannon.setJDBody(box2D.newBody(x, y, mapHeight, BodyType.STATIC, false,false));
        cannon.getJDBody().setUserData(cannon);
        cannonView = new CannonView();
    }

    @Override
    public void act(float Delta){
        if (cannon.getJDBody().isAwake()) {
            fireProjectile();
        }
        if (!cannon.getJDBody().isActive()) {
            this.remove();
        }
    }

    public void fireProjectile(){
        if(cannon.allowedToFire()){
            EnemyProjectileController enemyProjectileController = new EnemyProjectileController(box2D, cannon.getPosition().x, cannon.getPosition().y, new Vector2f(-2f, 0),1);
            getStage().addActor(enemyProjectileController);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        cannonView.render(batch, cannon.getPosition().x * PIXELS_TO_METERS, cannon.getPosition().y * PIXELS_TO_METERS);

    }

    @Override
    public void dispose() {
        cannonView.dispose();
    }
}