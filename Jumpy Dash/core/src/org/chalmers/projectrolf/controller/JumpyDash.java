package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.chalmers.projectrolf.model.Levels;

public class JumpyDash extends ApplicationAdapter {

	public static OrthographicCamera camera;

	private SpriteBatch batch;
	private Texture background;

	private Box2D box2D;
	private PlatformController platformController;
	private BulletController bulletController;
	private PlayerController playerController;
	private AbilityController abilityController;
	private CoinController coinController;
	private SoldierController soldierController;

    private Levels levels;
	private final float tileWidthHeight = 32;
	//private Box2DDebugRenderer debugRenderer;
	//private Matrix4 debugMatrix;

	@Override
	public void create () {

		// Box2D wrapper
		box2D = new Box2D(tileWidthHeight);

		// Controllers
		platformController = new PlatformController(box2D);
		bulletController = new BulletController(box2D);
		playerController = new PlayerController(box2D);
		abilityController = new AbilityController(box2D);
		coinController = new CoinController(box2D);
		soldierController = new SoldierController(box2D);

		levels = new Levels();
		loadMap(levels.getLevel1());

		background = new Texture(Gdx.files.internal("background_1.png"));
		background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		batch = new SpriteBatch();

		//debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 736);
	}

	private void loadMap(char[][] Level) {

		int mapHeight = Level.length;
		int mapWidth = Level[0].length;

		// Loop from top to bottom, left to right
		// Create objects, place them in lists and set their positions
		for (int y = 0; y < mapHeight; y++) {

			for (int x = 0; x < mapWidth; x++) {

				if (Level[y][x] == 'P') {
					playerController.createObject(x, y, mapHeight);

				} else if (Level[y][x] == '#') {
					platformController.createObject(x, y, mapHeight);

				} else if (Level[y][x] == 'C') {
					coinController.createObject(x, y, mapHeight);

				} else if (Level[y][x] == 'S') {
					soldierController.createObject(x, y, mapHeight);

				} else if (Level[y][x] == 'A') {
					abilityController.createObject(x, y, mapHeight);

				}
			}
		}
	}

	@Override
	public void render () {

		box2D.step();

		camera.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		/*
		// Debugging
		debugMatrix = batch.getProjectionMatrix().cpy().scale(box2D.getPixelsToMeters(), box2D.getPixelsToMeters(), 0);
		debugRenderer.render(box2D.world, debugMatrix);
		*/

		batch.begin();

		// Draw background
		batch.draw(background, 0, 0, 0, 0, 10000, 736);

		// Update controllers
		platformController.update(batch);
		playerController.update(batch);
		bulletController.update(batch);
		coinController.update(batch);
		abilityController.update(batch);
		soldierController.update(batch);

		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		platformController.dispose();
		playerController.dispose();
		bulletController.dispose();
		coinController.dispose();
		abilityController.dispose();
		soldierController.dispose();
	}
}
