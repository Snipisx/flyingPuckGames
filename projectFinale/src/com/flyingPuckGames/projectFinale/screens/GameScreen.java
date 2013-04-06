package com.flyingPuckGames.projectFinale.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.flyingPuckGames.projectFinale.MegaGame;
import com.flyingPuckGames.projectFinale.model.Player;

public class GameScreen implements Screen {
	
	// Objects -----/
	private FPSLogger fpsLogger;
	private MegaGame megagame;
	private Player player;
	
	// Render -----/
	private float screenW;
	private float screenH;
	private OrthographicCamera camera;
	private OrthogonalTiledMapRenderer tRenderer;
	private TiledMap tiledMap;
	private Array<Rectangle> tiles;
	private Pool<Rectangle> rectPool;
	private Texture bkgrnd;
	private SpriteBatch batch;
	private BitmapFont font;
	
	// Debug -----/
	private boolean debugON;
	private String debugInfo;
	private String debugInfoFps;
	private String debugInfoPlayePos;
	private String debugInfoPlayerVel;
	private String debugInfoPlayerState;
	private String debugInfoPlayerFacing;
	private float stateTime;

	// Constructor -----/
	public GameScreen(MegaGame megagame) {
		this.megagame = megagame;
	}

	/**
	 * Executed 60 times by second.
	 * @param delta
	 */
	@Override
	public void render(float delta) {
		//Cleaning -----/
		batch.dispose();
		batch = new SpriteBatch();
		clearScreen();
		
		//Camera & World -----/
		camera.position.x = player.position.x;
		camera.update();
		renderWorld(delta);
		
		//Player -----/
		player.updatePlayer(delta);
		player.renderCharacter(delta);
		
		//Debug Mode -----/
		stateTime += delta;
		if (Gdx.input.isKeyPressed(Keys.F10) && stateTime >0.2) debugON = !debugON;
		if (Gdx.input.isKeyPressed(Keys.F10)) stateTime=0;
		if (debugON) renderDebugMode();	
	}

	/* 
	 * Method called when the windows is resized.
	 */
	@Override
	public void resize(int width, int height) {
		screenW = Gdx.graphics.getWidth();
		screenH = Gdx.graphics.getHeight();
	}
	
	/* 
	 * Method called when the windows is created.
	 */
	@Override
	public void show() {
		screenW = Gdx.graphics.getWidth();
		screenH = Gdx.graphics.getHeight();
		rectPool = new Pool<Rectangle>() {
			@Override
			protected Rectangle newObject() {
				return new Rectangle();
			}
		};
		tiles = new Array<Rectangle>();

		bkgrnd = new Texture(Gdx.files.internal("maps/background.png"));
		batch = new SpriteBatch();
		createWorld();
		createCamera();
		player = new Player(tRenderer, rectPool, tiles, tiledMap);

		fpsLogger = new FPSLogger();
		font = new BitmapFont();
		debugON = false;
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

	/**
	 * Instances the tiledMap and the tiledRenderer.
	 */
	private void createWorld() {
		tiledMap = new TmxMapLoader().load("maps/plahTilemap.tmx");
		tRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1 / 16f);
	}

	/**
	 * Instances the orthographicCamera() and sets the principal values.
	 */
	private void createCamera() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 30f, 16.5f);
		camera.update();
	}

	/**
	 * Method used for rendering the tiled map on the screen.
	 */
	private void renderWorld(float delta) {
		batch.begin();
		batch.draw(bkgrnd, delta, delta, screenW, screenH);
		batch.end();
		tRenderer.setView(camera);
		tRenderer.render();
	}

	/**
	 * Method used to print on the screen debug information.
	 */
	private void renderDebugMode() {
		if ((Gdx.input.isKeyPressed(Keys.F1))) {
			player.position.set(2, 3);
			player.velocity.set(0, 0);
			System.out.println("Position Reseted.");
		}
		if ((Gdx.input.isKeyPressed(Keys.F2))) {
			createWorld();
			player = new Player(tRenderer, rectPool, tiles, tiledMap);
			System.out.println("All Reseted.");
		}

		batch.dispose();
		batch = new SpriteBatch();

		debugInfo = "F1 resets player pos  |  F2 Resets All";

		debugInfoFps = "Fps: " + Gdx.graphics.getFramesPerSecond();
		debugInfoPlayePos = "Player position: " + player.position;
		debugInfoPlayerVel = "Player velocity: " + player.velocity;
		debugInfoPlayerState = "Player state: " + player.state;

		debugInfoPlayerFacing = (player.facesRight) ? "Player facing:  >" : "Player facing:  <";

		batch.begin();
		font.draw(batch, debugInfo, 10, screenH - 3);
		font.draw(batch, debugInfoFps, 10, screenH - 37);// font = 15px by def.
		font.draw(batch, debugInfoPlayePos, 10, screenH - 54);// +17
		font.draw(batch, debugInfoPlayerVel, 10, screenH - 71);// +17
		font.draw(batch, debugInfoPlayerState, 10, screenH - 88);// +17
		font.draw(batch, debugInfoPlayerFacing, 10, screenH - 105);// +17
		batch.end();
	}

	/**
	 * This method clears the screen.
	 */
	private void clearScreen() {
		Gdx.graphics.getGL20().glClearColor(1, 0, 0, 1);
		Gdx.graphics.getGL20().glClear(
				GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
	}

}