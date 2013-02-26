package com.flyingPuckGames.projectFinale;

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

public class screenTest implements Screen {
	private OrthographicCamera camera;
	private OrthogonalTiledMapRenderer tRenderer;
	private TiledMap tiledMap;
	private Array<Rectangle> tiles;
	private float w;
	private float h;
	private FPSLogger fpsLogger;
	private MegaGame megagame;
	private Texture plahChar;
	private Texture bkgrnd;
	private SpriteBatch batch;
	private Rectangle plahRectangle;

	// plahChar
	public static final float plahRectangleWidth = 16;
	public static final float plahRectangleHeight = 16;
	public float plahRectangleX;
	public float plahRectangleY;

	public screenTest(MegaGame megagame) {
		this.megagame = megagame;
	}

	@Override
	public void render(float delta) {

		Gdx.graphics.getGL20().glClearColor(1, 0, 0, 1);
		Gdx.graphics.getGL20().glClear(
				GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		batch.begin();
		batch.draw(bkgrnd, delta, delta);
		batch.end();
		
		tRenderer.setView(camera);
		tRenderer.render();
		
		batch.begin();
		batch.draw(plahChar, plahRectangle.x, plahRectangle.y);
		batch.end();
		
		fpsLogger.log();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		plahRectangle = new Rectangle();
		plahRectangle.width = plahRectangleWidth;
		plahRectangle.height = plahRectangleHeight;
		plahRectangle.x = 16 + 16 / 2;
		plahRectangle.y = 32;

		plahChar = new Texture(Gdx.files.internal("data/plahCharacter.png"));
		bkgrnd = new Texture(Gdx.files.internal("maps/background.png"));

		batch = new SpriteBatch();
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		
		fpsLogger = new FPSLogger();
		tiles = new Array<Rectangle>();
		
		createWorld();
		prepareCamera();
		

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

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
	 * TODO
	 */
	private void createWorld() {
		tiledMap = new TmxMapLoader().load("maps/plahTilemap.tmx");
		tRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1/16f);
	}
	/**
	 * TODO
	 */
	private void prepareCamera(){
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 20, 11);
		camera.update();
	}
	
	private void renderWorld(){
		
	}

}
