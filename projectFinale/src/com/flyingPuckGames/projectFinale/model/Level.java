package com.flyingPuckGames.projectFinale.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;

public class Level {

	private int WIDTH;
	private int HEIGHT;
	private TiledMap tiledMap;
	//private SolidTile[][] blocks;
	private TiledMapTileLayer layer;


	public Level(TiledMap tiledMap) {
		this.setTiledMap(tiledMap);
		layer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
	//	loadLevel();
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

//	public SolidTile[][] getBlocks() {
//		return blocks;
//	}
//
//	public void setBlocks(SolidTile[][] blocks) {
//		this.blocks = blocks;
//	}
//
//	public SolidTile get(int col, int row) {
//		return blocks[col][row];
//	}
//
//	private void loadLevel() {
//		blocks = new SolidTile[layer.getWidth()][layer.getHeight()];
//		System.out.println("W:"+layer.getWidth()+" H:"+layer.getHeight());
//
//		for (int col = 0; col <= layer.getWidth()-1; col++) {
//			for (int row = 0; row <= layer.getHeight()-1; row++) {
//					blocks[col][row] = null;
//			}
//		}
//		
//		for (int col = 0; col <= layer.getWidth()-1; col++) {
//			System.out.println();
//			for (int row = 0; row <= layer.getHeight()-1; row++) {
//				Cell cell = layer.getCell(col, row);
//				if (cell != null){
//					blocks[col][row] = new SolidTile(new Vector2(col,row));
//					System.out.print("#");
//				} else {
//					System.out.print(".");
//				}
//			}
//		}
//		System.out.println();
//	}
}
