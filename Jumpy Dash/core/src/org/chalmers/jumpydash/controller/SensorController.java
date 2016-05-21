package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.jumpydash.model.Sensor;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.IView;
import org.chalmers.jumpydash.view.SensorView;
import static org.chalmers.jumpydash.physics.Box2D.PIXELS_TO_METERS;

public class SensorController extends Actor {

    private IView sensorView;
    private Sensor sensor;

    public SensorController(IBox2D box2D, int x, int y, int mapHeight) {
        sensor  = new Sensor();
        sensor.setJDBody(box2D.newBody(x, y, mapHeight, "static", false,true));
        sensor.getJDBody().setUserData(sensor);
        sensorView = new SensorView();
    }

    @Override
    public void act(float Delta){
        if (!sensor.getJDBody().isActive()) {
            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sensorView.render(batch, sensor.getPosition().x * PIXELS_TO_METERS, sensor.getPosition().y * PIXELS_TO_METERS);

    }

    public void dispose() {
        sensorView.dispose();
    }
}
