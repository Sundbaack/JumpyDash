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
	private BodyDef platformBodyDef;
	private Body platformBody;
	private Texture platformTile;
	private Platform platform;
	private BodyDef coinBodyDef;
	private Body coinBody;
	private Texture coinTile;
	private Coin coin;

	@Override
	public void create () {

		platformTile = new Texture(Gdx.files.internal("platform.png"));
		playerTile = new Texture(Gdx.files.internal("player.png"));
		coinTile = new Texture(Gdx.files.internal("coin.png"));
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);

		gameView = new GameView();
		world = new World(new Vector2(0, -100f), true); //Create a world object with a gravity vector

		// Player body

		playerBodyDef = new BodyDef();
		playerBodyDef.type = BodyDef.BodyType.DynamicBody;
		playerBodyDef.position.set(200, 200);
		playerBody = world.createBody(playerBodyDef);

		player = new Player(playerBody);

		// Platform body

		platformBodyDef = new BodyDef();
		platformBodyDef.type = BodyDef.BodyType.StaticBody;
		platformBodyDef.position.set(200,100);
		platformBody = world.createBody(platformBodyDef);

		platform = new Platform(platformBody);

		// Coin body

		coinBodyDef = new BodyDef();
		coinBodyDef.type = BodyDef.BodyType.StaticBody;
		coinBodyDef.position.set(20,20);
		coinBody = world.createBody(coinBodyDef);

		coin = new Coin(coinBody, 20);
	}

	public void handleInput() {
		// Jumping
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			player.jump();
		}
	 /*	if(Gdx.input.isKeyPressed(Input.Keys.D)){
			player.getBody().applyForce(new Vector2(5,0),new Vector2(player.getPosition()+5,0));
		}
		*/
	}

	@Override
	public void render () {

		handleInput();
		//player.move();
		world.step(1/60f, 6, 3);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(playerTile, player.getPosition().x, player.getPosition().y);
		batch.draw(platformTile,platform.getPosition().x,platform.getPosition().y);
		batch.draw(coinTile,coin.getPosition().x,platform.getPosition().y);
		batch.end();

	}

	@Override
	public void dispose() {
		gameView.dispose();
	}
}
