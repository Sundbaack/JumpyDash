package com.jumpydash;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

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
	private BodyDef soldierBodyDef;
	private Body soldierBody;
	private Soldier soldier;
	private Texture coinTile;
	private Coin coin;
	private Texture background;

	@Override
	public void create () {

		platformTile = new Texture(Gdx.files.internal("platform.png"));
		playerTile = new Texture(Gdx.files.internal("player.png"));
		coinTile = new Texture(Gdx.files.internal("coin.png"));
		background = new Texture(Gdx.files.internal("background_1.png"));
		background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);

		gameView = new GameView();
		world = new World(new Vector2(0, -100f), true); //Create a world object with a gravity vector

		// Player body Box2D

		playerBodyDef = new BodyDef();
		playerBodyDef.type = BodyDef.BodyType.DynamicBody;
		playerBodyDef.position.set(200, 200);
		playerBody = world.createBody(playerBodyDef);

		player = new Player(playerBody);


		// Platform body for Box2D

		platformBodyDef = new BodyDef();
		platformBodyDef.type = BodyDef.BodyType.StaticBody;
		platformBodyDef.position.set(200, 100);
		platformBody = world.createBody(platformBodyDef);

		platform = new Platform(platformBody);

		// Coin body for Box2D

		coinBodyDef = new BodyDef();
		coinBodyDef.type = BodyDef.BodyType.StaticBody;
		coinBodyDef.position.set(20, 20);
		coinBody = world.createBody(coinBodyDef);

		coin = new Coin(coinBody, 20);


		//Soldier you know
		soldierBodyDef = new BodyDef();
		soldierBodyDef.type = BodyDef.BodyType.DynamicBody;
		soldierBodyDef.position.set(25,25);
		soldierBody = world.createBody(soldierBodyDef);

		soldier = new Soldier(soldierBody);
		soldier.move();

		world.setContactListener(new ContactListener() {
			@Override
			public void beginContact(Contact contact) {
				// Check to see if the collision is between the the player and a platform
				if ((contact.getFixtureA().getBody() == player.getBody() &&
						contact.getFixtureB().getBody() == platform.getBody())
						||
						(contact.getFixtureA().getBody() == platform.getBody() &&
								contact.getFixtureB().getBody() == player.getBody())) {
						player.setJumpState();
				}
				if ((contact.getFixtureA().getBody() == soldier.getBody() &&
						contact.getFixtureB().getBody() == enemyTurnTile.getBody())
						||
						(contact.getFixtureA().getBody() == enemyTurnTile.getBody() &&
								contact.getFixtureB().getBody() == soldier.getBody())) {
					soldier.setCollision();
				}
			}

			@Override
			public void endContact(Contact contact) {

			}

			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {

			}

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {

			}
		});
	}

	public void handleInput() {

		// Jumping
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && player.getJumpState()) {
			player.setJumpState();
			player.jump();
		}
	}

	@Override
	public void render () {

		handleInput();

		//player.move();
		// Enable the camera to follow the player
		if(player.getPosition().x > 500) {
			camera.translate(new Vector2(2,0));
		}

		world.step(1 / 60f, 6, 3); // Step the physics simulation forward at a rate of 60hz

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		// Draw background
		batch.draw(background,0,0,0,0,10000,720);

		// Draw objects
		batch.draw(playerTile, player.getPosition().x, player.getPosition().y);
		batch.draw(platformTile,platform.getPosition().x,platform.getPosition().y);
		batch.draw(coinTile,coin.getPosition().x,platform.getPosition().y);
		camera.update();
		batch.end();
	}

	@Override
	public void dispose() {

		batch.dispose();
		playerTile.dispose();
		platformTile.dispose();
	}
}
