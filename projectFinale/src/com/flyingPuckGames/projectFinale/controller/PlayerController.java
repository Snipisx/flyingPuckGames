package com.flyingPuckGames.projectFinale.controller;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.flyingPuckGames.projectFinale.utils.Constants;
import com.flyingPuckGames.projectFinale.model.player.Player;
import com.flyingPuckGames.projectFinale.model.player.Player.State;

/**
 * Class used to alter the state of a given player. It implements all the basic mechanics.
 * @author flyingPuck(games);
 */
public class PlayerController {

	/** Keys allowed to be pressed */
	enum Keys {
		LEFT, RIGHT, JUMP, DEBUG
	}
	
	public InputMultiplexer inputSystem;
	private boolean isJumpButtonPressed;
	private Player player;
	private Pool<Rectangle> rectPool;
	private Array<Rectangle> wallTiles;
	private TiledMapTileLayer wallsLayer;
	
	/** Disposable */
	private Rectangle rect;
	private Cell cell;
	
	/** Map used to know the actual state of a given key. */
	static Map<PlayerController.Keys, Boolean> keys = new HashMap<PlayerController.Keys, Boolean>();
	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.JUMP, false);
		keys.put(Keys.DEBUG, false);
	};
	
	/** Constructor */
	public PlayerController(Player player, TiledMapTileLayer wallsLayer) {
		this.player = player;
		rectPool = new Pool<Rectangle>() {
			@Override
			protected Rectangle newObject() {
				return new Rectangle();
			}
		};
		wallTiles = new Array<Rectangle>();
		this.wallsLayer = wallsLayer;
		
		player.setState(State.WALKING);
		player.setGrounded(true);
	}

	/**
	 * Main method used to update the state of the Player object.
	 * @param delta, Exact time needed to execute 1 loop.
	 */
	public void update(float delta) {
		
		updateTime(delta);
		
		processInput();
		
		makePlayerFall();
		clampXVelocity();

		readyBounds();
		
		predictHowFarPlayerGoesInThisFrame(delta);

		if (playerIsMovingInXAxis()) {
			predictTilesInXAxis();
			movePlayerBoundsInXAxis();
			collisionsInXAxis();
			moveXBoundsPositionToPlayerPosition();
		}
		
		if (playerIsMovingInYAxis()) {
			predictTilesInYAxis();
			movePlayerBoundsInYAxis();
			collisionsInYAxis();
			moveYBoundsPositionToPlayerPosition();
		}
		
		rectPool.free(player.getBounds());
		player.getPosition().add(player.getVelocity());
		player.getVelocity().scl(1/delta);
		
		player.setXVelocity(player.getXVelocity() * Constants.DAMPING);
	}
	
	/**
	 * Updates the player stateTime.
	 * @param delta
	 */
	private void updateTime(float delta) {
		player.setStateTime(player.getStateTime() + delta);
	}
	
	/**
	 * Prepares the player rectangle to be used in the colission context.
	 */
	private void readyBounds() {
		player.setBounds(rectPool.obtain());
		player.setBounds(player.getXPosition(), player.getYPosition(), player.getWidth()-.01f, player.getHeight()-.01f);
	}

	/**
	 * Moves player rectangle in X Axis.
	 */
	private void movePlayerBoundsInXAxis() {
		player.setBoundsXPosition(player.getBoundsXPosition() + player.getXVelocity());
	}
	
	/**
	 * Moves player rectangle in Y Axis.
	 */
	private void moveXBoundsPositionToPlayerPosition(){
		player.setBoundsXPosition(player.getXPosition());
	}
	
	/**
	 * Moves player position in X Axis.
	 */
	private void moveYBoundsPositionToPlayerPosition(){
		player.setBoundsYPosition(player.getYPosition());
	}
	
	/**
	 * Moves player position in X Axis.
	 */
	private void movePlayerBoundsInYAxis() {
		player.setBoundsYPosition(player.getBoundsYPosition() + player.getYVelocity());
	}
	
	/**
	 * Scalar of the player velocity and delta time.
	 */
	private Vector2 predictHowFarPlayerGoesInThisFrame(float delta) {
		return player.getVelocity().scl(delta);
	}
	
	/*
	 * Adds gravity to the player so it falls.
	 */
	private void makePlayerFall() {
		player.setVelocity(player.getVelocity().add(0, Constants.GRAVITY));
	}
	
	/**
	 * Returns if the player is moving in the X axis.
	 * @return true if is moving in x, false if not.
	 */
	private boolean playerIsMovingInXAxis(){
		return Math.abs(player.getXVelocity()) > 0 ? true : false;
	}
	
	/**
	 * Returns if the player is moving in the X axis.
	 * @return true if is moving in y, false if not.
	 */
	private boolean playerIsMovingInYAxis(){
		return Math.abs(player.getYVelocity()) > 0 ? true : false;
	}
	
	/**
	 * Limits the velocity off the player so it doesn't keep increasing/decreasing towards infinite in the X axis.
	 */
	private void clampXVelocity(){
		clampXVelocityToMinimum();
		clampXVelocityToMaximum();
	}
	
	/**
	 * Clamps to the maximum allowed.
	 */
	private void clampXVelocityToMaximum(){
		if (Math.abs(player.getVelocity().x) > Constants.MAX_VELOCITY) {
				player.setXVelocity(Math.signum(player.getXVelocity()) * Constants.MAX_VELOCITY);
		}
	}
	
	/**
	 * Clamps to 0 if X velocity is less tan 1 so it doesnt keep multiplying infinitely with Constants.DAMPING
	 */
	private void clampXVelocityToMinimum(){
		if (Math.abs(player.getXVelocity()) < 1) {
			player.setXVelocity(0);
			if (player.isGrounded()) {
				player.setState(State.IDLE);
			}
		}
	}
	
	/**
	 * Predicts what tiles are in the X axis on a reduced range to get collisioned with.
	 */
	private void predictTilesInXAxis(){
		int startX, startY, endX, endY;
		
		//Moving right
		if (player.getVelocity().x > 0) {
			startX = endX = (int) Math.floor(player.getBoundsXPosition() + player.getBoundsWidth() + player.getXVelocity());
		} 
		//Moving left
		else {
			startX = endX = (int) Math.floor(player.getBoundsXPosition() + player.getXVelocity());
		}
		
		startY = (int) Math.floor(player.getBoundsYPosition());
		endY = (int) Math.floor(player.getBoundsYPosition() + player.getBoundsHeight());
		
		getTiles(startX, startY, endX, endY, wallTiles);
	}
	
	/**
	 * Predicts what tiles are in the Y axis on a reduced range to get collisioned with.
	 */
	private void predictTilesInYAxis(){
		int startX, startY, endX, endY;
		
		startX = (int) Math.floor(player.getBoundsXPosition());
		endX = (int) Math.floor(player.getBoundsXPosition() + player.getBoundsWidth());
		
		//Moving up
		if (player.getYVelocity() > 0) {
			startY = endY = (int) Math.floor(player.getBoundsYPosition() + player.getBoundsHeight() + player.getYVelocity());
		}
		//Moving down
		else {
			startY = endY = (int) Math.floor(player.getBoundsYPosition() + player.getYVelocity());
		}
		
		getTiles(startX, startY, endX, endY, wallTiles);
	}
	
	/**
	 * Based in a criteria recovers a range of tiles from the tileMapLayer.
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 * @param tiles
	 */
	private void getTiles(int startX, int startY, int endX, int endY,Array<Rectangle> tiles) {
		
		rectPool.freeAll(tiles);
		tiles.clear();
		
		for (int y = startY; y <= endY; y++) {
			for (int x = startX; x <= endX; x++) {
				 cell = wallsLayer.getCell(x, y);

				if (cell != null) {
					if (cell.getTile().getProperties().containsKey("solid")) {
						// System.out.println(cell.getTile().getProperties().get("solid").toString());
					}
					rect = rectPool.obtain();
					rect.set(x, y, 1, 1);
					tiles.add(rect);
				}
			}
		}
	}
	
	/**
	 * Checks overlaps of the rectangle of the player and a solid tile in the x axis.
	 */
	private void collisionsInXAxis() {
		for (Rectangle wallTile : wallTiles) {
			if(wallTile == null) continue;
			
			if (player.getBounds().overlaps(wallTile)) {
				stopPlayerInXAxis();
				break;
			}
		}			
	}
	
	/**
	 * Checks overlaps of the rectangle of the player and a solid tile in the x axis.
	 */
	private void collisionsInYAxis(){
		for (Rectangle wallTile : wallTiles) {
			if(wallTile == null) continue;
			
			if (player.getBounds().overlaps(wallTile)) {
				//Upward collisions
				if (player.getYVelocity() > 0) {
					player.setYPosition(wallTile.y - player.getHeight());
				} 
				//Downward collision
				else {
					player.setYPosition(wallTile.y + wallTile.height);
					player.setGrounded(true);
				}
				stopPlayerInYAxis();
				break;
			}
		}
	}
	
	private void stopPlayerInXAxis() {
		player.setXVelocity(0);
	}
	private void stopPlayerInYAxis() {
		player.setYVelocity(0);
	}
	
	//Input process.
	public void leftPressed() {
		keys.get(keys.put(Keys.LEFT, true));
	}
	public void leftReleased() {
		keys.get(keys.put(Keys.LEFT, false));
	}
	public void rightPressed() {
		keys.get(keys.put(Keys.RIGHT, true));
	}
	public void rightReleased() {
		keys.get(keys.put(Keys.RIGHT, false));
	}
	public void jumpPressed() {
		keys.get(keys.put(Keys.JUMP, true));
	}
	public void jumpReleased() {
		keys.get(keys.put(Keys.JUMP, false));
		isJumpButtonPressed = false;
	}
	public void debugPressed() {
		keys.get(keys.put(Keys.DEBUG, true));
	}
	public void debugReleased() {
		keys.get(keys.put(Keys.DEBUG, false));
	}
	
	/**
	 * Method used to see what inputs need to be processed.
	 * @return
	 */
	private boolean processInput() {
		if (keys.get(Keys.JUMP)) {
			if (!player.getState().equals(State.JUMPING)) {
				isJumpButtonPressed = true;
				player.setYVelocity(player.getYVelocity() + Constants.JUMP_VELOCITY);
				player.setState(State.JUMPING);
				player.setGrounded(false);
			}
			else if(isJumpButtonPressed){
				isJumpButtonPressed = false;
			}
		}
		if (keys.get(Keys.LEFT)) {
			player.setXVelocity(-Constants.MAX_VELOCITY);
			if (player.isGrounded()) {
				player.setState(State.WALKING);
			}
			player.setFacesRight(false);	

		} 
		else if (keys.get(Keys.RIGHT)) {
			player.setFacesRight(true);
			player.setXVelocity(Constants.MAX_VELOCITY);
			if (player.isGrounded()) {
				player.setState(State.WALKING);
			}
			player.setFacesRight(true);	
		} 
		if (keys.get(Keys.DEBUG)){
			player.setVelocity(new Vector2(0f,0f));
			player.setBounds(Constants.PLAYER_STARTING_POSITION.x, Constants.PLAYER_STARTING_POSITION.x, player.getWidth()-.01f, player.getHeight()-.01f);
			player.setPosition(Constants.PLAYER_STARTING_POSITION);
			player.setBoundsPosition(Constants.PLAYER_STARTING_POSITION);
		}
		return false;
	}
	
	 public void setInputSystems(InputProcessor... processors) {
		  inputSystem = new InputMultiplexer(processors);
		  Gdx.input.setInputProcessor(inputSystem);
	 }
}
