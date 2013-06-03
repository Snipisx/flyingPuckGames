package com.flyingPuckGames.projectFinale.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.flyingPuckGames.projectFinale.MegaGame;
import com.flyingPuckGames.projectFinale.model.World;
import com.flyingPuckGames.projectFinale.utils.RenderUtils;
import com.flyingPuckGames.projectFinale.utils.Screenshots;

public class OldWorldRenderer {
	
	private MegaGame megaGame;
	private static final float CAMERA_WIDTH = 20f;
	private static final float CAMERA_HEIGHT = 11f;
	private float W, H;
	
	private ShapeRenderer debugRenderer = new ShapeRenderer();
	private Screenshots shots;
	
	private World world;
	private OrthographicCamera camera;
	private Texture background;
	private SpriteBatch spriteBatch;
	private boolean debug = false;
	private PlayerRenderer playerRenderer;
	private float ppuX, ppuY; //Pixels per unit X/Y-axis
	private OrthogonalTiledMapRenderer tiledMapRenderer;
	
	private BitmapFont font;
	
	// Debug -----/
	private boolean debugON;
	private String debugInfo, debugInfoFps, debugInfoPlayerVel, debugInfoPlayePos, debugInfoPlayerState, debugInfoPlayerFacing;
	private float stateTime;
	
	public OldWorldRenderer(World world, boolean debug, MegaGame megaGame){
		this.megaGame = megaGame;
		this.world = world;
		this.debug = debug;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
		camera.update();
		setSize(megaGame.SCREENW, megaGame.SCREENH);
		font = new BitmapFont();
		prepareWorldRenderer();
		spriteBatch = new SpriteBatch();
		debug = true;
		System.out.println("CameraX: " + camera.position.x + "\nCameraY:" + camera.position.y);
		playerRenderer = new PlayerRenderer(world.getPlayer());
	}
	
	public void render(float delta) {
		RenderUtils.clearScreen();
		drawBackground(delta);
		drawTiledMap();
		playerRenderer.renderPlayer();
		camera.position.x = world.getPlayer().getPosition().x;
		camera.update();
		
//		System.out.println("X:" + world.getPlayer().getPosition().x + "\nY:" + world.getPlayer().getPosition().y);
//		System.out.println(ppuX + "-" + ppuY);
	}
	
	public void setSize (float w, float h){
		this.W = w;
		this.H = h;
		ppuX = W / CAMERA_WIDTH;
		ppuY = H / CAMERA_HEIGHT;
	}
	
	public void toggleDebug(){
		debug = !debug;
	}
	
	private void drawTiledMap(){
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
	}
	
	public void drawBackground(float delta){
		spriteBatch.begin();
			spriteBatch.draw(background, delta, delta, megaGame.SCREENW, megaGame.SCREENH);
		spriteBatch.end();
	}
	
	private void prepareWorldRenderer(){
		background = new Texture(Gdx.files.internal("maps/background.png"));
		tiledMapRenderer = new OrthogonalTiledMapRenderer(world.getTiledMap(), 1f/16f);
		System.out.println("TexturesLoaded");
	}
	
	/**
	 * Method used to print on the screen debug information.
	 */
	public void renderDebugText() {
		spriteBatch.begin();
		
			//Set
			debugInfo = "##Debug: ";
			debugInfoFps = "Fps: " + Gdx.graphics.getFramesPerSecond();
			debugInfoPlayePos = "Player position: " + world.getPlayer().getPosition();
			debugInfoPlayerVel = "Player velocity: " + world.getPlayer().getVelocity();
			debugInfoPlayerState = "Player state: " + world.getPlayer().getState();
			debugInfoPlayerFacing = (world.getPlayer().isFacesRight()) ? "Player facing:  >" : "Player facing:  <";
			
			//Draw
			font.draw(spriteBatch, debugInfo, 10, megaGame.SCREENH - 3);
			font.draw(spriteBatch, debugInfoFps, 10, megaGame.SCREENH - 37);// font = 15px by def.
			font.draw(spriteBatch, debugInfoPlayePos, 10, megaGame.SCREENH - 54);// +17
			font.draw(spriteBatch, debugInfoPlayerVel, 10, megaGame.SCREENH - 71);// +17
			font.draw(spriteBatch, debugInfoPlayerState, 10, megaGame.SCREENH - 88);// +17
			font.draw(spriteBatch, debugInfoPlayerFacing, 10, megaGame.SCREENH - 105);// +17
		
		spriteBatch.end();
	}
}
