package com.akira.flyingpuckgames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.tiled.TileAtlas;
import com.badlogic.gdx.graphics.g2d.tiled.TileMapRenderer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLoader;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

public class screenTest implements Screen {
	private OrthographicCamera camera;
	private TileMapRenderer tileMapRenderer;
	private TileAtlas tileAtlas;
	private TiledMap tiledMap;
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
		renderWorld();
		fpsLogger.log();

		batch.begin();
		batch.draw(plahChar, plahRectangle.x, plahRectangle.y);
		batch.end();

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
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		tiledMap = TiledLoader.createMap(Gdx.files
				.internal("maps/plahTilemap.tmx"));
		tileAtlas = new TileAtlas(tiledMap, Gdx.files.internal("maps"));
		tileMapRenderer = new TileMapRenderer(tiledMap, tileAtlas, 16, 16);
		fpsLogger = new FPSLogger();

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

	public void renderWorld() {
		tileMapRenderer.render(camera);
	}

}
