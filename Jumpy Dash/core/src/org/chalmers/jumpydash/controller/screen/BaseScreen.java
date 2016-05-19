package org.chalmers.jumpydash.controller.screen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public abstract class BaseScreen implements Screen {

    public abstract Game getGameInstance();

    public static void setScreen(BaseScreen screen) {
        screen.getGameInstance().setScreen(screen);
    }

    @Override
    public void show() {

    }

    @Override
    public abstract void render(float delta);

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public abstract void dispose();
}
