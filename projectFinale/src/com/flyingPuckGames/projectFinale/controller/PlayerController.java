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

public class PlayerController {
	
	enum Keys {
		LEFT, RIGHT, JUMP
	}
	public InputMultiplexer inputSystem;
	private boolean isJumpButtonPressed;
	private Player player;
	private Pool<Rectangle> rectPool;
	private Array<Rectangle> wallTiles;
	private TiledMapTileLayer wallsLayer;
	
	//Disposable
	private Rectangle rect;
	private Cell cell;
	
	static Map<PlayerController.Keys, Boolean> keys = new HashMap<PlayerController.Keys, Boolean>();
	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.JUMP, false);
	};
	
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

	public void update(float delta) {
		
		updateTime(delta);
		
		processInput();
		
		makePlayerFall();
		clampXVelocity();

		readyBounds();
		
		predictHowFarPlayerGoesInThisFrame(delta);

		if (playerIsMovingInXAxis()) {
			predictTilesInXAxis(delta);
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
	
	private void updateTime(float delta) {
		player.setStateTime(player.getStateTime() + delta);
	}

	private void readyBounds() {
		player.setBounds(rectPool.obtain());
		player.setBounds(player.getXPosition(), player.getYPosition(), player.getWsize()-.01f, player.getHsize()-.01f);
	}

	private void movePlayerBoundsInXAxis() {
		player.setBoundsXPosition(player.getBoundsXPosition() + player.getXVelocity());
	}
	
	private void moveXBoundsPositionToPlayerPosition(){
		player.setBoundsXPosition(player.getXPosition());
	}
	
	private void moveYBoundsPositionToPlayerPosition(){
		player.setBoundsYPosition(player.getYPosition());
	}
	
	private void movePlayerBoundsInYAxis() {
		player.setBoundsYPosition(player.getBoundsYPosition() + player.getYVelocity());
	}
	
	private Vector2 predictHowFarPlayerGoesInThisFrame(float delta) {
		return player.getVelocity().scl(delta);
	}

	private void makePlayerFall() {
		player.setVelocity(player.getVelocity().add(0, Constants.GRAVITY));
	}
	
	private boolean playerIsMovingInXAxis(){
		return Math.abs(player.getXVelocity()) > 0 ? true : false;
	}
	
	private boolean playerIsMovingInYAxis(){
		return Math.abs(player.getYVelocity()) > 0 ? true : false;
	}
	
	private void clampXVelocity(){
		clampXVelocityToMinimum();
		clampXVelocityToMaximum();
	}
	
	private void clampXVelocityToMaximum(){
		if (Math.abs(player.getVelocity().x) > Constants.MAX_VELOCITY) {
				player.setXVelocity(Math.signum(player.getXVelocity()) * Constants.MAX_VELOCITY);
		}
	}
	
	private void clampXVelocityToMinimum(){
		if (Math.abs(player.getXVelocity()) < 1) {
			player.setXVelocity(0);
			if (player.isGrounded()){
				player.setState(State.IDLE);
			}
		}
	}
	
	private void predictTilesInXAxis(float delta){
//		predictHowFarPlayerGoesInThisFrame(delta);
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
	
	private void collisionsInXAxis() {
		for (Rectangle wallTile : wallTiles) {
			if(wallTile == null) continue;
			
			if (player.getBounds().overlaps(wallTile)) {
				stopPlayerInXAxis(wallTile);
				break;
			}
		}			
	}
	
	private void collisionsInYAxis(){
		for (Rectangle wallTile : wallTiles) {
			if(wallTile == null) continue;
			
			if (player.getBounds().overlaps(wallTile)) {
				//Upward collisions
				if (player.getYVelocity() > 0) {
					player.setYPosition(wallTile.y - player.getHsize());
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
	
	private void stopPlayerInXAxis(Rectangle wallTile) {
		player.setXVelocity(0);
	}
	private void stopPlayerInYAxis() {
		player.setYVelocity(0);
	}
	
	//Input process.
	public void leftPressed() {
		keys.get(keys.put(Keys.LEFT, true));
	}

	public void rightPressed() {
		keys.get(keys.put(Keys.RIGHT, true));
	}

	public void jumpPressed() {
		keys.get(keys.put(Keys.JUMP, true));
	}
	
	public void leftReleased() {
		keys.get(keys.put(Keys.LEFT, false));
	}

	public void rightReleased() {
		keys.get(keys.put(Keys.RIGHT, false));
	}

	public void jumpReleased() {
		keys.get(keys.put(Keys.JUMP, false));
		isJumpButtonPressed = false;
		System.out.println("Hi");
		
	}
	
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
				player.setFacesRight(false);	
			}
		} 
		else if (keys.get(Keys.RIGHT)) {
			player.setFacesRight(true);
			player.setXVelocity(Constants.MAX_VELOCITY);
			if (player.isGrounded()) {
				player.setState(State.WALKING);
			}
			player.setFacesRight(true);	
		} 
		return false;
	}
	
	 public void setInputSystems(InputProcessor... processors) {
		  inputSystem = new InputMultiplexer(processors);
		  Gdx.input.setInputProcessor(inputSystem);
	 }
}
