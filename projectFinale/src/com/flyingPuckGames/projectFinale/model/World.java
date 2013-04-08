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
		player = new Player(new Vector2(3, 3));
	}

	// Getters -----------
	public Array<Rectangle> getCollisionRects() {
		return collisionRects;
	}
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
