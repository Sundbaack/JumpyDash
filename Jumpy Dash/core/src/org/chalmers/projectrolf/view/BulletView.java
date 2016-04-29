package org.chalmers.projectrolf.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.chalmers.projectrolf.controller.GameController;
import org.chalmers.projectrolf.model.Bullet;

import java.util.List;

public class BulletView {

    private List<Bullet> bulletList;
    private Texture bulletTile;

    public BulletView(List<Bullet> bulletList) {
        this.bulletList = bulletList;

        bulletTile = new Texture(Gdx.files.internal("bullet.png"));
    }

    public void render(SpriteBatch batch) {
        for(Bullet b: bulletList){
            batch.draw(bulletTile, b.getPosition().x * GameController.PIXELS_TO_METERS, b.getPosition().y * GameController.PIXELS_TO_METERS);
        }
    }

    public void dispose() {
        bulletTile.dispose();
    }
}

