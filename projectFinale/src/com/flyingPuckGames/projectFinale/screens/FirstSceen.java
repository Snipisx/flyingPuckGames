package com.flyingPuckGames.projectFinale.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.flyingPuckGames.projectFinale.MegaGame;
import com.flyingPuckGames.projectFinale.entity.plahCharacter;

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
	
	private plahCharacter character;
	

	
	public FirstSceen(MegaGame megagame) {
		this.megagame = megagame;
	}

	@Override
	public void render(float delta) {
		clearScreen();

		batch.begin();
		batch.draw(bkgrnd, delta, delta, screenW,screenH);
		batch.end();
		
		camera.position.x = character.position.x;
		camera.update();
		renderWorld();
		
//		System.out.println(character.toString());
		
		character.updatePlayer(delta);
		character.renderCharacter(delta);
	
		fpsLogger.log();
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
			protected Rectangle newObject () {
				return new Rectangle();
			}
		};
		tiles = new Array<Rectangle>();
		
		
		
		bkgrnd = new Texture(Gdx.files.internal("maps/background.png"));
		batch = new SpriteBatch();
		createWorld();
		createCamera();
		character = new plahCharacter(tRenderer, rectPool, tiles, tiledMap);
		
		fpsLogger = new FPSLogger();
		

		
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
	private void renderWorld() {
		tRenderer.setView(camera);
		tRenderer.render();
	}
	
	/**
	 * This method clears the screen.
	 */
	private void clearScreen(){
		Gdx.graphics.getGL20().glClearColor(1, 0, 0, 1);
		Gdx.graphics.getGL20().glClear(
				GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
	}

}
