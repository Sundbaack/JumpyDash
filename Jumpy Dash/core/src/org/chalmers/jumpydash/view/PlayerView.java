package org.chalmers.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import org.chalmers.jumpydash.model.Player;

public class PlayerView implements JDView {

    private Texture playerTileRunning;
    private Texture playerTileStanding;
    private Texture playerTileFalling;
    private TextureAtlas textureAtlas;
    private Animation animation;
    private float elapsedTime = 0f;
    private Player player;
    private Player.State currentState;

    public PlayerView(Player player) {
        this.player = player;
        playerTileStanding = new Texture(Gdx.files.internal("images/rawSprites/player00.png"));
        playerTileRunning = new Texture(Gdx.files.internal("images/rawSprites/player00.png"));
        playerTileFalling = new Texture(Gdx.files.internal("images/rawSprites/player15.png"));
        textureAtlas = new TextureAtlas(Gdx.files.internal("images/spriteSheets/playerAnimation.atlas"));
        animation = new Animation(1f / 40f, textureAtlas.getRegions());
    }

    public void render(Batch batch, float x, float y) {
        currentState = player.getState();
        //Fix magic values 6 and 2
        // Animation
        switch (currentState) {
            case JUMPING:
                if (elapsedTime >= animation.getAnimationDuration()) {
                    batch.draw(playerTileRunning, x-6 , y);
                    break;
                } else {
                    elapsedTime += Gdx.graphics.getDeltaTime();
                    batch.draw(animation.getKeyFrame(elapsedTime, true), x -6, y);
                    break;
                }
            case FALLING:
                batch.draw(playerTileFalling, x -6, y);
                elapsedTime = 0f;
                break;
            case RUNNING:
                batch.draw(playerTileRunning, x-6, y - 6);
                elapsedTime = 0f;
                break;
            default:
                batch.draw(playerTileStanding, x-6, y - 6);
                elapsedTime = 0f;
                break;
        }
    }

    public void dispose() {
        textureAtlas.dispose();
        playerTileFalling.dispose();
        playerTileRunning.dispose();
        playerTileStanding.dispose();
    }
}
