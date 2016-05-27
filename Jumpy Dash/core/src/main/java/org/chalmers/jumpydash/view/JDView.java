package main.java.org.chalmers.jumpydash.jumpydash.view;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface JDView {

    void render(Batch batch, float x, float y);

    void dispose();
}
