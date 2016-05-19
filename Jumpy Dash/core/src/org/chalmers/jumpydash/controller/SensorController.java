package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.jumpydash.model.Sensor;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.view.SensorView;
import static org.chalmers.jumpydash.physics.Box2D.PIXELS_TO_METERS;

public class SensorController extends Actor {

    private SensorView sensorView;
    private IBox2D box2D;
    private Sensor sensor;

    public SensorController(IBox2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        sensor  = new Sensor(box2D.newBody(x, y, mapHeight, "static", false,true));
        sensorView = new SensorView();
    }

    public void act(float Delta){

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sensorView.render(batch, sensor.getPosition().x * PIXELS_TO_METERS, sensor.getPosition().y * PIXELS_TO_METERS);

    }

    public void dispose() {
        sensorView.dispose();
    }
}
