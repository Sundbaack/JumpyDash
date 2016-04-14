package com.jumpydash;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;

public class World extends ApplicationAdapter {

	private SceneLoader sceneLoader;
	private Viewport viewport;

	@Override
	public void create () {
		viewport = new FitViewport(800, 480); // this should be the size of camera in WORLD units. make sure you check that in editor first.
		sceneLoader = new SceneLoader();
		sceneLoader.loadScene("MainScene", viewport);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1); // black background
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sceneLoader.getEngine().update(Gdx.graphics.getDeltaTime()); // getting the ashley engine and updating it (it will render things with it's own render system)

	}
}
