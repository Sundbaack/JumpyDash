package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import org.chalmers.projectrolf.model.Coin;
import org.chalmers.projectrolf.model.Platform;
import org.chalmers.projectrolf.model.Player;
import org.chalmers.projectrolf.model.Soldier;
import org.chalmers.projectrolf.view.CoinView;
import org.chalmers.projectrolf.view.PlayerView;

public class GameController extends ApplicationAdapter {

	private World world;

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private PlayerView playerView;
	private CoinView coinView;

	private BodyDef playerBodyDef;
	private Body playerBody;
	private Player player;

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
	public static final float PIXELS_TO_METERS = 100f;

	@Override
	public void create () {

		platformTile = new Texture(Gdx.files.internal("platform.png"));
		coinTile = new Texture(Gdx.files.internal("coin.png"));
		background = new Texture(Gdx.files.internal("background_1.png"));
		background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);

		world = new World(new Vector2(0, -10f), true); //Create a world object with a gravity vector

		// Player body Box2D

		playerBodyDef = new BodyDef();
		playerBodyDef.type = BodyDef.BodyType.DynamicBody;
		playerBodyDef.position.set(200/GameController.PIXELS_TO_METERS, 400/GameController.PIXELS_TO_METERS);
		playerBody = world.createBody(playerBodyDef);

		player = new Player(playerBody);
		playerView = new PlayerView(player);

		// Platform body for Box2D

		platformBodyDef = new BodyDef();
		platformBodyDef.type = BodyDef.BodyType.StaticBody;
		platformBodyDef.position.set(200/GameController.PIXELS_TO_METERS, 100/GameController.PIXELS_TO_METERS);
		platformBody = world.createBody(platformBodyDef);

		platform = new Platform(platformBody);

		// Coin body for Box2D

		coinBodyDef = new BodyDef();
		coinBodyDef.type = BodyDef.BodyType.StaticBody;
		coinBodyDef.position.set(20/GameController.PIXELS_TO_METERS, 20/GameController.PIXELS_TO_METERS);
		coinBody = world.createBody(coinBodyDef);

		coin = new Coin(coinBody, 20);

		// Soldier body for Box2D
		soldierBodyDef = new BodyDef();
		soldierBodyDef.type = BodyDef.BodyType.DynamicBody;
		soldierBodyDef.position.set(25/GameController.PIXELS_TO_METERS,25/GameController.PIXELS_TO_METERS);
		soldierBody = world.createBody(soldierBodyDef);

		soldier = new Soldier(soldierBody);

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
				/*if ((contact.getFixtureA().getBody() == soldier.getBody() &&
						contact.getFixtureB().getBody() == enemyTurnTile.getBody())
						||
						(contact.getFixtureA().getBody() == enemyTurnTile.getBody() &&
								contact.getFixtureB().getBody() == soldier.getBody())) {
					soldier.setCollision();
				}*/
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
		//soldier.move();

		// Enable the camera to follow the player
		if(player.getPosition().x > 500 / GameController.PIXELS_TO_METERS) {

			Vector3 position = camera.position;
			position.x = camera.position.x + 1280 / GameController.PIXELS_TO_METERS + (player.getPosition().x * GameController.PIXELS_TO_METERS - camera.position.x) * 0.1f;
			camera.position.set(position);
			camera.update();
		}

		world.step(1 / 60f, 6, 3); // Step the physics simulation forward at a rate of 60hz

		//player.move();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);

		batch.begin();

		// Draw background
		batch.draw(background,0,0,0,0,10000,720);

		// Draw objects
		playerView.render(batch);
		coinView.render(batch);
		batch.draw(platformTile,platform.getPosition().x*GameController.PIXELS_TO_METERS,platform.getPosition().y*GameController.PIXELS_TO_METERS);


		batch.end();
	}

	@Override
	public void dispose() {
		coinTile.dispose();
		batch.dispose();
		playerView.dispose();
		platformTile.dispose();
		coinView.dispose();
	}
}
