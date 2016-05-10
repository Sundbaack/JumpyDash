package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.chalmers.projectrolf.physics.Box2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JumpyDash extends ApplicationAdapter {

	private Stage stage;
	private Texture background;
	private SpriteBatch batch;
	private Box2D box2D;
	private final float tileWidthHeight = 32;
	//private Box2DDebugRenderer debugRenderer;
	//private Matrix4 debugMatrix;

	@Override
	public void create() {

		// Box2D wrapper
		box2D = new Box2D(tileWidthHeight);

		batch = new SpriteBatch();
		stage = new Stage(new ScreenViewport(box2D.getCamera()), batch);

		try {
			loadMap(new File("level1.txt"));
		} catch (FileNotFoundException e) {
			System.out.print("File not found");
		}

		background = new Texture(Gdx.files.internal("background_1.png"));
		background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

		//debugRenderer = new Box2DDebugRenderer();
	}

	private void loadMap(File level) throws FileNotFoundException  {
		Scanner scanLevel = new Scanner(level);

		char[][] level1 = new char[23][1200];

		for (int y = 0; y < level1.length; y++){
			String currentLine = scanLevel.nextLine();
			currentLine = currentLine.replaceAll("\\s+","");

			for(int x = 0; x < currentLine.length(); x++ ){
				level1[y][x]= currentLine.charAt(x);
			}
		}

		int mapHeight = level1.length;
		int mapWidth = level1[0].length;

		// Loop from top to bottom, left to right
		// Create objects, place them in lists and set their positions
		for (int y = 0; y < mapHeight; y++) {

			for (int x = 0; x < mapWidth; x++) {

				if (level1[y][x] == 'P') {
					PlayerController p = new PlayerController(box2D, x, y, mapHeight);
					stage.addActor(p);

				} else if (level1[y][x] == '#') {
					PlatformController platformController = new PlatformController(box2D,x, y, mapHeight);
					stage.addActor(platformController);
				} else if (level1[y][x] == 'C') {
					CoinController coinController = new CoinController(box2D,x,y,mapHeight);
					stage.addActor(coinController);

				} else if (level1[y][x] == 'S') {
					SoldierController soldierController = new SoldierController(box2D,x,y,mapHeight);
					stage.addActor(soldierController);

				} else if (level1[y][x] == 'A') {
					AbilityController abilityController = new AbilityController(box2D,x,y,mapHeight);
					stage.addActor(abilityController);

				}
			}
		}
	}

	@Override
	public void render() {

		box2D.step();
		box2D.update();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(box2D.getCamera().combined);

		/*
		// Debugging
		debugMatrix = batch.getProjectionMatrix().cpy().scale(box2D.getPixelsToMeters(), box2D.getPixelsToMeters(), 0);
		debugRenderer.render(box2D.world, debugMatrix);
		*/

		batch.begin();

		// Draw background
		batch.draw(background, 0, 0, 0, 0, 10000, 736);

		batch.end();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
}
