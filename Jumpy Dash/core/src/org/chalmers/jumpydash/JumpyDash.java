package org.chalmers.jumpydash;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.chalmers.jumpydash.controller.screen.ScreenManager;

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
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().initMenu(stage, uiStage);
	}

	@Override
	public void render() {
		Gdx.gl20.glClearColor(0, 0, 0, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
