package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import org.chalmers.projectrolf.model.Player;
import org.chalmers.projectrolf.view.PlayerView;

public class PlayerController {

    private Box2D box2D;
    public static Player player;
    private PlayerView playerView;
    private final float PIXELS_TO_METERS;

    public PlayerController(Box2D box2D) {
        this.box2D = box2D;
        this.PIXELS_TO_METERS = box2D.getPixelsToMeters();
        playerView = new PlayerView();
    }

    public void createObject(int x, int y, int mapHeight) {
        player = new Player(box2D.newDynamic(x, y, mapHeight));
    }

    public void update(SpriteBatch batch) {
        handleInput();
        render(batch);

        // Enable the camera to follow the player
        if(player.getPosition().x > 500 / PIXELS_TO_METERS) {

            Vector3 position = JumpyDash.camera.position;
            position.x = JumpyDash.camera.position.x + 1280 / PIXELS_TO_METERS + (player.getPosition().x * PIXELS_TO_METERS - JumpyDash.camera.position.x) * 0.1f;
            JumpyDash.camera.position.set(position);
            JumpyDash.camera.update();
        }
    }

    public void render(SpriteBatch batch) {
        playerView.render(player.getPosition().x * PIXELS_TO_METERS, player.getPosition().y * PIXELS_TO_METERS, batch);
    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            //player.setJumpState();
            player.jump();
        }
    }

    public static Vector2 getPosition() {
        return player.getPosition();
    }

    public void dispose() {
        playerView.dispose();
    }
}
