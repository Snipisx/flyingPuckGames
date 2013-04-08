package com.flyingPuckGames.projectFinale.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;

public class Level {

	private int WIDTH;
	private int HEIGHT;
	private TiledMap tiledMap;
	private SolidTile[][] blocks;
	
	public Level(TiledMap tiledMap) {
		this.setTiledMap(tiledMap);
		loadLevel();
	}

	public int getWidth() {
		return WIDTH;
	}
	public void setWidth(int width) {
		this.WIDTH = width;
	}
	public int getHeight() {
		return HEIGHT;
	}
	public void setHeight(int height) {
		this.HEIGHT = height;
	}
	public TiledMap getTiledMap() {
		return tiledMap;
	}
	public void setTiledMap(TiledMap tiledMap) {
		this.tiledMap = tiledMap;
	}
	public SolidTile[][] getBlocks() {
		return blocks;
	}
	public void setBlocks(SolidTile[][] blocks) {
		this.blocks = blocks;
	}
	public SolidTile get(int col, int row) {
		return blocks[col][row];
	}

	private void loadLevel() {
		WIDTH = 20;
		HEIGHT = 11;
		blocks = new SolidTile[WIDTH][HEIGHT];
		TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().getLayer(0);
		for (int col = 0; col < WIDTH; col++) {
			for (int row = 0; row < HEIGHT; row++) {
				blocks[col][row] = null;
			}
		}
		for (int col = 0; col <= HEIGHT; col++) {
			for (int row = 0; row <= WIDTH; row++) {
				Cell cell = layer.getCell(col, row);
				if (cell != null) blocks[col][row] = new SolidTile(new Vector2(col,row));
			}
		}
	}
}
