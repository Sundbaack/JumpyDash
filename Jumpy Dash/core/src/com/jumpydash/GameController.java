package com.jumpydash;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class GameController extends ApplicationAdapter {

	private World world;
	private GameView gameView;

	private OrthographicCamera camera;
	private SpriteBatch batch;

	private BodyDef playerBodyDef;
	private Body playerBody;
	private Player player;
	private Texture playerTile;

	@Override
	public void create () {

		playerTile = new Texture(Gdx.files.internal("player.png"));

		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);

		gameView = new GameView();
		world = new World(new Vector2(0, -30), true); //Create a world object with a gravity vector

		playerBodyDef = new BodyDef();
		playerBodyDef.type = BodyDef.BodyType.DynamicBody;
		playerBodyDef.position.set(200, 200);
		playerBody = world.createBody(playerBodyDef);

		player = new Player(playerBody);
	}

	public void handleInput() {

		// Jumping
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			player.jump();
		}
	}

	@Override
	public void render () {

		handleInput();

		world.step(1/60f, 6, 3);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(playerTile, player.getPosition().x, player.getPosition().y);
		batch.end();
	}

	@Override
	public void dispose() {
		gameView.dispose();
	}
}
