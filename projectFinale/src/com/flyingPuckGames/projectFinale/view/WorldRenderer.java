package com.flyingPuckGames.projectFinale.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.flyingPuckGames.projectFinale.MegaGame;
import com.flyingPuckGames.projectFinale.model.Player;
import com.flyingPuckGames.projectFinale.model.SolidTile;
import com.flyingPuckGames.projectFinale.model.World;

public class WorldRenderer {
	
	private MegaGame megaGame;
	private static final float CAMERA_WIDTH = 20f;
	private static final float CAMERA_HEIGHT = 11f;
	private float W;
	private float H;
	
	private ShapeRenderer debugRenderer = new ShapeRenderer();

	private World world;
	private OrthographicCamera camera;
	private Texture playerTexture;
	private Texture background;
	private SpriteBatch spriteBatch;
	private boolean debug = false;

	private float ppuX;	//Pixels per unit X-axis
	private float ppuY; //Pixels per unit Y-axis
	private OrthogonalTiledMapRenderer tiledMapRenderer;
	
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
	
	public WorldRenderer(World world, boolean debug, MegaGame megaGame){
		this.megaGame = megaGame;
		this.world = world;
		this.debug = debug;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
		camera.update();
		setSize(megaGame.SCREENW, megaGame.SCREENH);
		
		font = new BitmapFont();
		loadTextures();
		spriteBatch = new SpriteBatch();
		debug = true;
		System.out.println("CameraX: " + camera.position.x + "\nCameraY:" + camera.position.y);
	}
	
	public void render(float delta) {
		clearScreen();
		drawTiledMap(delta);
		drawPlayer();
		drawDebugBoxes();
		drawDebug();
		
		camera.position.x = world.getPlayer().getPosition().x;
		camera.update();
		System.out.println("X:" + world.getPlayer().getPosition().x + "\nY:" + world.getPlayer().getPosition().y);
		System.out.println(ppuX + "-" + ppuY);
	}
	public void setSize (float w, float h){
		this.W = w;
		this.H = h;
		ppuX = (float)W / CAMERA_WIDTH;
		ppuY = (float)H / CAMERA_HEIGHT;
	}
	public boolean isDebug(){
		return debug;
	}
	public void toggleDebug(){
		debug = !debug;
	}
	private void drawPlayer(){

		if (world.getPlayer().isFacesRight()) {
			spriteBatch.begin();
			spriteBatch.draw(
					playerTexture,
					world.getPlayer().getPosition().x * ppuX,
					world.getPlayer().getPosition().y * ppuY,
					world.getPlayer().getWsize()*ppuX,
					world.getPlayer().getHsize()*ppuY
			);
			spriteBatch.end();
			System.out.println("HolaPlayer>");
		} else {
			spriteBatch.begin();
			spriteBatch.draw(playerTexture,
					world.getPlayer().getPosition().x ,
					world.getPlayer().getPosition().y ,
					-world.getPlayer().getWsize()*ppuX,
					world.getPlayer().getHsize()*ppuY
			);
			spriteBatch.end();
			System.out.println("HolaPlayer<");
		}
		
	}
	private void drawTiledMap(float delta){
		spriteBatch.begin();
		spriteBatch.draw(background, delta, delta, megaGame.SCREENW, megaGame.SCREENH);
		spriteBatch.end();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		System.out.println("HolaTiled");
	}
	private void loadTextures(){
		playerTexture =  new Texture(Gdx.files.internal("data/plahCharacter.png"));
		background = new Texture(Gdx.files.internal("maps/background.png"));
		tiledMapRenderer = new OrthogonalTiledMapRenderer(world.getTiledMap(), 1f/16f);
		System.out.println("TexturesLoaded");
	}
	
	private void drawDebugBoxes() {
		
		// render blocks
		debugRenderer.setProjectionMatrix(camera.combined);
		debugRenderer.begin(ShapeType.Filled);
		
		SolidTile block;
		for (int col = 0; col < world.getLevel().getWidth(); col++) {
			for (int row = 0; row < world.getLevel().getHeight(); row++) {
				block =  world.getLevel().get(col, row); 
				Rectangle rect = block.getBounds();
				debugRenderer.setColor(new Color(1, 0, 0, 1));
				debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
			}
		}
		
		// render Player
		Player player = world.getPlayer();
		Rectangle rect = player.getBounds();
		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
		debugRenderer.end();
		System.out.println("HolaBoxes");
	}
	
	
	private void drawDebug() {
		// render blocks
		debugRenderer.setProjectionMatrix(camera.combined);
		debugRenderer.begin(ShapeType.Filled);
		SolidTile block;
		for (int col = 0; col <  world.getLevel().getWidth(); col++) {
			for (int row = 0; row < world.getLevel().getHeight(); row++) {
				block = world.getLevel().get(col, row); 
				Rectangle rect = block.getBounds();
				debugRenderer.setColor(new Color(1, 0, 0, 1));
				debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
			}
		}
		// render Bob
		Player bob = world.getPlayer();
		Rectangle rect = bob.getBounds();
		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
		debugRenderer.end();
	}
	/**
	 * Method used to print on the screen debug information.
	 */
	public void renderDebugText() {
		spriteBatch.dispose();
		spriteBatch = new SpriteBatch();
		
		spriteBatch.begin();
			debugInfo = "F1 resets player pos  |  F2 Resets All";
			debugInfoFps = "Fps: " + Gdx.graphics.getFramesPerSecond();
			debugInfoPlayePos = "Player position: " + world.getPlayer().getPosition();
			debugInfoPlayerVel = "Player velocity: " + world.getPlayer().getVelocity();
			debugInfoPlayerState = "Player state: " + world.getPlayer().getState();
			debugInfoPlayerFacing = (world.getPlayer().isFacesRight()) ? "Player facing:  >" : "Player facing:  <";
			
			font.draw(spriteBatch, debugInfo, 10, megaGame.SCREENH - 3);
			font.draw(spriteBatch, debugInfoFps, 10, megaGame.SCREENH - 37);// font = 15px by def.
			font.draw(spriteBatch, debugInfoPlayePos, 10, megaGame.SCREENH - 54);// +17
			font.draw(spriteBatch, debugInfoPlayerVel, 10, megaGame.SCREENH - 71);// +17
			font.draw(spriteBatch, debugInfoPlayerState, 10, megaGame.SCREENH - 88);// +17
			font.draw(spriteBatch, debugInfoPlayerFacing, 10, megaGame.SCREENH - 105);// +17
		spriteBatch.end();
		System.out.println("HolaText");
	}
	/**
	 * This method clears the screen.
	 */
	private void clearScreen() {
		Gdx.graphics.getGL20().glClearColor(1, 0, 0, 0);
		Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		System.out.println("HolaClear");
	}
}
