package com.akira.flyingpuckgames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.tiled.TileAtlas;
import com.badlogic.gdx.graphics.g2d.tiled.TileMapRenderer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLoader;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;

public class screenTest implements Screen {
	private OrthographicCamera camera;
	private TileMapRenderer tileMapRenderer;
	private TileAtlas tileAtlas;
	private TiledMap tiledMap;
	private float w;
	private float h;
	private FPSLogger fpsLogger;
	private MegaGame megagame;

	public screenTest(MegaGame megagame) {
		this.megagame = megagame;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		renderWorld();
		fpsLogger.log();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		tiledMap = TiledLoader.createMap(Gdx.files
				.internal("maps/testMap001.tmx"));
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
