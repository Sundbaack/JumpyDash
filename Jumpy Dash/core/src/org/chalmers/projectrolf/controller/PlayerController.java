package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.chalmers.projectrolf.model.Player;
import org.chalmers.projectrolf.view.PlayerView;

/**
 * Created by alexsundback on 2016-04-28.
 */
public class PlayerController {

    private BodyDef playerBodyDef;
    private Body playerBody;
    public static Player player;
    private PlayerView playerView;

    public PlayerController() {
    }

    public void createObject(int x, int y, float tileWidthHeight, int mapHeight) {
        // Player body Box2D
        playerBodyDef = new BodyDef();
        playerBodyDef.type = BodyDef.BodyType.DynamicBody;
        playerBodyDef.position.set(x * tileWidthHeight / GameController.PIXELS_TO_METERS, (mapHeight - 1 - y) * 32 / GameController.PIXELS_TO_METERS);
        playerBody = GameController.world.createBody(playerBodyDef);

        player = new Player(playerBody, tileWidthHeight / GameController.PIXELS_TO_METERS);
        playerView = new PlayerView(player);
    }

    public PlayerView getPlayerView(){
        return this.playerView;
    }

}
