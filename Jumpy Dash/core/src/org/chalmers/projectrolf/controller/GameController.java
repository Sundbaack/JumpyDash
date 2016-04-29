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
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.*;

import org.chalmers.projectrolf.model.*;
import org.chalmers.projectrolf.view.*;

import java.util.ArrayList;
import java.util.List;

public class GameController extends ApplicationAdapter {

	public static World world;
	public static OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture background;
	private PlatformController platformController;
	private BulletController bulletController;
	private PlayerView playerView;
	private ItemView itemView;
	private PlatformView platformView;
	private EnemyView enemyView;

	private List<Platform> platformList;
	private List<Coin> coinList;
	private List<Soldier> soldierList;
	private List<Ability> abilityList;
	private List<Bullet> bulletList;

	private Player player;
    private Levels levels;
	private float tileWidthHeight;

	private long previousFireTime;

	public static final float PIXELS_TO_METERS = 100f;
	private Box2DDebugRenderer debugRenderer;
	private Matrix4 debugMatrix;

	@Override
	public void create () {
		tileWidthHeight = 32;

		platformController = new PlatformController();
		bulletController = new BulletController(tileWidthHeight);
		platformList = new ArrayList<Platform>();
		coinList = new ArrayList<Coin>();
		soldierList = new ArrayList<Soldier>();
		abilityList = new ArrayList<Ability>();
		bulletList = new ArrayList<Bullet>();

		world = new World(new Vector2(0, -10f), true); //Create a world object with a gravity vector


        levels = new Levels();
		loadMap(levels.getLevel1());

		//platformView = new PlatformView(platformList);
		enemyView  = new EnemyView(soldierList);
		itemView = new ItemView(abilityList, coinList, bulletList);
		playerView = new PlayerView(player);

		background = new Texture(Gdx.files.internal("background_1.png"));
		background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		batch = new SpriteBatch();

		//debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 736);

		world.setContactListener(new ContactListener() {
			@Override
			public void beginContact(Contact contact) {
				// Check to see if the collision is between the the player and a platform
				for (Platform p: platformList) {
					if ((contact.getFixtureA().getBody() == player.getBody() &&
							contact.getFixtureB().getBody() == p.getBody())
							||
							(contact.getFixtureA().getBody() == p.getBody() &&
									contact.getFixtureB().getBody() == player.getBody())) {
						player.setJumpState();

					}
				}
				for (Soldier s: soldierList){
					if((contact.getFixtureA().getBody() == player.getBody() &&
                            contact.getFixtureB().getBody() == s.getBody())
                            ||
                            (contact.getFixtureA().getBody() == s.getBody() &&
                                    contact.getFixtureB().getBody() == player.getBody())) {
                        System.out.println("Ajabaja nu dog du");
                    }
				}
                for(Coin c: coinList){
                    if((contact.getFixtureA().getBody() == player.getBody() &&
                            contact.getFixtureB().getBody() == c.getBody())
                            ||
                            (contact.getFixtureA().getBody() == c.getBody() &&
                                    contact.getFixtureB().getBody() == player.getBody())) {
                        player.setPoints(c.getValue());
						coinList.remove(coinList.indexOf(c));

                    }
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

	private void loadMap(char[][] Level) {

		int mapHeight = Level.length;
		int mapWidth = Level[0].length;

		// Loop from top to bottom, left to right
		// Create objects, place them in lists and set their positions
		for (int y = 0; y < mapHeight; y++) {

			for (int x = 0; x < mapWidth; x++) {

				// Player
				if (Level[y][x] == 'P') {

					// Player body Box2D
					BodyDef playerBodyDef = new BodyDef();
					playerBodyDef.type = BodyDef.BodyType.DynamicBody;
					playerBodyDef.position.set(x * tileWidthHeight / GameController.PIXELS_TO_METERS, (mapHeight - 1 - y) * 32 / GameController.PIXELS_TO_METERS);
					Body playerBody = world.createBody(playerBodyDef);

					player = new Player(playerBody, tileWidthHeight / PIXELS_TO_METERS);
				} else if (Level[y][x] == '#') {
					platformController.createObject(x,y,tileWidthHeight,mapHeight);
					// Platform body for Box2D
					/*BodyDef platformBodyDef = new BodyDef();
					platformBodyDef.type = BodyDef.BodyType.StaticBody;
					platformBodyDef.position.set(x * tileWidthHeight / GameController.PIXELS_TO_METERS, (mapHeight - 1 - y) * 32 / GameController.PIXELS_TO_METERS);
					Body platformBody = world.createBody(platformBodyDef);

					Platform platform = new Platform(platformBody, tileWidthHeight / PIXELS_TO_METERS);
					platformList.add(platform);*/
				} else if (Level[y][x] == 'C') {

					// Coin body for Box2D
					BodyDef coinBodyDef = new BodyDef();
					coinBodyDef.type = BodyDef.BodyType.StaticBody;
					coinBodyDef.position.set(x * tileWidthHeight / GameController.PIXELS_TO_METERS, (mapHeight - 1 - y) * 32 / GameController.PIXELS_TO_METERS);
					Body coinBody = world.createBody(coinBodyDef);

					Coin coin = new Coin(coinBody, 20, tileWidthHeight / PIXELS_TO_METERS);
					coinList.add(coin);
				} else if (Level[y][x] == 'S') {

					// Soldier body Box2D
					BodyDef soldierBodyDef = new BodyDef();
					soldierBodyDef.type = BodyDef.BodyType.DynamicBody;
					soldierBodyDef.position.set(x * tileWidthHeight / GameController.PIXELS_TO_METERS, (mapHeight - 1 - y) * 32 / GameController.PIXELS_TO_METERS);
					Body soldierBody = world.createBody(soldierBodyDef);

					Soldier soldier = new Soldier(soldierBody, tileWidthHeight / PIXELS_TO_METERS);
					soldierList.add(soldier);
				} else if (Level[y][x] == 'A') {

					// Ability body Box2D
					BodyDef abilityBodyDef = new BodyDef();
					abilityBodyDef.type = BodyDef.BodyType.DynamicBody;
					abilityBodyDef.position.set(x * tileWidthHeight / GameController.PIXELS_TO_METERS, (mapHeight - 1 - y) * 32 / GameController.PIXELS_TO_METERS);
					Body abilityBody = world.createBody(abilityBodyDef);

					Ability ability = new Ability(abilityBody, tileWidthHeight / PIXELS_TO_METERS);
					abilityList.add(ability);
				}
			}
		}
	}

	private void handleInput() {

		// Jumping
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player.getJumpState()) {
			player.setJumpState();
			player.jump();
		}

		if (Gdx.input.isKeyPressed(Input.Keys.F)) {
			fireBullet();
        }
	}

	private void fireBullet() {

		// Cooldown
		long fireCooldown = 50;

		// Allow shooting if not on cooldown
		if (System.currentTimeMillis() - previousFireTime >= fireCooldown && bulletList.size() < 3) {

			// Reset cooldown
			previousFireTime = System.currentTimeMillis();

			// Bullet body Box2D
			BodyDef bulletBodyDef = new BodyDef();
			bulletBodyDef.type = BodyDef.BodyType.KinematicBody;
			bulletBodyDef.position.set(player.getPosition().x + (32 / GameController.PIXELS_TO_METERS), player.getPosition().y + (8 / GameController.PIXELS_TO_METERS));
			Body bulletBody = world.createBody(bulletBodyDef);
			bulletBody.setBullet(true);

			Bullet bullet = new Bullet(bulletBody, (tileWidthHeight / 2) / GameController.PIXELS_TO_METERS);
			bulletList.add(bullet);
		}

	}

    // Remove bullets when moving out of screen
	private void updateBullets() {
		for (int i = 0; i < bulletList.size(); i++) {
			if (((bulletList.get(i).getPosition().x * GameController.PIXELS_TO_METERS) + 16) > (camera.position.x + 1280 / 2)) {
				bulletList.remove(i);
			}
		}
	}

	@Override
	public void render () {

		handleInput();
		updateBullets();
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

		/*
		debugMatrix = batch.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS,
				PIXELS_TO_METERS, 0);
		*/

		batch.begin();

		// Draw background
		batch.draw(background, 0, 0, 0, 0, 10000, 736);

		// Draw objects
		playerView.render(batch);
		itemView.render(batch);
		platformController.getPlatformView().render(batch);
		enemyView.render(batch);

		batch.end();

		//debugRenderer.render(world, debugMatrix);
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
