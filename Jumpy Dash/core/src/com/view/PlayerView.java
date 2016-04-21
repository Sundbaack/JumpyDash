package com.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.controller.GameController;
import com.model.Player;

public class PlayerView {

    private Player player;
    private Texture playerTile;

    public PlayerView(Player player) {
        this.player = player;
        playerTile = new Texture(Gdx.files.internal("player.png"));
    }

    public void render(SpriteBatch batch) {
        batch.draw(playerTile, player.getPosition().x * GameController.PIXELS_TO_METERS, player.getPosition().y * GameController.PIXELS_TO_METERS);
    }

    public void dispose() {
        playerTile.dispose();
    }
}
