package main.java.org.chalmers.jumpydash.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class LockedDoorView implements JDView {

    private Texture lockedDoorTile;

    public LockedDoorView() {
        lockedDoorTile = new Texture(Gdx.files.internal("images/lockedDoor.png"));
    }

    public void render(Batch batch,float x, float y) {
        batch.draw(lockedDoorTile, x, y);
    }

    public void dispose() {
        lockedDoorTile.dispose();
    }

}
