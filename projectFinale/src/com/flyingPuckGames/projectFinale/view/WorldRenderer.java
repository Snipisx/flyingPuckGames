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
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.flyingPuckGames.projectFinale.MegaGame;
import com.flyingPuckGames.projectFinale.model.Player;
import com.flyingPuckGames.projectFinale.model.SolidTile;
import com.flyingPuckGames.projectFinale.model.World;

public class WorldRenderer {
	private MegaGame megaGame;
	
	private static final float CAMERA_WIDTH = 20f;
	private static final float CAMERA_HEIGHT = 11f;
	private int W;
	private int	H;
	
	private ShapeRenderer debugRenderer = new ShapeRenderer();

	private World world;
	private OrthographicCamera camera;
	private Texture playerTexture;
	private Texture background;
	private SpriteBatch spriteBatch;
	private boolean debug = false;

	private float ppuX;	//Pixels per unit X-axis
	private float ppuY; //Pixels per unit Y-axis
	private TiledMapRenderer tiledMapRenderer;
	
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
		
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		loadTextures();
		debug = true;
		world.getPlayer().setPosition(new Vector2(camera.position.x - 2,camera.position.y - 2));
		System.out.println("CameraX: " + camera.position.x + "\nCameraY:" + camera.position.y);
	}
	
	public void render(float delta) {
		spriteBatch.begin();
			drawTiledMap(delta);
			drawPlayer();
			font.draw(spriteBatch, "Hola que tal.", 14, 15);
//			renderDebugText();
//			if(debug)drawDebugBoxes();
		spriteBatch.end();
//		camera.position.set(camera.position.x + 0.05f,camera.position.y + 0.05f, 0);
//		camera.update();
		System.out.println("X:" + world.getPlayer().getPosition().x + "\nY:" + world.getPlayer().getPosition().y);
		System.out.println(ppuX + "-" + ppuY);
	}
	public void setSize (int w, int h){
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
			spriteBatch.draw(
					playerTexture,
					world.getPlayer().getPosition().x * ppuX ,
					world.getPlayer().getPosition().y * ppuY,
					world.getPlayer().getWsize(),
					world.getPlayer().getHsize()
			);
			System.out.println("HolaPlayer>");
		} else {
			spriteBatch.draw(playerTexture,
					world.getPlayer().getPosition().x ,
					world.getPlayer().getPosition().y ,
					-world.getPlayer().getWsize(),
					world.getPlayer().getHsize()
			);
			System.out.println("HolaPlayer<");
		}
	}
	private void drawTiledMap(float delta){
		spriteBatch.draw(background, delta, delta, megaGame.SCREENW, megaGame.SCREENH);
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
		SolidTile[][] blocks = world.getLevel().getBlocks();
		SolidTile block;
		for (int col = 0; col < 20; col++) {
			for (int row = 0; row < 11; row++) {
				block = blocks[col][row];
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
	/**
	 * Method used to print on the screen debug information.
	 */
	private void renderDebugText() {
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
