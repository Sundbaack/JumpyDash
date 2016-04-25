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

import org.chalmers.projectrolf.model.*;
import org.chalmers.projectrolf.view.ItemView;
import org.chalmers.projectrolf.view.PlatformView;
import org.chalmers.projectrolf.view.PlayerView;
import org.chalmers.projectrolf.view.EnemyView;

import java.util.ArrayList;
import java.util.List;

public class GameController extends ApplicationAdapter {

	private World world;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture background;

	private PlayerView playerView;
	private ItemView itemView;
	private PlatformView platformView;
	private EnemyView enemyView;

	private List<Platform> platformList;
	private List<Coin> coinList;
	private List<Soldier> soldierList;
	private List<Ability> abilityList;

	private BodyDef playerBodyDef;
	private Body playerBody;
	private Player player;

	private BodyDef platformBodyDef;
	private Body platformBody;
	private Platform platform;

	private BodyDef coinBodyDef;
	private Body coinBody;
	private Coin coin;

	private BodyDef soldierBodyDef;
	private Body soldierBody;
	private Soldier soldier;

	private BodyDef abilityBodyDef;
	private Body abilityBody;
	private Ability ability;

	public static final float PIXELS_TO_METERS = 100f;
	public int mapWidth;
	public int mapHeight;

	@Override
	public void create () {

		platformList = new ArrayList<Platform>();
		coinList = new ArrayList<Coin>();
		soldierList = new ArrayList<Soldier>();
		abilityList = new ArrayList<Ability>();

		world = new World(new Vector2(0, -10f), true); //Create a world object with a gravity vector

		char[][] Level1 = {
				{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','P','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','#','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'}
		};

		mapHeight = Level1.length;
		mapWidth = Level1[0].length;

		// Loop from top to bottom, left to right
		// Create objects, place them in lists and set their positions
		for (int y = 0; y < mapHeight; y++) {

			for (int x = 0; x < mapWidth; x++) {

				// Player
				if (Level1[y][x] == 'P') {

					// Player body Box2D
					playerBodyDef = new BodyDef();
					playerBodyDef.type = BodyDef.BodyType.DynamicBody;
					playerBodyDef.position.set(x * 32 / GameController.PIXELS_TO_METERS, y * 32 / GameController.PIXELS_TO_METERS);
					playerBody = world.createBody(playerBodyDef);

					player = new Player(playerBody);
				} else if (Level1[y][x] == '#') {

					// Platform body for Box2D
					platformBodyDef = new BodyDef();
					platformBodyDef.type = BodyDef.BodyType.StaticBody;
					platformBodyDef.position.set(x * 32 / GameController.PIXELS_TO_METERS, y * 32 / GameController.PIXELS_TO_METERS);
					platformBody = world.createBody(platformBodyDef);

					platform = new Platform(platformBody);
					platformList.add(platform);
				} else if (Level1[y][x] == 'C') {

					// Coin body for Box2D
					coinBodyDef = new BodyDef();
					coinBodyDef.type = BodyDef.BodyType.StaticBody;
					coinBodyDef.position.set(x * 32 / GameController.PIXELS_TO_METERS, y * 32 / GameController.PIXELS_TO_METERS);
					coinBody = world.createBody(coinBodyDef);

					coin = new Coin(coinBody, 20);
					coinList.add(coin);
				} else if (Level1[y][x] == 'S') {

					// Soldier body Box2D
					soldierBodyDef = new BodyDef();
					soldierBodyDef.type = BodyDef.BodyType.DynamicBody;
					soldierBodyDef.position.set(x * 32 / GameController.PIXELS_TO_METERS, y * 32 / GameController.PIXELS_TO_METERS);
					soldierBody = world.createBody(soldierBodyDef);

					soldier = new Soldier(soldierBody);
					soldierList.add(soldier);
				} else if (Level1[y][x] == 'A') {

					// Ability body Box2D
					abilityBodyDef = new BodyDef();
					abilityBodyDef.type = BodyDef.BodyType.DynamicBody;
					abilityBodyDef.position.set(x * 32 / GameController.PIXELS_TO_METERS, y * 32 / GameController.PIXELS_TO_METERS);
					abilityBody = world.createBody(abilityBodyDef);

					ability = new Ability(abilityBody);
					abilityList.add(ability);
				}
			}
		}

		platformView = new PlatformView(platformList);
		enemyView  = new EnemyView(soldierList);
		itemView = new ItemView(abilityList, coinList);
		playerView = new PlayerView(player);

		background = new Texture(Gdx.files.internal("background_1.png"));
		background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 736);

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
		//player.move();

		// Enable the camera to follow the player
		if(player.getPosition().x > 500 / GameController.PIXELS_TO_METERS) {

			Vector3 position = camera.position;
			position.x = camera.position.x + 1280 / GameController.PIXELS_TO_METERS + (player.getPosition().x * GameController.PIXELS_TO_METERS - camera.position.x) * 0.1f;
			camera.position.set(position);
			camera.update();
		}

		world.step(1 / 60f, 6, 3); // Step the physics simulation forward at a rate of 60hz

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);

		batch.begin();

		// Draw background
		batch.draw(background, 0, 0, 0, 0, 10000, 720);

		// Draw objects
		playerView.render(batch);
		itemView.render(batch);
		platformView.render(batch);
		enemyView.render(batch);

		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		playerView.dispose();
		itemView.dispose();
		platformView.dispose();
		enemyView.dispose();
	}
}
