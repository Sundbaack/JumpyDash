package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import org.chalmers.jumpydash.model.Sensor;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.JDView;
import org.chalmers.jumpydash.view.SensorView;
import org.chalmers.jumpydash.physics.Box2D;

public class SensorController extends JDController {

    private JDView sensorView;
    private Sensor sensor;
    private String type;

    public SensorController(IBox2D box2D, int x, int y, int mapHeight, String type) {
        sensor  = new Sensor(type);
        sensor.setJDBody(box2D.newBody(x, y, mapHeight, "static", false,true));
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
