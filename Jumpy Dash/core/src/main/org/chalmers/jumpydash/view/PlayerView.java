package org.chalmers.jumpydash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import org.chalmers.jumpydash.model.Player;

public class PlayerView implements JDView {

    private Texture playerTileStandard;
    private Texture playerTileInvincible;
    private TextureAtlas playerAtlas;
    private boolean invincible;
    private Animation animation;
    private float elapsedTime = 0f;
    private Player player;
    private Player.State currentState;

    public PlayerView(Player player) {
        this.player = player;
        playerTileInvincible = new Texture(Gdx.files.internal("images/playerInvincible.png"));
        playerTileStandard = new Texture(Gdx.files.internal("images/rawSprites/player00.png"));

        playerAtlas = new TextureAtlas(Gdx.files.internal("images/spriteSheets/playerAnimation.atlas"));
        animation = new Animation(1f / 40f, playerAtlas.getRegions());
    }

    public void render(Batch batch, float x, float y) {
        currentState = player.getState();
        invincible = player.isInvincible();
        // Animation
        switch (currentState) {
            case JUMPING:
                if (elapsedTime >= animation.getAnimationDuration()) {
                    drawTile(batch, x, y);
                    break;
                } else {
                    elapsedTime += Gdx.graphics.getDeltaTime();
                    batch.draw(animation.getKeyFrame(elapsedTime, true), x -6, y-6);
                    break;
                }
            case FALLING:
                drawTile(batch, x, y);
                elapsedTime = 0f;
                break;
            default:
                drawTile(batch, x, y);
                elapsedTime = 0f;
                break;
        }
    }

    public void drawTile(Batch batch, float x, float y) {
        if(invincible){
            batch.draw(playerTileInvincible, x-6,y-6);
        }else{
            batch.draw(playerTileStandard, x -6, y-6);
        }
    }

    public void dispose() {
        playerAtlas.dispose();
        playerTileStandard.dispose();
        playerTileInvincible.dispose();
    }
}
