package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.chalmers.projectrolf.model.Player;
import org.chalmers.projectrolf.view.PlayerView;

public class PlayerController {

    private BodyDef playerBodyDef;
    private Body playerBody;
    public static Player player;
    private PlayerView playerView;
    private final float tileWidthHeight;
    private final float PIXELS_TO_METERS;

    public PlayerController(float tileWidthHeight, float PIXELS_TO_METERS) {
        this.tileWidthHeight = tileWidthHeight;
        this.PIXELS_TO_METERS = PIXELS_TO_METERS;
        playerView = new PlayerView();
    }

    public void createObject(int x, int y, int mapHeight) {
        // Player body Box2D
        playerBodyDef = new BodyDef();
        playerBodyDef.type = BodyDef.BodyType.DynamicBody;
        playerBodyDef.position.set(x * tileWidthHeight / PIXELS_TO_METERS, (mapHeight - 1 - y) * 32 / PIXELS_TO_METERS);
        playerBody = JumpyDash.world.createBody(playerBodyDef);

        player = new Player(playerBody, tileWidthHeight / PIXELS_TO_METERS);
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

    public void dispose() {
        playerView.dispose();
    }
}
