package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.jumpydash.physics.IBox2D;
import org.chalmers.jumpydash.model.Player;
import org.chalmers.jumpydash.view.PlayerView;

public class PlayerController extends Actor {

    private IBox2D box2D;
    private static Player player;
    private PlayerView playerView;

    public PlayerController(IBox2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        playerView = new PlayerView();
        player = new Player(box2D.newDynKin(x, y, mapHeight, false));
    }

    @Override
    public void act(float delta) {
        handleInput();
        player.move();
        //Check if player is dead
        if(player.getHealth() <= 0){
            System.out.println("You are dead");
        }

        // Enable the camera to follow the player
        if (player.getPosition().x > 500 / box2D.getPixelsToMeters()) {

            Vector3 position = box2D.getCamera().position;
            position.x = box2D.getCamera().position.x + 1280 / box2D.getPixelsToMeters() + (player.getPosition().x * box2D.getPixelsToMeters() - box2D.getCamera().position.x) * 0.1f;
            box2D.getCamera().position.set(position);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        playerView.render(batch, player.getPosition().x * box2D.getPixelsToMeters(), player.getPosition().y * box2D.getPixelsToMeters());
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && player.getJumpState()) {
            player.setJumpState();
            player.jump();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)&& player.allowedToFire()==true) {
            BulletController bulletController = new BulletController(box2D, player.getPosition().x, player.getPosition().y);
            getStage().addActor(bulletController);
        }
    }

    public static Player getPlayer() {
        return player;
    }

    public void dispose() {
        playerView.dispose();
    }
}
