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
import org.chalmers.projectrolf.view.*;

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

    private Bullet bullet;
	private Player player;

	public static final float PIXELS_TO_METERS = 100f;

	@Override
	public void create () {

		platformList = new ArrayList<Platform>();
		coinList = new ArrayList<Coin>();
		soldierList = new ArrayList<Soldier>();
		abilityList = new ArrayList<Ability>();

		world = new World(new Vector2(0, -10f), true); //Create a world object with a gravity vector

		char[][] Level1 = {
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','P','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','#','#','#','#','#','#','#','.','.','.','.','.','.','.','.','#','#'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','S','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','#','#','#','#','#','#','#','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'}
		};

		loadMap(Level1);

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

	public void loadMap(char[][] Level) {

		int mapHeight = Level.length;
		int mapWidth = Level[0].length;
		int tileWidthHeight = 32;

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

					player = new Player(playerBody, tileWidthHeight);
				} else if (Level[y][x] == '#') {

					// Platform body for Box2D
					BodyDef platformBodyDef = new BodyDef();
					platformBodyDef.type = BodyDef.BodyType.StaticBody;
					platformBodyDef.position.set(x * tileWidthHeight / GameController.PIXELS_TO_METERS, (mapHeight - 1 - y) * 32 / GameController.PIXELS_TO_METERS);
					Body platformBody = world.createBody(platformBodyDef);

					Platform platform = new Platform(platformBody, tileWidthHeight);
					platformList.add(platform);
				} else if (Level[y][x] == 'C') {

					// Coin body for Box2D
					BodyDef coinBodyDef = new BodyDef();
					coinBodyDef.type = BodyDef.BodyType.StaticBody;
					coinBodyDef.position.set(x * tileWidthHeight / GameController.PIXELS_TO_METERS, (mapHeight - 1 - y) * 32 / GameController.PIXELS_TO_METERS);
					Body coinBody = world.createBody(coinBodyDef);

					Coin coin = new Coin(coinBody, 20, tileWidthHeight);
					coinList.add(coin);
				} else if (Level[y][x] == 'S') {

					// Soldier body Box2D
					BodyDef soldierBodyDef = new BodyDef();
					soldierBodyDef.type = BodyDef.BodyType.DynamicBody;
					soldierBodyDef.position.set(x * tileWidthHeight / GameController.PIXELS_TO_METERS, (mapHeight - 1 - y) * 32 / GameController.PIXELS_TO_METERS);
					Body soldierBody = world.createBody(soldierBodyDef);

					Soldier soldier = new Soldier(soldierBody, tileWidthHeight);
					soldierList.add(soldier);
				} else if (Level[y][x] == 'A') {

					// Ability body Box2D
					BodyDef abilityBodyDef = new BodyDef();
					abilityBodyDef.type = BodyDef.BodyType.DynamicBody;
					abilityBodyDef.position.set(x * tileWidthHeight / GameController.PIXELS_TO_METERS, (mapHeight - 1 - y) * 32 / GameController.PIXELS_TO_METERS);
					Body abilityBody = world.createBody(abilityBodyDef);

					Ability ability = new Ability(abilityBody, tileWidthHeight);
					abilityList.add(ability);
				}
			}
		}
	}

	public void handleInput() {

		// Jumping
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && player.getJumpState()) {
			player.setJumpState();
			player.jump();
		}
        if(Gdx.input.isKeyJustPressed(Input.Keys.F)){
        
        }
	}

	@Override
	public void render () {

		handleInput();
		//soldier.move();
		player.move();

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
		batch.draw(background, 0, 0, 0, 0, 10000, 736);

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
