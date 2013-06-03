package com.flyingPuckGames.projectFinale.controller;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.flyingPuckGames.projectFinale.model.player.Player;
import com.flyingPuckGames.projectFinale.model.player.Player.State;

public class PlayerController {
	
	private static final float MAX_VELOCITY = 10f;
	private static final float JUMP_VELOCITY = 40f;
	private static final float GRAVITY = -2.5f;
	private static final float DAMPING = 0.87f;
	private Player player;
	private Pool<Rectangle> rectPool;
	private Array<Rectangle> wallTiles;
	private TiledMapTileLayer wallsLayer;
	
	
	public PlayerController(Player player, Pool<Rectangle> rectPool, Array<Rectangle> tiles, TiledMapTileLayer wallsLayer) {
		this.player = player;
		this.rectPool = rectPool;
		this.wallTiles = tiles;
		this.wallsLayer = wallsLayer;
	
	}

	public void update(float delta) {
		player.setStateTime(player.getStateTime() + delta);
		player.setVelocity(player.getVelocity().add(0, GRAVITY));
		
		clampVelocity();
		
		player.getVelocity().scl(delta);
		player.setBounds(rectPool.obtain());
		player.getBounds().set(player.getPosition().x, player.getPosition().y, player.getWsize(), player.getHsize());
		
		predictTilesInXAxis();
	}
	
	
	private void clampVelocity(){
		clampVelocityToMaximum();
		clampVelocityToMinimum();
	}
	
	private void clampVelocityToMaximum(){
		if (Math.abs(player.getVelocity().x) > MAX_VELOCITY) {
				player.setVelocity(new Vector2(Math.signum(player.getVelocity().x) * MAX_VELOCITY, player.getVelocity().y ));
		}
	}
	
	private void clampVelocityToMinimum(){
		if (Math.abs(player.getVelocity().x) < 1) {
			player.setVelocity(new Vector2(0, player.getVelocity().y));
			if (player.isGrounded()){
				player.setState(State.IDLE);

			}
		}
	}
	private void predictTilesInXAxis(){
		int startX, startY, endX, endY;
		if (player.getVelocity().x > 0) {
			startX = endX = (int) (player.getPosition().x + player.getWsize() + player.getVelocity().x);
		} else {
			startX = endX = (int) (player.getPosition().x + player.getVelocity().x);
		}
		startY = (int) (player.getPosition().y);
		endY = (int) (player.getPosition().x + player.getHsize());
		
		getTiles(startX, startY, endX, endY, wallTiles);
	}
	
	private void getTiles(int startX, int startY, int endX, int endY,
			Array<Rectangle> tiles) {
		
		rectPool.freeAll(tiles);
		tiles.clear();
		for (int y = startY; y <= endY; y++) {
			for (int x = startX; x <= endX; x++) {
				Cell cell = wallsLayer.getCell(x, y);

				if (cell != null) {
					if (cell.getTile().getProperties().containsKey("solid")) {
						// System.out.println(cell.getTile().getProperties().get("solid").toString());
					}
					Rectangle rect = rectPool.obtain();
					rect.set(x, y, 1, 1);
					tiles.add(rect);
				}
			}
		}
	}
	
}
