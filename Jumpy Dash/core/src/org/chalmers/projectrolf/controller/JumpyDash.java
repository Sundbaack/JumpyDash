package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import org.chalmers.projectrolf.model.*;

public class JumpyDash extends ApplicationAdapter {

	public static World world;
	public static OrthographicCamera camera;

	private SpriteBatch batch;
	private Texture background;

	private PlatformController platformController;
	private BulletController bulletController;
	private PlayerController playerController;
	private AbilityController abilityController;
	private CoinController coinController;
	private SoldierController soldierController;

    private Levels levels;
	private final float tileWidthHeight = 32;
	public static final float PIXELS_TO_METERS = 100f;

	//private Box2DDebugRenderer debugRenderer;
	//private Matrix4 debugMatrix;

	@Override
	public void create () {

		// Controllers
		platformController = new PlatformController(tileWidthHeight);
		bulletController = new BulletController(tileWidthHeight / 2);
		playerController = new PlayerController(tileWidthHeight);
		abilityController = new AbilityController(tileWidthHeight);
		coinController = new CoinController(tileWidthHeight);
		soldierController = new SoldierController(tileWidthHeight);

		world = new World(new Vector2(0, -10f), true); //Create a world object with a gravity vector

		levels = new Levels();
		loadMap(levels.getLevel1());

		background = new Texture(Gdx.files.internal("background_1.png"));
		background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		batch = new SpriteBatch();

		//debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 736);

		world.setContactListener(new CollisionListener());

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

		/*
		// Debugging
		debugMatrix = batch.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS, PIXELS_TO_METERS, 0);
		debugRenderer.render(world, debugMatrix);
		*/

		world.step(1 / 60f, 6, 3); // Step the physics simulation forward at a rate of 60fps

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);

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
