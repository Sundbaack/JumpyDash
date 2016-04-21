package com.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.controller.GameController;
import com.model.Player;

public class PlayerView {

    private SpriteBatch batch;
    private Player player;
    private Texture playerTile;

    public PlayerView(SpriteBatch batch, Player player) {
        this.batch = batch;
        this.player = player;
        playerTile = new Texture(Gdx.files.internal("player.png"));
    }

    public void render() {
        batch.begin();
        batch.draw(playerTile, player.getPosition().x*GameController.PIXELS_TO_METERS, player.getPosition().y* GameController.PIXELS_TO_METERS);
        batch.end();
    }

    public void dispose() {
        playerTile.dispose();
    }
}
