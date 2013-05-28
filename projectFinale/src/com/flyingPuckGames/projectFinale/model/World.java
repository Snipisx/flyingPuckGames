package com.flyingPuckGames.projectFinale.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.utils.Array;

public class World {

	private Player player;
	private Level level;
	private TiledMap tiledMap;
	
	Array<Rectangle> collisionRects = new Array<Rectangle>();
	
	public World() {
		createWorld();
	}

	private void createWorld() {
		tiledMap = new TmxMapLoader().load("maps/plahTilemap.tmx");
		level = new Level(tiledMap);
		player = new Player(new Vector2(6, 8));
	}

	// Getters -----------
	public Array<Rectangle> getCollisionRects() {
		return collisionRects;
	}
	
//	public List<SolidTile> getDrawableBlocks(int width, int height) {
//		int x = (int)player.getPosition().x - width;
//		int y = (int)player.getPosition().y - height;
//		if (x < 0) {
//			x = 0;
//		}
//		if (y < 0) {
//			y = 0;
//		}
//		int x2 = x + 2 * width;
//		int y2 = y + 2 * height;
//		if (x2 > level.getWidth()) {
//			x2 = level.getWidth() - 1;
//		}
//		if (y2 > level.getHeight()) {
//			y2 = level.getHeight() - 1;
//		}
//		 List<SolidTile> tiles = new ArrayList<SolidTile>();
//		 SolidTile tile;
//		 for (int col = x; col <= x2; col++) {
//			 for (int row = y; row <= y2; row++) {
//				 tile = level.getBlocks()[col][row];
//				 if (tile != null) {
//					 tiles.add(tile);
//				 }
//			 }
//		 }
//		 return tiles;
//		 		
//	}
	public Player getPlayer() {
		return player;
	}
	public Level getLevel() {
		return level;
	}
	public TiledMap getTiledMap() {
		return tiledMap;
	}
}
