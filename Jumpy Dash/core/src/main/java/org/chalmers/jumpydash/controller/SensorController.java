package main.java.org.chalmers.jumpydash.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import main.java.org.chalmers.jumpydash.jumpydash.model.Sensor;
import main.java.org.chalmers.jumpydash.jumpydash.physics.BodyType;
import main.java.org.chalmers.jumpydash.jumpydash.physics.IBox2D;
import main.java.org.chalmers.jumpydash.jumpydash.view.JDView;
import main.java.org.chalmers.jumpydash.jumpydash.view.SensorView;
import main.java.org.chalmers.jumpydash.jumpydash.physics.Box2D;

public class SensorController extends JDController {

    private JDView sensorView;
    private Sensor sensor;
    private String type;

    public SensorController(IBox2D box2D, int x, int y, int mapHeight, String type) {
        sensor  = new Sensor(type);
        sensor.setJDBody(box2D.newBody(x, y, mapHeight, BodyType.STATIC, false,true));
        sensor.getJDBody().setUserData(sensor);
        this.type = type;
        sensorView = new SensorView(type);
        }


    @Override
    public void act(float Delta){
        if (!sensor.getJDBody().isActive()) {
            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sensorView.render(batch, sensor.getPosition().x * Box2D.PIXELS_TO_METERS, sensor.getPosition().y * Box2D.PIXELS_TO_METERS);

    }

    @Override
    public void dispose() {
        sensorView.dispose();
    }
}
