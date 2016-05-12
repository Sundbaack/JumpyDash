package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class JumpyDash extends Game {

	private Stage stage;

	@Override
	public void create() {
		// Create a new Stage and set inputProcessor
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		// Switch to menu
		setScreen(new MenuScreen(this, stage));
	}

	@Override
	public void render() {
		// Render the current Screen
		getScreen().render(Gdx.graphics.getDeltaTime());

		// Update actors
		stage.act();
		stage.draw();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
}
