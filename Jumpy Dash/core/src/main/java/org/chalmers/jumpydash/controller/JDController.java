package main.java.org.chalmers.jumpydash.jumpydash.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class JDController extends Actor {

    @Override
    public abstract void act(float delta);

    @Override
    public abstract void draw(Batch batch, float parentAlpha);

    public abstract void dispose();
}
