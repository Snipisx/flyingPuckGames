package com.flyingPuckGames.projectFinale.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
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
import com.flyingPuckGames.projectFinale.entity.Entity;
import com.flyingPuckGames.projectFinale.entity.Player;

public class FirstSceen implements Screen {
	private float screenW;
	private float screenH;
	private OrthographicCamera camera;
	private OrthogonalTiledMapRenderer tRenderer;
	private TiledMap tiledMap;
	private Array<Rectangle> tiles;
	private FPSLogger fpsLogger;
	private MegaGame megagame;
	private Pool<Rectangle> rectPool;

	private Texture bkgrnd;
	private SpriteBatch batch;

	private Player player;
	private BitmapFont font;

	public FirstSceen(MegaGame megagame) {
		this.megagame = megagame;
	}

	@Override
	public void render(float delta) {

		camera.position.x = player.position.x;
		camera.update();
		renderWorld(delta);

		player.updatePlayer(delta);
		player.renderCharacter(delta);

		fpsLogger.log();

		// System.out.println(character.toString());
		// Reset all debug purposes.
		if ((Gdx.input.isKeyPressed(Keys.F2))) {
			createWorld();
			player = new Player(tRenderer, rectPool, tiles, tiledMap);
		}
		renderDebugInfo(delta);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

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
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

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
		camera.setToOrtho(false, 20, 11);
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
	private void renderDebugInfo(float delta) {

		String debugInfo = "F1 resets player pos  |  F2 Resets All";

		String debugInfoFps = "Fps: " + Gdx.graphics.getFramesPerSecond();
		String debugInfoPlayePos = "Player position: " + player.position;
		String debugInfoPlayerVel = "Player velocity: " + player.velocity;
		String debugInfoPlayerState = "Player state: " + player.state;
		String debugInfoPlayerFacing;

		if (player.facesRight) {
			debugInfoPlayerFacing = "Player facing: " + "  >";
		} else {
			debugInfoPlayerFacing = "Player facing: " + "  <";
		}

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