package com.flyingPuckGames.projectFinale.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.flyingPuckGames.projectFinale.model.Level;
import com.flyingPuckGames.projectFinale.utils.Constants;
import com.flyingPuckGames.projectFinale.utils.RenderUtils;

/**
 * World renderer.
 * @author flyingPuck(games);
 */
public class WorldRenderer {
	
	private Level level;
	private OrthogonalTiledMapRenderer tileRenderer;
	private OrthographicCamera camera;
	private SpriteBatch spriteBatch;
	private	Integer screenW;
	private	Integer screenH;
	private Texture background;

	/**
	 * Contructor.
	 * @param level selected level to render.
	 */
	public WorldRenderer(Level level){
		this.level = level;
		
		setActualWindowSize();
		createCamera();
		createWorld();
		
		spriteBatch = new SpriteBatch();
	}
	
	/**
	 * Method that updates the state of the renderer.
	 * @param delta
	 * @param cameraPosition (Player actual position.)
	 */
	public void update(float delta, Vector2 cameraPosition){
		updateCamera(cameraPosition);
		renderWorld(delta);
	}
	
	/**
	 * Instances the tiledMap and the tiledRenderer.
	 */
	private void createWorld() {
		tileRenderer = new OrthogonalTiledMapRenderer(level.getTiledMap(), 1 / Constants.TILE_UNIT_SIZE);
		background = new Texture(Gdx.files.internal(Constants.TEST_BACKGROUND_PATH));
	}

	/**
	 * Instances the orthographicCamera() and sets the principal values.
	 */
	private void createCamera() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constants.TOTAL_WIDTH_IN_TILES, Constants.TOTAL_HEIGHT_IN_TILES);
		camera.update();
	}
	
	/**
	 * Renders the level.
	 * @param delta time.
	 */
	private void renderWorld(float delta){
		spriteBatch.begin();
		spriteBatch.draw(background, delta, delta, screenW, screenH);
		spriteBatch.end();
		
		tileRenderer.setView(camera);
		tileRenderer.render();
	}
	
	/**
	 * Method that updates the camera based on the position of the player.
	 * @param cameraPosition player position.
	 */
	private void updateCamera(Vector2 cameraPosition){
		camera.position.x = cameraPosition.x;
		camera.position.y = cameraPosition.y;
		camera.update();
	}

	//Getters and setters -------------/
	public OrthogonalTiledMapRenderer getTileRenderer() {
		return tileRenderer;
	}

	public void setTileRenderer(OrthogonalTiledMapRenderer tileRenderer) {
		this.tileRenderer = tileRenderer;
	}
	
	/**
	 * Actual window size.
	 */
	public void setActualWindowSize(){
		screenW = Gdx.graphics.getWidth();
		screenH = Gdx.graphics.getHeight();
	}
	
	
}
