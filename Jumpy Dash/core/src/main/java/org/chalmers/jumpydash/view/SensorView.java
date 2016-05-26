package main.java.org.chalmers.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class SensorView implements JDView {

    private Texture sensorTile;

    public SensorView(String type) {
        if(type.equalsIgnoreCase("player")){
            sensorTile = new Texture(Gdx.files.internal("images/switchOff.png"));
        }
        else if(type.equalsIgnoreCase("soldier")){
            sensorTile = new Texture(Gdx.files.internal("images/transparentTexture.png"));
        }

    }

    public void render(Batch batch,float x, float y) {
        batch.draw(sensorTile, x, y);
    }

    public void dispose() {
        sensorTile.dispose();
    }

}
