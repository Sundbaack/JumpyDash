package org.chalmers.projectrolf.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.chalmers.projectrolf.controller.GameController;
import org.chalmers.projectrolf.model.Ability;
import org.chalmers.projectrolf.model.Bullet;
import org.chalmers.projectrolf.model.Coin;

import java.util.List;

public class ItemView {

    private List<Ability> abilityList;
    private Texture abilityTile;
    private List<Coin> coinList;
    private Texture coinTile;
    private List<Bullet> bulletList;
    private Texture bulletTile;

    public ItemView(List<Ability> abilityList, List<Coin> coinList, List<Bullet> bulletList) {
        this.abilityList = abilityList;
        this.coinList = coinList;
        this.bulletList = bulletList;

        abilityTile = new Texture(Gdx.files.internal("ability.png"));
        coinTile = new Texture(Gdx.files.internal("coin.png"));
        bulletTile = new Texture(Gdx.files.internal("bullet.png"));
    }

    public void render(SpriteBatch batch) {
        for(Ability a: abilityList){
            batch.draw(abilityTile, a.getPosition().x * GameController.PIXELS_TO_METERS, a.getPosition().y * GameController.PIXELS_TO_METERS);
        }
        for(Coin c: coinList){
            batch.draw(coinTile, c.getPosition().x * GameController.PIXELS_TO_METERS, c.getPosition().y * GameController.PIXELS_TO_METERS);
        }
        for(Bullet b: bulletList){
            batch.draw(bulletTile, b.getPosition().x * GameController.PIXELS_TO_METERS, b.getPosition().y * GameController.PIXELS_TO_METERS);
        }
    }

    public void dispose() {
        abilityTile.dispose();
        coinTile.dispose();
        bulletTile.dispose();
    }
}

