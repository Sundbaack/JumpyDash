package main.java.org.chalmers.jumpydash.jumpydash.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import main.java.org.chalmers.jumpydash.jumpydash.model.Player;
import main.java.org.chalmers.jumpydash.jumpydash.physics.Box2D;
import main.java.org.chalmers.jumpydash.jumpydash.physics.IBox2D;
import main.java.org.chalmers.jumpydash.jumpydash.util.Options;
import main.java.org.chalmers.jumpydash.jumpydash.view.JDView;
import main.java.org.chalmers.jumpydash.jumpydash.view.PlayerView;
import main.java.org.chalmers.jumpydash.jumpydash.physics.BodyType;

import javax.vecmath.Vector2f;

public class PlayerController extends JDController {

    private IBox2D box2D;
    private Player player;
    private JDView playerView;
    private Sound sound;
    private static final int CAMERA_UPDATE_POINT = 500;

    public PlayerController(IBox2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        player = new Player();

        player.setJDBody(box2D.newBody(x, y, mapHeight, BodyType.DYNAMIC, false,false));

        player.getJDBody().setUserData(player);
        player.setImpulse(player.getJDBody().getMass() * 4f);
        playerView = new PlayerView(player);

        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/gun.wav"));
    }

    @Override
    public void act(float delta) {
        if (player.getJDBody().isAwake()) {
            handleInput();
            player.move();
        }
        System.out.println("State " + player.currentState);
        if(player.isInvincible()) {
            if (System.currentTimeMillis() - player.getInvinciblePickUpTime() >= 10000) {
                player.setInvincible();
            }
        }

        // Check if player falls below map
        if(player.getPosition().y < (-Box2D.TILE_SIZE/ Box2D.PIXELS_TO_METERS)){
            player.setDamage(player.getHealth());
        }

        // Enable the camera to follow the player
        if (player.getPosition().x > CAMERA_UPDATE_POINT / Box2D.PIXELS_TO_METERS) {
            Vector3 position = box2D.getCamera().position;
            position.x = box2D.getCamera().position.x + Box2D.SCREEN_WIDTH / Box2D.PIXELS_TO_METERS + (player.getPosition().x * Box2D.PIXELS_TO_METERS - box2D.getCamera().position.x) * 0.1f;
            box2D.getCamera().position.set(position);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        playerView.render(batch, player.getPosition().x * Box2D.PIXELS_TO_METERS, player.getPosition().y * Box2D.PIXELS_TO_METERS);
    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && (player.getState() == Player.State.RUNNING
                || player.getState() == Player.State.STANDING)) {
            player.jump();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F) && player.allowedToFire()) {
            BulletController bulletController = new BulletController(box2D, player.getPosition().x,
                    player.getPosition().y,new Vector2f(12f + player.getJDBody().getLinearVelocity().x, 0));
            getStage().addActor(bulletController);
            if (Options.getInstance().getSound()) {
                sound.play(1);
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void dispose() {
        playerView.dispose();
        sound.dispose();
    }
}
