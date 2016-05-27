package main.java.org.chalmers.jumpydash.jumpydash.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import main.java.org.chalmers.jumpydash.jumpydash.model.LockedDoor;
import main.java.org.chalmers.jumpydash.jumpydash.physics.IBox2D;
import main.java.org.chalmers.jumpydash.jumpydash.util.Options;
import main.java.org.chalmers.jumpydash.jumpydash.physics.BodyType;
import main.java.org.chalmers.jumpydash.jumpydash.view.JDView;
import main.java.org.chalmers.jumpydash.jumpydash.physics.Box2D;
import main.java.org.chalmers.jumpydash.jumpydash.view.LockedDoorView;

public class LockedDoorController extends JDController {

    private JDView lockedDoorView;
    private LockedDoor lockedDoor;
    private Sound lockedDoorSound;

    public LockedDoorController(IBox2D box2D, int x, int y, int mapHeight) {
        lockedDoorView = new LockedDoorView();
        lockedDoor = new LockedDoor();
        lockedDoor.setJDBody(box2D.newBody(x, y, mapHeight, BodyType.STATIC, true, false));
        lockedDoor.getJDBody().setUserData(lockedDoor);
        lockedDoorSound = Gdx.audio.newSound(Gdx.files.internal("sounds/key.wav"));
    }

    @Override
    public void act(float Delta){
        if (!lockedDoor.getJDBody().isActive()) {
            if (Options.getInstance().getSound()) {
                lockedDoorSound.play(1);
            }
            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        lockedDoorView.render(batch, lockedDoor.getPosition().x * Box2D.PIXELS_TO_METERS, lockedDoor.getPosition().y * Box2D.PIXELS_TO_METERS);
    }

    @Override
    public void dispose() {
        lockedDoorView.dispose();
        lockedDoorSound.dispose();
    }
}
