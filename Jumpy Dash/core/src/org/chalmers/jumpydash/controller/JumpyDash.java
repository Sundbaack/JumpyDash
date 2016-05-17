package org.chalmers.jumpydash.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class JumpyDash extends Game {

	private Stage stage;
	private Stage uiStage;

	@Override
	public void create() {
		// Create a new Stage
		stage = new Stage();

		// Create a new Stage for UI
		uiStage = new Stage();

		// Switch to menu
		setScreen(new MenuScreen(this, stage, uiStage));
	}

	@Override
	public void render() {
		super.render();

		// Update actors
		stage.act();
		stage.draw();
		uiStage.act();
		uiStage.draw();
	}

	@Override
	public void dispose() {
		super.dispose();
		stage.dispose();
		uiStage.dispose();
	}
}
