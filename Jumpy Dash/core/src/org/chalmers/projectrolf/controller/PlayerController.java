package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.chalmers.projectrolf.model.JDBody;
import org.chalmers.projectrolf.model.Player;
import org.chalmers.projectrolf.view.PlayerView;

public class PlayerController extends Actor {

    private Box2D box2D;
    private static JDBody body;
    private static Player player;
    private PlayerView playerView;

    public PlayerController(Box2D box2D, int x, int y, int mapHeight) {
        this.box2D = box2D;
        playerView = new PlayerView();
        player = new Player();
        body = box2D.newDynamic(x, y, mapHeight);
        body.setUserData(player);
        player.setImpulse(this.body.getMass() * 6f);
    }

    public void act(float delta) {
        handleInput();
        //.move();

        // Enable the camera to follow the player
        if(getPosition().x > 500 / box2D.getPixelsToMeters()) {

            Vector3 position = box2D.getCamera().position;
            position.x = box2D.getCamera().position.x + 1280 / box2D.getPixelsToMeters() + (getPosition().x * box2D.getPixelsToMeters() - box2D.getCamera().position.x) * 0.1f;
            box2D.getCamera().position.set(position);
            box2D.getCamera().update();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        playerView.render(batch, getPosition().x * box2D.getPixelsToMeters(), getPosition().y * box2D.getPixelsToMeters());

    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && player.getJumpState()) {
            player.setJumpState();
            jump();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.F)){
            BulletController bulletController = new BulletController(box2D);
            getStage().addActor(bulletController);
            System.out.println(getStage().getActors());
        }
    }

    public void jump() {
        getJDBody().applyLinearImpulse(new Vector2(0, player.getImpulse()), getJDBody().getWorldCenter(), true);
    }

    public void move() {

        Vector2 speed = getJDBody().getLinearVelocity();
        float speedX = speed.x;

        if (speedX < player.getMaxSpeedX()) {
            getJDBody().applyForceToCenter(new Vector2(6, 0), true);
        }
    }

    public JDBody getJDBody() {
        return this.body;
    }

    public static Vector2 getPosition() {
        return body.getPosition();
    }

    public static Player getPlayer() {
        return player;
    }

    public void dispose() {
        playerView.dispose();
    }
}
