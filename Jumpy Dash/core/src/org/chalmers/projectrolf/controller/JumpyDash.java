package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.chalmers.projectrolf.physics.Box2D;
import org.chalmers.projectrolf.physics.IBox2D;
import org.chalmers.projectrolf.service.LoadFromFile;

import java.io.File;
import java.io.FileNotFoundException;

public class JumpyDash extends Game {

	private Stage stage;
	private IBox2D box2D;
	private final float tileWidthHeight = 32;
	//private Box2DDebugRenderer debugRenderer;
	//private Matrix4 debugMatrix;

	@Override
	public void create() {
		box2D = new Box2D(tileWidthHeight);
		box2D.getWorld().setContactListener(new CollisionListener());

		stage = new Stage(new ScreenViewport(box2D.getCamera()));

		try {
			LoadFromFile.loadMap(box2D, stage, new File("level1.txt"));
		} catch (FileNotFoundException e) {
			System.out.print("File not found");
		}

		//debugRenderer = new Box2DDebugRenderer();
	}

	@Override
	public void render() {
		box2D.update();

		/*
		// Debugging
		debugMatrix = batch.getProjectionMatrix().cpy().scale(box2D.getPixelsToMeters(), box2D.getPixelsToMeters(), 0);
		debugRenderer.render(box2D.world, debugMatrix);
		*/

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
}
